package io.leres.teachers;

import io.leres.entities.PersonData;
import io.leres.entities.Teacher;

public abstract class TeacherFixture {

    public static Teacher getDefaultProfessor() {
        Teacher teacher = new Teacher(
                new PersonData("first", "second", "0123"),
                Teacher.TeacherType.PROFESSOR
        );
        teacher.setId(1L);
        return teacher;
    }
}
