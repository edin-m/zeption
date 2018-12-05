package io.leres.students.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class StudentNotFound extends Exception {
    public StudentNotFound(String message) {
        super(message);
    }
}
