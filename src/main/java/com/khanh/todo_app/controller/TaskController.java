package com.khanh.todo_app.controller;

import com.khanh.todo_app.model.Task;
import com.khanh.todo_app.service.TaskService;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;

// Bao hieu day la REST API
@RestController
//// Định nghĩa đường dẫn gốc: http://localhost:8080/api/v1/tasks
@RequestMapping("/api/v1/tasks") // Base URL cho tat ca cac API lien quan den cong viec
public class TaskController {

  private final TaskService taskService;

  // Inject service vao controller
  public TaskController(TaskService taskService) {
    this.taskService = taskService;
  }

  // GET /api/v1/tasks - Lay tat ca cac cong viec
  @GetMapping
  public List<Task> getAllTasks() {
    return taskService.getAllTasks();
  }
  // GET /api/v1/tasks/{id} - Lay cong viec theo ID
  @GetMapping("/{id}")
  public Task getTaskById(@PathVariable int id) {
    return taskService.getTaskById(id);
  }

  // POST /api/v1/tasks - Them cong viec moi
  @PostMapping
  public Task createTask(@Valid @RequestBody Task task) {
    return taskService.createTask(task);
  }

  // PUT /api/v1/tasks/{id} - Cap nhat cong viec theo ID
  @PutMapping("/{id}")
  public Task updateTask(@PathVariable int id, @Valid @RequestBody Task taskDetails) {
    return taskService.updateTask(id, taskDetails);
  }
}
