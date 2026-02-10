package com.project.ecommerce.controller;

<<<<<<< HEAD
import com.project.ecommerce.model.Category;
import com.project.ecommerce.repository.CategoryRepository;
import com.project.ecommerce.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
=======
>>>>>>> b5d71d248b6bcc0562807e56da4617f8e4f23d39
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
<<<<<<< HEAD
    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestBody Category category) {
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.createCategory(category));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategory(@PathVariable int id) {
        return categoryService.getCategory(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable int id, @RequestBody Category categoryDetails) {
        return ResponseEntity.ok(categoryService.updateCategory(id, categoryDetails));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable int id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
=======

    private final List<Map<String, String>> categories = new ArrayList<>();

    @GetMapping
    public List<Map<String, String>> getAllCategories() {
        return categories;
    }

    @PostMapping
    public Map<String, String> createCategory(@RequestBody Map<String, String> category) {
        categories.add(category);
        return category;
>>>>>>> b5d71d248b6bcc0562807e56da4617f8e4f23d39
    }
}
