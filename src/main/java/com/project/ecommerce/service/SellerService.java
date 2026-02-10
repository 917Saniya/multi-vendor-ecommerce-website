package com.project.ecommerce.service;

import com.project.ecommerce.model.Seller;
import com.project.ecommerce.repository.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SellerService {

    @Autowired
    private SellerRepository sellerRepository;
    public Seller createSeller(Seller seller) {
        return sellerRepository.save(seller);
    }
    public List<Seller> getAllSellers() {
        return sellerRepository.findAll();
    }
    public Seller getSellerById(int id) {
        return sellerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Seller not found"));
    }
}
