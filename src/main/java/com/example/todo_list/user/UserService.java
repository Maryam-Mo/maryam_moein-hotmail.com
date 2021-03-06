package com.example.todo_list.user;

import com.example.todo_list.category.Category;

import java.util.List;

public interface UserService {

    List<User> findUsers();
    User findUser(long id);
    User register(User user);
    User update(User user);
    User updatePassword(long id, String newPassword);
    void delete(long id);
    User logIn(User user);
}
