package com.example.todo_list.category;

import com.example.todo_list.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

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
}
