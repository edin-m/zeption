package io.leres.students;

import io.leres.entities.Person;
import io.leres.entities.Student;

public abstract class StudentFixture {

    public static Student getDefaultStudent() {
        Student student = new Student();
        student.setPerson(new Person("01234", "first", "last", "1234"));
        return student;
    }
}
