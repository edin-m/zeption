package io.leres.controllers;

import io.leres.courses.*;
import io.leres.courses.exceptions.AlreadyAssignedToCourse;
import io.leres.courses.exceptions.NotAssignedToCourse;
import io.leres.exceptions.NotImplemented;
import io.leres.exceptions.ResourceAlreadyExists;
import io.leres.exceptions.ResourceNotFound;
import io.leres.students.Student;
import io.leres.students.StudentFinder;
import io.leres.teachers.Teacher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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
    public ResponseEntity<?> assignStudentToCourse(@PathVariable Long courseId, @PathVariable Long studentId) throws ResourceNotFound, AlreadyAssignedToCourse {
        Student student = studentFinder.getStudentById(studentId);
        Course course = courseFinder.getCourseById(courseId);
        courseAssigner.assignStudentToCourse(course, student);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/courses/{courseId}/students/{studentId}")
    public ResponseEntity<?> removeStudentFromCourse(@PathVariable Long courseId, @PathVariable Long studentId) throws ResourceNotFound, NotAssignedToCourse {
        Student student = studentFinder.getStudentById(studentId);
        Course course = courseFinder.getCourseById(courseId);
        courseAssigner.removeStudentFromCourse(course, student);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/courses/{courseId}/students")
    public List<Student> getStudentsInCourse(@PathVariable Long courseId) throws ResourceNotFound {
        Course course = courseFinder.getCourseById(courseId);
        return new ArrayList<>(course.getStudents());
    }

    @PostMapping("/courses/{courseId}/teachers/{teacherId}")
    public ResponseEntity<?> assignTeacherToCourse(@PathVariable Long courseId, @PathVariable Long teacherId) {
        throw new NotImplemented();
    }

    @DeleteMapping("/courses/{courseId}/teachers/{teacherId}")
    public ResponseEntity<?> removeTeacherFromCourse(@PathVariable Long courseId, @PathVariable Long teacherId) {
        throw new NotImplemented();
    }

    @GetMapping("/courses/{courseId}/teachers")
    public List<Teacher> getTeachersInCourse(@PathVariable Long courseId) throws ResourceNotFound {
        Course course = courseFinder.getCourseById(courseId);
        return new ArrayList<>(course.getTeachers());
    }
}
