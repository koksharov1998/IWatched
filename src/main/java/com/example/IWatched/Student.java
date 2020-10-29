package com.example.IWatched;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Entity
public class Student {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;

  private String name;

  public Student(String name) {
    this.name = name;
  }

  //getters, setters

  public String toString() {
    return "{id=" + id + ", name=" + name + "}";
  }
}

