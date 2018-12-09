package io.leres.teachers.repo;

import io.leres.entities.Teacher;
import org.springframework.data.repository.CrudRepository;

public interface TeacherRepository extends CrudRepository<Teacher, Long> {

    Teacher findByPersonData_SocialId(String socialId);

}
