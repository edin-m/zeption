package io.leres.teachers.repo;

import io.leres.teachers.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {

    Teacher findByPerson_SocialId(String socialId);

    int countByPerson_SocialId(String socialId);

}
