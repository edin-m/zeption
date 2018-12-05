package io.leres.students;

import io.leres.entities.PersonData;
import io.leres.entities.Student;

public abstract class StudentFixture {

    public static Student getDefaultStudent() {
        return new Student(
                new PersonData("first", "last", "1234")
        );
    }
}
