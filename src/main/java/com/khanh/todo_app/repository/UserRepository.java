package com.khanh.todo_app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.khanh.todo_app.model.User;

@Repository
public interface  UserRepository extends JpaRepository<User, Long> {
  // Ham tuy chinh de tim kiem User theo username (can thiet cho dang nhap)
  Optional<User> findByUsername(String username);

  // Ham tim kiem User theo email
  Optional<User> findByEmail(String email);
}

