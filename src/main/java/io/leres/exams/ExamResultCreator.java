package io.leres.exams;

import io.leres.entities.Exam;
import io.leres.entities.ExamResult;
import io.leres.entities.Student;

public interface ExamResultCreator {

    ExamResult storeExamResult(Exam exam, Student student, int grade);

}
