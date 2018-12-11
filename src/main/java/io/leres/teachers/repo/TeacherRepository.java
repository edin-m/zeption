package io.leres.teachers.repo;

import io.leres.teachers.Teacher;
import org.springframework.data.repository.CrudRepository;

public interface TeacherRepository extends CrudRepository<Teacher, Long> {

    Teacher findByPerson_SocialId(String socialId);

}
