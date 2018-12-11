package io.leres.classes.exceptions;

import io.leres.students.Student;
import io.leres.entities.UniClass;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class StudentAlreadyEnrolled extends Exception {
    public StudentAlreadyEnrolled(Student student, UniClass uniClass) {
        super(String.format("Student already enrolled (%s, %s) (%s, %s)",
                student.getId(), "",
                uniClass.getId(), uniClass.getName()));
    }
}
