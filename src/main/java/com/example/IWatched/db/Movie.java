package com.example.IWatched.db;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.springframework.util.Base64Utils;
import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;

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
  public int id;

  public String title;

  // TODO: Использовать объект типа даты-времени
  public int release_year;

  @ManyToOne
  @JoinColumn(name = "genre")
  private Genre genre;

  private String description;

  @Lob
  public byte[] poster;

  @OneToMany(mappedBy = "movie")
  private List<Rating> ratings;

  public String toString() {
    return this.title;
  }

  public void addPoster(String pathToPoster) {
    try {
      this.poster = Files.readAllBytes(Paths.get(System.getProperty("user.dir") + "\\src\\main\\resources\\static\\posters\\" + pathToPoster));
    } catch (IOException e) {
      e.printStackTrace();
      System.out.println("Не получилось загрузить постер к фильму по пути: " + pathToPoster);
    }
  }
}
