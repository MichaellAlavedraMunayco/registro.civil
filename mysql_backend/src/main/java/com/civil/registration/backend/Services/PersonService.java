package com.civil.registration.backend.Services;

import com.civil.registration.backend.Models.Person;
import com.civil.registration.backend.Repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonService {

  @Autowired
  private PersonRepository personRepository;

  public List<Person> readAll() {

    List<Person> personList = new ArrayList<>();

    personRepository.findAll().forEach(personList::add);

    return personList;

  }

  public Person readOne(Long id) {

    return personRepository.findById(id).get();

  }

  public Person createOrUpdate(Person person) {

    if (Person.validateDNI(person.getDni()) && Person.validateEmail(person.getEmail())) {

      Person newPerson = personRepository.save(person);

      return newPerson;

    } else {

      return null;

    }

  }

  public void delete(Person person) {

    personRepository.delete(person);

  }

}
