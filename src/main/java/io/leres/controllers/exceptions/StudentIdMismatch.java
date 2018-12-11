package io.leres.controllers.exceptions;

import io.leres.students.Student;

public class StudentIdMismatch extends Exception {
    public StudentIdMismatch(Student student, long studentId) {
        super(String.format("Student Id mismatch %s vs %s", student.getId(), studentId));
    }
}
