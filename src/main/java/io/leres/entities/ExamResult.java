package io.leres.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ExamResult extends Resource {
//
//    @ManyToOne
//    private Exam exam;
//
//    @OneToOne
//    private Student student;

    private int grade;

}
