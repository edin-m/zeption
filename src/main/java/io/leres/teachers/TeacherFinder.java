package io.leres.teachers;

import io.leres.teachers.exceptions.TeacherNotFound;

public interface TeacherFinder {

    Teacher getTeacherById(long teacherId) throws TeacherNotFound;

}
