package com.khanh.todo_app.controller.auth;

import com.khanh.todo_app.dto.JwtAuthResponse;
import com.khanh.todo_app.dto.LoginRequestDto;
import com.khanh.todo_app.dto.RegisterRequestDto;
import com.khanh.todo_app.service.auth.AuthService;
import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

  private final AuthService authService;

  public AuthController(AuthService authService) {
    this.authService = authService;
  }

  // POST /api/v1/auth/login
  @PostMapping("/login")
  public ResponseEntity<JwtAuthResponse> login(@RequestBody LoginRequestDto loginDto) {
    // Gọi Service để xử lý login và tạo token
    JwtAuthResponse response = authService.login(loginDto);

    return ResponseEntity.ok(response);
  }

  // POST /api/v1/auth/register - User registration
  @PostMapping("/register")
  public ResponseEntity<String> registerUser(@Valid @RequestBody RegisterRequestDto requestDto) {
    try {
      authService.registerUser(requestDto);
      return ResponseEntity.status(HttpStatus.CREATED).body("Dang ky thanh cong");
    } catch (RuntimeException e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
  }
  // ...existing code...

  // // GET /api/v1/auth/users - CHỈ dùng để test
  // @GetMapping("/users")
  // public ResponseEntity<List<User>> getAllUsers() {
  // List<User> users = userRepository.findAll();
  // return ResponseEntity.ok(users);
  // }
}
