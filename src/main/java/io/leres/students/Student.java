package io.leres.students;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.leres.courses.Course;
import io.leres.entities.Person;
import io.leres.entities.Resource;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@EqualsAndHashCode(callSuper = true, exclude = {"courses"})
@NoArgsConstructor
@ToString(exclude = {"courses"})
public class Student extends Resource {

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true, optional = false)
    @JoinColumn(name = "person_info_id")
    private Person person;

    @JsonIgnore
    @ManyToMany(mappedBy = "students")
    private Set<Course> courses = new HashSet<>();

}
