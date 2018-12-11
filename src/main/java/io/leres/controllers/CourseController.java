package io.leres.controllers;

import io.leres.courses.*;
import io.leres.courses.exceptions.StudentAlreadyAssignedToCourse;
import io.leres.courses.exceptions.StudentNotAssignedToCourse;
import io.leres.students.Student;
import io.leres.exceptions.ResourceAlreadyExists;
import io.leres.exceptions.ResourceNotFound;
import io.leres.students.StudentFinder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CourseController {

    private CourseFinder courseFinder;
    private CourseCuder courseCuder;
    private StudentFinder studentFinder;
    private CourseAssigner courseAssigner;

    public CourseController(CourseFinder courseFinder, CourseCuder courseCuder, StudentFinder studentFinder, CourseAssigner courseAssigner) {
        this.courseFinder = courseFinder;
        this.courseCuder = courseCuder;
        this.studentFinder = studentFinder;
        this.courseAssigner = courseAssigner;
    }

    @PostMapping("/courses")
    public Course createCourse(@RequestBody Course example) throws ResourceAlreadyExists {
        return courseCuder.createCourse(example);
    }

    @GetMapping("/courses/{courseId}")
    public Course getCourse(@PathVariable Long courseId) throws ResourceNotFound {
        return courseFinder.getCourseById(courseId);
    }

    @DeleteMapping("/courses/{courseId}")
    public ResponseEntity<?> deleteCourse(@PathVariable Long courseId) throws ResourceNotFound {
        courseCuder.removeCourse(courseId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/courses/{courseId}/post")
    public CoursePost createCoursePost(@PathVariable Long courseId, @RequestBody CoursePost exampleCoursePost) throws ResourceNotFound {
        Course course = courseFinder.getCourseById(courseId);
        return courseCuder.createCoursePost(course, exampleCoursePost);
    }

    @PostMapping("/courses/{courseId}/students/{studentId}")
    public ResponseEntity<?> assignStudentToCourse(@PathVariable Long courseId, @PathVariable Long studentId) throws ResourceNotFound, StudentAlreadyAssignedToCourse {
        Student student = studentFinder.getStudentById(studentId);
        Course course = courseFinder.getCourseById(courseId);
        courseAssigner.assignStudentToCourse(course, student);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/courses/{courseId}/students/{studentId}")
    public ResponseEntity<?> removeStudentFromCourse(@PathVariable Long courseId, @PathVariable Long studentId) throws ResourceNotFound, StudentNotAssignedToCourse {
        Student student = studentFinder.getStudentById(studentId);
        Course course = courseFinder.getCourseById(courseId);
        courseAssigner.removeStudentFromCourse(course, student);
        return ResponseEntity.ok().build();
    }
}
