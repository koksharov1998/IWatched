package com.example.IWatched.db;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "movies")
public class Movie {

  @Lob
  private byte[] poster;
  // TODO: Использовать объект типа даты-времени
  private int release_year;
  private String title;
  @Id
  @GeneratedValue
  private int id;
  @ManyToMany
  @JoinColumn(name = "genre")
  private Set<Genre> genre;

  @Lob
  @Column(columnDefinition="CLOB")
  private String description;

  @OneToMany(mappedBy = "movie")
  private List<Rating> ratings;

  public Movie() {
  }

  public Movie(String title, int release_year) {
    this.title = title;
    this.release_year = release_year;
  }

  public String getTitle() {
    return title;
  }

  public int getId() {
    return id;
  }

  public String toString() {
    return this.title;
  }

  public void addPoster(String pathToPoster) {
    try {
      this.poster = Files.readAllBytes(Paths.get(
          System.getProperty("user.dir") + "/src/main/resources/static/posters/"
              + pathToPoster));
    } catch (IOException e) {
      e.printStackTrace();
      System.out.println("Не получилось загрузить постер к фильму по пути: " + pathToPoster);
    }
  }

  public void addPoster(byte[] poster) {
    this.poster = poster;
  }

  public void setGenre(Genre genre) {
    Set<Genre> setGenres = new HashSet<Genre>();
    setGenres.add(genre);
    this.genre = setGenres;
  }

  public void setGenres(Set<Genre> genres) {
    this.genre = genres;
  }

  public int getRelease_year() {
    return release_year;
  }

  public byte[] getPoster() {
    return poster;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
}
