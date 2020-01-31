package com.example.todo_list.api;

import com.example.todo_list.user.User;
import com.example.todo_list.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/user")
public class UserResource {

    @Autowired
    private UserService userService;

    @GetMapping("/findALl")
    public List<User> findUsers() {
        return userService.findUsers();
    }

    @GetMapping("/findById/{id}")
    public User findUser(@PathVariable long id) {
        return userService.findUser(id);
    }


    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return userService.register(user);
    }

    @PostMapping("/update/details")
    public User updateDetails(@RequestBody User user){
        return userService.update(user);
    }

    @PostMapping("/update/password/{id}")
    public User updatePassword(@PathVariable long id, @RequestBody String newPassword){
        return userService.updatePassword(id, newPassword);
    }

    @DeleteMapping("delete/{id}")
    public void delete(@PathVariable long id){
        userService.delete(id);
    }


}
