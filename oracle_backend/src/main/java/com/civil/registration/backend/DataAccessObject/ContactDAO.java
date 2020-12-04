package com.civil.registration.backend.DataAccessObject;

import java.util.List;

import com.civil.registration.backend.DomainModels.Contact;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class ContactDAO {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  // CREATE
  public int create(Contact contact) {

    SimpleJdbcInsert insertActor = new SimpleJdbcInsert(jdbcTemplate);

    insertActor.withTableName("contact").usingColumns("address", "homephone", "phone", "personId");

    BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(contact);

    return insertActor.execute(param);

  }

  // READ
  public List<Contact> readAllByPerson(int personId) {

    String query = "SELECT * FROM CONTACT WHERE personId = ?";

    Object[] args = { personId };

    List<Contact> contactList = jdbcTemplate.query(query, args, BeanPropertyRowMapper.newInstance(Contact.class));

    return contactList;

  }

  public Contact read(int id) {

    String query = "SELECT * FROM CONTACT WHERE id = ?";

    Object[] args = { id };

    Contact contact = jdbcTemplate.queryForObject(query, args, BeanPropertyRowMapper.newInstance(Contact.class));

    return contact;

  }

  // UPDATE
  public int update(Contact contact) {

    String query = "UPDATE CONTACT SET address=:address, homephone=:homephone, phone=:phone, personId=:personId WHERE id=:id";

    BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(contact);

    NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(jdbcTemplate);

    return template.update(query, param);

  }

  // DELETE
  public int delete(int id) {

    String query = "DELETE FROM CONTACT WHERE id = ?";

    return jdbcTemplate.update(query, id);

  }

}
