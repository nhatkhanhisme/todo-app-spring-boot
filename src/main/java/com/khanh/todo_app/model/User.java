package com.khanh.todo_app.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data // Lombok annotation to generate getters, setters, toString, etc.
@NoArgsConstructor
@AllArgsConstructor
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @NotBlank (message = "Username khong duoc de trong")
  @Column (unique = true)
  private String username;

  @NotBlank (message = "Email khong duoc de trong")
  @Email (message = "Dinh dang email khong hop le")
  @Column (unique = true)
  private String email;

  @NotBlank (message = "Password khong duoc de trong")
  private String password;

  // Quan trong cho bao mat
  private String Role; // "USER" hoac "ADMIN"
}
