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
import io.leres.util.CustomObjectMapper;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
    private StudentRepository studentRepository;

    ObjectMapper mapper = new CustomObjectMapper();

    private long courseId;
    private long studentId;

    @Before
    public void setUp() {
        courseRepository.deleteAllInBatch();
        coursePostRepository.deleteAllInBatch();
    }

    @Test
    public void testCreatingCourseWithPost() throws Exception {
        courseRepository.deleteAllInBatch();

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

        log.info(response);
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

    private void setUpSingleCourseInRepository() {
        Course course = courseRepository.save(CourseFixture.getExampleCourse());
        courseId = course.getId();
    }

    private void setUpSingleStudentInRepository() {
        Student student = studentRepository.save(StudentFixture.getExampleStudent());
        studentId = student.getId();
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
}
