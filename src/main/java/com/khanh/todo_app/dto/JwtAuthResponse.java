package com.khanh.todo_app.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class JwtAuthResponse {
  private String accessToken;
  private String tokenType = "Bearer"; // Loai token chuan nhat

  public JwtAuthResponse(String accessToken) {
    this.accessToken = accessToken;
  }
}
