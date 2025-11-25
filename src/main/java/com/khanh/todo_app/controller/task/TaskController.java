package com.khanh.todo_app.controller.task;

import com.khanh.todo_app.dto.TaskRequestDto;
import com.khanh.todo_app.dto.TaskResponseDto;
import com.khanh.todo_app.service.task.TaskService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;

// Controller layer for Task management
@RestController
// https://localhost:8080/api/v1/tasks
@RequestMapping("/api/v1/tasks") // Base URL for all endpoints in this controller
public class TaskController {

  private final TaskService taskService;

  // Constructor injection of TaskService
  public TaskController(TaskService taskService) {
    this.taskService = taskService;
  }

  // GET /api/v1/tasks - get all tasks
  @GetMapping
  public ResponseEntity<List<TaskResponseDto>> getAllTasks() {
    return ResponseEntity.ok(taskService.getAllTasks());
  }
  // GET /api/v1/tasks/{id} - get task by ID
  @GetMapping("/{id}")
  public ResponseEntity<TaskResponseDto>  getTaskById(@PathVariable int id) {
    TaskResponseDto responseDto = taskService.getTaskById(id);
    return ResponseEntity.ok(responseDto);
  }

  // POST /api/v1/tasks - create new task
  @PostMapping
  public ResponseEntity<TaskResponseDto> createTask(@Valid @RequestBody TaskRequestDto requestDto) {
    TaskResponseDto responseDto = taskService.createTask(requestDto);

    // Return HTTP 201 Created status code
    return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
  }

  // PUT /api/v1/tasks/{id} - Update task by ID
  @PutMapping("/{id}")
  public ResponseEntity<TaskResponseDto>  updateTask(@PathVariable int id, @Valid @RequestBody TaskRequestDto requestDto) {
    TaskResponseDto responseDto = taskService.updateTask(id, requestDto);
    return ResponseEntity.ok(responseDto);
  }

  // DELETE /api/v1/tasks/{id} - Delete task by ID
  @DeleteMapping("/{id}")
  public ResponseEntity<Void>  deleteTask(@PathVariable int id) {
    taskService.deleteTask(id);
    return ResponseEntity.noContent().build();
  }
}
