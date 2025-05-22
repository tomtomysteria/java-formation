package com.example.springboot_demo.service;

import com.example.springboot_demo.model.Role;
import com.example.springboot_demo.model.User;
import com.example.springboot_demo.repository.RoleRepository;
import com.example.springboot_demo.repository.UserRepository;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService {

  private static final Logger logger = LogManager.getLogger(UserService.class);

  private final UserRepository userRepository;
  private final RoleRepository roleRepository;
  private final BCryptPasswordEncoder passwordEncoder;

  public UserService(UserRepository userRepository, RoleRepository roleRepository) {
    this.userRepository = userRepository;
    this.roleRepository = roleRepository;
    this.passwordEncoder = new BCryptPasswordEncoder();
  }

  public List<User> getAllUsers() {
    return userRepository.findAll();
  }

  public Optional<User> getUserById(Long id) {
    return userRepository.findById(id);
  }

  public Optional<User> findByUsername(String username) {
    return userRepository.findByUsername(username);
  }

  public User saveUser(User user) {
    user.setPassword(passwordEncoder.encode(user.getPassword()));

    // Ensure roles are valid and exist in the database
    Set<Role> validatedRoles = user.getRoles().stream()
        .map(role -> roleRepository.findByName(role.getName())
            .orElseThrow(() -> new RuntimeException("Role not found: " + role.getName())))
        .collect(Collectors.toSet());

    user.setRoles(validatedRoles);
    return userRepository.save(user);
  }

  public Optional<User> updateUser(Long id, User userDetails) {
    return userRepository.findById(id).map(user -> {
      user.setUsername(userDetails.getUsername());
      user.setPassword(passwordEncoder.encode(userDetails.getPassword()));
      user.setRoles(userDetails.getRoles());
      return userRepository.save(user);
    });
  }

  public boolean deleteUser(Long id) {
    if (userRepository.existsById(id)) {
      userRepository.deleteById(id);
      return true;
    }
    return false;
  }

  public User assignRolesToUser(Long userId, Set<Role> roles) {
    logger.info("Attempting to assign roles to user with ID: {}", userId);

    return userRepository.findById(userId).map(user -> {
      logger.info("User found: {}", user.getUsername());
      logger.info("Roles to assign: {}", roles);

      // Validate roles
      Set<Role> validatedRoles = roles.stream()
          .map(role -> roleRepository.findByName(role.getName())
              .orElseThrow(() -> new RuntimeException("Role not found: " + role.getName())))
          .collect(Collectors.toSet());

      user.setRoles(validatedRoles);
      User updatedUser = userRepository.save(user);

      logger.info("Roles successfully assigned to user: {}", updatedUser.getUsername());
      return updatedUser;
    }).orElseThrow(() -> {
      logger.error("User with ID {} not found", userId);
      return new RuntimeException("User not found");
    });
  }

  public Set<Role> getAllRoles() {
    return Set.copyOf(roleRepository.findAll());
  }
}
