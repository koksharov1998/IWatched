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

  @Id
  @GeneratedValue
  private int id;
  @ManyToOne
  @JoinColumn(name = "nickname")
  private User user;
  @ManyToOne
  @JoinColumn(name = "movie_id")
  private Movie movie;
  private float rating;
  private String review;

  public Rating() {
  }

  public Rating(User user, Movie movie, float rating, String review) {
    this.user = user;
    this.movie = movie;
    this.rating = rating;
    this.review = review;
  }

  public User getUser() {
    return user;
  }

  public Movie getMovie() {
    return movie;
  }

  public float getRating() {
    return rating;
  }

  public String getReview() {
    return review;
  }
}
