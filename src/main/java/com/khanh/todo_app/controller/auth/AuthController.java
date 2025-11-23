package com.khanh.todo_app.controller.auth;

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
@RequestMapping("/api/v1/auth") // public trong SecurityConfig
public class AuthController {

  private final  AuthService authService;

  public AuthController(AuthService authService) {
    this.authService = authService;
  }

  // ---- Authentication endpoints can be added here ----

  // POST /api/v1/auth/register - User registration
  @PostMapping("/register")
  public ResponseEntity<String> registerUser(@Valid @RequestBody RegisterRequestDto requestDto) {
    try {
      authService.registerUser(requestDto);
      
      // Tra ve trang thai 201 Created neu dang ky thanh cong
      return new ResponseEntity<>("Dang ky thanh cong",  HttpStatus.CREATED);
    } catch (RuntimeException e) {
      // TODO: handle exception
      return  new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
  }
}
