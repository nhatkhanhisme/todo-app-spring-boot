package com.khanh.todo_app.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequestDto {

  @NotBlank(message = "Username khong duoc de trong")
  private String username;

  @NotBlank(message = "Password khong duoc de trong")
  @Email(message = "Dinh dang email khong hop le")
  private String email;

  @NotBlank(message = "Password khong duoc de trong")
  private String password;
}
