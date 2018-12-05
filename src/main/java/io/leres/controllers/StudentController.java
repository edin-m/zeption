package io.leres.controllers;

import io.leres.entities.Student;
import io.leres.students.StudentCreator;
import io.leres.students.StudentRetriever;
import io.leres.students.StudentUpdater;
import io.leres.students.exceptions.StudentAlreadyExists;
import io.leres.students.exceptions.StudentNotFound;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {

    private StudentRetriever studentRetriever;
    private StudentUpdater studentUpdater;
    private StudentCreator studentCreator;

    public StudentController(StudentRetriever studentRetriever, StudentUpdater studentUpdater,
                             StudentCreator studentCreator) {
        this.studentRetriever = studentRetriever;
        this.studentUpdater = studentUpdater;
        this.studentCreator = studentCreator;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/students")
    public List<Student> getStudents() {
        return studentRetriever.getAllStudents();
    }

    @RequestMapping(method = RequestMethod.GET, path = "/students/{id}")
    public Student getStudentById(@PathVariable("id") Long id) throws StudentNotFound {
        return studentRetriever.getStudentById(id);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/students")
    public Student createStudent(@RequestBody Student student) throws StudentAlreadyExists {
        return studentCreator.createStudent(student);
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/students/{id}")
    public Student updateStudent(@RequestBody Student student) throws StudentNotFound {
        return studentUpdater.updateStudent(student);
    }

}
