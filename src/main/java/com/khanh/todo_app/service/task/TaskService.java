package com.khanh.todo_app.service.task;

import com.khanh.todo_app.dto.TaskRequestDto;
import com.khanh.todo_app.dto.TaskResponseDto;
import com.khanh.todo_app.model.Task;
import com.khanh.todo_app.repository.TaskRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

// Service layer for Task management
@Service
public class TaskService {

  private final TaskRepository taskRepository;

  // Constructor injection of TaskRepository
  public TaskService(TaskRepository taskRepository) {
    this.taskRepository = taskRepository;
  }

  // Get all tasks
  public List<TaskResponseDto> getAllTasks() {
    // Get all Task entities from the database
    List<Task> tasks = taskRepository.findAll();

    // Transfer Object from Entity to DTO (Mapping)
    return tasks.stream().map(task -> {
      TaskResponseDto dto = new TaskResponseDto();
      dto.setId(task.getId());
      dto.setTitle(task.getTitle());
      dto.setCompleted(task.isCompleted());
      return dto;
    }).collect(Collectors.toList());
  }

  // Get task by ID
  public TaskResponseDto getTaskById(int id) {
    // Find the Task entity by ID
    // If not found, throw an exception
    Task task = taskRepository.findById(id).orElseThrow(
        () -> new RuntimeException("Task not found"));

    // Transfer Object from Entity to DTO (Mapping)
    return TaskResponseDto.fromEntity(task);
  }

  // Create a new task
  public TaskResponseDto createTask(TaskRequestDto requestDto) {
    // Transfer Object from DTO -> Entity (Mapping)
    Task newTask = new Task();
    newTask.setTitle(requestDto.getTitle());
    newTask.setDescription(requestDto.getDescription());
    newTask.setCompleted(requestDto.isCompleted());

    // Luu Entity vao DB
    Task savedTask = taskRepository.save(newTask);

    // Chuyen doi Entity -> DTO (Mapping)
    TaskResponseDto responseDto = new TaskResponseDto();
    responseDto.setId(savedTask.getId());
    responseDto.setTitle(savedTask.getTitle());
    responseDto.setCompleted(savedTask.isCompleted());

    return responseDto;
  }

  public TaskResponseDto updateTask(int id, TaskRequestDto requestDto) {

    // kiem tra task da ton tai chua
    Task existingTask = taskRepository.findById(id).orElseThrow(
        () -> new RuntimeException("Task not found"));

    // Cập nhật các trường của công việc
    if (requestDto.getTitle() != null) {
      existingTask.setTitle(requestDto.getTitle());
    }

    existingTask.setCompleted(requestDto.isCompleted());
    // Luu va tra ve Response DTO
    Task savedTask = taskRepository.save(existingTask);

    // Chuyen doi Entity -> DTO (Mapping)
    return TaskResponseDto.fromEntity(savedTask);
  }

  public void deleteTask(int id) {
    taskRepository.findById(id).orElseThrow(
        () -> new RuntimeException("Task not found"));
    taskRepository.deleteById(id);
  }
}
