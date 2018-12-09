package io.leres.controllers;

import io.leres.classes.ClassFinder;
import io.leres.classes.exceptions.UniClassNotFound;
import io.leres.controllers.model.CurriculumEntryRequestModel;
import io.leres.curriculums.CurriculumPoster;
import io.leres.entities.CurriculumEntry;
import io.leres.entities.Teacher;
import io.leres.entities.UniClass;
import io.leres.exceptions.NotImplemented;
import io.leres.teachers.TeacherFinder;
import io.leres.teachers.exceptions.TeacherNotFound;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CurriculumController {

    private TeacherFinder teacherFinder;
    private ClassFinder classFinder;
    private CurriculumPoster curriculumPoster;

    public CurriculumController(TeacherFinder teacherFinder, ClassFinder classFinder, CurriculumPoster curriculumPoster) {
        this.teacherFinder = teacherFinder;
        this.classFinder = classFinder;
        this.curriculumPoster = curriculumPoster;
    }

    @RequestMapping(method = RequestMethod.POST, path = "/curriculums")
    public void postCurriculumEntry(@RequestBody CurriculumEntryRequestModel requestModel) throws TeacherNotFound, UniClassNotFound {
        Teacher teacher = teacherFinder.getTeacherById(requestModel.getTeacherId());
        UniClass uniClass = classFinder.getUniClassForId(requestModel.getUniClassId());
        curriculumPoster.addTextMessageToCurriculum(teacher, uniClass, requestModel.getWeekOfCurriculum(), requestModel.getDescription());
    }

    @RequestMapping(method = RequestMethod.GET, path = "/curriculums/{uniClassId}")
    public List<CurriculumEntry> getEntriesForUniClass(@PathVariable("uniClassId") long uniClassId) throws NotImplemented {
        throw new NotImplemented("GET: /curriculums/{uniClassId}");
    }
}
