package io.leres.students;

import io.leres.exceptions.ResourceNotFound;

public interface StudentFinder {

    Student getStudentById(long studentId) throws ResourceNotFound;

}
