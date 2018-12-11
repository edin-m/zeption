package io.leres.courses.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class AlreadyAssignedToCourse extends Exception {
    public AlreadyAssignedToCourse(Class<?> class_, long resourceId) {
        super(String.format("Resource %s %s already assigned to class", class_.toString(), resourceId));
    }
}
