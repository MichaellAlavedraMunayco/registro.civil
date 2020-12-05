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

  public Person() {
  }

  public Person(Long id, String dni, String fullname, String email, String birthdate, String age, String gender) {

    this.id = id;
    this.dni = dni;
    this.fullname = fullname;
    this.email = email;
    this.birthdate = birthdate;
    this.age = age;
    this.gender = gender;

  }

  public Long getId() {

    return id;

  }

  public void setId(Long id) {

    this.id = id;

  }

  public String getDni() {

    return dni;

  }

  public void setDni(String dni) {

    this.dni = dni;

  }

  public String getFullname() {

    return fullname;

  }

  public void setFullname(String fullname) {

    this.fullname = fullname;

  }

  public String getEmail() {

    return email;

  }

  public void setEmail(String email) {

    this.email = email;

  }

  public String getBirthdate() {

    return birthdate;

  }

  public void setBirthdate(String birthdate) {

    this.birthdate = birthdate;

  }

  public String getAge() {

    return age;

  }

  public void setAge(String age) {

    this.age = age;

  }

  public String getGender() {

    return gender;

  }

  public void setGender(String gender) {

    this.gender = gender;

  }

  @Override
  public String toString() {
    return "Person: Id='" + id + "', DNI='" + dni + "', Fullname='" + fullname + "', Email='" + email + "', Birthdate='"
        + birthdate + "', Age='" + age + "', Gender='" + gender + "'";
  }

  // utils

  public static Boolean validateDNI(String dni) {

    return dni.matches("[0-9]{8}");

  }

  public static Boolean validateEmail(String email) {

    return email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");

  }

}