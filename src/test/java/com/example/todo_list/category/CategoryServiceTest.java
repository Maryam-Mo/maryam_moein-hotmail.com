package com.example.todo_list.category;

import com.example.todo_list.TodoServiceTestListApplicationTests;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


public class CategoryServiceTest extends TodoServiceTestListApplicationTests {

    @Autowired private CategoryService categoryService;

    @Test
    public void create(){
        Category categoryToSave = new Category(1, "category1");
        Category savedCategory = categoryService.create(categoryToSave);
        assertNotNull(savedCategory);
        assertEquals(categoryToSave.getId(), savedCategory.getId());
        assertEquals(categoryToSave.getName(), savedCategory.getName());
    }

    @Test
    public void update(){
        Category categoryToSave = new Category(1, "category1");
        Category categoryToUpdate = categoryService.create(categoryToSave);
        categoryToUpdate.setName("category2");
        Category updatedCategory = categoryService.update(categoryToUpdate);
        assertNotNull(updatedCategory);
        assertEquals(categoryToUpdate.getId(), updatedCategory.getId());
        assertEquals(categoryToUpdate.getName(), updatedCategory.getName());
    }


}
