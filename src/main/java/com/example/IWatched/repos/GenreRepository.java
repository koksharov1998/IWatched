package com.example.IWatched.repos;

import com.example.IWatched.db.Genre;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository
    extends CrudRepository<Genre, Integer> {

}
