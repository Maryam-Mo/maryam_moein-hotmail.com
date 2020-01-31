package com.example.todo_list.api;

import com.example.todo_list.category.Category;
import com.example.todo_list.todo.TodoList;
import com.example.todo_list.todo.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/todo")
public class TodoResource {

    @Autowired private TodoService todoService;

    @GetMapping("/findALl")
    public List<TodoList> findAll() {
        return todoService.findAll();
    }

    @GetMapping("/findById/{id}")
    public TodoList findTodoList(@PathVariable long id) {
        return todoService.findById(id);
    }

    @PostMapping("/create")
    public TodoList create(@RequestBody TodoList todoList) {
        return todoService.create(todoList);
    }

    @PostMapping("/update")
    public TodoList update(@RequestBody TodoList todoList){
        return todoService.update(todoList);
    }

    @DeleteMapping("delete/{id}")
    public void delete(@PathVariable long id){
        todoService.delete(id);
    }
}
