package io.leres.students.repo;

import io.leres.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {

    Student findByPersonData_SocialId(String socialId);

}
