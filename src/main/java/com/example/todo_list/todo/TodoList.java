package com.example.todo_list.todo;

import com.example.todo_list.category.Category;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

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

    @ManyToOne()
    @JoinColumn(name = "category_id")
    private Category category;

    public void setCategory(Category category) {
        this.category = category;
    }

    public TodoList(String name, String status) {
        this.name = name;
        this.status = status;
    }

    public TodoList(String name, String status, Category category) {
        this.name = name;
        this.status = status;
        this.category = category;
    }

    public TodoList(long id, String name, String status, Category category) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.category = category;
    }
}
