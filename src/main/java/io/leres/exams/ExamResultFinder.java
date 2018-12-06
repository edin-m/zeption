package io.leres.exams;

import io.leres.entities.ExamResult;
import io.leres.exams.exceptions.ExamResultNotFound;

public interface ExamResultFinder {

    ExamResult getExamResultById(long examResultId) throws ExamResultNotFound;

}
