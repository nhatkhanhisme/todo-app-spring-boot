package com.khanh.todo_app.model;

import jakarta.persistence.*; // import JPA library

@Entity // Mark this class as a JPA entity
@Table(name = "tasks") // Map this entity to the "tasks" table in the database
public class Task {
  @Id // Mark this field as the primary key
  @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generate the ID value
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
