package io.leres.entities;

import lombok.*;

import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ExamResult extends Resource {

    @ManyToOne
    private Exam exam;

    @OneToOne
    private Student student;

    private int grade;

}