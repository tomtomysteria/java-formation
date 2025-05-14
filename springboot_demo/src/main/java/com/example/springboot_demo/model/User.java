package com.example.springboot_demo.model;

public class User {
  private String name;
  private int age;

  // Constructeurs
  public User() {
  }

  public User(String name, int age) {
    this.name = name;
    this.age = age;
  }

  // Getters
  public String getName() {
    return name;
  }

  public int getAge() {
    return age;
  }

  // Setters
  public void setName(String name) {
    this.name = name;
  }

  public void setAge(int age) {
    this.age = age;
  }
}
