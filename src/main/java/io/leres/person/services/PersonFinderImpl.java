package io.leres.person.services;

import io.leres.person.PersonFinder;
import io.leres.person.repo.PersonRepository;

public class PersonFinderImpl implements PersonFinder {

    private PersonRepository personRepository;

    public PersonFinderImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public boolean existsSocialId(String socialId) {
        return personRepository.countBySocialId(socialId) > 0;
    }
}
