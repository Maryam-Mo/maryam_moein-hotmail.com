package com.example.todo_list.category;

import com.example.todo_list.exception.TodoListException;
import com.example.todo_list.todo.TodoList;
import com.example.todo_list.todo.TodoRepository;
import com.example.todo_list.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryTodoServiceImpl implements CategoryTodoService {
    @Autowired CategoryRepository categoryRepository;
    @Autowired TodoRepository todoRepository;

    @Override
    public void createTodoInfo(TodoList todoList) {
        Optional<Category> categoryOptional = categoryRepository.findById(todoList.getCategory().getId());
        if (!categoryOptional.isPresent()){
            throw new TodoListException("Category does not exist!");
        }
        categoryOptional.get().setTodoLists(categoryOptional.get().addTodoList(todoList));
        categoryRepository.save(categoryOptional.get());
    }

    @Override
    public TodoList update(TodoList todoList) {

        return null;
    }
}
