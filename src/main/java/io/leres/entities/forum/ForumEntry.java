package io.leres.entities.forum;

import io.leres.entities.Resource;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ForumEntry extends Resource {

    @ManyToOne
    private ForumUser author;

    @ManyToOne
    private ForumThread thread;

    private String content;

    private int upvotes = 0;

}
