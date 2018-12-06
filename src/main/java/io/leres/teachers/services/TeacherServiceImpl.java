package io.leres.teachers.services;

import io.leres.entities.Teacher;
import io.leres.teachers.exceptions.TeacherNotFound;
import io.leres.teachers.repo.TeacherRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
class TeacherServiceImpl implements TeacherService {

    private TeacherRepository teacherRepository;

    public TeacherServiceImpl(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    @Override
    public Teacher getTeacherById(long teacherId) throws TeacherNotFound {
        Optional<Teacher> teacher = teacherRepository.findById(teacherId);

        if (!teacher.isPresent()) {
            throw new TeacherNotFound(teacherId);
        }

        return teacher.get();
    }
}
