package com.khanh.todo_app.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.khanh.todo_app.model.Task;

// Repository interface for Task entity
// inherit JpaRepository to get CRUD methods
@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {

}
