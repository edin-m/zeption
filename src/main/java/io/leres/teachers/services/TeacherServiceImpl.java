package io.leres.teachers.services;

import io.leres.teachers.repo.TeacherRepository;
import org.springframework.stereotype.Service;

@Service
class TeacherServiceImpl implements TeacherService {

    private TeacherRepository teacherRepository;

    public TeacherServiceImpl(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }


}
