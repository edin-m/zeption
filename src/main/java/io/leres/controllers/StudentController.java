package io.leres.controllers;

import io.leres.entities.Person;
import io.leres.entities.Student;
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
        studentCuder.removeStudent(studentId);
        return ResponseEntity.ok().build();
    }

//    @RequestMapping(method = RequestMethod.GET, path = "/students")
//    public List<Student> getStudents() {
//        return studentRetriever.getAllStudents();
//    }
//
//    @RequestMapping(method = RequestMethod.GET, path = "/students/{id}")
//    public Student getStudentById(@PathVariable("id") Long id) throws StudentNotFound {
//        return studentRetriever.getStudentById(id);
//    }
//
//    @RequestMapping(method = RequestMethod.POST, path = "/students")
//    public Student createStudent(@RequestBody Student student) throws StudentAlreadyExists {
//        return studentCreator.createStudent(student);
//    }
//
//    @RequestMapping(method = RequestMethod.PUT, path = "/students/{id}")
//    public Student updateStudent(@RequestBody Student student, @PathVariable("id") long studentId)
//            throws StudentNotFound, StudentIdMismatch {
//        if (studentId != student.getId()) {
//            throw new StudentIdMismatch(student, studentId);
//        }
//        return studentUpdater.updateStudent(student);
//    }

}
