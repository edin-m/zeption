package io.leres.teachers;

import io.leres.entities.Person;
import io.leres.exceptions.ResourceAlreadyExists;
import io.leres.exceptions.ResourceNotFound;

public interface TeacherCuder {

    Teacher createTeacher(Person person) throws ResourceAlreadyExists;

    void removeTeacher(long teacherId) throws ResourceNotFound;

}
