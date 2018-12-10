package io.leres.courses.services;

import io.leres.courses.Course;

public abstract class CourseFixture {
    public static Course getExampleCourse() {
        return new Course("CS-101");
    }
}
