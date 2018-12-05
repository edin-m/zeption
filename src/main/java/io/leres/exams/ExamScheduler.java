package io.leres.exams;

import io.leres.entities.Teacher;
import io.leres.entities.UniClass;

import java.time.Instant;

public interface ExamScheduler {

    void scheduleExam(Teacher signOffTeacher, Instant timeAt, UniClass uniClass);

}
