package io.leres.students;

import io.leres.entities.Person;
import io.leres.exceptions.ResourceAlreadyExists;
import io.leres.exceptions.ResourceNotFound;

public interface StudentCuder {

    Student createStudent(Person person) throws ResourceAlreadyExists;

    Student updateStudentPerson(long studentId, Person person) throws ResourceNotFound;

    void removeStudent(Student student);
}
