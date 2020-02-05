package com.example.todo_list.user;

import com.example.todo_list.category.Category;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String firstName;
    private String lastName;
    private String contactNo;
    private String userName;
    private String password;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Category> categories = new ArrayList<>();

    public User(long id, String firstName, String lastName, String contactNo, String userName, String password) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.contactNo = contactNo;
        this.userName = userName;
        this.password = password;

    }

    public User(String firstName, String lastName, String contactNo, String userName, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.contactNo = contactNo;
        this.userName = userName;
        this.password = password;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public List<Category> addCategory(Category category) {
        categories.add(category);
        category.setUser(this);
        return categories;
    }

    public List<Category> removeCategory(Category category) {
        categories.remove(category);
        category.setUser(null);
        return categories;
    }
}
