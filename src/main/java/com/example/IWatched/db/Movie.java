package com.example.IWatched.db;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "movies")
public class Movie {

  @Id
  @GeneratedValue
  public int id;
  public String title;
  // TODO: Использовать объект типа даты-времени
  public int release_year;
  @Lob
  public byte[] poster;
  @ManyToOne
  @JoinColumn(name = "genre")
  private Genre genre;
  private String description;
  @OneToMany(mappedBy = "movie")
  private List<Rating> ratings;

  public Movie() {
  }

  public Movie(String title, int release_year) {
    this.title = title;
    this.release_year = release_year;
  }

  public String toString() {
    return this.title;
  }

  public void addPoster(String pathToPoster) {
    try {
      this.poster = Files.readAllBytes(Paths.get(
          System.getProperty("user.dir") + "\\src\\main\\resources\\static\\posters\\"
              + pathToPoster));
    } catch (IOException e) {
      e.printStackTrace();
      System.out.println("Не получилось загрузить постер к фильму по пути: " + pathToPoster);
    }
  }
}
