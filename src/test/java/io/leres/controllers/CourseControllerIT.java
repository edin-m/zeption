package io.leres.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.leres.IntegrationTests;
import io.leres.courses.Course;
import io.leres.courses.CourseFixture;
import io.leres.courses.CoursePost;
import io.leres.courses.repo.CoursePostRepository;
import io.leres.courses.repo.CourseRepository;
import io.leres.students.Student;
import io.leres.students.StudentFixture;
import io.leres.students.repo.StudentRepository;
import io.leres.teachers.Teacher;
import io.leres.teachers.TeacherFixture;
import io.leres.teachers.repo.TeacherRepository;
import io.leres.util.CustomObjectMapper;
import io.leres.util.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Category(IntegrationTests.class)
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@Slf4j
public class CourseControllerIT {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private CoursePostRepository coursePostRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private StudentRepository studentRepository;

    ObjectMapper mapper = new CustomObjectMapper();

    private long courseId;
    private long studentId;

    @Before
    public void setUp() {
        courseRepository.deleteAllInBatch();
        coursePostRepository.deleteAllInBatch();
        teacherRepository.deleteAllInBatch();
    }

    @Test
    public void testCreatingCourseWithPost() throws Exception {
        Course course = CourseFixture.getExampleCourse();
        String body = mapper.writeValueAsString(course);

        mvc.perform(post("/courses")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body))
                .andExpect(status().isOk());

        assertThat(courseRepository.count()).isEqualTo(1);
    }

    @Test
    @Transactional
    public void testCreatingCoursePost() throws Exception {
        setUpSingleCourseInRepository();

        CoursePost coursePost = new CoursePost("course post content");
        String body = mapper.writeValueAsString(coursePost);

        String response = mvc.perform(post(String.format("/courses/%s/post", courseId))
                .contentType(MediaType.APPLICATION_JSON)
                .content(body))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        verifyCoursePostIsSavedCorrectly();
    }

    private void verifyCoursePostIsSavedCorrectly() {
        List<CoursePost> coursePosts = coursePostRepository.findAll();
        assertThat(coursePosts).hasSize(1);
        assertThat(coursePosts.get(0).getCourse().getName())
                .isEqualTo(CourseFixture.getExampleCourse().getName());
    }

    @Test
    public void testAssigningNonExistingStudentToCourse() throws Exception {
        setUpSingleCourseInRepository();

        mvc.perform(post(String.format("/courses/%s/students/%s", courseId, studentId)))
                .andExpect(status().is(404));
    }

    @Test
    public void testAssigningStudentToNonExistingCourse() throws Exception {
        setUpSingleStudentInRepository();

        mvc.perform(post(String.format("/courses/%s/students/%s", courseId, studentId)))
                .andExpect(status().is(404));
    }

    @Test
    @Transactional
    public void testAssigningStudentToCourse() throws Exception {
        setUpSingleCourseInRepository();
        setUpSingleStudentInRepository();

        mvc.perform(post(String.format("/courses/%s/students/%s", courseId, studentId)))
                .andExpect(status().isOk());

        verifyStudentIsAssignedToCourse();
    }

    private void verifyStudentIsAssignedToCourse() {
        Student student = studentRepository.getOne(studentId);
        Course course = courseRepository.getOne(courseId);

        assertThat(course.getStudents()).hasSize(1);
        assertThat(new ArrayList<>(course.getStudents()).get(0).getPerson()).isEqualTo(student.getPerson());
    }

    @Test
    public void testAssigningAlreadyAssignedStudentToCourse() throws Exception {
        setUpStudentAssignedToCourse();

        mvc.perform(post(String.format("/courses/%s/students/%s", courseId, studentId)))
                .andExpect(status().is(409));
    }

    @Test
    public void testRemovingNonExistingStudentFromCourse() throws Exception {
        setUpSingleCourseInRepository();

        mvc.perform(delete(String.format("/courses/%s/students/%s", courseId, studentId)))
                .andExpect(status().is(404));
    }

    @Test
    public void testRemovingStudentFromNonExistingCourse() throws Exception {
        setUpSingleStudentInRepository();

        mvc.perform(delete(String.format("/courses/%s/students/%s", courseId, studentId)))
                .andExpect(status().is(404));
    }

    @Test
    @Transactional
    public void testRemovingStudentFromCourse() throws Exception {
        setUpStudentAssignedToCourse();

        mvc.perform(delete(String.format("/courses/%s/students/%s", courseId, studentId)))
                .andExpect(status().isOk());

        verifyStudentIsRemovedFromCourse();
    }

    private void setUpStudentAssignedToCourse() {
        Course course = courseRepository.save(CourseFixture.getExampleCourse());
        Student student = studentRepository.save(StudentFixture.getExampleStudent());
        course.getStudents().add(student);
        course = courseRepository.save(course);

        courseId = course.getId();
        studentId = student.getId();
    }

    private void verifyStudentIsRemovedFromCourse() {
        Course course = courseRepository.getOne(courseId);

        assertThat(course.getStudents()).hasSize(0);
    }

    @Test
    public void testRemovingNonAssignedStudentFromCourse() throws Exception {
        setUpSingleStudentInRepository();
        setUpSingleCourseInRepository();

        mvc.perform(delete(String.format("/courses/%s/students/%s", courseId, studentId)))
                .andExpect(status().is(409));
    }

    private void setUpSingleStudentInRepository() {
        Student student = studentRepository.save(StudentFixture.getExampleStudent());
        studentId = student.getId();
    }

    private void setUpSingleCourseInRepository() {
        Course course = courseRepository.save(CourseFixture.getExampleCourse());
        courseId = course.getId();
    }

    @Test
    public void testGettingStudentsFromNonExistingCourse() throws Exception {
        mvc.perform(get(String.format("/courses/%s/students", 1000L)))
                .andExpect(status().is(404));
    }

    @Test
    @Transactional
    public void testGettingStudentsForCourse() throws Exception {
        Student student = studentRepository.save(StudentFixture.getExampleStudent());
        Course course = CourseFixture.getExampleCourse();
        course.getStudents().add(student);
        course = courseRepository.save(course);

        String result = mvc.perform(get(String.format("/courses/%s/students", course.getId())))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        List<Student> students = JsonUtils.<Student>fromJsonArray(result);
        assertThat(students).hasSize(1);
    }

    @Test
    public void testGettingTeachersFromNonExistingCourse() throws Exception {
        mvc.perform(get(String.format("/courses/%s/teachers", 1000L)))
                .andExpect(status().is(404));

    }

    @Test
    @Transactional
    public void testGettingTeachersForCourse() throws Exception {
        Teacher teacher = teacherRepository.save(TeacherFixture.getExampleTeacher());
        Course course = CourseFixture.getExampleCourse();
        course.getTeachers().add(teacher);
        course = courseRepository.save(course);

        String result = mvc.perform(get(String.format("/courses/%s/teachers", course.getId())))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        List<Teacher> teachers = JsonUtils.<Teacher>fromJsonArray(result);
        assertThat(teachers).hasSize(1);
    }

    @Test
    public void testAssigningNonExistingTeacherToCourse() throws Exception {
        Course course = courseRepository.save(CourseFixture.getExampleCourse());

        mvc.perform(post(String.format("/courses/%s/teachers/1000", course.getId())))
                .andExpect(status().is(404))
                .andReturn().getResponse();
    }

    @Test
    public void testAssigningTeacherToNonExistingCourse() throws Exception {
        Teacher teacher = teacherRepository.save(TeacherFixture.getExampleTeacher());

        mvc.perform(post(String.format("/courses/1000/teachers/%s", teacher.getId())))
                .andExpect(status().is(404))
                .andReturn().getResponse();
    }

    @Test
    @Transactional
    public void testAssigningAlreadyAssignedTeacherToCourse() throws Exception {
        Teacher teacher = teacherRepository.save(TeacherFixture.getExampleTeacher());
        Course course = CourseFixture.getExampleCourse();
        course.getTeachers().add(teacher);
        course = courseRepository.save(course);

        mvc.perform(post(String.format("/courses/%s/teachers/%s",
                course.getId(), teacher.getId())))
                .andExpect(status().is(409));
    }

    @Test
    @Transactional
    public void testAssigningTeacherToCourse() throws Exception {
        Course course = courseRepository.save(CourseFixture.getExampleCourse());
        Teacher teacher = teacherRepository.save(TeacherFixture.getExampleTeacher());

        mvc.perform(post(String.format("/courses/%s/teachers/%s",
                course.getId(), teacher.getId())))
                .andExpect(status().isOk());

        course = courseRepository.getOne(course.getId());
        assertThat(new ArrayList<>(course.getTeachers()).get(0)).isEqualTo(teacher);
    }

    @Test
    public void testRemovingNonExistingTeacherFromCourse() throws Exception {
        Course course = courseRepository.save(CourseFixture.getExampleCourse());

        mvc.perform(delete(String.format("/courses/%s/teachers/1000", course.getId())))
                .andExpect(status().is(404))
                .andReturn().getResponse();
    }

    @Test
    public void testRemovingTeacherFromNonExistingCourse() throws Exception {
        Teacher teacher = teacherRepository.save(TeacherFixture.getExampleTeacher());

        mvc.perform(delete(String.format("/courses/1000/teachers/%s", teacher.getId())))
                .andExpect(status().is(404))
                .andReturn().getResponse();
    }

    @Test
    @Transactional
    public void testRemovingTeacherFromCourse() throws Exception {
        Teacher teacher = teacherRepository.save(TeacherFixture.getExampleTeacher());
        Course course = CourseFixture.getExampleCourse();
        course.getTeachers().add(teacher);
        course = courseRepository.save(course);

        mvc.perform(delete(String.format("/courses/%s/teachers/%s",
                course.getId(), teacher.getId())))
                .andExpect(status().isOk());
    }

    @Test
    public void testRemovingNonAssignedTeacherFromCourse() throws Exception {
        Teacher teacher = teacherRepository.save(TeacherFixture.getExampleTeacher());
        Course course = courseRepository.save(CourseFixture.getExampleCourse());

        mvc.perform(delete(String.format("/courses/%s/teachers/%s",
                course.getId(), teacher.getId())))
                .andExpect(status().is(409));
    }
}
