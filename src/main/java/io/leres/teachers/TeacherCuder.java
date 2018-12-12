package io.leres.teachers;

import io.leres.entities.Person;
import io.leres.exceptions.ResourceAlreadyExists;

public interface TeacherCuder {

    Teacher createTeacher(Person person) throws ResourceAlreadyExists;

    void removeTeacher(Teacher teacher);

}
