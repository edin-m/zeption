package io.leres.classes;

import io.leres.students.Student;
import io.leres.entities.UniClass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Enrolement {
    private Student student;
    private UniClass uniClass;
}
