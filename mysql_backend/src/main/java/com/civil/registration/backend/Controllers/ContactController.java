package com.civil.registration.backend.Controllers;

import java.util.List;

import com.civil.registration.backend.Models.Contact;
import com.civil.registration.backend.Services.ContactService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/contact")
public class ContactController {

  @Autowired
  private ContactService contactService;

  @RequestMapping(value = "/get/all/by/person/{personId}", method = RequestMethod.GET)
  public List<Contact> getContactListByPerson(@PathVariable(name = "personId") Long personId) {

    return this.contactService.readAllByPerson(personId);

  }

  @RequestMapping(value = "/get/one/{id}", method = RequestMethod.GET)
  public Contact getContact(@PathVariable(name = "id") Long id) {

    return this.contactService.readOne(id);

  }

  @RequestMapping(value = "/add", method = RequestMethod.POST)
  public Contact addContact(@RequestBody Contact contact) {

    return this.contactService.createOrUpdate(contact);

  }

  @RequestMapping(value = "/update", method = RequestMethod.PUT)
  public Contact updateContact(@RequestBody Contact contact) {

    return this.contactService.createOrUpdate(contact);

  }

  @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
  public void delete(@PathVariable(name = "id") Long id) {

    this.contactService.deleteById(id);

  }

}
