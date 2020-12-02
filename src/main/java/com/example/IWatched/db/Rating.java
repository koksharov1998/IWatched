package com.example.IWatched.db;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ratings")
public class Rating {

  public Rating() {}

  public Rating(User user, Movie movie, float rating, String review) {
    this.user = user;
    this.movie = movie;
    this.rating = rating;
    this.review = review;
  }

  @Id
  @GeneratedValue
  private int id;

  @ManyToOne
  @JoinColumn(name = "nickname")
  private User user;

  @ManyToOne
  @JoinColumn(name = "movie_id")
  public Movie movie;

  public float rating;

  public String review;

}
