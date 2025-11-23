package com.khanh.todo_app.service.auth;

import com.khanh.todo_app.dto.RegisterRequestDto;
import com.khanh.todo_app.model.User;
import com.khanh.todo_app.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
  }

  // ---- CRUD operations for User entity can be added here ----
  public void registerUser(RegisterRequestDto registerRequestDto) {
    // Check if username or email already exists
    if (userRepository.findByUsername(registerRequestDto.getUsername()).isPresent()) {
      throw new IllegalArgumentException("Username da ton tai");
    }

    if (userRepository.findByEmail(registerRequestDto.getEmail()).isPresent()) {
      throw new IllegalArgumentException("Email da ton tai");
    }

    // Create new User entity
    User newUser = new User();
    newUser.setUsername(registerRequestDto.getUsername());
    newUser.setEmail(registerRequestDto.getEmail());
    newUser.setPassword(passwordEncoder.encode(registerRequestDto.getPassword()));
    newUser.setRole("USER"); // Default role
    userRepository.save(newUser);
  }


}
