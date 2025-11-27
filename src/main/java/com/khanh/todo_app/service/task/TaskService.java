package com.khanh.todo_app.service.task;

import com.khanh.todo_app.dto.TaskRequestDto;
import com.khanh.todo_app.dto.TaskResponseDto;
import com.khanh.todo_app.model.Task;
import com.khanh.todo_app.repository.TaskRepository;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;
import com.khanh.todo_app.mapper.TaskMapper;

// Service layer for Task management
@Service
public class TaskService {

  private final TaskRepository taskRepository;
  private final TaskMapper taskMapper;

  // Constructor injection of TaskRepository
  public TaskService(TaskRepository taskRepository, TaskMapper taskMapper) {
    this.taskRepository = taskRepository;
    this.taskMapper = taskMapper; // constructor injection of TaskMapper
  }

  // Get all tasks
  public List<TaskResponseDto> getAllTasks() {
    // Get all Task entities from the database
    List<Task> tasks = taskRepository.findAll();

    return taskMapper.toDtoList(tasks);
  }

  // Get task by ID
  public TaskResponseDto getTaskById(int id) {
    // Find the Task entity by ID
    // If not found, throw an exception
    Task task = taskRepository.findById(id).orElseThrow(
        () -> new RuntimeException("Task not found"));

    return taskMapper.toDto(task);
  }

  // Create a new task
  public TaskResponseDto createTask(TaskRequestDto requestDto) {
    Task newTask = taskMapper.toEntity(requestDto);
    // Luu Entity vao DB
    Task savedTask = taskRepository.save(newTask);

    return taskMapper.toDto(savedTask);
  }

  public TaskResponseDto updateTask(int id, TaskRequestDto requestDto) {

    // check if task exists (404)
    Task existingTask = taskRepository.findById(id).orElseThrow(
        () -> new RuntimeException("Task not found"));

    taskMapper.updateEntityFromDto(requestDto, existingTask);
    Task savedTask = taskRepository.save(existingTask);
    return taskMapper.toDto(savedTask);
  }

  public void deleteTask(int id) {
    taskRepository.findById(id).orElseThrow(
        () -> new RuntimeException("Task not found"));
    taskRepository.deleteById(id);
  }
}
