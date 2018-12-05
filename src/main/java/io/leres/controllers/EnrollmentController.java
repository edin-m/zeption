package io.leres.controllers;

import io.leres.classes.ClassFinder;
import io.leres.classes.EnrollementService;
import io.leres.classes.Enrolement;
import io.leres.classes.exceptions.StudentAlreadyEnrolled;
import io.leres.classes.exceptions.UniClassNotFound;
import io.leres.entities.Student;
import io.leres.entities.UniClass;
import io.leres.students.StudentRetriever;
import io.leres.students.exceptions.StudentNotFound;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EnrollmentController {

    private StudentRetriever studentRetriever;
    private EnrollementService enrolementService;
    private ClassFinder classFinder;

    public EnrollmentController(StudentRetriever studentRetriever, EnrollementService enrolementService, ClassFinder classFinder) {
        this.studentRetriever = studentRetriever;
        this.enrolementService = enrolementService;
        this.classFinder = classFinder;
    }

    @RequestMapping(method = RequestMethod.POST, path = "/enrollment")
    public Enrolement enrollStudentToUniClass(@RequestParam("studentId") long studentId,
                                              @RequestParam("uniClassId") long uniClassId)
            throws StudentNotFound, UniClassNotFound, StudentAlreadyEnrolled {
        Student student = studentRetriever.getStudentById(studentId);
        UniClass uniClass = classFinder.getUniClassForId(uniClassId);
        return enrolementService.enrollStudentToClass(student, uniClass);
    }
}
