package com.example.todo_list.category;

import java.util.List;

public interface CategoryService {

    List<Category> findAll();
    Category findById(long id);
    Category create(Category category);
    Category update(Category category);
    Category delete(long id);
}
