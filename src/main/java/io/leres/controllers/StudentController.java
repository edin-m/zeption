package io.leres.controllers;

import io.leres.entities.Person;
import io.leres.students.Student;
import io.leres.exceptions.ResourceAlreadyExists;
import io.leres.exceptions.ResourceNotFound;
import io.leres.students.StudentCuder;
import io.leres.students.StudentFinder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class StudentController {

    private StudentFinder studentFinder;
    private StudentCuder studentCuder;

    public StudentController(StudentFinder studentFinder, StudentCuder studentCuder) {
        this.studentFinder = studentFinder;
        this.studentCuder = studentCuder;
    }

    @PostMapping("/students")
    public Student createStudent(@RequestBody Person person) throws ResourceAlreadyExists {
        return studentCuder.createStudent(person);
    }

    @PutMapping("/students/{studentId}/person")
    public Student updateStudentPersonData(@PathVariable Long studentId, @RequestBody Person person) throws ResourceNotFound {
        return studentCuder.updateStudentPerson(studentId, person);
    }

    @GetMapping("/students/{studentId}")
    public Student getStudent(@PathVariable("studentId") Long studentId) throws ResourceNotFound {
        return studentFinder.getStudentById(studentId);
    }

    @DeleteMapping("/students/{studentId}")
    public ResponseEntity<?> deleteStudent(@PathVariable("studentId") Long studentId) throws ResourceNotFound {
        Student student = studentFinder.getStudentById(studentId);
        studentCuder.removeStudent(student);
        return ResponseEntity.ok().build();
    }

}
