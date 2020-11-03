package com.example.IWatched.db;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "movies")
public class Movie {

  public Movie() {}

  public Movie(String title, int release_year) {
    this.title = title;
    this.release_year = release_year;
  }

  @Id
  @GeneratedValue
  private int id;

  public String title;

  // TODO: Использовать объект типа даты-времени
  public int release_year;

  @ManyToOne
  @JoinColumn(name = "genre")
  private Genre genre;

  private String description;

  private String poster;

  @OneToMany(mappedBy = "movie")
  private List<Rating> ratings;

  public String toString() {
    return this.title;
  }

}
