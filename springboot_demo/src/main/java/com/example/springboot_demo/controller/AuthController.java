package com.example.springboot_demo.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.springboot_demo.dto.LoginDTO;
import com.example.springboot_demo.security.JwtUtil;
import com.example.springboot_demo.service.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {

  private final JwtUtil jwtUtil;
  private final UserService userService;
  private final PasswordEncoder passwordEncoder;

  public AuthController(JwtUtil jwtUtil, UserService userService, PasswordEncoder passwordEncoder) {
    this.jwtUtil = jwtUtil;
    this.userService = userService;
    this.passwordEncoder = passwordEncoder;
  }

  @PostMapping("/login")
  public Map<String, String> login(@RequestBody LoginDTO loginDTO) {
    System.out.println("Login attempt for user: " + loginDTO.getUsername());
    String dbPassword = userService.getPasswordByUsername(loginDTO.getUsername());
    if (passwordEncoder.matches(loginDTO.getPassword(), dbPassword)) {
      String token = jwtUtil.generateToken(loginDTO.getUsername());
      return Map.of("token", token);
    } else {
      throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Identifiants invalides");
    }
  }
}
