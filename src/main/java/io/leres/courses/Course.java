package io.leres.courses;

import io.leres.entities.Resource;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Course extends Resource {

    public String name;

    public Course(String name) {
        this.name = name;
    }
}
