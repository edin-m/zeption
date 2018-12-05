package io.leres.classes.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UniClassNotFound extends Exception {
    public UniClassNotFound(long uniClassId) {
        super(String.format("Invalid UniClass (%s)", uniClassId));
    }
}
