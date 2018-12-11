package io.leres.students.repo;

import io.leres.students.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {

    Student findByPerson_SocialId(String socialId);
}
