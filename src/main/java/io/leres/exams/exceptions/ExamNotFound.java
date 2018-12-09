package io.leres.exams.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ExamNotFound extends Exception {
    public ExamNotFound(long examId) {
        super(String.format("Exam %s not found", examId));
    }
}
