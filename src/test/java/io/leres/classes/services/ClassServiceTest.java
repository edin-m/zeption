package io.leres.classes.services;

import io.leres.classes.Enrolement;
import io.leres.classes.exceptions.StudentAlreadyEnrolled;
import io.leres.classes.exceptions.UniClassNotFound;
import io.leres.classes.repo.UniClassRepository;
import io.leres.entities.PersonData;
import io.leres.entities.Student;
import io.leres.entities.Teacher;
import io.leres.entities.UniClass;
import org.junit.Before;
import org.junit.Test;
import org.mockito.AdditionalAnswers;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

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
        Student student = new Student(
                new PersonData("first", "last", "0123")
        );
        exampleClass.addStudentToEnrolledStudents(student);

        classService.enrollStudentToClass(student, exampleClass);
    }

    @Test
    public void testEnrollingStudentToClass() throws StudentAlreadyEnrolled {
        Student student = new Student(
                new PersonData("first", "last", "0123")
        );

        Enrolement enrolement = classService.enrollStudentToClass(student, exampleClass);

        assertThat(enrolement.getUniClass().getEnrolledStudents()).contains(student);
        verify(uniClassRepositoryMock).save(any(UniClass.class));
    }

    @Test
    public void testAssigningTeacherToUniClass() {
        Teacher teacher = new Teacher(new PersonData("first", "last", "0123"), Teacher.TeacherType.PROFESSOR);

        classService.assignTeacherToUniClass(teacher, exampleClass);

        assertThat(exampleClass.getTeacher()).isEqualTo(teacher);
        verify(uniClassRepositoryMock).save(any(UniClass.class));
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
