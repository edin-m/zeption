package io.leres.courses.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class StudentAlreadyAssignedToCourse extends Exception {
    public StudentAlreadyAssignedToCourse(long studentId) {
        super(String.format("Student %s already assigned to class", studentId));
    }
}
