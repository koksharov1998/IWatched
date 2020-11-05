package com.example.IWatched.web;

import com.example.IWatched.db.Movie;
import com.example.IWatched.services.MovieService;
import com.example.IWatched.services.UserService;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.StringBufferInputStream;
import java.nio.charset.StandardCharsets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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

  @RequestMapping(value = "/movies/poster/{movieId}", method = RequestMethod.GET)
  @ResponseBody
  public ResponseEntity<InputStreamResource> downloadMoviePoster(@PathVariable int movieId) {
    Movie movie = movieService.findById(movieId);

    return ResponseEntity.ok()
        .contentLength(movie.poster.length)
        .contentType(MediaType.IMAGE_JPEG)
        .body(new InputStreamResource(new ByteArrayInputStream(movie.poster)));
  }


}
