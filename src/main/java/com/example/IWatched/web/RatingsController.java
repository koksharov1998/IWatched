package com.example.IWatched.web;

import com.example.IWatched.db.Rating;
import com.example.IWatched.services.MovieService;
import com.example.IWatched.services.RatingService;
import com.example.IWatched.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RatingsController {

  @Autowired
  private RatingService ratingService;
  @Autowired
  private MovieService movieService;
  @Autowired
  private UserService userService;


  @PostMapping("/ratings")
  public String createRating(int movieID, String review, float ratingDigital, Model model,
      Authentication authentication) {
    ratingService.save(new Rating(userService.loadUserByUsername(authentication.getName()),
        movieService.findById(movieID), ratingDigital, review));
    return "redirect:/movies/" + movieID;
  }

}
