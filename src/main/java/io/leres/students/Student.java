package io.leres.students;

import io.leres.entities.Person;
import io.leres.entities.Resource;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class Student extends Resource {

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true, optional = false)
    @JoinColumn(name = "person_info_id")
    private Person person;

}
