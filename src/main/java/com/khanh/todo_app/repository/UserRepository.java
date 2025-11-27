package com.khanh.todo_app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.khanh.todo_app.model.User;

@Repository
public interface  UserRepository extends JpaRepository<User, Long> {
  // function to find User by username
  // if found, return User object wrapped in Optional
  // if not found, return Optional.empty()
  Optional<User> findByUsername(String username);
  // function to find User by email
  Optional<User> findByEmail(String email);

  boolean existsByUsername(String username);
  boolean existsByEmail(String email);
}

