package io.leres.courses;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.leres.entities.Resource;
import io.leres.students.Student;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true, exclude = {"posts"})
@ToString(callSuper = true, exclude = {"posts"})
public class Course extends Resource {

    public String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "course", orphanRemoval = true)
    private Set<CoursePost> posts = new HashSet<>();

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(name = "course_students",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id"))
    private Set<Student> students = new HashSet<>();

    public Course(String name) {
        this.name = name;
    }
}
