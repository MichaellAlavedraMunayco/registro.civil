package com.civil.registration.backend.Repositories;

import com.civil.registration.backend.Models.Person;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person, Long> {
}
