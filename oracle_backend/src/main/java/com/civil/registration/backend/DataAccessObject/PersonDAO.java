package com.civil.registration.backend.DataAccessObject;

import java.util.List;

import com.civil.registration.backend.DomainModels.Person;

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
public class PersonDAO {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  // CREATE
  public int create(Person person) {

    if (Person.validateDNI(person.getDni()) && Person.validateEmail(person.getEmail())) {

      SimpleJdbcInsert insertActor = new SimpleJdbcInsert(jdbcTemplate);

      insertActor.withTableName("person").usingColumns("dni", "fullname", "email", "birthdate", "age", "gender");

      BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(person);

      return insertActor.execute(param);

    } else {

      return 0;

    }

  }

  // READ
  public List<Person> readAll() {

    String query = "SELECT * FROM PERSON";

    List<Person> personList = jdbcTemplate.query(query, BeanPropertyRowMapper.newInstance(Person.class));

    return personList;

  }

  public Person read(int id) {

    String query = "SELECT * FROM PERSON WHERE id = ?";

    Object[] args = { id };

    Person person = jdbcTemplate.queryForObject(query, args, BeanPropertyRowMapper.newInstance(Person.class));

    return person;

  }

  // UPDATE
  public int update(Person person) {

    if (Person.validateDNI(person.getDni()) && Person.validateEmail(person.getEmail())) {

      String query = "UPDATE PERSON SET dni=:dni, fullname=:fullname, email=:email, birthdate=:birthdate, age=:age, gender=:gender WHERE id=:id";

      BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(person);

      NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(jdbcTemplate);

      return template.update(query, param);

    } else {

      return 0;

    }

  }

  // DELETE
  public int delete(int id) {

    String query = "DELETE FROM PERSON WHERE id = ?";

    return jdbcTemplate.update(query, id);

  }

}
