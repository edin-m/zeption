package io.leres.entities;

import lombok.*;

import javax.persistence.Embedded;
import javax.persistence.Entity;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Teacher extends Resource {

    public enum TeacherType {
        PROFESSOR,
        TEACHING_ASSISTENT
    }

    @Embedded
    private PersonData personData;

    TeacherType teacherType;
}
