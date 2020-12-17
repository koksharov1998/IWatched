package com.example.IWatched.web;

import com.example.IWatched.db.Genre;
import com.example.IWatched.db.Movie;
import com.example.IWatched.db.Rating;
import com.example.IWatched.db.User;
import com.example.IWatched.services.GenreService;
import com.example.IWatched.services.MovieService;
import com.example.IWatched.services.RatingService;
import com.example.IWatched.services.UserService;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.StringBufferInputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class MoviesController {

  @Autowired
  private MovieService movieService;

  @Autowired
  private GenreService genreService;

  @Autowired
  private UserService userService;

  @Autowired
  private RatingService ratingService;

  @GetMapping("/movies")
  public String movieList(Model model) {
    model.addAttribute("movies", movieService.findAll());
    return "movies";
  }


  @GetMapping("/movies/{movieId}")
  public String getMovie(@PathVariable("movieId") int movieId, Model model, Authentication authentication) {
    Movie movie = movieService.findById(movieId);
    model.addAttribute("movie", movie);
    if (authentication != null) {
      User user = userService.loadUserByUsername(authentication.getName());
      model.addAttribute("state", user.isMovieWatched(movie) ? "watched"
          : user.isMovieWanted(movie) ? "wanted" : "nothing");
    }
    model.addAttribute("ratings", ratingService.findByMovie(movie));
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

  @GetMapping("/movies/add")
  public String getMovieAddingPage(Model model) {
    model.addAttribute("genres", genreService.findAll());
    return "newMovie";
  }

  @PostMapping("/movies/add")
  public String addMovie(@RequestParam("poster") MultipartFile poster, String title, int year, String[] genres, Model model)
      throws IOException {
    Movie movie = new Movie(title, year);
    movie.addPoster(poster.getBytes());
    movie.setGenres(Genre.StringListToGenreSet(genres));
    movieService.save(movie);
    return "redirect:/movies/add";
  }

  @PostMapping("/movies")
  public String getFiltredMovies(String genre, String movieStatus, Model model, Authentication authentication) {
    if (movieStatus == null)
      movieStatus = "Все";
    if (genre.equals("Все") && movieStatus.equals("Все"))
        return "redirect:/movies";
    // TODO: Сделать запрос на уровне базы данных
    Set<Movie> movies;
    if (genre.equals("Все"))
      movies = new HashSet<Movie>(movieService.findAll());
    else
      movies = new HashSet<Movie>(Arrays.asList(movieService.findByGenre(genre)));
    if (authentication != null & !movieStatus.equals("Все")) {
      User user = userService.loadUserByUsername(authentication.getName());
      switch (movieStatus)
      {
        case "Просмотренные":
          movies.retainAll(user.getWatchedMovies());
          break;
        case "Желаемые к просмотру":
          movies.retainAll(user.getWantedMovies());
          break;
        case "Новые для Вас":
          movies.removeAll(user.getWantedMovies());
          movies.removeAll(user.getWatchedMovies());
          break;
      }
    }
    model.addAttribute("movies", movies.toArray());
    return "movies";
  }
}
