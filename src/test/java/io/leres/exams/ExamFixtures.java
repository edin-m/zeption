package io.leres.exams;

import io.leres.entities.Exam;

public abstract class ExamFixtures {

    public static Exam getDefaultExam() {
        Exam exam = new Exam();
        exam.setId(1L);
        return exam;
    }

}
