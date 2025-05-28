package com.example.springboot_demo.service;

import com.example.springboot_demo.dto.UpdateUserDTO;
import com.example.springboot_demo.dto.UserDTO;
import com.example.springboot_demo.exception.ExceptionUtils;
import com.example.springboot_demo.exception.ServiceOperationException;
import com.example.springboot_demo.exception.UserNotFoundException;
import com.example.springboot_demo.exception.RoleNotFoundException;
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
    try {
      logger.info("Fetching all users from the database");
      return userRepository.findAll();
    } catch (Exception e) {
      throw ExceptionUtils.handleAndThrowServiceOperationException(e, "Unable to fetch users at this time.");
    }
  }

  public Optional<User> getUserByUuid(String uuid) {
    try {
      logger.info("Fetching user by UUID: {}", uuid);
      return userRepository.findByUuid(uuid);
    } catch (Exception e) {
      throw ExceptionUtils.handleAndThrowServiceOperationException(e, "Unable to fetch user at this time.");
    }
  }

  public Optional<User> findByUsername(String username) {
    try {
      logger.info("Fetching user by username: {}", username);
      return userRepository.findByUsername(username);
    } catch (Exception e) {
      throw ExceptionUtils.handleAndThrowServiceOperationException(e, "Unable to fetch user at this time.");
    }
  }

  public User saveUserFromDTO(UserDTO userDTO) {
    try {
      logger.info("Saving user with username: {}", userDTO.getUsername());
      User user = new User();
      user.setUsername(userDTO.getUsername());
      user.setPassword(passwordEncoder.encode(userDTO.getPassword()));

      Set<Role> validatedRoles = validateAndFetchRoles(userDTO.getRoles());
      user.setRoles(validatedRoles);
      User savedUser = userRepository.save(user);
      logger.info("User saved successfully with username: {}", userDTO.getUsername());
      return savedUser;
    } catch (Exception e) {
      throw ExceptionUtils.handleAndThrowServiceOperationException(e, "Unable to save user at this time.");
    }
  }

  public Optional<User> updateUserFromDTO(String uuid, UpdateUserDTO userDetails) {
    try {
      logger.info("Updating user with UUID: {}", uuid);
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
    } catch (Exception e) {
      throw ExceptionUtils.handleAndThrowServiceOperationException(e, "Unable to update user at this time.");
    }
  }

  public boolean deleteUserByUuid(String uuid) {
    try {
      logger.info("Deleting user with UUID: {}", uuid);
      return userRepository.findByUuid(uuid).map(user -> {
        userRepository.delete(user);
        return true;
      }).orElse(false);
    } catch (Exception e) {
      throw ExceptionUtils.handleAndThrowServiceOperationException(e, "Unable to delete user at this time.");
    }
  }

  public User assignRolesToUserByUuid(String userUuid, Set<Role> roles) {
    try {
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
        return new ServiceOperationException("User not found");
      });
    } catch (Exception e) {
      throw ExceptionUtils.handleAndThrowServiceOperationException(e, "Unable to assign roles at this time.");
    }
  }

  public Set<Role> getAllRoles() {
    try {
      logger.info("Fetching all roles");
      return Set.copyOf(roleRepository.findAll());
    } catch (Exception e) {
      throw ExceptionUtils.handleAndThrowServiceOperationException(e, "Unable to fetch roles at this time.");
    }
  }

  private Set<Role> validateAndFetchRoles(Set<String> roleNames) {
    try {
      return roleNames.stream()
          .map(roleName -> roleRepository.findByName(roleName)
              .orElseThrow(() -> new RoleNotFoundException("Role not found: " + roleName)))
          .collect(Collectors.toSet());
    } catch (Exception e) {
      throw ExceptionUtils.handleAndThrowServiceOperationException(e,
          "Unable to validate and fetch roles at this time.");
    }
  }

  public String getPasswordByUsername(String username) {
    try {
      String password = userRepository.findPasswordByUsername(username);
      if (password == null) {
        throw new UserNotFoundException("User not found or password is null");
      }
      logger.info("Fetching password for user: {}", username);
      return password;
    } catch (Exception e) {
      throw ExceptionUtils.handleAndThrowServiceOperationException(e, "Unable to fetch password at this time.");
    }
  }

  public Set<Role> getRolesByUsername(String username) {
    try {
      return userRepository.findByUsername(username)
          .map(User::getRoles)
          .orElseThrow(() -> new UserNotFoundException("User not found: " + username));
    } catch (Exception e) {
      throw ExceptionUtils.handleAndThrowServiceOperationException(e, "Unable to fetch roles for user at this time.");
    }
  }
}
