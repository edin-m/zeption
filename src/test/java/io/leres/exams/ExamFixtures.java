package io.leres.exams;

import io.leres.entities.Exam;
import io.leres.entities.ExamResult;
import io.leres.students.StudentFixture;

public abstract class ExamFixtures {

    public static Exam getDefaultExam() {
        Exam exam = new Exam();
        exam.setId(1L);
        return exam;
    }

    public static ExamResult getDefaultExamResult() {
        ExamResult examResult = new ExamResult(
                getDefaultExam(),
                StudentFixture.getDefaultStudent(),
                100
        );
        examResult.setId(1L);

        return examResult;
    }

}
