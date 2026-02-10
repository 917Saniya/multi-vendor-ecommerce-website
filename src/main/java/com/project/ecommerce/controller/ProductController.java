package com.project.ecommerce.controller;

import com.project.ecommerce.model.Product;
import com.project.ecommerce.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // ✅ ADD PRODUCT (Seller adds product)
    @PostMapping("/seller/{sellerId}")
    public ResponseEntity<Product> addProduct(
            @PathVariable int sellerId,
            @RequestBody Product product) {

        return ResponseEntity.ok(
                productService.addProduct(sellerId, product)
        );
    }

    // ✅ UPDATE PRODUCT
    @PutMapping("/{productId}")
    public ResponseEntity<Product> updateProduct(
            @PathVariable int productId,
            @RequestBody Product product) {

        return ResponseEntity.ok(
                productService.updateProduct(productId, product)
        );
    }

    // ✅ DELETE PRODUCT
    @DeleteMapping("/{productId}")
    public ResponseEntity<String> deleteProduct(@PathVariable int productId) {
        productService.deleteProduct(productId);
        return ResponseEntity.ok("Product deleted successfully");
    }

    // ✅ GET PRODUCTS OF SELLER
    @GetMapping("/seller/{sellerId}")
    public ResponseEntity<?> getSellerProducts(@PathVariable int sellerId) {
        return ResponseEntity.ok(
                productService.getProductsBySeller(sellerId)
        );
    }
}
