package io.leres.courses.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class NotAssignedToCourse extends Exception {
    public NotAssignedToCourse(Class<?> class_, long resourceId) {
        super(String.format("Resource %s %s not assigned to course", class_.toString(), resourceId));
    }
}
