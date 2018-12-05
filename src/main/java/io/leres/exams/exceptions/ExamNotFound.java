package io.leres.exams.exceptions;

public class ExamNotFound extends Exception {
    public ExamNotFound(long examId) {
        super(String.format("Exam %s not found", examId));
    }
}
