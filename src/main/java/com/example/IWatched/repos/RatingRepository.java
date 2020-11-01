package com.example.IWatched.repos;

import com.example.IWatched.db.Rating;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RatingRepository
    extends CrudRepository<Rating, Integer> {

}
