package io.leres.students.repo;

import io.leres.entities.Student;
import org.springframework.data.repository.CrudRepository;

public interface StudentRepository extends CrudRepository<Student, Long> {

    Student findByPersonData_SocialId(String name);

}
