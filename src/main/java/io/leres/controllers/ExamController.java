package io.leres.controllers;

import io.leres.classes.ClassFinder;
import io.leres.classes.exceptions.UniClassNotFound;
import io.leres.controllers.model.ExamRequestModel;
import io.leres.entities.Exam;
import io.leres.entities.Teacher;
import io.leres.entities.UniClass;
import io.leres.exams.ExamScheduler;
import io.leres.teachers.TeacherFinder;
import io.leres.teachers.exceptions.TeacherNotFound;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExamController {

    private TeacherFinder teacherFinder;
    private ClassFinder classFinder;
    private ExamScheduler examScheduler;

    public ExamController(TeacherFinder teacherFinder, ClassFinder classFinder, ExamScheduler examScheduler) {
        this.teacherFinder = teacherFinder;
        this.classFinder = classFinder;
        this.examScheduler = examScheduler;
    }

    @RequestMapping(method = RequestMethod.POST, path = "/exams")
    public Exam createNewExam(@RequestBody ExamRequestModel examRequestModel) throws TeacherNotFound, UniClassNotFound {
        Teacher teacher = teacherFinder.getTeacherById(examRequestModel.getTeacherId());
        UniClass uniClass = classFinder.getUniClassForId(examRequestModel.getUniClassId());
        return examScheduler.scheduleExam(teacher, examRequestModel.getTimeAt(), uniClass);
    }
}
