package com.khanh.todo_app.service.auth;

import com.khanh.todo_app.dto.JwtAuthResponse;
import com.khanh.todo_app.dto.LoginRequestDto;
import com.khanh.todo_app.dto.RegisterRequestDto;
import com.khanh.todo_app.model.User;
import com.khanh.todo_app.repository.UserRepository;
import com.khanh.todo_app.security.jwt.JwtTokenProvider;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

// This class provides methods for user login and registration
// Consider implementing additional features such as password reset, email verification, etc.
@Service
public class AuthService {
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final AuthenticationManager authenticationManager;
  private final JwtTokenProvider jwtTokenProvider;

  public AuthService(UserRepository userRepository,
      PasswordEncoder passwordEncoder,
      AuthenticationManager authenticationManager,
      JwtTokenProvider jwtTokenProvider) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
    this.authenticationManager = authenticationManager;
    this.jwtTokenProvider = jwtTokenProvider;
  }

  // ---- CRUD operations for User entity can be added here ----
  public JwtAuthResponse login(LoginRequestDto loginDto) {
    // Verify username and password
    // Exception will be handled in controller or global exception handler
    Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
            loginDto.getUsername(),
            loginDto.getPassword()));

    // Set the authentication object in the SecurityContext for the current thread
    SecurityContextHolder.getContext().setAuthentication(authentication);
    // Tao JWT Token
    String token = jwtTokenProvider.generateToken(authentication);

    return new JwtAuthResponse(token);
  }

  @Transactional // Ensure transaction when saving new user
  public void registerUser(RegisterRequestDto registerRequestDto) {
    // Check if username or email already exists
    if (userRepository.existsByUsername(registerRequestDto.getUsername())) {
      throw new IllegalArgumentException("Username da ton tai");
    }
    // Check if email already exists
    if (userRepository.existsByEmail(registerRequestDto.getEmail())) {
      throw new IllegalArgumentException("Email da ton tai");
    }

    // Validation password strength
    if (registerRequestDto.getPassword().length() < 6) {
      throw new IllegalArgumentException("Mat khau phai co it nhat 6 ky tu");
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
