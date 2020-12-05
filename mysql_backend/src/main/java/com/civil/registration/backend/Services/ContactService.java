package com.civil.registration.backend.Services;

import com.civil.registration.backend.Models.Contact;
import com.civil.registration.backend.Repositories.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ContactService {

  @Autowired
  private ContactRepository contactRepository;

  public List<Contact> readAllByPerson(Long personId) {

    List<Contact> contactList = new ArrayList<>();

    contactRepository.findAll().forEach(contactList::add);

    contactList.removeIf(contact -> (contact.getPersonId() != personId));

    return contactList;

  }

  public Contact readOne(Long id) {

    return contactRepository.findById(id).get();

  }

  public Contact createOrUpdate(Contact contact) {

    return contactRepository.save(contact);

  }

  public void deleteById(Long contactId) {

    contactRepository.deleteById(contactId);

  }

}
