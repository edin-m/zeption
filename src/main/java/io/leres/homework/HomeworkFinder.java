package io.leres.homework;

import io.leres.entities.homework.HomeworkAssignment;
import io.leres.entities.homework.HomeworkEntry;
import io.leres.homework.exceptions.AssignmentNotFound;

import java.util.List;

public interface HomeworkFinder {

    HomeworkAssignment getAssignmentById(long assignmentId) throws AssignmentNotFound;

    List<HomeworkEntry> getEntriesForAssignment(HomeworkAssignment assignment);

}
