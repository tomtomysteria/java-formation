package com.example.springboot_demo.controller;

import com.example.springboot_demo.model.User;
import com.example.springboot_demo.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

  private final UserRepository repo;

  public UserController(UserRepository repo) {
    this.repo = repo;
  }

  // Get all users
  @GetMapping
  public List<User> getAllUsers() {
    return repo.findAll();
  }

  // Create a new user
  @PostMapping
  public ResponseEntity<?> createUser(@RequestBody User user) {
    if (repo.findByUsername(user.getUsername()).isPresent()) {
      return new ResponseEntity<>("Username already exists", HttpStatus.CONFLICT);
    }
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    User savedUser = repo.save(user);
    return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
  }

  // Get a user by ID
  @GetMapping("/{id}")
  public ResponseEntity<User> getUserById(@PathVariable Long id) {
    Optional<User> user = repo.findById(id);
    return user.map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
  }

  // Update a user
  @PutMapping("/{id}")
  public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User userDetails) {
    return repo.findById(id)
        .map(user -> {
          user.setUsername(userDetails.getUsername());
          user.setPassword(userDetails.getPassword());
          user.setRole(userDetails.getRole());
          User updatedUser = repo.save(user);
          return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        })
        .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
  }

  // Delete a user
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
    if (repo.existsById(id)) {
      repo.deleteById(id);
      return ResponseEntity.noContent().build();
    } else {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
  }

  @GetMapping("/profile")
  public String getProfile() {
    return "Profil utilisateur accessible";
  }

  @GetMapping("/by-username/{username}")
  public Optional<User> getByUsername(@PathVariable String username) {
    return repo.findByUsername(username);
  }
}
