package com.example.IWatched.services;

import com.example.IWatched.db.Genre;
import com.example.IWatched.db.Movie;
import com.example.IWatched.repos.MovieRepository;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieService {

  private MovieRepository movieRepository;

  @PersistenceContext
  private EntityManager em;

  public List<Movie> findAll() {
    return (List<Movie>) movieRepository.findAll();
  }

  public Movie findById(int id) {
    return movieRepository.findById(id).get();
  }

  public Movie save(Movie movie) {
    return movieRepository.save(movie);
  }

  @Autowired
  public void DataInit(MovieRepository movieRepository) {
    this.movieRepository = movieRepository;
  }

  public Movie[] findByGenre(String genre) {
    return movieRepository.findByGenre(new Genre(genre));
  }
}
