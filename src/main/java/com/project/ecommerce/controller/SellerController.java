package com.project.ecommerce.controller;

import com.project.ecommerce.model.Seller;
import com.project.ecommerce.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sellers")
public class SellerController {

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
}
