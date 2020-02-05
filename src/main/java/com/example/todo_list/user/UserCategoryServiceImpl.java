package com.example.todo_list.user;

import com.example.todo_list.category.Category;
import com.example.todo_list.category.CategoryRepository;
import com.example.todo_list.exception.TodoListException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserCategoryServiceImpl implements UserCategoryService {

    @Autowired UserRepository userRepository;
    @Autowired CategoryRepository categoryRepository;

    @Override
    public void createCategoryInfo(Category category) {
        Optional<User> userOptional = userRepository.findById(category.getUser().getId());
        if (!userOptional.isPresent()){
            throw new TodoListException("User does not exist!");
        }
        userOptional.get().setCategories(userOptional.get().addCategory(category));
        userRepository.save(userOptional.get());
    }

    @Override
    public Category deleteCategoryInfo(long id) {
        Optional<Category> categoryOptional = categoryRepository.findById(id);
        if (!categoryOptional.isPresent()) {
            throw new TodoListException("Category does not exist!");
        }
        Optional<User> userOptional = userRepository.findById(categoryOptional.get().getUser().getId());
        if (!userOptional.isPresent()){
            throw new TodoListException("User does not exist!");
        }
        userOptional.get().setCategories(userOptional.get().removeCategory(categoryOptional.get()));
        userRepository.save(userOptional.get());
        categoryRepository.delete(categoryOptional.get());
        return categoryOptional.get();
    }
}
