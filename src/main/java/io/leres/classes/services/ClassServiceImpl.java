package io.leres.classes.services;

import io.leres.classes.repo.UniClassRepository;
import io.leres.entities.UniClass;
import io.leres.exceptions.ResourceAlreadyExists;
import io.leres.exceptions.ResourceNotFound;
import org.springframework.stereotype.Service;

@Service
class ClassServiceImpl implements ClassService {

    private UniClassRepository uniClassRepository;

    ClassServiceImpl(UniClassRepository uniClassRepository) {
        this.uniClassRepository = uniClassRepository;
    }

    @Override
    public UniClass createUniClass(UniClass example) throws ResourceAlreadyExists {
        UniClass uniClass = uniClassRepository.findByName(example.getName());

        if (uniClass != null) {
            throw new ResourceAlreadyExists(UniClass.class, example.getName());
        }

        return uniClassRepository.save(example);
    }

    @Override
    public UniClass update(long uniClassId, UniClass example) throws ResourceNotFound {
        UniClass uniClass = getById(uniClassId);

        uniClass.setName(example.getName());
        uniClass.setDescription(example.getDescription());

        return uniClass;
    }

    @Override
    public void remove(long uniClassId) throws ResourceNotFound {
        if (!uniClassRepository.existsById(uniClassId)) {
            throw new ResourceNotFound(UniClass.class, uniClassId);
        }

        uniClassRepository.deleteById(uniClassId);
    }

    private UniClass getById(long uniClassId) throws ResourceNotFound {
        return uniClassRepository.findById(uniClassId).orElseThrow(() -> new ResourceNotFound(UniClass.class, uniClassId));
    }


//    @Override
//    public Enrolement enrollStudentToClass(Student student, UniClass uniClass) throws StudentAlreadyEnrolled {
//        if (uniClass.isEnrolled(student)) {
//            throw new StudentAlreadyEnrolled(student, uniClass);
//        }
//
//        uniClass.addStudentToEnrolledStudents(student);
//        UniClass savedUniClass = uniClassRepository.save(uniClass);
//
//        return new Enrolement(student, savedUniClass);
//    }
//
//    @Override
//    public void assignTeacherToUniClass(Teacher teacher, UniClass uniClass) {
//        uniClass.setTeacher(teacher);
//        uniClassRepository.save(uniClass);
//    }
//
//    @Override
//    public UniClass getUniClassForId(long uniClassId) throws UniClassNotFound {
//        Optional<UniClass> uniClass = uniClassRepository.findById(uniClassId);
//
//        if (!uniClass.isPresent()) {
//            throw new UniClassNotFound(uniClassId);
//        }
//
//        return uniClass.get();
//    }
//
//    @Override
//    public int findWeekOfClass(UniClass uniClass, Instant forTime) {
//        ZonedDateTime start = uniClass.getStartDate().atZone(ZoneId.of("UTC"));
//        start = start.with(DayOfWeek.MONDAY);
//        ZonedDateTime atTime = forTime.atZone(ZoneId.of("UTC"));
//
//        long weeks = ChronoUnit.WEEKS.between(start.toLocalDate(), atTime.toLocalDate());
//        return Math.toIntExact(weeks + 1);
//    }
//
//    @Override
//    public UniClass createUniClass(String name, String description) {
//        UniClass uniClass = new UniClass(name, description);
//        return uniClassRepository.save(uniClass);
//    }
//
//    @Override
//    public void saveExisting(UniClass uniClass) {
//        uniClassRepository.save(uniClass);
//    }
//
//    @Override
//    public UniClass scheduleExam(UniClass uniClass, Instant dateStart, Instant dateEnd) {
//        uniClass.setStartDate(dateStart);
//        uniClass.setEndDate(dateEnd);
//        return uniClassRepository.save(uniClass);
//    }
}
