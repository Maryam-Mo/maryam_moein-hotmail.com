package com.example.todo_list.api;

import com.example.todo_list.category.Category;
import com.example.todo_list.category.CategoryService;
import com.example.todo_list.user.UserCategoryService;
import com.example.todo_list.user.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/category")
public class CategoryResource {

    @Autowired CategoryService categoryService;
    @Autowired UserCategoryService userCategoryService;

    @ApiOperation(value = "Find All Categories")
    @GetMapping("/findAll")
    public List<Category> findAll() {
        return categoryService.findAll();
    }

    @GetMapping("/findById/{id}")
    public Category findCategory(@PathVariable long id) {
        return categoryService.findById(id);
    }

    @PostMapping("/create")
    public Category create(@RequestBody Category category) {
        Category createdCategory = categoryService.create(category);
        userCategoryService.createCategoryInfo(category);
        return createdCategory;
    }

    @PostMapping("/update")
    public Category update(@RequestBody Category category){
        return categoryService.update(category);
    }

    @PutMapping("delete/{id}")
    public Category delete(@PathVariable long id){
        return userCategoryService.deleteCategoryInfo(id);
    }
}
