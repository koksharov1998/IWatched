package com.example.IWatched.db;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "genres")
public class Genre {

  public Genre() {}

  @GeneratedValue
  private int id;

  @Id
  private String genre;

}
