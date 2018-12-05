package io.leres.students.services;

import io.leres.entities.Student;
import io.leres.students.exceptions.StudentAlreadyExists;
import io.leres.students.exceptions.StudentNotFound;
import io.leres.students.repo.StudentRepository;
import io.leres.util.ListUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
class StudentServiceImpl implements StudentService {

    private StudentRepository studentRepository;

    StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student createStudent(Student student) throws StudentAlreadyExists {
        Student found = studentRepository.findByPersonData_SocialId(student.getPersonData().getSocialId());

        if (found != null) {
            throw new StudentAlreadyExists();
        }

        return studentRepository.save(student);
    }

    @Override
    public List<Student> getAllStudents() {
        Iterable<Student> students = studentRepository.findAll();
        return ListUtils.iterableToList(students);
    }

    @Override
    public Student getStudentById(Long id) throws StudentNotFound {
        Optional<Student> student = studentRepository.findById(id);

        if (!student.isPresent()) {
            throw new StudentNotFound(String.format("Student under id: %s not found", id));
        }

        return student.get();
    }

    @Override
    public Student updateStudent(Student student) throws StudentNotFound {
        Student found = getStudentById(student.getId());

        found.setPersonData(student.getPersonData());

        return studentRepository.save(found);
    }
}
