package com.example.springboot_demo.service;

import com.example.springboot_demo.dto.UpdateUserDTO;
import com.example.springboot_demo.dto.UserDTO;
import com.example.springboot_demo.exception.UserNotFoundException;
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

  public Optional<User> getUserByUuid(String uuid) {
    return userRepository.findByUuid(uuid);
  }

  public Optional<User> findByUsername(String username) {
    return userRepository.findByUsername(username);
  }

  public User saveUserFromDTO(UserDTO userDTO) {
    User user = new User();
    user.setUsername(userDTO.getUsername());
    user.setPassword(passwordEncoder.encode(userDTO.getPassword()));

    Set<Role> validatedRoles = validateAndFetchRoles(userDTO.getRoles());
    user.setRoles(validatedRoles);
    return userRepository.save(user);
  }

  public Optional<User> updateUserFromDTO(String uuid, UpdateUserDTO userDetails) {
    return userRepository.findByUuid(uuid).map(user -> {
      user.setUsername(userDetails.getUsername());

      String password = userDetails.getPassword();
      if (password != null && !password.isEmpty()) {
        user.setPassword(passwordEncoder.encode(password));
      }

      Set<Role> validatedRoles = validateAndFetchRoles(userDetails.getRoles());
      user.setRoles(validatedRoles);
      return userRepository.save(user);
    });
  }

  public boolean deleteUserByUuid(String uuid) {
    return userRepository.findByUuid(uuid).map(user -> {
      userRepository.delete(user);
      return true;
    }).orElse(false);
  }

  public User assignRolesToUserByUuid(String userUuid, Set<Role> roles) {
    logger.info("Attempting to assign roles to user with UUID: {}", userUuid);

    return userRepository.findByUuid(userUuid).map(user -> {
      logger.info("User found: {}", user.getUsername());
      logger.info("Roles to assign: {}", roles);

      Set<Role> validatedRoles = validateAndFetchRoles(roles.stream().map(Role::getName).collect(Collectors.toSet()));
      user.setRoles(validatedRoles);
      User updatedUser = userRepository.save(user);

      logger.info("Roles successfully assigned to user: {}", updatedUser.getUsername());
      return updatedUser;
    }).orElseThrow(() -> {
      logger.error("User with UUID {} not found", userUuid);
      return new RuntimeException("User not found");
    });
  }

  public Set<Role> getAllRoles() {
    return Set.copyOf(roleRepository.findAll());
  }

  private Set<Role> validateAndFetchRoles(Set<String> roleNames) {
    return roleNames.stream()
        .map(roleName -> roleRepository.findByName(roleName)
            .orElseThrow(() -> new RuntimeException("Role not found: " + roleName)))
        .collect(Collectors.toSet());
  }

  public String getPasswordByUsername(String username) {
    String password = userRepository.findPasswordByUsername(username);
    if (password == null) {
      throw new UserNotFoundException("User not found or password is null");
    }
    logger.info("Fetching password for user: {}", password);
    return password;
  }
}
