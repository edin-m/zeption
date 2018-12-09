package io.leres.entities;

import lombok.*;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "students")
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Student extends Resource implements Serializable {

    @Embedded
    private PersonData personData;

    public String getFullName() {
        return personData.getFirstName() + " " + personData.getLastName();
    }
}
