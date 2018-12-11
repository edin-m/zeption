package io.leres.students;

import io.leres.entities.Person;

public abstract class StudentFixture {

    public static Student getExampleStudent() {
        Student student = new Student();
        student.setPerson(new Person("01234", "first", "last", "1234"));
        student.setId(1L);
        return student;
    }
}
