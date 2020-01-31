package com.example.todo_list.user;

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
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String firstName;
    private String lastName;
    private String contactNo;
    private String userName;
    private String password;

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
}
