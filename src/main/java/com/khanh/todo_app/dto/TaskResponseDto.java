package com.khanh.todo_app.dto;

import com.khanh.todo_app.model.Task;

public class TaskResponseDto {
 private int id;
  private String title;
  private boolean completed;

  public TaskResponseDto() {
  }
  public TaskResponseDto(int id, String title, boolean completed) {
    this.id = id;
    this.title = title;
    this.completed = completed;
  }
  public int getId() {
    return id;
  }
  public void setId(int id) {
    this.id = id;
  }
  public String getTitle() {
    return title;
  }
  public void setTitle(String title) {
    this.title = title;
  }
  public boolean isCompleted() {
    return completed;
  }
  public void setCompleted(boolean completed) {
    this.completed = completed;
  }
  public static TaskResponseDto fromEntity(Task task) {
    TaskResponseDto dto = new TaskResponseDto();
    dto.setId(task.getId());
    dto.setTitle(task.getTitle());
    dto.setCompleted(task.isCompleted());
    return dto;
  }
}
