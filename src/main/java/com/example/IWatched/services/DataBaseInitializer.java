package com.example.IWatched.services;

import com.example.IWatched.db.User;
import com.example.IWatched.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DataBaseInitializer implements ApplicationRunner {

  @Autowired
  UserService userService;

  @Override
  public void run(ApplicationArguments args) throws Exception {

    User user1 = new User("Stepan");

    User user2 = new User("Admin");

    userService.save(user1);
    userService.save(user2);
  }
}
