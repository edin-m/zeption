package io.leres.exams;

import io.leres.entities.Teacher;

import java.time.Instant;
import java.util.Set;

public interface ExamScheduler {

    void scheduleExam(Teacher signOffTeacher, Instant timeAt, Set<Teacher> attendingTeachers);

}
