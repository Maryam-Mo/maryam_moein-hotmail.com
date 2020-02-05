package com.example.todo_list.todo;

import com.example.todo_list.TodoServiceTestListApplicationTests;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class TodoServiceTest extends TodoServiceTestListApplicationTests {

    @Autowired private TodoService todoService;

    @Test
    public void create(){
        TodoList todoToSave = new TodoList(1, "Todo1", "chgnvjhmb", "new");
        TodoList savedTodo = todoService.create(todoToSave);
        assertNotNull(savedTodo);
        assertEquals(todoToSave.getId(), savedTodo.getId());
        assertEquals(todoToSave.getName(), savedTodo.getName());
        assertEquals(todoToSave.getStatus(), savedTodo.getStatus());
    }


}
