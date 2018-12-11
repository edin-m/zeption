package io.leres.teachers;

import io.leres.exceptions.ResourceNotFound;

public interface TeacherFinder {

    Teacher getTeacherById(long teacherId) throws ResourceNotFound;

}
