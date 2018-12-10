package io.leres.courses.repo;

import io.leres.courses.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
    int countByName(String name);
}
