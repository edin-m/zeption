package io.leres.classes.services;

import io.leres.classes.Enrolement;
import io.leres.classes.exceptions.StudentAlreadyEnrolled;
import io.leres.classes.exceptions.UniClassNotFound;
import io.leres.classes.repo.UniClassRepository;
import io.leres.entities.Student;
import io.leres.entities.Teacher;
import io.leres.entities.UniClass;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
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
    public int calculateWeekOfClass(UniClass uniClass, Instant forTime) {
        ZonedDateTime start = uniClass.getStartDate().atZone(ZoneId.of("UTC"));
        start = start.with(DayOfWeek.MONDAY);
        ZonedDateTime atTime = forTime.atZone(ZoneId.of("UTC"));

        long weeks = ChronoUnit.WEEKS.between(start.toLocalDate(), atTime.toLocalDate());
        return Math.toIntExact(weeks + 1);
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
