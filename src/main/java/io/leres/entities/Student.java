package io.leres.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class Student extends Resource {

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
