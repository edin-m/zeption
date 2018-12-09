package io.leres.entities;

import lombok.*;

import javax.persistence.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class UniClass extends Resource {

    private String name;

    private String description;

    @ManyToOne
    private Teacher teacher;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Student> enrolledStudents = new HashSet<>();

    private Instant startDate = Instant.now();

    private Instant endDate = Instant.now();

    @OneToMany
    private List<CurriculumEntry> entries = new ArrayList<>();

    public UniClass(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public void addStudentToEnrolledStudents(Student student) {
        enrolledStudents.add(student);
    }

    public boolean isEnrolled(Student student) {
        return enrolledStudents.contains(student);
    }

    public void addEntry(CurriculumEntry curriculumEntry) {
        entries.add(curriculumEntry);
    }
}
