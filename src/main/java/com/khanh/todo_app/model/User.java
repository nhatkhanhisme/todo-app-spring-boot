package com.khanh.todo_app.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
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
  private long id;

  @NotBlank (message = "Username khong duoc de trong")
  @Column (unique = true, nullable = false, length = 50)
  private String username;

  @NotBlank (message = "Email khong duoc de trong")
  @Email (message = "Dinh dang email khong hop le")
  @Column (unique = true, nullable = false, length = 100)
  private String email;

  @NotBlank (message = "Password khong duoc de trong")
  @Column (nullable = false)
  @JsonIgnore
  private String password;

  @CreationTimestamp
  @Column (name = "created_at", nullable = false, updatable = false)
  private LocalDateTime createdAt;

  @PrePersist
  protected void onCreate() {
    this.createdAt = LocalDateTime.now();
  }
  // "USER" or "ADMIN"
  @Column (nullable = false, length = 20)
  private String role;
}
