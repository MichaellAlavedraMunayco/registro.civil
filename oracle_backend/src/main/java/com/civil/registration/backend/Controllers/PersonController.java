package com.civil.registration.backend.Controllers;

import java.util.List;

import com.civil.registration.backend.DataAccessObject.PersonDAO;
import com.civil.registration.backend.DomainModels.Person;
import com.google.gson.Gson;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class PersonController {

  @Autowired
  private PersonDAO personDAO;

  @GetMapping("/person/get/all")
  public ResponseEntity<String> getPersonList() {

    List<Person> personList = personDAO.readAll();

    return ResponseEntity.ok(new Gson().toJson(personList));

  }

  @GetMapping("/person/get/one/{id}")
  public ResponseEntity<String> getPerson(@PathVariable(name = "id") int id) {

    Person person = personDAO.read(id);

    return ResponseEntity.ok(new Gson().toJson(person));

  }

  @PostMapping("/person/add")
  public ResponseEntity<String> addPerson(@Validated @RequestBody Person person) {

    int result = personDAO.create(person);

    return ResponseEntity.ok(new Gson().toJson(result));

  }

  @PostMapping(value = "/person/update")
  public ResponseEntity<String> updatePerson(@Validated @RequestBody Person person) {

    int result = personDAO.update(person);

    return ResponseEntity.ok(new Gson().toJson(result));

  }

  @DeleteMapping("/person/delete/{id}")
  public ResponseEntity<String> deletePerson(@PathVariable(name = "id") int id) {

    int result = personDAO.delete(id);

    return ResponseEntity.ok(new Gson().toJson(result));

  }

}