package io.leres.entities;

import lombok.*;

import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Exam extends Resource {

    @OneToOne
    private Teacher signOffTeacher;

    private Instant takenAt = Instant.now();

    @OneToMany
    private Set<Student> participants = new HashSet<>();

    @OneToMany
    private Set<Teacher> attending = new HashSet<>();

    @OneToMany
    private Set<ExamResult> results = new HashSet<>();

}