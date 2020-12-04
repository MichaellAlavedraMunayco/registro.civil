package com.civil.registration.backend.DomainModels;

public class Person {

  private int id;
  private String dni;
  private String fullname;
  private String email;
  private String birthdate;
  private String age;
  private String gender;

  protected Person() {
  }

  protected Person(String dni, String fullname, String email, String birthdate, String age, String gender) {

    this.dni = dni;
    this.fullname = fullname;
    this.email = email;
    this.birthdate = birthdate;
    this.age = age;
    this.gender = gender;

  }

  // getters and setters go here...

  public int getId() {

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

    return dni.matches("[0-9]{9}");

  }

  public static Boolean validateEmail(String email) {

    return email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");

  }

}