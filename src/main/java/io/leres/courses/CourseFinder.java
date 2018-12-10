package io.leres.courses;

import io.leres.exceptions.ResourceNotFound;

public interface CourseFinder {

    Course getById(long courseId) throws ResourceNotFound;
}
