package com.civil.registration.backend.Controllers;

import java.util.List;

import com.civil.registration.backend.DataAccessObject.ContactDAO;
import com.civil.registration.backend.DomainModels.Contact;
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
public class ContactController {

  @Autowired
  private ContactDAO contactDAO;

  @GetMapping("/contact/get/all/by/person/{personId}")
  public ResponseEntity<String> getContactList(@PathVariable(name = "personId") int personId) {

    List<Contact> contactList = contactDAO.readAllByPerson(personId);

    return ResponseEntity.ok(new Gson().toJson(contactList));

  }

  @GetMapping("/contact/get/one/{id}")
  public ResponseEntity<String> getContact(@PathVariable(name = "id") int id) {

    Contact contact = contactDAO.read(id);

    return ResponseEntity.ok(new Gson().toJson(contact));

  }

  @PostMapping("/contact/add")
  public ResponseEntity<String> addContact(@Validated @RequestBody Contact contact) {

    int result = contactDAO.create(contact);

    return ResponseEntity.ok(new Gson().toJson(result));

  }

  @PostMapping(value = "/contact/update")
  public ResponseEntity<String> updateContact(@Validated @RequestBody Contact contact) {

    int result = contactDAO.update(contact);

    return ResponseEntity.ok(new Gson().toJson(result));

  }

  @DeleteMapping("/contact/delete/{id}")
  public ResponseEntity<String> deleteContact(@PathVariable(name = "id") int id) {

    int result = contactDAO.delete(id);

    return ResponseEntity.ok(new Gson().toJson(result));

  }

}