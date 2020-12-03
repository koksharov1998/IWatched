package com.example.IWatched.repos;

import com.example.IWatched.db.Genre;
import com.example.IWatched.db.Movie;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository
    extends CrudRepository<Movie, Integer> {

  Movie[] findByGenre(Genre genre);
}
