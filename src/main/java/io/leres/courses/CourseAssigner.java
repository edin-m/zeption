package io.leres.courses;

import io.leres.courses.exceptions.StudentAlreadyAssignedToCourse;
import io.leres.courses.exceptions.StudentNotAssignedToCourse;
import io.leres.students.Student;

public interface CourseAssigner {

    void assignStudentToCourse(Course course, Student student) throws StudentAlreadyAssignedToCourse;

    void removeStudentFromCourse(Course course, Student student) throws StudentNotAssignedToCourse;

}
