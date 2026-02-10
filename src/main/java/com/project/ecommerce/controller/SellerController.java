package com.project.ecommerce.controller;

<<<<<<< HEAD
import com.project.ecommerce.model.Seller;
import com.project.ecommerce.service.SellerService;
=======
import com.project.ecommerce.model.Product;
import com.project.ecommerce.model.Seller;
import com.project.ecommerce.repository.SellerRepository;
import com.project.ecommerce.service.ProductService;
>>>>>>> b5d71d248b6bcc0562807e56da4617f8e4f23d39
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sellers")
public class SellerController {

<<<<<<< HEAD
    @Autowired
    private SellerService sellerService;

    @PostMapping
    public ResponseEntity<Seller> createSeller(@RequestBody Seller seller) {
        Seller savedSeller = sellerService.createSeller(seller);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedSeller);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Seller> getSeller(@PathVariable int id) {
        return sellerService.getSeller(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
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
=======
//    @Autowired
//    private ProductService productService;
//
//    // ADD PRODUCT
//    @PostMapping("/{sellerId}/products")
//    public Product addProduct(
//            @PathVariable int sellerId,
//            @RequestBody Product product) {
//        return productService.addProduct(product, sellerId);
//    }
//
//    // GET PRODUCTS BY SELLER
//    @GetMapping("/{sellerId}/products")
//    public List<Product> getProducts(@PathVariable int sellerId) {
//        return productService.getProductsBySeller(sellerId);
//    }
//
//    // UPDATE PRODUCT
//    @PutMapping("/products/{productId}")
//    public Product updateProduct(
//            @PathVariable int productId,
//            @RequestBody Product product) {
//        return productService.updateProduct(productId, product);
//    }
//
//    // DELETE PRODUCT
//    @DeleteMapping("/products/{productId}")
//    public String deleteProduct(@PathVariable int productId) {
//        productService.deleteProduct(productId);
//        return "Product deleted";
//    }
>>>>>>> b5d71d248b6bcc0562807e56da4617f8e4f23d39
}
