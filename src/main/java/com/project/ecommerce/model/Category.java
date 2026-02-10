package com.project.ecommerce.model;

import jakarta.persistence.*;

@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int categoryId;

<<<<<<< HEAD
    @Column(nullable = false)
    private String categoryName;

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

=======
    private String categoryName;

    // getters & setters

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

>>>>>>> b5d71d248b6bcc0562807e56da4617f8e4f23d39
    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
<<<<<<< HEAD


=======
>>>>>>> b5d71d248b6bcc0562807e56da4617f8e4f23d39
