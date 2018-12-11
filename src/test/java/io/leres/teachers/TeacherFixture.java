package io.leres.teachers;

import io.leres.entities.Person;

public abstract class TeacherFixture {

    public static Teacher getExampleTeacher() {
        Teacher teacher = new Teacher();
        teacher.setPerson(new Person("0123", "fist", "middle", "last"));
        teacher.setId(1L);
        return teacher;
    }
}
