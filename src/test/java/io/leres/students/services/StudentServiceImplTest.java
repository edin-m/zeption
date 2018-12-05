package io.leres.students.services;

import io.leres.UnitTests;
import io.leres.entities.PersonData;
import io.leres.entities.Student;
import io.leres.students.StudentFixture;
import io.leres.students.exceptions.StudentAlreadyExists;
import io.leres.students.exceptions.StudentNotFound;
import io.leres.students.repo.StudentRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.AdditionalAnswers;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@Category(UnitTests.class)
public class StudentServiceImplTest {

    private StudentRepository studentRepositoryMock;

    private StudentService studentService;

    private Student exampleStudent;

    @Before
    public void setUp() throws StudentNotFound {
        studentRepositoryMock = mock(StudentRepository.class);

        studentService = new StudentServiceImpl(studentRepositoryMock);

        exampleStudent = StudentFixture.getDefaultStudent();
        setupMocks();
    }

    private void setupMocks() throws StudentNotFound {
        when(studentRepositoryMock.findById(
                anyLong())
        ).thenReturn(Optional.of(
                new Student(new PersonData("name", "last", "")))
        );

        when(studentRepositoryMock.save(
                any(Student.class)
        )).thenAnswer(AdditionalAnswers.returnsFirstArg());
    }

    @Test(expected = StudentAlreadyExists.class)
    public void testCreatingExistingStudentThrows() throws StudentAlreadyExists {
        when(studentRepositoryMock.findByPersonData_SocialId(
                eq("1234"))
        ).thenReturn(exampleStudent);

        studentService.createStudent(exampleStudent);
    }

    @Test
    public void testCreatingExistingStudentsSaves() throws StudentAlreadyExists {
        studentService.createStudent(exampleStudent);

        verify(studentRepositoryMock).save(any(Student.class));
    }

    @Test
    public void testGetsAllSutdents() {
        Student student1 = new Student(new PersonData("first", "last", "0123"));
        Student student2 = new Student(new PersonData("second", "last", "0124"));
        when(studentRepositoryMock.findAll()).thenReturn(Arrays.asList(student1, student2));

        List<Student> students = studentService.getAllStudents();

        assertThat(students).hasSize(2);
    }

    @Test(expected = StudentNotFound.class)
    public void testGettingNonExistingStudentThrows() throws StudentNotFound {
        when(studentRepositoryMock.findById(
                anyLong()
        )).thenReturn(Optional.empty());

        studentService.getStudentById(1L);
    }

    @Test
    public void testGettingExistingStudent() throws StudentNotFound {
        exampleStudent.setId(1L);
        when(studentRepositoryMock.findById(
                anyLong()
        )).thenReturn(Optional.of(exampleStudent));

        Student student = studentService.getStudentById(1L);

        assertThat(student).isEqualTo(exampleStudent);
        verify(studentRepositoryMock).findById(1L);
    }

}
