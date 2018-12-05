package io.leres.exams;

import io.leres.entities.Exam;
import io.leres.entities.ExamResult;
import io.leres.exams.exceptions.ExamResultNotFound;

import java.util.List;

public interface ExamResultFinder {

    ExamResult getExamResultById(long examResultId) throws ExamResultNotFound;

}
