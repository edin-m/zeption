package io.leres.controllers;

import io.leres.classes.ClassFinder;
import io.leres.classes.exceptions.UniClassNotFound;
import io.leres.controllers.model.ExamRequestModel;
import io.leres.entities.Exam;
import io.leres.entities.ExamResult;
import io.leres.entities.Teacher;
import io.leres.entities.UniClass;
import io.leres.exams.ExamFinder;
import io.leres.exams.ExamScheduler;
import io.leres.exams.exceptions.ExamNotFound;
import io.leres.teachers.TeacherFinder;
import io.leres.teachers.exceptions.TeacherNotFound;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ExamController {

    private TeacherFinder teacherFinder;
    private ClassFinder classFinder;
    private ExamScheduler examScheduler;
    private ExamFinder examFinder;

    public ExamController(TeacherFinder teacherFinder, ClassFinder classFinder, ExamScheduler examScheduler, ExamFinder examFinder) {
        this.teacherFinder = teacherFinder;
        this.classFinder = classFinder;
        this.examScheduler = examScheduler;
        this.examFinder = examFinder;
    }

    @RequestMapping(method = RequestMethod.POST, path = "/exams")
    public Exam createNewExam(@RequestBody ExamRequestModel examRequestModel) throws TeacherNotFound, UniClassNotFound {
        Teacher teacher = teacherFinder.getTeacherById(examRequestModel.getTeacherId());
        UniClass uniClass = classFinder.getUniClassForId(examRequestModel.getUniClassId());
        return examScheduler.scheduleExam(teacher, examRequestModel.getTimeAt(), uniClass);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/exams/{examId}")
    public Exam getExam(@PathVariable("examId") long examId) throws ExamNotFound {
        return examFinder.getExamById(examId);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/exams/{examId}/results")
    public List<ExamResult> getExamResults(@PathVariable("examId") long examId) throws ExamNotFound {
        Exam exam = examFinder.getExamById(examId);
        return new ArrayList<>(exam.getResults());
    }
}
