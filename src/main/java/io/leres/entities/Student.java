package io.leres.entities;

import lombok.*;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Transient;
import java.io.Serializable;

@Entity
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
