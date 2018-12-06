package io.leres.homework.exceptions;

public class AssignmentNotFound extends Exception {
    public AssignmentNotFound(long assignmentId) {
        super(String.format("Homework assignment %s not found", assignmentId));
    }
}
