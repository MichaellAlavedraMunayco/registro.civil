package com.civil.registration.backend.Controllers;

import java.util.List;

import com.civil.registration.backend.Models.Person;
import com.civil.registration.backend.Services.PersonService;
import com.google.gson.Gson;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class PersonController {

  private PersonService personService;

  @Autowired
  public void setPersonService(PersonService personService) {
    this.personService = personService;
  }

  @GetMapping("/person/get/all")
  public ResponseEntity<String> getPersonList() {

    List<Person> personList = personService.readAll();

    return ResponseEntity.ok(new Gson().toJson(personList));

  }

  @GetMapping("/person/get/one/{id}")
  public ResponseEntity<String> getPerson(@PathVariable(name = "id") Long id) {

    Person person = personService.readOne(id);

    return ResponseEntity.ok(new Gson().toJson(person));

  }

  @PostMapping("/person/add")
  public ResponseEntity<String> addPerson(@Validated @RequestBody Person person) {

    Person savedPerson = personService.createOrUpdate(person);

    return ResponseEntity.ok(new Gson().toJson(savedPerson));

  }

  @PostMapping("/person/update")
  public ResponseEntity<String> updatePerson(@Validated @RequestBody Person person) {

    Person savedPerson = personService.createOrUpdate(person);

    if (savedPerson != null) {

      return ResponseEntity.ok(new Gson().toJson(savedPerson));

    } else {

      return ResponseEntity.ok(new Gson().toJson(0));

    }

  }

  @PostMapping("/person/delete")
  public ResponseEntity<String> delete(@Validated @RequestBody Person person) {

    personService.delete(person);

    return ResponseEntity.ok(new Gson().toJson(1));

  }

}
