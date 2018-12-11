package io.leres.courses.repo;

import io.leres.courses.CoursePost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoursePostRepository extends JpaRepository<CoursePost, Long> {
}
