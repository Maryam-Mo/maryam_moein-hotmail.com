package com.example.todo_list.user;

import com.example.todo_list.category.Category;

public interface UserCategoryService {
    void createCategoryInfo(Category category);

    Category deleteCategoryInfo(long id);
}
