package com.example.springboot_demo.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.springboot_demo.dto.UserDTO;
import com.example.springboot_demo.model.User;
import com.example.springboot_demo.repository.UserRepository;
import com.example.springboot_demo.security.JwtUtil;

@RestController
@RequestMapping("/auth")
public class AuthController {

  private final JwtUtil jwtUtil;
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  public AuthController(JwtUtil jwtUtil, UserRepository userRepository, PasswordEncoder passwordEncoder) {
    this.jwtUtil = jwtUtil;
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
  }

  @PostMapping("/login")
  public Map<String, String> login(@RequestBody UserDTO user) {
    User dbUser = userRepository.findByUsername(user.getUsername())
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Identifiants invalides"));

    if (passwordEncoder.matches(user.getPassword(), dbUser.getPassword())) {
      String token = jwtUtil.generateToken(dbUser.getUsername());
      return Map.of("token", token);
    } else {
      throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Identifiants invalides");
    }
  }
}
