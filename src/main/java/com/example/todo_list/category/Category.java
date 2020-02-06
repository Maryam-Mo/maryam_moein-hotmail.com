package com.example.todo_list.category;

import com.example.todo_list.todo.TodoList;
import com.example.todo_list.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Category {

    @Id
    @GeneratedValue
    private long id;
    private String name;

    @ManyToOne()
    @JoinColumn(name = "user_id")
    private User user;
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore
    private List<TodoList> todoLists = new ArrayList<>();


    public Category(String name) {
        this.name = name;
    }

    public Category(String name, User user) {
        this.name = name;
        this.user = user;
    }

    public Category(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public void setTodoLists(List<TodoList> todoLists) {
        this.todoLists = todoLists;
    }

    public List<TodoList> addTodoList(TodoList todoList) {
        todoLists.add(todoList);
        todoList.setCategory(this);
        return todoLists;
    }

    public List<TodoList> removeTodoList(TodoList todoList) {
        todoLists.remove(todoList);
        todoList.setCategory(null);
        return todoLists;
    }
}
