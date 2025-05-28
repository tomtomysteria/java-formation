package com.example.springboot_demo.controller;

import java.util.Map;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.springboot_demo.dto.LoginDTO;
import com.example.springboot_demo.model.Role;
import com.example.springboot_demo.security.JwtUtil;
import com.example.springboot_demo.service.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {

  private static final Logger logger = LogManager.getLogger(AuthController.class);

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
    logger.info("Login attempt for user: {}", loginDTO.getUsername());
    try {
      String dbPassword = userService.getPasswordByUsername(loginDTO.getUsername());
      if (passwordEncoder.matches(loginDTO.getPassword(), dbPassword)) {
        Set<Role> roles = userService.getRolesByUsername(loginDTO.getUsername());
        String token = jwtUtil.generateToken(loginDTO.getUsername(), roles);
        logger.info("Login successful for user: {}", loginDTO.getUsername());
        return Map.of("token", token);
      } else {
        logger.warn("Invalid credentials for user: {}", loginDTO.getUsername());
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid credentials");
      }
    } catch (Exception e) {
      logger.error("Error during login for user: {}", loginDTO.getUsername(), e);
      throw e;
    }
  }
}
