package io.leres.person.repo;

import io.leres.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {

    long countBySocialId(String socialId);
}
