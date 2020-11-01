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

  @Id
  @GeneratedValue
  private int id;

  private String title;

  @ManyToOne
  @JoinColumn(name = "genre")
  private Genre genre;

  private String description;

  private String poster;

  // Почему movie, а не movies??
  @OneToMany(mappedBy = "movie")
  private List<Rating> ratings;

}
