package com.example.IWatched.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

  public HelloController() {

  }

  @GetMapping("/hello")
  public String getHello() {
    return "hello";
  }

}
