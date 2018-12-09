package io.leres.teachers;

import io.leres.entities.Teacher;
import io.leres.teachers.exceptions.TeacherAlreadyExists;

public interface TeacherCreator {

    Teacher createTeacher(Teacher teacher) throws TeacherAlreadyExists;

}
