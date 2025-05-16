package com.example.springboot_demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springboot_demo.model.NonPersistentUser;

@RestController
public class HelloController {

  @GetMapping("/bonjour")
  public String sayHello() {
    return "Bonjour depuis Spring Boot !";
  }

  @GetMapping("/utilisateur")
  public NonPersistentUser getUser() {
    return new NonPersistentUser("Jean Dupont", 30);
  }

}
