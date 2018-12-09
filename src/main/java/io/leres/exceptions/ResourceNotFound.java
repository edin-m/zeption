package io.leres.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFound extends Exception {
    public ResourceNotFound(Class<?> class_, String resourceName) {
        super(String.format("Resource %s:%s not found", resourceName, class_.toString()));
    }

    public ResourceNotFound(Class<?> class_, long resourceId) {
        super(String.format("Resource %s:%s not found", resourceId, class_.toString()));
    }

    public ResourceNotFound(long resourceId) {
        super(String.format("Resource %s not found", resourceId));
    }
}
