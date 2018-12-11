package io.leres.entities.forum;

import io.leres.entities.Resource;
import lombok.*;

import javax.persistence.Entity;

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

//    @OneToOne
//    private Student student;
//
//    @OneToOne
//    private Teacher teacher;

    private ForumUserRole role;

}
