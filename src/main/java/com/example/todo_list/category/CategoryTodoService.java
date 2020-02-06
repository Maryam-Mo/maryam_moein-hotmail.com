package com.example.todo_list.category;

import com.example.todo_list.todo.TodoList;

public interface CategoryTodoService {
    void createTodoInfo(TodoList todoList);

    TodoList update(TodoList todoList);
}
