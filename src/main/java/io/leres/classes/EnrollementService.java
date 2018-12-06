package io.leres.classes;

import io.leres.classes.exceptions.StudentAlreadyEnrolled;
import io.leres.entities.Student;
import io.leres.entities.Teacher;
import io.leres.entities.UniClass;

public interface EnrollementService {

    Enrolement enrollStudentToClass(Student student, UniClass uniClass) throws StudentAlreadyEnrolled;

    void assignTeacherToUniClass(Teacher teacher, UniClass uniClass);

}
