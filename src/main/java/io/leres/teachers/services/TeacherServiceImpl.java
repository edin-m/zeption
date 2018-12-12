package io.leres.teachers.services;

import io.leres.entities.Person;
import io.leres.exceptions.ResourceAlreadyExists;
import io.leres.exceptions.ResourceNotFound;
import io.leres.teachers.Teacher;
import io.leres.teachers.repo.TeacherRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
class TeacherServiceImpl implements TeacherService {

    private TeacherRepository teacherRepository;

    TeacherServiceImpl(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    @Override
    public Teacher createTeacher(Person person) throws ResourceAlreadyExists {
        if (teacherRepository.countByPerson_SocialId(person.getSocialId()) > 0) {
            throw new ResourceAlreadyExists(Teacher.class, person.getSocialId());
        }

        Teacher teacher = new Teacher();
        teacher.setPerson(person);

        return teacherRepository.save(teacher);
    }

    @Override
    public void removeTeacher(Teacher teacher) {
        teacher.getCourses().forEach(course -> course.removeTeacher(teacher));
        teacherRepository.delete(teacher);
    }

    @Override
    public Teacher getTeacherById(long teacherId) throws ResourceNotFound {
        Optional<Teacher> teacher = teacherRepository.findById(teacherId);

        if (!teacher.isPresent()) {
            throw new ResourceNotFound(Teacher.class, teacherId);
        }

        return teacher.get();
    }
}
