package io.leres.curriculums.repo;

import io.leres.entities.CurriculumEntry;
import org.springframework.data.repository.CrudRepository;

public interface CurriculumEntryRepository extends CrudRepository<CurriculumEntry, Long> {
}
