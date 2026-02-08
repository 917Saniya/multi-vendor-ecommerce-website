package com.project.ecommerce.service;

import com.project.ecommerce.model.Seller;
import com.project.ecommerce.repository.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellerService {

    @Autowired
    private SellerRepository sellerRepository;

    public Seller registerSeller(Seller seller) {
        return sellerRepository.save(seller);
    }
}

