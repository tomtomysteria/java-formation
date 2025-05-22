package com.example.springboot_demo.controller;

import com.example.springboot_demo.model.Role;
import com.example.springboot_demo.model.User;
import com.example.springboot_demo.service.UserService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/users")
public class UserController {

  private static final Logger logger = LogManager.getLogger(UserController.class);

  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  // Get all users
  @GetMapping
  public List<User> getAllUsers() {
    return userService.getAllUsers();
  }

  // Create a new user
  @PostMapping
  public ResponseEntity<String> createUser(@RequestBody User user) {
    if (userService.findByUsername(user.getUsername()).isPresent()) {
      return new ResponseEntity<>("Username already exists", HttpStatus.CONFLICT);
    }
    userService.saveUser(user);
    return new ResponseEntity<>("User created successfully", HttpStatus.CREATED);
  }

  // Get a user by ID
  @GetMapping("/{id}")
  public ResponseEntity<User> getUserById(@PathVariable Long id) {
    return userService.getUserById(id)
        .map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
  }

  // Update a user
  @PutMapping("/{id}")
  public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User userDetails) {
    return userService.updateUser(id, userDetails)
        .map(updatedUser -> new ResponseEntity<>(updatedUser, HttpStatus.OK))
        .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
  }

  // Delete a user
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
    if (userService.deleteUser(id)) {
      return ResponseEntity.noContent().build();
    } else {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
  }

  // Endpoint to get the profile of the logged-in user
  @GetMapping("/profile")
  @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
  public ResponseEntity<Map<String, String>> getProfile(Authentication authentication) {
    logger.info("TEST PROFILE LOG");
    String username = authentication.getName();
    String role = authentication.getAuthorities().stream()
        .map(GrantedAuthority::getAuthority)
        .findFirst()
        .orElse("USER");
    return ResponseEntity.ok(Map.of("username", username, "role", role));
  }

  @GetMapping("/by-username/{username}")
  public Optional<User> getByUsername(@PathVariable String username) {
    return userService.findByUsername(username);
  }

  @PostMapping("/{id}/roles")
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<User> assignRolesToUser(@PathVariable Long id, @RequestBody Set<Role> roles) {
    try {
      User updatedUser = userService.assignRolesToUser(id, roles);
      return ResponseEntity.ok(updatedUser);
    } catch (RuntimeException e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
  }

  @GetMapping("/roles")
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<Set<Role>> getAllRoles() {
    Set<Role> roles = userService.getAllRoles();
    return ResponseEntity.ok(roles);
  }
}
