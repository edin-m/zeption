package io.leres.entities.homework;

import io.leres.entities.Resource;
import lombok.*;

import javax.persistence.Entity;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class HomeworkEntry extends Resource {
//
//    @ManyToOne
//    private Student author;
//
//    @ManyToOne
//    private HomeworkAssignment homeworkAssignment;

    private String data;
}
