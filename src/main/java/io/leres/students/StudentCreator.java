package io.leres.students;

import io.leres.entities.Student;
import io.leres.students.exceptions.StudentAlreadyExists;

public interface StudentCreator {

    Student createStudent(Student student) throws StudentAlreadyExists;

}
