package com.example.IWatched;

import com.example.IWatched.db.User;
import com.example.IWatched.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DataBaseInitializer implements ApplicationRunner {

  private UserRepository userRepository;

  @Autowired
  public void DataInit(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public void run(ApplicationArguments args) throws Exception {

    User user1 = new User("Stepan");

    User user2 = new User("Admin");

    userRepository.save(user1);
    userRepository.save(user2);
  }
}
