package com.example.todo_list.todo;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodoRepository extends JpaRepository<TodoList, Long> {

    TodoList findByName(String name);
    List<TodoList> findAllByStatus(String status);
}
