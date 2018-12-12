package io.leres.controllers;

import io.leres.entities.Person;
import io.leres.exceptions.ResourceAlreadyExists;
import io.leres.exceptions.ResourceNotFound;
import io.leres.teachers.Teacher;
import io.leres.teachers.TeacherCuder;
import io.leres.teachers.TeacherFinder;
import org.springframework.http.ResponseEntity;
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
    public Teacher createTeacher(@RequestBody Person person) throws ResourceAlreadyExists {
        return teacherCuder.createTeacher(person);
    }

    @GetMapping("/teachers/{teacherId}")
    public Teacher getTeacher(@PathVariable Long teacherId) throws ResourceNotFound {
        return teacherFinder.getTeacherById(teacherId);
    }

    @DeleteMapping("/teachers/{teacherId}")
    public ResponseEntity<?> deleteTeacher(@PathVariable Long teacherId) throws ResourceNotFound {
        Teacher teacher = teacherFinder.getTeacherById(teacherId);
        teacherCuder.removeTeacher(teacher);
        return ResponseEntity.ok().build();
    }
}
