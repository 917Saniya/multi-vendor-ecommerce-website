package com.project.ecommerce.service;

import com.project.ecommerce.model.Seller;
import com.project.ecommerce.repository.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SellerService {
    @Autowired
    private SellerRepository sellerRepository;

    public Seller createSeller(Seller seller) {
        return sellerRepository.save(seller);
    }

    public Optional<Seller> getSeller(int id) {
        return sellerRepository.findById(id);
    }

    public Seller updateSeller(int id, Seller sellerDetails) {
        Seller seller = sellerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Seller not found"));
        seller.setShopName(sellerDetails.getShopName());
        seller.setEmail(sellerDetails.getEmail());
        seller.setPassword(sellerDetails.getPassword());
        seller.setStatus(sellerDetails.getStatus());
        return sellerRepository.save(seller);
    }

    public void deleteSeller(int id) {
        sellerRepository.deleteById(id);
    }
}
