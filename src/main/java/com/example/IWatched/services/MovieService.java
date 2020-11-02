package com.example.IWatched.services;

import com.example.IWatched.db.Movie;
import com.example.IWatched.repos.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {
    private MovieRepository movieRepository;

    List<Movie> findAll() {
        return (List<Movie>) movieRepository.findAll();
    }

    Movie findById(int id){
        return movieRepository.findById(id).get();
    }

    Movie save(Movie movie){
        return movieRepository.save(movie);
    }

    @Autowired
    public void DataInit(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }
}
