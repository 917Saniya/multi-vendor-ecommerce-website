package com.project.ecommerce.repository;

import com.project.ecommerce.model.Product;
import com.project.ecommerce.model.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SellerRepository extends JpaRepository<Seller, Integer> {
    List<Product> findBySellerId(int sellerId);
}
