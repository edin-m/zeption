package io.leres.courses;

import io.leres.exceptions.ResourceAlreadyExists;
import io.leres.exceptions.ResourceNotFound;

public interface CourseCuder {

    Course createCourse(Course example) throws ResourceAlreadyExists;

    void removeCourse(long courseId) throws ResourceNotFound;

    CoursePost createCoursePost(Course course, CoursePost example);

    void removeCoursePost(CoursePost coursePost);
}
