package com.civil.registration.backend.Controllers;

import java.util.List;

import com.civil.registration.backend.Models.Person;
import com.civil.registration.backend.Services.PersonService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/person")
public class PersonController {

  @Autowired
  PersonService personService;

  @RequestMapping(value = "/get/all", method = RequestMethod.GET)
  public List<Person> getPersonList() {

    return this.personService.readAll();

  }

  @RequestMapping(value = "/get/one/{id}", method = RequestMethod.GET)
  public Person getPerson(@PathVariable(name = "id") Long id) {

    return this.personService.readOne(id);

  }

  @RequestMapping(value = "/add", method = RequestMethod.POST)
  public Person addPerson(@RequestBody Person person) {

    return this.personService.createOrUpdate(person);
  }

  @RequestMapping(value = "/update", method = RequestMethod.PUT)
  public Person updatePerson(@RequestBody Person person) {

    return this.personService.createOrUpdate(person);

  }

  @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
  public void delete(@PathVariable(name = "id") Long id) {

    this.personService.deleteById(id);

  }

}
