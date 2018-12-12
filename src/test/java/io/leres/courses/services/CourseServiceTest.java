package io.leres.courses.services;

import io.leres.UnitTests;
import io.leres.courses.Course;
import io.leres.courses.CourseFixture;
import io.leres.courses.CoursePost;
import io.leres.courses.exceptions.AlreadyAssignedToCourse;
import io.leres.courses.exceptions.NotAssignedToCourse;
import io.leres.courses.repo.CoursePostRepository;
import io.leres.courses.repo.CourseRepository;
import io.leres.exceptions.ResourceAlreadyExists;
import io.leres.exceptions.ResourceNotFound;
import io.leres.students.Student;
import io.leres.students.StudentFixture;
import io.leres.teachers.Teacher;
import io.leres.teachers.TeacherFixture;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.AdditionalAnswers;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@Category(UnitTests.class)
public class CourseServiceTest {

    private CourseService courseService;
    private CourseRepository courseRepositoryMock;
    private CoursePostRepository coursePostRepository;

    private Course exampleCourse;

    @Before
    public void setUp() {
        courseRepositoryMock = mock(CourseRepository.class);
        coursePostRepository = mock(CoursePostRepository.class);
        courseService = new CourseServiceImpl(courseRepositoryMock, coursePostRepository);

        exampleCourse = CourseFixture.getExampleCourse();

        setUpMocks();
    }

    private void setUpMocks() {
        setUpCourseRepository();
        setUpCoursePostRepository();
    }

    private void setUpCourseRepository() {
        when(courseRepositoryMock.save(
                any(Course.class)
        )).thenAnswer(AdditionalAnswers.returnsFirstArg());

        when(courseRepositoryMock.findById(
                anyLong()
        )).thenReturn(Optional.of(exampleCourse));
    }

    private void setUpCoursePostRepository() {
        when(coursePostRepository.save(
                any(CoursePost.class)
        )).thenAnswer(AdditionalAnswers.returnsFirstArg());
    }

    @Test(expected = ResourceAlreadyExists.class)
    public void testCreatingExistingCourseThrows() throws ResourceAlreadyExists {
        when(courseRepositoryMock.countByName(
                anyString()
        )).thenReturn(1);

        courseService.createCourse(exampleCourse);
    }

    @Test
    public void testCreatingCourseSaves() throws ResourceAlreadyExists {
        Course course = courseService.createCourse(exampleCourse);

        assertThat(course.getName()).isEqualTo(exampleCourse.getName());
        verify(courseRepositoryMock).save(any());
    }

    @Test(expected = ResourceNotFound.class)
    public void testRemovingNonExistingCourseThrows() throws ResourceNotFound {
        when(courseRepositoryMock.existsById(
                anyLong()
        )).thenReturn(false);

        courseService.removeCourse(1L);
    }

    @Test
    public void testRemovingCourseDeletes() throws ResourceNotFound {
        when(courseRepositoryMock.existsById(
                anyLong()
        )).thenReturn(true);

        courseService.removeCourse(1L);

        verify(courseRepositoryMock).deleteById(1L);
    }

    @Test(expected = ResourceNotFound.class)
    public void testGettingNonExistingCourseThrows() throws ResourceNotFound {
        when(courseRepositoryMock.findById(
                anyLong()
        )).thenReturn(Optional.empty());

        courseService.getCourseById(1L);
    }

    @Test
    public void testGettingCourse() throws ResourceNotFound {
        Course course = courseService.getCourseById(1L);

        assertThat(course).isEqualTo(exampleCourse);
    }

    @Test
    public void testCreatingCoursePost() {
        Course course = new Course("CS-101");
        CoursePost coursePost = new CoursePost("content");

        CoursePost result = courseService.createCoursePost(course, coursePost);

        assertThat(result.getContent()).isEqualTo(coursePost.getContent());
        assertThat(result.getCourse()).isEqualTo(course);
        assertThat(course.getPosts()).hasSize(1);
    }

    @Test(expected = ResourceNotFound.class)
    public void testGettingNonExistingCoursePostsThrows() throws ResourceNotFound {
        when(coursePostRepository.findById(1L)).thenReturn(Optional.empty());

        courseService.getCoursePostById(1L);
    }

    @Test
    public void testGettingCoursePost() throws ResourceNotFound {
        when(coursePostRepository.findById(1L))
                .thenReturn(Optional.of(CourseFixture.getExampleCoursePost()));

        CoursePost coursePost = courseService.getCoursePostById(1L);

        assertThat(coursePost.getContent()).isNotEmpty();
    }

    @Test
    public void testRemovingCoursePostRemovesItFromCoursePostSet() {
        Course course = CourseFixture.getExampleCourse();
        CoursePost coursePost = CourseFixture.getExampleCoursePost();
        course.addPost(coursePost);

        courseService.removeCoursePost(coursePost);

        assertThat(course.getPosts()).hasSize(0);
        assertThat(coursePost.getCourse()).isNull();
        verify(coursePostRepository).delete(coursePost);
    }

    @Test(expected = AlreadyAssignedToCourse.class)
    public void testAssigningStudentToCourseWhenAlreadyAssigned() throws AlreadyAssignedToCourse {
        Course course = CourseFixture.getExampleCourse();
        Student student = StudentFixture.getExampleStudent();
        student.setId(1L);
        course.getStudents().add(student);

        courseService.assignStudentToCourse(course, student);
    }

    @Test
    public void testAssigningStudentToCourseSaves() throws AlreadyAssignedToCourse {
        Course course = CourseFixture.getExampleCourse();
        Student student = StudentFixture.getExampleStudent();
        student.setId(1L);

        courseService.assignStudentToCourse(course, student);

        assertThat(course.getStudents()).hasSize(1);
        verify(courseRepositoryMock).save(course);
    }

    @Test(expected = NotAssignedToCourse.class)
    public void testRemovingNonAssignedStudentFromCourseThrows() throws NotAssignedToCourse {
        Student student = StudentFixture.getExampleStudent();
        Course course = CourseFixture.getExampleCourse();

        courseService.removeStudentFromCourse(course, student);
    }

    @Test
    public void testRemovingAssignedStudentFromCourseSaves() throws NotAssignedToCourse {
        Student student = StudentFixture.getExampleStudent();
        Course course = CourseFixture.getExampleCourse();
        course.getStudents().add(student);

        courseService.removeStudentFromCourse(course, student);

        assertThat(course.getStudents()).hasSize(0);
        verify(courseRepositoryMock).save(course);
    }

    @Test(expected = AlreadyAssignedToCourse.class)
    public void testAssigningAlreadyAssignedTeacherToCourseThrows() throws AlreadyAssignedToCourse {
        Course course = CourseFixture.getExampleCourse();
        Teacher teacher = TeacherFixture.getExampleTeacher();
        course.getTeachers().add(teacher);

        courseService.assignTeacherToCourse(course, teacher);
    }

    @Test
    public void testAssigningTeacherToCourseSaves() throws AlreadyAssignedToCourse {
        Course course = CourseFixture.getExampleCourse();
        Teacher teacher = TeacherFixture.getExampleTeacher();

        courseService.assignTeacherToCourse(course, teacher);

        assertThat(course.getTeachers()).hasSize(1);
        verify(courseRepositoryMock).save(course);
    }

    @Test(expected = NotAssignedToCourse.class)
    public void testRemovingNonAssignedTeacherFromCourseThrows() throws NotAssignedToCourse {
        Course course = CourseFixture.getExampleCourse();
        Teacher teacher = TeacherFixture.getExampleTeacher();

        courseService.removeTeacherFromCourse(course, teacher);
    }

    @Test
    public void testRemovingAssignedTeacherFromCourseSaves() throws NotAssignedToCourse {
        Course course = CourseFixture.getExampleCourse();
        Teacher teacher = TeacherFixture.getExampleTeacher();
        course.getTeachers().add(teacher);

        courseService.removeTeacherFromCourse(course, teacher);

        assertThat(course.getTeachers()).hasSize(0);
        verify(courseRepositoryMock).save(course);
    }
}
