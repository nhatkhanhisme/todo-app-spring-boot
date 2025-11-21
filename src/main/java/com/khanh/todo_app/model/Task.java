package com.khanh.todo_app.model;

import jakarta.persistence.*; // import JPA library
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity // Bao hieu day la mot entity (mot bang trong CSDL)
@Table(name = "tasks") // Dat ten bang trong CSDL la "tasks"
public class Task {
  @Id // Danh dau truong nay la khoa chinh
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @NotBlank(message = "Tieu de khong duoc de trong")
  @Size(min = 3, max = 100, message = "Tieu de phai tu 3 den  100 ky tu")
  
  private int id;
  private String title;
  private String description;
  private boolean completed;

  public Task() {
  }

  public Task(int id, String title, String description, boolean completed) {

    this.id = id;
    this.title = title;
    this.description = description;
    this.completed = completed;
  }

  // Getter & Setter methods
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
