package com.example.IWatched.web;

import com.example.IWatched.db.Movie;
import com.example.IWatched.db.Rating;
import com.example.IWatched.services.MovieService;
import com.example.IWatched.services.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RatingsController {

  @Autowired
  private RatingService ratingService;
  @Autowired
  private MovieService movieService;


  @PostMapping("/ratings")
  public String createRating(String username, int movieID, String review, float ratingDigital, Model model, Authentication authentication) {
    System.out.println((UserDetails)authentication.getPrincipal());
    ratingService.save(new Rating(movieService.findById(movieID), ratingDigital, review));
    return "redirect:/movies/" + movieID;
  }

}
