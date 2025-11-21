package com.khanh.todo_app.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.khanh.todo_app.model.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {
  // JpaRepository đã có sẵn các hàm: findAll(), save(), findById(),
  // deleteById()...

}
