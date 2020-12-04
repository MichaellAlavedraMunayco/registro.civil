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

  public Long getId() {

    return this.id;

  }

  public String getAddress() {

    return this.address;

  }

  public String getHomephone() {

    return this.homephone;

  }

  public String getPhone() {

    return this.phone;

  }

  public Long getPersonId() {

    return this.personId;

  }

}