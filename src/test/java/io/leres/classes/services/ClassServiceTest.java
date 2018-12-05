package io.leres.classes.services;

import io.leres.UnitTests;
import io.leres.classes.Enrolement;
import io.leres.classes.exceptions.StudentAlreadyEnrolled;
import io.leres.classes.exceptions.UniClassNotFound;
import io.leres.classes.repo.UniClassRepository;
import io.leres.entities.Student;
import io.leres.entities.Teacher;
import io.leres.entities.UniClass;
import io.leres.fixtures.TeacherFixture;
import io.leres.students.StudentFixture;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.AdditionalAnswers;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@Category(UnitTests.class)
public class ClassServiceTest {

    private UniClassRepository uniClassRepositoryMock;

    private ClassService classService;

    private UniClass exampleClass;

    @Before
    public void setUp() {
        uniClassRepositoryMock = mock(UniClassRepository.class);

        classService = new ClassServiceImpl(uniClassRepositoryMock);

        exampleClass = new UniClass("CS-101", "Intro");
        setupMocks();
    }

    private void setupMocks() {
        when(uniClassRepositoryMock.save(
                any(UniClass.class))
        ).thenAnswer(AdditionalAnswers.returnsFirstArg());
    }

    @Test(expected = StudentAlreadyEnrolled.class)
    public void testEnrollingAlreadyEnrolledStudentThrows() throws StudentAlreadyEnrolled {
        Student student = StudentFixture.getDefaultStudent();

        exampleClass.addStudentToEnrolledStudents(student);

        classService.enrollStudentToClass(student, exampleClass);
    }

    @Test
    public void testEnrollingStudentToClass() throws StudentAlreadyEnrolled {
        Student student = StudentFixture.getDefaultStudent();

        Enrolement enrolement = classService.enrollStudentToClass(student, exampleClass);

        assertThat(enrolement.getUniClass().getEnrolledStudents()).contains(student);
        verify(uniClassRepositoryMock).save(any(UniClass.class));
    }

    @Test
    public void testAssigningTeacherToUniClass() {
        Teacher teacher = TeacherFixture.getDefaultProfessor();

        classService.assignTeacherToUniClass(teacher, exampleClass);

        assertThat(exampleClass.getTeacher()).isEqualTo(teacher);
        verify(uniClassRepositoryMock).save(any(UniClass.class));
    }

    @Test
    public void testCalculatingWeekOfClass() {
        Instant startDate = LocalDate.parse("2018-12-08").atStartOfDay().toInstant(ZoneOffset.UTC);
        exampleClass.setStartDate(startDate);
        Instant timeOfInterest = LocalDate.parse("2018-12-10").atStartOfDay().toInstant(ZoneOffset.UTC);

        int weekOfClass = classService.calculateWeekOfClass(exampleClass, timeOfInterest);

        assertThat(weekOfClass).isEqualTo(2);
    }

    @Test(expected = UniClassNotFound.class)
    public void testGettingInvalidClassThrows() throws UniClassNotFound {
        when(uniClassRepositoryMock.findById(
                anyLong()
        )).thenReturn(Optional.empty());

        classService.getUniClassForId(1L);
    }

    @Test
    public void testGetsInvalidClass() throws UniClassNotFound {
        when(uniClassRepositoryMock.findById(
                anyLong()
        )).thenReturn(Optional.of(exampleClass));

        UniClass uniClass = classService.getUniClassForId(1L);

        assertThat(uniClass.getName()).isEqualTo(exampleClass.getName());
        verify(uniClassRepositoryMock).findById(1L);
    }

    @Test
    public void testCreatingUniClass() {
        UniClass uniClass = classService.createUniClass("CS-101", "Intro");

        assertThat(uniClass.getName()).isEqualTo("CS-101");
        verify(uniClassRepositoryMock).save(any(UniClass.class));
    }

}
