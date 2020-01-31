package com.example.todo_list.todo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<TodoList, Long> {
    TodoList findByName(String name);
}
