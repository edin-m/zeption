package io.leres.students;

import io.leres.entities.Person;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class StudentTest {

    @Test
    public void testLombok() {
        Student student1 = new Student();
        student1.setPerson(new Person("0123", "first", "middle", "last"));
        student1.setId(1L);
        Student student2 = new Student();
        student2.setPerson(new Person("0123", "first", "middle", "last"));
        student2.setId(2L);

        assertThat(student1).isNotEqualTo(student2);
    }

}
