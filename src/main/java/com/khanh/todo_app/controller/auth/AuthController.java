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
  public ResponseEntity<JwtAuthResponse> login(@Valid @RequestBody LoginRequestDto loginDto) {
    // Exception will be handled in global exception handler
    JwtAuthResponse response = authService.login(loginDto);

    return ResponseEntity.ok(response);
  }

  // POST /api/v1/auth/register - User registration
  @PostMapping("/register")
  public ResponseEntity<String> registerUser(@Valid @RequestBody RegisterRequestDto requestDto) {
    authService.registerUser(requestDto);
    return new ResponseEntity<>("Dang ky thanh cong", HttpStatus.CREATED);
  }
}
