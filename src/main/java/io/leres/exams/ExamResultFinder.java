package io.leres.exams;

import io.leres.entities.Exam;
import io.leres.entities.ExamResult;

import java.util.List;

public interface ExamResultFinder {

    ExamResult getExamResult(long examResultId);

    List<ExamResult> getExamResultsByExam(Exam exam);
    
}
