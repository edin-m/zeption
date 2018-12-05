package io.leres.fixtures;

import io.leres.entities.PersonData;
import io.leres.entities.Teacher;

public abstract class TeacherFixture {

    public static Teacher getDefaultProfessor() {
        return new Teacher(
                new PersonData("first", "second", "0123"),
                Teacher.TeacherType.PROFESSOR
        );
    }
}
