package com.project.ecommerce.controller;

import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final List<Map<String, String>> categories = new ArrayList<>();

    @GetMapping
    public List<Map<String, String>> getAllCategories() {
        return categories;
    }

    @PostMapping
    public Map<String, String> createCategory(@RequestBody Map<String, String> category) {
        categories.add(category);
        return category;
    }
}
