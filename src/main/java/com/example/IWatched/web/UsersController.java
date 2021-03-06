package com.example.IWatched.web;

import com.example.IWatched.db.Movie;
import com.example.IWatched.db.User;
import com.example.IWatched.services.MovieService;
import com.example.IWatched.services.RatingService;
import com.example.IWatched.services.UserService;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
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
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UsersController {

  @Autowired
  private UserService userService;
  @Autowired
  private RatingService ratingService;
  @Autowired
  private MovieService movieService;


  @ResponseBody
  @RequestMapping("/users")
  public String index() {
    Iterable<User> all = userService.findAll();

    StringBuilder sb = new StringBuilder();

    all.forEach(p -> sb.append(p.getUsername() + "<br>"));

    return sb.toString();
  }

  @GetMapping("/users/{username}")
  public String getUser(@PathVariable("username") String username, Model model) {
    model.addAttribute("user", userService.loadUserByUsername(username));
    model.addAttribute("ratings", ratingService.findByUsername(username));

    return "user";
  }

  @RequestMapping(value = "/users/avatars/{username}", method = RequestMethod.GET)
  @ResponseBody
  public ResponseEntity<InputStreamResource> downloadUserAvatar(@PathVariable String username)
      throws IOException {
    User user = userService.loadUserByUsername(username);

    try {
      // TODO: достаём настоящую аватарку
      throw new ArrayIndexOutOfBoundsException();
      /*
      return ResponseEntity.ok()
          .contentLength(user.getAvatar().length)
          .contentType(MediaType.IMAGE_JPEG)
          .body(new InputStreamResource(new ByteArrayInputStream(user.getAvatar())));
       */
    } catch (ArrayIndexOutOfBoundsException e) {
      byte[] no_avatar = Files.readAllBytes(Paths.get(
          System.getProperty("user.dir") + "/src/main/resources/static/no_avatar.jpg"));
      return ResponseEntity.ok()
          .contentLength(no_avatar.length)
          .contentType(MediaType.IMAGE_JPEG)
          .body(new InputStreamResource(new ByteArrayInputStream(no_avatar)));
    }
  }

  @PostMapping("/movieState")
  public String changeState(int movieID, String state, Model model, Authentication authentication) {
    if (authentication != null) {
      User user = userService.loadUserByUsername(authentication.getName());
      Movie movie = movieService.findById(movieID);
      switch (state) {
        case "want":
          user.addMovieToWanted(movie);
          break;
        case "watched":
          user.addMovieToWatched(movie);
          user.deleteMovieFromWanted(movie);
          break;
        case "dontwant":
          user.deleteMovieFromWanted(movie);
          break;
        case "notwatched":
          user.deleteMovieFromWatched(movie);
          break;
      }
      userService.updateUser(user);
    }
    return "redirect:/movies/" + movieID;
  }


}
