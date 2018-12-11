package io.leres.courses;

import io.leres.courses.exceptions.AlreadyAssignedToCourse;
import io.leres.courses.exceptions.NotAssignedToCourse;
import io.leres.students.Student;
import io.leres.teachers.Teacher;

public interface CourseAssigner {

    void assignStudentToCourse(Course course, Student student) throws AlreadyAssignedToCourse;

    void removeStudentFromCourse(Course course, Student student) throws NotAssignedToCourse;

    void assignTeacherToCourse(Course course, Teacher teacher) throws AlreadyAssignedToCourse;

    void removeTeacherFromCourse(Course course, Teacher teacher) throws NotAssignedToCourse;
}
