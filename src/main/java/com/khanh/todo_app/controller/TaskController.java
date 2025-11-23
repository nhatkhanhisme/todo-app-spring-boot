package com.khanh.todo_app.controller;

import com.khanh.todo_app.dto.TaskRequestDto;
import com.khanh.todo_app.dto.TaskResponseDto;
import com.khanh.todo_app.service.TaskService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

  // GET /api/v1/tasks - Lay tat ca cac cong viec @GetMapping
  @GetMapping
  public ResponseEntity<List<TaskResponseDto>> getAllTasks() {
    return ResponseEntity.ok(taskService.getAllTasks());
  }
  // GET /api/v1/tasks/{id} - Lay cong viec theo ID
  @GetMapping("/{id}")
  public ResponseEntity<TaskResponseDto>  getTaskById(@PathVariable int id) {
    TaskResponseDto responseDto = taskService.getTaskById(id);
    return ResponseEntity.ok(responseDto);
  }

  // POST /api/v1/tasks - Them cong viec moi
  @PostMapping
  public ResponseEntity<TaskResponseDto> createTask(@Valid @RequestBody TaskRequestDto requestDto) {
    TaskResponseDto responseDto = taskService.createTask(requestDto);

    // return dto va tra ve trang thai 201 Created
    return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
  }

  // PUT /api/v1/tasks/{id} - Cap nhat cong viec theo ID
  @PutMapping("/{id}")
  public ResponseEntity<TaskResponseDto>  updateTask(@PathVariable int id, @Valid @RequestBody TaskRequestDto requestDto) {
    TaskResponseDto responseDto = taskService.updateTask(id, requestDto);
    return ResponseEntity.ok(responseDto);
  }

  // DELETE /api/v1/tasks/{id} - Xoa cong viec theo ID
  @DeleteMapping("/{id}")
  public ResponseEntity<Void>  deleteTask(@PathVariable int id) {
    taskService.deleteTask(id);
    return ResponseEntity.noContent().build();
  }
}
