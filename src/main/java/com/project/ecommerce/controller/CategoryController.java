package com.project.ecommerce.controller;

import com.project.ecommerce.model.Category;
import com.project.ecommerce.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@CrossOrigin
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    // Create category
    @PostMapping
    public Category createCategory(@RequestBody Category category) {
        return categoryService.createCategory(category);
    }

    // Get all categories
    @GetMapping
    public List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }

    // Get category by id
    @GetMapping("/{id}")
    public Category getCategory(@PathVariable int id) {
        return categoryService.getCategoryById(id);
    }

    // Update category
    @PutMapping("/{id}")
    public Category updateCategory(
            @PathVariable int id,
            @RequestBody Category category
    ) {
        return categoryService.updateCategory(id, category);
    }

    // Delete category
    @DeleteMapping("/{id}")
    public String deleteCategory(@PathVariable int id) {
        categoryService.deleteCategory(id);
        return "Category deleted successfully";
    }
}
