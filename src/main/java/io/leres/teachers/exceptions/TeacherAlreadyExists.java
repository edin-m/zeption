package io.leres.teachers.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class TeacherAlreadyExists extends Exception {
    public TeacherAlreadyExists() {
        super("Teacher already exists");
    }
}
