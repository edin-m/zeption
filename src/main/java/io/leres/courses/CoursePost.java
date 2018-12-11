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
public class CoursePost extends Resource {

    private String content;

}
