package io.leres.classes.services;

import io.leres.classes.Enrolement;
import io.leres.classes.exceptions.UniClassNotFound;
import io.leres.classes.exceptions.StudentAlreadyEnrolled;
import io.leres.classes.repo.UniClassRepository;
import io.leres.entities.Student;
import io.leres.entities.Teacher;
import io.leres.entities.UniClass;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
class ClassServiceImpl implements ClassService {

    private UniClassRepository uniClassRepository;

    ClassServiceImpl(UniClassRepository uniClassRepository) {
        this.uniClassRepository = uniClassRepository;
    }

    @Override
    public Enrolement enrollStudentToClass(Student student, UniClass uniClass) throws StudentAlreadyEnrolled {
        if (uniClass.isEnrolled(student)) {
            throw new StudentAlreadyEnrolled(student, uniClass);
        }

        uniClass.addStudentToEnrolledStudents(student);
        UniClass savedUniClass = uniClassRepository.save(uniClass);

        return new Enrolement(student, savedUniClass);
    }

    @Override
    public void assignTeacherToUniClass(Teacher teacher, UniClass uniClass) {
        uniClass.setTeacher(teacher);
        uniClassRepository.save(uniClass);
    }

    @Override
    public UniClass getUniClassForId(long uniClassId) throws UniClassNotFound {
        Optional<UniClass> uniClass = uniClassRepository.findById(uniClassId);

        if (!uniClass.isPresent()) {
            throw new UniClassNotFound(uniClassId);
        }

        return uniClass.get();
    }

    @Override
    public UniClass createUniClass(String name, String description) {
        UniClass uniClass = new UniClass(name, description);
        return uniClassRepository.save(uniClass);
    }

    @Override
    public void saveExisting(UniClass uniClass) {
        uniClassRepository.save(uniClass);
    }
}
