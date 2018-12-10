package io.leres.students.services;

import io.leres.entities.Person;
import io.leres.entities.Student;
import io.leres.exceptions.ResourceAlreadyExists;
import io.leres.exceptions.ResourceNotFound;
import io.leres.students.repo.StudentRepository;
import org.springframework.stereotype.Service;

@Service
class StudentServiceImpl implements StudentService {

    private StudentRepository studentRepository;

    StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student createStudent(Person person) throws ResourceAlreadyExists {
        Student student = studentRepository.findByPerson_SocialId(person.getSocialId());

        if (student != null) {
            throw new ResourceAlreadyExists(Student.class, person.getSocialId());
        }

        student = new Student();
        student.setPerson(person);

        return studentRepository.save(student);
    }

    @Override
    public Student updateStudentPerson(long studentId, Person person) throws ResourceNotFound {
        Student student = getStudentById(studentId);

        Person studentPerson = student.getPerson();
        studentPerson.setAddress(person.getAddress());
        studentPerson.setSocialId(person.getSocialId());
        studentPerson.setDateOfBirth(person.getDateOfBirth());
        studentPerson.setEmail(person.getEmail());
        studentPerson.setFirstName(person.getFirstName());
        studentPerson.setMiddleName(person.getMiddleName());
        studentPerson.setLastName(person.getLastName());
        studentPerson.setGender(person.getGender());
        studentPerson.setPhoneNumber(person.getPhoneNumber());

        return studentRepository.save(student);
    }

    @Override
    public void removeStudent(long studentId) throws ResourceNotFound {
        if (!studentRepository.existsById(studentId)) {
            throw new ResourceNotFound(Student.class, studentId);
        }

        studentRepository.deleteById(studentId);
    }

    @Override
    public Student getStudentById(long studentId) throws ResourceNotFound {
        return studentRepository.findById(studentId).orElseThrow(() -> new ResourceNotFound(Student.class, studentId));
    }
}
