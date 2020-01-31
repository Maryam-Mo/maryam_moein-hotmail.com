package com.example.todo_list.user;

import com.example.todo_list.exception.TodoListException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import static org.mockito.ArgumentMatchers.*;


import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class UserServiceTest {
    private UserService service;
    @MockBean
    private UserRepository userRepository;
    private BCryptPasswordEncoder passwordEncoder;

    @Before
    public void setUp() {
        service = new UserServiceImpl(userRepository);
        passwordEncoder = new BCryptPasswordEncoder();
    }

    @Test
    public void create() {
        User user = new User();
        user.setUserName("test");
        user.setPassword(passwordEncoder.encode("test"));

        when(userRepository.save(any(User.class))).thenReturn(getSavedUser(user));

        User savedUser = service.register(user);
        assertNotNull(savedUser);
        assertNotNull(savedUser.getId());
        assertEquals(savedUser.getUserName(), user.getUserName());
        assertFalse(passwordEncoder.matches(savedUser.getPassword(), user.getPassword()));
    }

    @Test(expected = TodoListException.class)
    public void createDuplicateThenThrowException() throws TodoListException {
        String rawPassword = "test";

        User user = new User();
        user.setUserName("test");
        user.setPassword(rawPassword);

        when(userRepository.findByUserName(anyString())).thenReturn(getSavedUser(user));

        service.register(user);
    }

    @Test
    public void update() {
        User userToUpdate = new User();
        userToUpdate.setId(1l);
        userToUpdate.setUserName("test");
        userToUpdate.setPassword("test");
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(userToUpdate));

        User updatedUser = new User();
        updatedUser.setId(1l);
        updatedUser.setUserName("test update");
        updatedUser.setPassword(passwordEncoder.encode("test"));

        when(userRepository.save(any(User.class))).thenReturn(updatedUser);

        User user = service.update(userToUpdate);
        assertNotNull(user);
        assertEquals(user.getUserName(), updatedUser.getUserName());
        assertTrue((user.getPassword().equalsIgnoreCase(updatedUser.getPassword())));
    }

    @Test
    public void updatePassword() {
        User user = new User();
        user.setId(1l);
        user.setUserName("test");
        user.setPassword("test");

        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));

        String rawPassword = "testUpdate";

        User updatedUser = new User();
        updatedUser.setUserName(user.getUserName());
        updatedUser.setPassword(passwordEncoder.encode(rawPassword));

        when(userRepository.save(any(User.class))).thenReturn(updatedUser);

        User receivedUser = service.updatePassword(user.getId(), rawPassword);
        assertNotNull(receivedUser);
        assertEquals(receivedUser.getUserName(), user.getUserName());
        assertTrue(passwordEncoder.matches(rawPassword, receivedUser.getPassword()));
    }

    private User getSavedUser(User user) {
        User savedUser = new User();
        savedUser.setId(1l);
        savedUser.setUserName(user.getUserName());
        savedUser.setPassword(passwordEncoder.encode(user.getPassword()));
        return savedUser;
    }

}
