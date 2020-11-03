package com.example.IWatched.services;

import com.example.IWatched.db.Role;
import com.example.IWatched.db.User;
import com.example.IWatched.repos.RoleRepository;
import com.example.IWatched.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DataBaseInitializer implements ApplicationRunner {

  @Autowired
  UserService userService;

  @Autowired
  RoleRepository roleRepository;

  @Override
  public void run(ApplicationArguments args) throws Exception {
    roleRepository.save(new Role(1L, "ROLE_USER"));
    roleRepository.save(new Role(2L, "ROLE_ADMIN"));

    User user1 = new User("Stepan");
    user1.setPassword("lox");
    user1.setPasswordConfirm("lox");

    User user2 = new User("Admin");

    userService.save(user1);
    //userService.save(user2);
  }
}
