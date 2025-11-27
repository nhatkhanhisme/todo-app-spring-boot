package com.khanh.todo_app.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequestDto {

  @NotBlank(message = "Username khong duoc de trong")
  @Size (min = 3, max = 50, message = "Username phai co do dai tu 3 den 50 ky tu")
  private String username;

  @NotBlank(message = "Password khong duoc de trong")
  @Email(message = "Dinh dang email khong hop le")
  private String email;

  @NotBlank(message = "Password khong duoc de trong")
  @Size (min = 6, max = 100, message = "Password phai co do dai tu 6 den 100 ky tu")
  private String password;
}
