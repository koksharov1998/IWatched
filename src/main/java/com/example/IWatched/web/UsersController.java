package com.example.IWatched.web;

import com.example.IWatched.db.User;
import com.example.IWatched.services.BdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UsersController {

  @Autowired
  private BdService<User> userService;

  @ResponseBody
  @RequestMapping("/users")
  public String index() {
    Iterable<User> all = userService.findAll();

    StringBuilder sb = new StringBuilder();

    all.forEach(p -> sb.append(p.getUsername() + "<br>"));

    return sb.toString();
  }

}
