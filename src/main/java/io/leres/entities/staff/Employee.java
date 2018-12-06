package io.leres.entities.staff;

import io.leres.entities.Resource;
import lombok.*;

import javax.persistence.Entity;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Employee extends Resource {

    public enum EmployeePosition {
        STUDENT_ASSISTENT,
        TEACHER,
        ADMINISTRATION,
        CONTRACTOR
    }

//    private List<EmployeePosition> employeePositions;

    private long studentTeacherId;

}
