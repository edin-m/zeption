package io.leres.exams;

import io.leres.entities.Exam;
import io.leres.entities.ExamResult;
import io.leres.entities.Student;
import io.leres.exams.exceptions.ExamResultNotFound;

public interface ExamResultCruder {

    ExamResult storeExamResult(Exam exam, Student student, int grade);

    ExamResult getExamResultById(long examResultId) throws ExamResultNotFound;

}
