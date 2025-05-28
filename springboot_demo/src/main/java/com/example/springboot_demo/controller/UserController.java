package com.example.springboot_demo.controller;

import com.example.springboot_demo.dto.UpdateUserDTO;
import com.example.springboot_demo.dto.UserDTO;
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
import org.springframework.web.server.ResponseStatusException;

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

  // Create a new user using UserDTO
  @PostMapping()
  public ResponseEntity<User> createUser(@RequestBody UserDTO userDTO) {
    if (userService.findByUsername(userDTO.getUsername()).isPresent()) {
      logger.warn("Username already exists: {}", userDTO.getUsername());
      throw new ResponseStatusException(HttpStatus.CONFLICT, "Username already exists");
    }
    User savedUser = userService.saveUserFromDTO(userDTO);
    return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
  }

  // Get a user by UUID
  @GetMapping("/{uuid}")
  public ResponseEntity<User> getUserByUuid(@PathVariable String uuid) {
    Optional<User> user = userService.getUserByUuid(uuid);
    if (user.isPresent()) {
      return new ResponseEntity<>(user.get(), HttpStatus.OK);
    } else {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
    }
  }

  // Update a user
  @PutMapping("/{uuid}")
  public ResponseEntity<User> updateUser(@PathVariable String uuid, @RequestBody UpdateUserDTO userDetails) {
    return userService.updateUserFromDTO(uuid, userDetails)
        .map(updatedUser -> {
          logger.info("User updated successfully with UUID: {}", uuid);
          return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        })
        .orElseGet(() -> {
          logger.warn("User not found for update with UUID: {}", uuid);
          return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        });
  }

  // Delete a user
  @DeleteMapping("/{uuid}")
  public ResponseEntity<Void> deleteUser(@PathVariable String uuid) {
    if (userService.deleteUserByUuid(uuid)) {
      logger.info("User deleted successfully with UUID: {}", uuid);
      return ResponseEntity.noContent().build();
    } else {
      logger.warn("User not found for deletion with UUID: {}", uuid);
      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
  }

  // Endpoint to get the profile of the logged-in user
  @GetMapping("/profile")
  @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
  public ResponseEntity<Map<String, String>> getProfile(Authentication authentication) {
    logger.info("Fetching profile for logged-in user");
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

  @PostMapping("/{userUuid}/roles")
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<User> assignRolesToUser(@PathVariable String userUuid, @RequestBody Set<Role> roles) {
    try {
      User updatedUser = userService.assignRolesToUserByUuid(userUuid, roles);
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
