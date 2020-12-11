package com.example.IWatched.db;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "genres")
public class Genre {

  @Id
  public String genre;

  public Genre() {
  }

  public Genre(String genre) {
    this.genre = genre;
  }

  public static Set<Genre> StringListToGenreSet(String[] str_genres) {
    Set<Genre> genres = new HashSet<Genre>();
    for (String str_genre: str_genres) {
      genres.add(new Genre(str_genre));
    }
    return genres;
  }
}
