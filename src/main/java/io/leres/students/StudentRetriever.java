package io.leres.students;

import io.leres.entities.Student;
import io.leres.students.exceptions.StudentNotFound;

import java.util.List;

public interface StudentRetriever {

    List<Student> getAllStudents();

    Student getStudentById(Long id) throws StudentNotFound;
}
