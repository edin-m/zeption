package io.leres.controllers;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class EnrollmentController {

//    private StudentRetriever studentRetriever;
//    private EnrollementService enrolementService;
//    private ClassFinder classFinder;
//
//    public EnrollmentController(StudentRetriever studentRetriever, EnrollementService enrolementService, ClassFinder classFinder) {
//        this.studentRetriever = studentRetriever;
//        this.enrolementService = enrolementService;
//        this.classFinder = classFinder;
//    }
//
//    @RequestMapping(method = RequestMethod.POST, path = "/enrollment")
//    public Enrolement enrollStudentToUniClass(@RequestParam("studentId") long studentId,
//                                              @RequestParam("uniClassId") long uniClassId)
//            throws StudentNotFound, UniClassNotFound, StudentAlreadyEnrolled {
//        Student student = studentRetriever.getStudentById(studentId);
//        UniClass uniClass = classFinder.getUniClassForId(uniClassId);
//        return enrolementService.enrollStudentToClass(student, uniClass);
//    }
}
