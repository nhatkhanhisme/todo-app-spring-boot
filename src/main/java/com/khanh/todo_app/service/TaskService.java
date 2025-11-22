package com.khanh.todo_app.service;

import com.khanh.todo_app.model.Task;
import com.khanh.todo_app.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

// đánh dấu để Spring biết đây là nơi xử lý logic nghiệp vụ
@Service
public class TaskService {

  private final TaskRepository taskRepository;

  // Inject repository vào service
  public TaskService(TaskRepository taskRepository) {
    this.taskRepository = taskRepository;
  }

  // Get all tasks
  public List<Task> getAllTasks() {
    return taskRepository.findAll();
  }
  public Task getTaskById(int id) {
    return taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Task not found"));
  }

  // Create a new task
  public Task createTask(Task task) {
    return taskRepository.save(task);
  }

  public Task updateTask(int id, Task taskDetails) {

    // kiem tra task da ton tai chua
    Task existingTask = taskRepository.findById(id).orElseThrow(
      () -> new RuntimeException("Task not found")
    );

    // Cập nhật các trường của công việc
    if (taskDetails.getTitle() != null) {
      existingTask.setTitle(taskDetails.getTitle());
    }

    existingTask.setCompleted(taskDetails.isCompleted());
    return taskRepository.save(existingTask);
  }
}
