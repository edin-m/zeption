package io.leres.courses.services;

import io.leres.UnitTests;
import io.leres.courses.Course;
import io.leres.courses.repo.CourseRepository;
import io.leres.exceptions.ResourceAlreadyExists;
import io.leres.exceptions.ResourceNotFound;
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

    private Course exampleCourse;

    @Before
    public void setUp() {
        courseRepositoryMock = mock(CourseRepository.class);
        courseService = new CourseServiceImpl(courseRepositoryMock);

        exampleCourse = CourseFixture.getExampleCourse();

        setUpMocks();
    }

    private void setUpMocks() {
        when(courseRepositoryMock.save(
                any(Course.class)
        )).thenAnswer(AdditionalAnswers.returnsFirstArg());

        when(courseRepositoryMock.findById(
                anyLong()
        )).thenReturn(Optional.of(exampleCourse));
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
        courseService.createCourse(exampleCourse);

        verify(courseRepositoryMock).save(any());
    }

    @Test(expected = ResourceNotFound.class)
    public void testRemovingNonExistingCourseThrows() throws ResourceNotFound {
        when(courseRepositoryMock.existsById(
                anyLong()
        )).thenReturn(true);

        courseService.removeCourse(1L);
    }

    @Test
    public void testRemovingCourseDelets() throws ResourceNotFound {
        when(courseRepositoryMock.existsById(
                anyLong()
        )).thenReturn(false);

        courseService.removeCourse(1L);

        verify(courseRepositoryMock).deleteById(1L);
    }

    @Test(expected = ResourceNotFound.class)
    public void testGettingNonExistingCourseThrows() throws ResourceNotFound {
        when(courseRepositoryMock.findById(
                anyLong()
        )).thenReturn(Optional.empty());

        courseService.getById(1L);
    }

    @Test
    public void testGettingCourse() throws ResourceNotFound {
        Course course = courseService.getById(1L);

        assertThat(course).isEqualTo(exampleCourse);
    }
}
