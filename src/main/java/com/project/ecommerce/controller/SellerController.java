package com.project.ecommerce.controller;

import com.project.ecommerce.model.Product;
import com.project.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sellers")
public class SellerController {

    @Autowired
    private ProductService productService;

    // ADD PRODUCT
    @PostMapping("/{sellerId}/add-product")
    public Product addProduct(
            @PathVariable int sellerId,
            @RequestBody Product product) {

        return productService.addProduct(product, sellerId);
    }

    // VIEW PRODUCTS BY SELLER
    @GetMapping("/{sellerId}/products")
    public List<Product> getSellerProducts(@PathVariable int sellerId) {
        return productService.getProductsBySeller(sellerId);
    }

    @PutMapping("/products/{productId}")
    public Product updateProduct(@PathVariable int productId,
                                 @RequestBody Product product) {
        return productService.updateProduct(productId, product);
    }

    @DeleteMapping("/products/{productId}")
    public String deleteProduct(@PathVariable int productId) {
        productService.deleteProduct(productId);
        return "Product deleted successfully";
    }
}
