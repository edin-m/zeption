package io.leres.exams;

import io.leres.entities.Exam;
import io.leres.entities.Student;
import io.leres.exams.exceptions.ExamNotFound;

import java.util.List;

public interface ExamFinder {

    Exam getExamById(long examId) throws ExamNotFound;

    List<Exam> getRecentExamsForStudent(Student student);

}
