package io.leres.controllers;

import io.leres.courses.Course;
import io.leres.courses.CourseCuder;
import io.leres.courses.CourseFinder;
import io.leres.exceptions.ResourceAlreadyExists;
import io.leres.exceptions.ResourceNotFound;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CourseController {

    private CourseFinder courseFinder;
    private CourseCuder courseCuder;

    public CourseController(CourseFinder courseFinder, CourseCuder courseCuder) {
        this.courseFinder = courseFinder;
        this.courseCuder = courseCuder;
    }

    @PostMapping("/courses")
    public Course createCourse(@RequestBody Course example) throws ResourceAlreadyExists {
        return courseCuder.createCourse(example);
    }

    @GetMapping("/courses/{courseId}")
    public Course getCourse(@PathVariable Long courseId) throws ResourceNotFound {
        return courseFinder.getById(courseId);
    }

    @DeleteMapping("/courses/{courseId}")
    public ResponseEntity<?> deleteCourse(@PathVariable Long courseId) throws ResourceNotFound {
        courseCuder.removeCourse(courseId);
        return ResponseEntity.ok().build();
    }
}
