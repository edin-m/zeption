package io.leres.entities.homework;

import io.leres.entities.Resource;
import io.leres.entities.UniClass;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.time.Instant;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class HomeworkAssignment extends Resource {

    @ManyToOne
    private UniClass uniClass;

    private String text;

    private int weekOfClass;

    private Instant dueDate = Instant.now();

}
