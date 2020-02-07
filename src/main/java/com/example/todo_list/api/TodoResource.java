package com.example.todo_list.api;

import com.example.todo_list.category.Category;
import com.example.todo_list.category.CategoryTodoService;
import com.example.todo_list.todo.TodoList;
import com.example.todo_list.todo.TodoRequest;
import com.example.todo_list.todo.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/todo")
public class TodoResource {

    @Autowired private TodoService todoService;
    @Autowired private CategoryTodoService categoryTodoService;

    @GetMapping("/findAll")
    public List<TodoList> findAll() {
        return todoService.findAll();
    }

    @PostMapping("/findAllByStatus")
    public List<TodoList> findAllByStatus(@RequestBody TodoRequest todoRequest) {
        return todoService.findAllByStatus(todoRequest);
    }

    @GetMapping("/findById/{id}")
    public TodoList findTodoList(@PathVariable long id) {
        return todoService.findById(id);
    }

    @PostMapping("/create")
    public TodoList create(@RequestBody TodoList todoList) {
        TodoList todo = todoService.create(todoList);
        categoryTodoService.createTodoInfo(todoList);
        return todo;
    }

    @PostMapping("/update")
    public TodoList update(@RequestBody TodoList todoList){
        return todoService.update(todoList);
    }

    @PutMapping("delete/{id}")
    public TodoList delete(@PathVariable long id){
        return categoryTodoService.delete(id);
    }
}
