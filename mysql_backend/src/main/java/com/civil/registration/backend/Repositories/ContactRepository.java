package com.civil.registration.backend.Repositories;

import com.civil.registration.backend.Models.Contact;
import org.springframework.data.repository.CrudRepository;

public interface ContactRepository extends CrudRepository<Contact, Long> {
}
