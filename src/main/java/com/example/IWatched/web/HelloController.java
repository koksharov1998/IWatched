package com.example.IWatched.web;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {


  public String index() {
    return "Hello word!";
  }
}
