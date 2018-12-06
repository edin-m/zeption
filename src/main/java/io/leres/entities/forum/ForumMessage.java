package io.leres.entities.forum;

import io.leres.entities.Resource;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.time.Instant;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ForumMessage extends Resource {

    @ManyToOne
    private ForumUser author;

    @ManyToOne
    private ForumThread thread;

    private Instant createdAt = Instant.now();

    private String message;

}
