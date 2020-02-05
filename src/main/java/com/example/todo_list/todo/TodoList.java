package com.example.todo_list.todo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class TodoList {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private String status;

    public TodoList(String name, String description, String status) {
        this.name = name;
        this.status = status;
    }

    public TodoList(long id, String name, String description, String status) {
        this.id = id;
        this.name = name;
        this.status = status;
    }
}
