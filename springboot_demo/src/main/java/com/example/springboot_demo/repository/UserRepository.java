package com.example.springboot_demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.example.springboot_demo.model.User;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findByUsername(String username);

  Optional<User> findByUuid(String uuid);

  @Query("SELECT u.password FROM User u WHERE u.username = :username")
  String findPasswordByUsername(@Param("username") String username);
}
