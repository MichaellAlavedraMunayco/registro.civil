package com.civil.registration.backend.Controllers;

import java.util.List;

import com.civil.registration.backend.Models.Contact;
import com.civil.registration.backend.Services.ContactService;
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
public class ContactController {

  private ContactService contactService;

  @Autowired
  public void setContactService(ContactService contactService) {
    this.contactService = contactService;
  }

  @GetMapping("/contact/get/all/by/person/{personId}")
  public ResponseEntity<String> getContactList(@PathVariable(name = "personId") Long personId) {

    List<Contact> contactList = contactService.readAllByPerson(personId);

    return ResponseEntity.ok(new Gson().toJson(contactList));

  }

  @GetMapping("/contact/get/one/{id}")
  public ResponseEntity<String> getContact(@PathVariable(name = "id") Long id) {

    Contact contact = contactService.readOne(id);

    return ResponseEntity.ok(new Gson().toJson(contact));

  }

  @PostMapping("/contact/add")
  public ResponseEntity<String> addContact(@Validated @RequestBody Contact contact) {

    Contact savedContact = contactService.createOrUpdate(contact);

    return ResponseEntity.ok(new Gson().toJson(savedContact));

  }

  @PostMapping("/contact/update")
  public ResponseEntity<String> updateContact(@Validated @RequestBody Contact contact) {

    Contact savedContact = contactService.createOrUpdate(contact);

    return ResponseEntity.ok(new Gson().toJson(savedContact));

  }

  @PostMapping("/contact/delete")
  public ResponseEntity<String> delete(@Validated @RequestBody Contact contact) {

    contactService.delete(contact);

    return ResponseEntity.ok(new Gson().toJson(1));

  }

}
