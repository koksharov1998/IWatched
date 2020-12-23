package com.example.IWatched.web;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {


  @GetMapping("/hello")
  public String getHello() {
    return "hello";
  }


  @RequestMapping(value = "/favicon.ico", method = RequestMethod.GET)
  @ResponseBody
  public ResponseEntity<InputStreamResource> downloadFavicon() throws IOException {

    System.out.println("dssfs");
    byte[] favicon;

    favicon = Files.readAllBytes(Paths.get(
        System.getProperty("user.dir") + "/src/main/resources/static/favicon.png"));

    return ResponseEntity.ok()
        .contentLength(favicon.length)
        .contentType(MediaType.IMAGE_PNG)
        .body(new InputStreamResource(new ByteArrayInputStream(favicon)));

  }
}
