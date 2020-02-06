package com.example.todo_list.todo;

import java.util.List;

public interface TodoService {
    List<TodoList> findAll();
    TodoList findById(long id);
    TodoList create(TodoList todo);
    TodoList update(TodoList todo);
    void delete(long id);
    List<TodoList> findAllByStatus(TodoRequest todoRequest);
}
