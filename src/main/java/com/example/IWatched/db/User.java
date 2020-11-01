package com.example.IWatched.db;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User {

  @Id
  @GeneratedValue
  private int id;

  private String nickname;

  private String name;

  private String surname;

  private String password_hash;

  private boolean is_admin;

  public User() {
  }

  public User(String nickname) {
    this.nickname = nickname;
  }

  public String getNickname() {
    return nickname;
  }
}

