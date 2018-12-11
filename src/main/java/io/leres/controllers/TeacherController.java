package io.leres.controllers;

import io.leres.entities.Person;
import io.leres.exceptions.NotImplemented;
import io.leres.teachers.TeacherCuder;
import io.leres.teachers.TeacherFinder;
import org.springframework.web.bind.annotation.*;

@RestController
public class TeacherController {

    private TeacherFinder teacherFinder;
    private TeacherCuder teacherCuder;

    TeacherController(TeacherFinder teacherFinder, TeacherCuder teacherCuder) {
        this.teacherCuder = teacherCuder;
        this.teacherFinder = teacherFinder;
    }

    @PostMapping("/teachers")
    public void createTeacher(@RequestBody Person person) {
        throw new NotImplemented();
    }

    @DeleteMapping("/teachers/{teacherId}")
    public void deleteTeacher(@PathVariable Long teacherId) {
        throw new NotImplemented();
    }
}
