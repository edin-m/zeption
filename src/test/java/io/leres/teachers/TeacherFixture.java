package io.leres.teachers;

import io.leres.entities.Person;
import io.leres.entities.Teacher;

public abstract class TeacherFixture {

    public static Teacher getDefaultProfessor() {
        Teacher teacher = new Teacher(
                new Person("0123", "first", "middle", "last"),
                Teacher.TeacherType.PROFESSOR
        );
        teacher.setId(1L);
        return teacher;
    }
}
