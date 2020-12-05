package com.civil.registration.backend.Models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "contact")
public class Contact {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String address;

  private String homephone;

  private String phone;

  @Column(name = "personId", length = 11, nullable = false)
  private Long personId;

  public Contact() {
  }

  public Contact(Long id, String address, String homephone, String phone, Long personId) {

    this.id = id;
    this.address = address;
    this.homephone = homephone;
    this.phone = phone;
    this.personId = personId;

  }

  public Long getId() {

    return this.id;

  }

  public void setId(Long id) {

    this.id = id;

  }

  public String getAddress() {

    return this.address;

  }

  public void setAddress(String address) {

    this.address = address;

  }

  public String getHomephone() {

    return this.homephone;

  }

  public void setHomephone(String homephone) {

    this.homephone = homephone;

  }

  public String getPhone() {

    return this.phone;

  }

  public void setPhone(String phone) {

    this.phone = phone;

  }

  public Long getPersonId() {

    return this.personId;

  }

  public void setPersonId(Long personId) {

    this.personId = personId;

  }

  @Override
  public String toString() {
    return "Contact: Id='" + id + "', Address='" + address + "', HomePhone='" + homephone + "', Phone='" + phone
        + "', PersonId='" + personId + "'";
  }

}