package com.khanh.todo_app.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class TaskRequestDto {
 @NotBlank (message = "Tieu de khong duoc de trong")
 @Size (min = 3, max = 100, message = "Tieu de phai co do dai tu 3 den 100 ky tu")
  private String title;
  private String description;
  private boolean completed;

  TaskRequestDto() {
  }
  public TaskRequestDto(String title, String description, boolean completed) {
    this.title = title;
    this.description = description;
    this.completed = completed;
  }

  public String getTitle() {
    return title;
  }
  public void setTitle(String title) {
    this.title = title;
  }
  public String getDescription() {
    return description;
  }
  public void setDescription(String description) {
    this.description = description;
  }
  public boolean isCompleted() {
    return completed;
  }
  public void setCompleted(boolean completed) {
    this.completed = completed;
  }
}
