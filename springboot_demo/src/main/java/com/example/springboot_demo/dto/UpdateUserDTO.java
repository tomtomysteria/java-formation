package com.example.springboot_demo.dto;

import java.util.Set;

import org.springframework.lang.Nullable;

public class UpdateUserDTO {
  private String username;
  @Nullable
  private String password; // Le champ password est maintenant facultatif
  private Set<String> roles;

  // Getters and Setters
  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  @Nullable
  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Set<String> getRoles() {
    return roles;
  }

  public void setRoles(Set<String> roles) {
    this.roles = roles;
  }

}
