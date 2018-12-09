package io.leres.controllers;

import io.leres.classes.ClassFinder;
import io.leres.classes.UniClassCuder;
import io.leres.entities.CurriculumEntry;
import io.leres.entities.UniClass;
import io.leres.exceptions.ResourceAlreadyExists;
import io.leres.exceptions.ResourceNotFound;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
public class CurriculumController {

    private UniClassCuder uniClassCuder;
    private ClassFinder uniClassFinder;

    public CurriculumController(UniClassCuder uniClassCuder, ClassFinder uniClassFinder) {
        this.uniClassCuder = uniClassCuder;
        this.uniClassFinder = uniClassFinder;
    }

//    private TeacherFinder teacherFinder;
//    private ClassFinder classFinder;
//    private CurriculumPoster curriculumPoster;

//    public CurriculumController(TeacherFinder teacherFinder, ClassFinder classFinder, CurriculumPoster curriculumPoster) {
//        this.teacherFinder = teacherFinder;
//        this.classFinder = classFinder;
//        this.curriculumPoster = curriculumPoster;
//    }

    @PostMapping("/uni-classes")
    public UniClass createUniClass(@RequestBody UniClass example) throws ResourceAlreadyExists {
        return uniClassCuder.createUniClass(example);
    }

    @PutMapping("/uni-classes/{uniClassId}")
    public UniClass updateUniClass(@PathVariable Long uniClassId, @RequestBody UniClass uniClass) throws ResourceNotFound {
        return uniClassCuder.update(uniClassId, uniClass);
    }

    @DeleteMapping("/uni-classes/{uniClassId}")
    public ResponseEntity<?> removeUniClass(@PathVariable Long uniClassId) throws ResourceNotFound {
        uniClassCuder.remove(uniClassId);
        return ResponseEntity.ok().build();
    }



//    @RequestMapping(method = RequestMethod.POST, path = "/curriculums")
//    public void postCurriculumEntry(@RequestBody CurriculumEntryRequestModel requestModel) throws TeacherNotFound, UniClassNotFound {
//        Teacher teacher = teacherFinder.getTeacherById(requestModel.getTeacherId());
//        UniClass uniClass = classFinder.getUniClassForId(requestModel.getUniClassId());
//        curriculumPoster.addTextMessageToCurriculum(teacher, uniClass, requestModel.getWeekOfCurriculum(), requestModel.getDescription());
//    }
//
//    @RequestMapping(method = RequestMethod.GET, path = "/curriculums/{uniClassId}")
//    public List<CurriculumEntry> getEntriesForUniClass(@PathVariable("uniClassId") long uniClassId) throws NotImplemented {
//        throw new NotImplemented("GET: /curriculums/{uniClassId}");
//    }
}
