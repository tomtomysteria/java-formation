package com.example.springboot_demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springboot_demo.model.User;

@RestController
public class HelloController {

  @GetMapping("/bonjour")
  public String sayHello() {
    return "Bonjour depuis Spring Boot !";
  }

  @GetMapping("/utilisateur")
  public User getUser() {
    return new User("Jean Dupont", 30);
  }

}
