package io.leres.exams.exceptions;

public class ExamResultNotFound extends Exception {
    public ExamResultNotFound(long examResultId) {
        super(String.format("Exam result %s not found", examResultId));
    }
}
