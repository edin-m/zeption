package io.leres.homework;

import io.leres.entities.Teacher;
import io.leres.entities.UniClass;
import io.leres.entities.homework.HomeworkAssignment;

import java.time.Instant;

public interface HomeworkAssignCreator {

    HomeworkAssignment createHomeworkAssignment(Teacher teacher, UniClass uniClass, int weekOfClass, Instant dueDate);

}
