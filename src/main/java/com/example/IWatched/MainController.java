package com.example.IWatched;

import com.example.IWatched.db.User;
import com.example.IWatched.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {

  @Autowired
  private UserRepository userRepository;

  @ResponseBody
  @RequestMapping("/users")
  public String index() {
    Iterable<User> all = userRepository.findAll();

    StringBuilder sb = new StringBuilder();

    all.forEach(p -> sb.append(p.getNickname() + "<br>"));

    return sb.toString();
  }

}
