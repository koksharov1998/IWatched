package com.example.IWatched.db;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "genres")
public class Genre {

  @Id
  private String genre;

  public Genre() {
  }

}
