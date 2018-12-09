package io.leres.students.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class StudentAlreadyExists extends Exception {
    public StudentAlreadyExists(String studentName) {
        super(String.format("The student under the same name (%s) already exists", studentName));
    }
}
