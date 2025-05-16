package com.example.springboot_demo.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.springboot_demo.model.User;
import com.example.springboot_demo.security.JwtUtil;

@RestController
@RequestMapping("/auth")
public class AuthController {

  private final JwtUtil jwtUtil;

  public AuthController(JwtUtil jwtUtil) {
    this.jwtUtil = jwtUtil;
  }

  @PostMapping("/login")
  public Map<String, String> login(@RequestBody User user) {
    // Exemple basique (à remplacer par une vraie vérification en base)
    if ("admin".equals(user.getUsername()) && "admin123".equals(user.getPassword())
        || "user".equals(user.getUsername()) && "user123".equals(user.getPassword())) {
      String token = jwtUtil.generateToken(user.getUsername());
      return Map.of("token", token);
    } else {
      throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Identifiants invalides");
    }
  }
}
