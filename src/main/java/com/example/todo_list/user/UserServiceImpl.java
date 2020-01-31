package com.example.todo_list.user;

import com.example.todo_list.exception.TodoListException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private BCryptPasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    public List<User> findUsers() {
        return userRepository.findAll();
    }

    @Override
    public User findUser(long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public User register(User user) throws TodoListException {
        User duplicatedUser = userRepository.findByUserName(user.getUserName());
        if (duplicatedUser != null) {
            throw new TodoListException("Username already existed!");
        }
        return userRepository.save(new User(user.getFirstName(),
                user.getLastName(), user.getContactNo(), user.getUserName(), passwordEncoder.encode(user.getPassword())));
    }

    @Override
    public User update(User user) throws TodoListException{
        Optional<User> userToUpdate = userRepository.findById(user.getId());
        if (!userToUpdate.isPresent()) {
            throw new TodoListException("User does not exist!");
        } else {
            User duplicatedUser = userRepository.findByUserName(user.getUserName());
            if (duplicatedUser != null && user.getId() != userToUpdate.get().getId()) {
                throw new TodoListException("Username already existed!");
            }
        }
        user.setPassword(passwordEncoder.encode(userToUpdate.get().getPassword()));
        return userRepository.save(user);
    }

    @Override
    public User updatePassword(long id, String newPassword) throws TodoListException{
        Optional<User> userToUpdate = userRepository.findById(id);
        if (!userToUpdate.isPresent()) {
            throw new TodoListException("User does not exist!");
        } else {
            User user = userToUpdate.get();
            user.setPassword(passwordEncoder.encode(newPassword));
            return userRepository.save(user);
        }
    }

    @Override
    public void delete(long id) throws TodoListException{
        Optional<User> userToDelete = userRepository.findById(id);
        if (!userToDelete.isPresent()) {
            throw new TodoListException("User does not exist!");
        } else {
            userRepository.delete(userToDelete.get());
        }
    }

    @Override
    public User logIn(User user) {
        return null;
    }

    @Override
    public User logOut(User user) {
        return null;
    }

}
