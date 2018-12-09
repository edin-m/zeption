package io.leres.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class NotImplemented extends RuntimeException {
    public NotImplemented() {
        super("Not Implemented");
    }

    public NotImplemented(String message) {
        super(String.format("Not implemented: %s", message));
    }
}
