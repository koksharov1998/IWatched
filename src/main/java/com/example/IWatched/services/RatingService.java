package com.example.IWatched.services;

import com.example.IWatched.db.Movie;
import com.example.IWatched.db.Rating;
import com.example.IWatched.repos.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingService implements BdService<Rating>{

    private RatingRepository ratingRepository;
    @Autowired
    private UserService userService;

    @Override
    public List<Rating> findAll() {
        return (List<Rating>) ratingRepository.findAll();
    }

    @Override
    public Rating findById(int id) {
        return ratingRepository.findById(id).get();
    }

    @Override
    public Rating save(Rating rating) {
        return ratingRepository.save(rating);
    }

    @Autowired
    public void DataInit(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }

    public Rating[] findByUsername(String username) {
        return ratingRepository.findByUser(userService.loadUserByUsername(username));
    }

    public Rating[] findByMovie(Movie movie) {
        return ratingRepository.findByMovie(movie);
    }
}
