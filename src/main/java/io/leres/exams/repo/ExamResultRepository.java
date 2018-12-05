package io.leres.exams.repo;

import io.leres.entities.ExamResult;
import org.springframework.data.repository.CrudRepository;

public interface ExamResultRepository extends CrudRepository<ExamResult, Long> {
}
