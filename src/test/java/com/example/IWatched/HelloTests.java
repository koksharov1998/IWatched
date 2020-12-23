package com.example.IWatched;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class HelloTests {

  @LocalServerPort
  private int port;

  @Autowired
  private TestRestTemplate restTemplate;

  @Test
  public void NonAuthorizedUserGoToLoginPage() throws Exception {
    assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/hello",
        String.class)).contains("<form action=\"/login\" method=\"post\">");
  }

  @Test
  public void AuthorizedUserGoToHello() throws Exception {
    // Добавить как-то к запросу авторизованного пользователя
    assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/hello",
        String.class)).contains("Hello");
  }

  // @WithMock()

}
