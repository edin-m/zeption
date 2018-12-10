package io.leres.courses;

import io.leres.exceptions.ResourceAlreadyExists;
import io.leres.exceptions.ResourceNotFound;

public interface CourseCuder {

    Course createCourse(Course example) throws ResourceAlreadyExists;

    void removeCourse(long courseId) throws ResourceNotFound;
}
