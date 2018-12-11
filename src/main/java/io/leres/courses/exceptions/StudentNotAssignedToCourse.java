package io.leres.courses.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class StudentNotAssignedToCourse extends Exception {
    public StudentNotAssignedToCourse(long studentId) {
        super(String.format("Student %s not assigned to course", studentId));
    }
}
