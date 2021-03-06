package io.leres.teachers.services;

import io.leres.UnitTests;
import io.leres.courses.Course;
import io.leres.courses.CourseFixture;
import io.leres.exceptions.ResourceAlreadyExists;
import io.leres.exceptions.ResourceNotFound;
import io.leres.teachers.Teacher;
import io.leres.teachers.TeacherFixture;
import io.leres.teachers.repo.TeacherRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.AdditionalAnswers;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@Category(UnitTests.class)
public class TeacherServiceTest {

    private TeacherRepository teacherRepositoryMock;

    private TeacherService teacherService;

    private Teacher exampleTeacher;

    @Before
    public void setUp() {
        teacherRepositoryMock = mock(TeacherRepository.class);

        teacherService = new TeacherServiceImpl(teacherRepositoryMock);

        exampleTeacher = TeacherFixture.getExampleTeacher();
        setUpMocks();
    }

    private void setUpMocks() {
        when(teacherRepositoryMock.save(
                any(Teacher.class)
        )).thenAnswer(AdditionalAnswers.returnsFirstArg());
    }

    @Test(expected = ResourceAlreadyExists.class)
    public void testCreatingExistingTeacherThrows() throws ResourceAlreadyExists {
        when(teacherRepositoryMock.countByPerson_SocialId(
                anyString()
        )).thenReturn(1);

        teacherService.createTeacher(exampleTeacher.getPerson());
    }

    @Test
    public void testCreatingTeacherSaves() throws ResourceAlreadyExists {
        Teacher teacher = teacherService.createTeacher(exampleTeacher.getPerson());

        assertThat(teacher.getPerson().getSocialId()).isEqualTo(exampleTeacher.getPerson().getSocialId());
        verify(teacherRepositoryMock).save(any());
    }

    @Test
    public void testRemovingTeacherSaves() throws ResourceNotFound {
        Teacher teacher = TeacherFixture.getExampleTeacher();
        Course course = CourseFixture.getExampleCourse();
        course.addTeacher(teacher);
        when(teacherRepositoryMock.findById(1L)).thenReturn(Optional.of(teacher));

        teacherService.removeTeacher(teacher);

        assertThat(teacher.getCourses()).hasSize(0);
        assertThat(course.getTeachers()).hasSize(0);
        verify(teacherRepositoryMock).delete(any(Teacher.class));
    }

    @Test(expected = ResourceNotFound.class)
    public void testGettingNonExistingTeacher() throws ResourceNotFound {
        when(teacherRepositoryMock.findById(1L)).thenReturn(Optional.empty());

        teacherService.getTeacherById(1L);
    }

    @Test
    public void testGettingTeacher() throws ResourceNotFound {
        when(teacherRepositoryMock.findById(1L)).thenReturn(Optional.of(exampleTeacher));

        Teacher teacher = teacherService.getTeacherById(1L);

        assertThat(teacher.getPerson().getSocialId()).isEqualTo(teacher.getPerson().getSocialId());
    }
}
