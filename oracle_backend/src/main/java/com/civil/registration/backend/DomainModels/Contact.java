package com.civil.registration.backend.DomainModels;

public class Contact {

  private int id;
  private String address;
  private String homephone;
  private String phone;
  private int personId;

  protected Contact() {
  }

  protected Contact(String address, String homephone, String phone, int personId) {

    this.address = address;
    this.homephone = homephone;
    this.phone = phone;
    this.personId = personId;

  }

  // getters and setters go here...

  public int getId() {

    return this.id;

  }

  public String getAddress() {

    return this.address;

  }

  public String getHomePhone() {

    return this.homephone;

  }

  public String getPhone() {

    return this.phone;

  }

  public int getPersonId() {

    return this.personId;

  }

}