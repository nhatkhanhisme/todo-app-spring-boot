package com.khanh.todo_app.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequestDto {
  @NotBlank(message = "Username khong duoc de trong")
  private String username;
  @NotBlank(message = "Password khong duoc de trong")
  private String password;
}
