package io.leres.courses;

public abstract class CourseFixture {
    public static Course getExampleCourse() {
        return new Course("CS-101");
    }

    public static CoursePost getExampleCoursePost() {
        return new CoursePost("Course post content");
    }
}
