package com.project.ecommerce.controller;

import com.project.ecommerce.model.Product;
import com.project.ecommerce.model.Seller;
import com.project.ecommerce.service.SellerService;
import com.project.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sellers")
public class SellerController {

    @Autowired
    private SellerService sellerService;

    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<Seller> createSeller(@RequestBody Seller seller) {
        Seller savedSeller = sellerService.createSeller(seller);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedSeller);
    }

    @GetMapping("/{id}")
    public Seller getSeller(@PathVariable int id) {
        return sellerService.getSellerById(id);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Seller> updateSeller(@PathVariable int id, @RequestBody Seller sellerDetails) {
        Seller updatedSeller = sellerService.updateSeller(id, sellerDetails);
        return ResponseEntity.ok(updatedSeller);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSeller(@PathVariable int id) {
        sellerService.deleteSeller(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{sellerId}/products")
    public ResponseEntity<Product> addProduct(@PathVariable int sellerId, @RequestBody Product product) {
        Product savedProduct = productService.addProduct(sellerId, product);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProduct);
    }

    @GetMapping("/{sellerId}/products")
    public ResponseEntity<List<Product>> getProducts(@PathVariable int sellerId) {
        return ResponseEntity.ok(productService.getProductsBySeller(sellerId));
    }

    @PutMapping("/products/{productId}")
    public ResponseEntity<Product> updateProduct(@PathVariable int productId, @RequestBody Product product) {
        return ResponseEntity.ok(productService.updateProduct(productId, product));
    }

    @DeleteMapping("/products/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable int productId) {
        productService.deleteProduct(productId);
        return ResponseEntity.noContent().build();
    }
}