package com.example.springboot_demo.model;

public class User {
  private String username;
  private String password;
  private int age;
  private String role;

  // Constructeurs
  public User() {
  }

  public User(String username, String password, int age, String role) {
    this.username = username;
    this.password = password;
    this.age = age;
    this.role = role;
  }

  // Getters & Setters
  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }

}
