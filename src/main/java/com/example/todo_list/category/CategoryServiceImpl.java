package com.example.todo_list.category;

import com.example.todo_list.exception.TodoListException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category findById(long id) {
        return categoryRepository.findById(id).get();
    }

    @Override
    public Category create(Category category) {
        Category savedCategory = categoryRepository.findByName(category.getName());
        if (savedCategory != null) {
            throw new TodoListException("Category name already exist!");
        }
        return categoryRepository.save(category);
    }

    @Override
    public Category update(Category category){
        Optional<Category> categoryOptional = categoryRepository.findById(category.getId());
        if (!categoryOptional.isPresent()) {
            throw new TodoListException("Category does not exist!");
        } else {
            Category duplicatedCategory = categoryRepository.findByName(category.getName());
            if (duplicatedCategory != null && category.getId() != categoryOptional.get().getId()) {
                throw new TodoListException("Category name is already existed!");
            }
        }
        return categoryRepository.save(category);
    }

    @Override
    public Category delete(long id) {
        Optional<Category> categoryOptional = categoryRepository.findById(id);
        if (!categoryOptional.isPresent()) {
            throw new TodoListException("Category does not exist!");
        }
        categoryRepository.delete(categoryOptional.get());
        return categoryOptional.get();
    }

}
