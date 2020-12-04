package com.civil.registration.backend.Models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "person")
public class Person {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String dni;
  private String fullname;
  private String email;
  private String birthdate;
  private String age;
  private String gender;

  public Long getId() {

    return this.id;

  }

  public String getDni() {

    return this.dni;

  }

  public String getFullname() {

    return this.fullname;

  }

  public String getEmail() {

    return this.email;

  }

  public String getBirthdate() {

    return this.birthdate;

  }

  public String getAge() {

    return this.age;

  }

  public String getGender() {

    return this.gender;

  }

  // utils

  public static Boolean validateDNI(String dni) {

    return dni.matches("[0-9]{8}");

  }

  public static Boolean validateEmail(String email) {

    return email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");

  }

}