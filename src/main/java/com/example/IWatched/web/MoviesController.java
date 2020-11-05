package com.example.IWatched.web;

import com.example.IWatched.services.MovieService;
import com.example.IWatched.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class MoviesController {

  @Autowired
  private MovieService movieService;

  @GetMapping("/movies")
  public String movieList(Model model) {
    model.addAttribute("movies", movieService.findAll());
    return "movies";
  }


  @GetMapping("/movies/{movieId}")
  public String getMovie(@PathVariable("movieId") int movieId, Model model) {
    model.addAttribute("movie", movieService.findById(movieId));
    return "movie";
  }


}
