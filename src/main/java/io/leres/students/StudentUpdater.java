package io.leres.students;

import io.leres.entities.Student;
import io.leres.students.exceptions.StudentNotFound;

public interface StudentUpdater {

    Student updateStudent(Student student) throws StudentNotFound;

}
