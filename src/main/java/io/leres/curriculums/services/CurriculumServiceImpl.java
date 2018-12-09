package io.leres.curriculums.services;

import io.leres.curriculums.repo.CurriculumEntryRepository;
import org.springframework.stereotype.Service;

@Service
class CurriculumServiceImpl implements CurriculumService {

    private CurriculumEntryRepository curriculumEntryRepository;
//    private ClassCuder classCruder;

    CurriculumServiceImpl(CurriculumEntryRepository curriculumEntryRepository) {
        this.curriculumEntryRepository = curriculumEntryRepository;
    }

//    @Override
//    public void addTextMessageToCurriculum(Teacher teacher, UniClass uniClass, int weekOfCurriculum, String description) {
////        CurriculumEntry entry = new CurriculumEntry(uniClass, teacher, weekOfCurriculum, description, "");
////
////        uniClass.addEntry(entry);
////
////        classCruder.saveExisting(uniClass);
////        curriculumEntryRepository.save(entry);
//    }
}
