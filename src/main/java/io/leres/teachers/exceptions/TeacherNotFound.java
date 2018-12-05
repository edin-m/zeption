package io.leres.teachers.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class TeacherNotFound extends Exception {
    public TeacherNotFound(long teacherId) {
        super(String.format("Teacher %s not found"));
    }
}
