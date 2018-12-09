package io.leres.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.Instant;

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Person extends Resource {

    public enum Gender {
        MALE,
        FEMALE
    }

    private String firstName;

    private String middleName;

    private String lastName;

    private String socialId;

    private Instant dateOfBirth;

    private String address;

    private String email;

    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private Gender gender;
}
