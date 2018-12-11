package io.leres.courses;

import io.leres.exceptions.ResourceNotFound;

public interface CourseFinder {

    Course getCourseById(long courseId) throws ResourceNotFound;
}
