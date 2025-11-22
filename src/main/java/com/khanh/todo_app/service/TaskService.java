package com.khanh.todo_app.service;

import com.khanh.todo_app.dto.TaskRequestDto;
import com.khanh.todo_app.dto.TaskResponseDto;
import com.khanh.todo_app.model.Task;
import com.khanh.todo_app.repository.TaskRepository;

import jakarta.validation.Valid;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

// đánh dấu để Spring biết đây là nơi xử lý logic nghiệp vụ
@Service
public class TaskService {

  private final TaskRepository taskRepository;

  // Inject repository vào service
  public TaskService(TaskRepository taskRepository) {
    this.taskRepository = taskRepository;
  }

  // Get all tasks
  public List<TaskResponseDto> getAllTasks() {
    // Lay day sach Entity tu DB
    List<Task> tasks = taskRepository.findAll();

    // Chuyen doi danh sach Entity -> DTO (Mapping)
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
    Task task = taskRepository.findById(id).orElseThrow(
      () -> new RuntimeException("Task not found")
    );
    // Chuyen doi Entity -> DTO (Mapping)
    return TaskResponseDto.fromEntity(task);
  }

  // Create a new task
  public TaskResponseDto  createTask(TaskRequestDto requestDto) {
    // Chuyen doi DTO -> Entity (Mapping)
    Task newTask = new Task();
    newTask.setTitle(requestDto.getTitle());
    newTask.setDescription(requestDto.getDescription());
    newTask.setCompleted(requestDto.isCompleted());

    // Luu Entity vao DB
    Task savedTask = taskRepository.save(newTask);

    // Chuyen doi Entity -> DTO (Mapping)
    TaskResponseDto responseDto = new TaskResponseDto();
    responseDto.setId( savedTask.getId());
    responseDto.setTitle( savedTask.getTitle());
    responseDto.setCompleted( savedTask.isCompleted());

    return responseDto;
  }

  public TaskResponseDto updateTask(int id, TaskRequestDto requestDto) {

    // kiem tra task da ton tai chua
    Task existingTask = taskRepository.findById(id).orElseThrow(
      () -> new RuntimeException("Task not found")
    );

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
      () -> new RuntimeException("Task not found")
    );
    taskRepository.deleteById(id);
  }
}
