package com.example.IWatched.repos;

import com.example.IWatched.db.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository
    extends CrudRepository<User, Integer> {

}
