package io.leres.curriculums.services;

import io.leres.classes.ClassManager;
import io.leres.curriculums.repo.CurriculumEntryRepository;
import io.leres.entities.CurriculumEntry;
import io.leres.entities.Teacher;
import io.leres.entities.UniClass;
import org.springframework.stereotype.Service;

@Service
class CurriculumServiceImpl implements CurriculumService {

    private CurriculumEntryRepository curriculumEntryRepository;
    private ClassManager classManager;

    CurriculumServiceImpl(CurriculumEntryRepository curriculumEntryRepository, ClassManager classManager) {
        this.curriculumEntryRepository = curriculumEntryRepository;
        this.classManager = classManager;
    }

    @Override
    public void addTextMessageToCurriculum(Teacher teacher, UniClass uniClass, int weekOfCurriculum, String description) {
        CurriculumEntry entry = new CurriculumEntry(uniClass, teacher, weekOfCurriculum, description, "");

        uniClass.addEntry(entry);

        classManager.saveExisting(uniClass);
        curriculumEntryRepository.save(entry);
    }
}
