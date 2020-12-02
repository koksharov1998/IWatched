package com.example.IWatched.repos;

import com.example.IWatched.db.Rating;
import com.example.IWatched.db.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RatingRepository
    extends CrudRepository<Rating, Integer> {

  Rating[] findByUser(User username);

}
