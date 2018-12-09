package io.leres.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ResourceAlreadyExists extends Exception {
    public ResourceAlreadyExists(Class<?> class_, long resourceId) {
        super(String.format("The resource %s:%s already exists", resourceId, class_.toString()));
    }
    public ResourceAlreadyExists(Class<?> class_, String resourceId) {
        super(String.format("The resource %s:%s already exists", resourceId, class_.toString()));
    }
}
