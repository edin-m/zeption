package io.leres.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class Student extends Resource implements Serializable {

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "person_info_id")
    private Person person;



//    @Embedded
//    private PersonData personData;
//
//    public String getFullName() {
//        return personData.getFirstName() + " " + personData.getLastName();
//    }
}
