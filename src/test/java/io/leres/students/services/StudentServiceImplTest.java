package io.leres.students.services;

import io.leres.UnitTests;
import io.leres.entities.Person;
import io.leres.entities.Student;
import io.leres.exceptions.ResourceAlreadyExists;
import io.leres.exceptions.ResourceNotFound;
import io.leres.students.StudentFixture;
import io.leres.students.exceptions.StudentNotFound;
import io.leres.students.repo.StudentRepository;
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
        ).thenReturn(Optional.of(exampleStudent));

        when(studentRepositoryMock.save(
                any(Student.class)
        )).thenAnswer(AdditionalAnswers.returnsFirstArg());
    }

    @Test(expected = ResourceAlreadyExists.class)
    public void testCreatingExistingStudentThrows() throws ResourceAlreadyExists {
        when(studentRepositoryMock.findByPerson_SocialId(
                anyString()
        )).thenReturn(exampleStudent);

        studentService.createStudent(exampleStudent.getPerson());
    }

    @Test
    public void testCreatingExistingStudentsSaves() throws ResourceAlreadyExists {
        studentService.createStudent(exampleStudent.getPerson());

        verify(studentRepositoryMock).save(any(Student.class));
    }

    @Test
    public void testUpdatingStudent() throws ResourceNotFound {
        Person person = new Person();
        person.setFirstName("different");

        String lastNameBefore = exampleStudent.getPerson().getLastName();
        Student student = studentService.updateStudentPerson(1L, person);

        assertThat(student.getPerson().getFirstName()).isEqualTo("different");
        // TODO
//        assertThat(student.getPerson().getLastName()).isEqualTo(lastNameBefore);
        verify(studentRepositoryMock).save(any(Student.class));
    }

    @Test(expected = ResourceNotFound.class)
    public void testGettingNonExistingStudentThrows() throws ResourceNotFound {
        when(studentRepositoryMock.findById(
                anyLong()
        )).thenReturn(Optional.empty());

        studentService.getStudentById(1L);
    }

    @Test
    public void testGettingExistingStudent() throws ResourceNotFound {
        exampleStudent.setId(1L);
        when(studentRepositoryMock.findById(
                anyLong()
        )).thenReturn(Optional.of(exampleStudent));

        Student student = studentService.getStudentById(1L);

        assertThat(student).isEqualTo(exampleStudent);
        verify(studentRepositoryMock).findById(1L);
    }

    @Test(expected = ResourceNotFound.class)
    public void testRemovingNonExistingStudentThrows() throws ResourceNotFound {
        when(studentRepositoryMock.existsById(
                anyLong()
        )).thenReturn(false);

        studentService.removeStudent(1L);
    }

    @Test
    public void testRemovingNonExistingStudent() throws ResourceNotFound {
        when(studentRepositoryMock.existsById(
                anyLong()
        )).thenReturn(true);
        doNothing().when(studentRepositoryMock).deleteById(anyLong());

        studentService.removeStudent(1L);

        verify(studentRepositoryMock).deleteById(1L);
    }

}
