package io.leres.teachers;

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
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true, exclude = {"courses"})
@ToString(callSuper = true, exclude = {"courses"})
public class Teacher extends Resource {

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, optional = false)
    @JoinColumn
    private Person person;


    @JsonIgnore
    @ManyToMany(mappedBy = "teachers")
    private Set<Course> courses = new HashSet<>();

}
