package com.example.IWatched.services;

import com.example.IWatched.db.Movie;
import com.example.IWatched.db.Role;
import com.example.IWatched.db.User;
import com.example.IWatched.repos.MovieRepository;
import com.example.IWatched.repos.RoleRepository;
import com.example.IWatched.repos.UserRepository;
import java.util.ArrayList;
import java.util.List;
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

  @Override
  public void run(ApplicationArguments args) throws Exception {
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
    
    UploadSomeMovies();
    
  }

  private void UploadSomeMovies() {
    List<Movie> movies = new ArrayList<Movie>();

    Movie movie = new Movie("Дюна", 2021);
    movie.addPoster("Дюна.jpg");
    movies.add(movie);


    movie = new Movie("Довод", 2020);
    movie.addPoster("Довод.jpg");
    movies.add(movie);

    movie = new Movie("Ирландец", 2019);
    movie.addPoster("Ирландец.jpg");
    movies.add(movie);


  /*
    movies.add(new Movie("Сиротский Бруклин", 2019));
    movies.add(new Movie("Братья Систерс", 2018));
    movies.add(new Movie("Мстители Война бесконечности", 2018));
    movies.add(new Movie("Дело храбрых", 2017));
    movies.add(new Movie("Джон Уик 2", 2017));
    movies.add(new Movie("Любой ценой", 2016));
    movies.add(new Movie("Разрушение", 2015));

 */

    movieRepository.saveAll(movies);

  }
}
