package io.leres.entities.forum;

import io.leres.entities.Resource;
import io.leres.students.Student;
import io.leres.teachers.Teacher;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ForumUser extends Resource {

    public enum ForumUserRole {
        USER,
        ADMIN,
        SUPER_ADMIN
    }

    @OneToOne
    private Student student;

    @OneToOne
    private Teacher teacher;

    private ForumUserRole role;

}
