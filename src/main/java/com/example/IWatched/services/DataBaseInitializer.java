package com.example.IWatched.services;

import com.example.IWatched.db.Genre;
import com.example.IWatched.db.Movie;
import com.example.IWatched.db.Role;
import com.example.IWatched.db.User;
import com.example.IWatched.repos.MovieRepository;
import com.example.IWatched.repos.RoleRepository;
import com.example.IWatched.repos.UserRepository;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DataBaseInitializer implements ApplicationRunner {

  @Autowired
  UserService userService;

  @Autowired
  RoleRepository roleRepository;

  @Autowired
  MovieRepository movieRepository;

  @Autowired
  GenreService genreService;

  @Override
  public void run(ApplicationArguments args) throws Exception {

    // Если в базе данных уже есть роль админа, то можно (?) считать, что базу уже инициализировали,
    // поэтому можно не проводить инициализацию заново
    // Возможно можно сделать с другим условием или вовсе как-то иначе
    if (roleRepository.findById(2L).isPresent())
      return;

    roleRepository.save(new Role(1L, "ROLE_USER"));
    roleRepository.save(new Role(2L, "ROLE_ADMIN"));

    User user1 = new User("Stepan");
    user1.setPassword("lox");
    user1.setPasswordConfirm("lox");

    User admin = new User("Admin");
    admin.setPassword("admin");
    admin.setPasswordConfirm("admin");

    userService.save(user1);
    userService.saveAdmin(admin);

    genreService.save(new Genre("Комикс"));
    genreService.save(new Genre("Фантастика"));
    genreService.save(new Genre("Драма"));
    genreService.save(new Genre("Боевик"));
    
    UploadSomeMovies();
    
  }

  private void UploadSomeMovies() {
    List<Movie> movies = new ArrayList<Movie>();

    Genre comics = new Genre("Комикс");
    Genre fiction = new Genre("Фантастика");
    Genre drama = new Genre("Драма");
    Genre thriller = new Genre("Боевик");


    Movie movie = new Movie("Дюна", 2021);
    movie.addPoster("Dune.jpg");
    Set<Genre> setGenres = new HashSet<Genre>();
    setGenres.add(fiction);
    setGenres.add(drama);
    movie.setGenres(setGenres);
    movies.add(movie);

    movie = new Movie("Довод", 2020);
    movie.addPoster("Tenet.jpg");
    movie.setGenre(fiction);
    movies.add(movie);

    movie = new Movie("Ирландец", 2019);
    movie.addPoster("The Irishman.jpg");
    movie.setGenre(drama);
    movies.add(movie);

    movie = new Movie("Сиротский Бруклин", 2019);
    movie.addPoster("Motherless Brooklyn.jpg");
    movie.setGenre(drama);
    movies.add(movie);

    movie = new Movie("Братья Систерс", 2018);
    movie.addPoster("The Sisters Brothers.jpg");
    movie.setGenre(drama);
    movies.add(movie);

    movie = new Movie("Мстители Война бесконечности", 2018);
    movie.addPoster("Avengers Infinity War .jpg");
    movie.setGenre(comics);
    movies.add(movie);

    movie = new Movie("Дело храбрых", 2017);
    movie.addPoster("Only the Brave .jpg");
    movie.setGenre(drama);
    movies.add(movie);

    movie = new Movie("Джон Уик 2", 2017);
    movie.addPoster("John Wick Chapter Two .jpg");
    movie.setGenre(thriller);
    movies.add(movie);

    movie = new Movie("Любой ценой", 2016);
    movie.addPoster("Hell or High Water .jpg");
    movie.setGenre(drama);
    movies.add(movie);

    movie = new Movie("Разрушение", 2015);
    movie.addPoster("Demolition .jpg");
    movie.setGenre(drama);
    movies.add(movie);

    movieRepository.saveAll(movies);

  }
}
