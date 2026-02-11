package com.project.ecommerce.service;

import com.project.ecommerce.model.Product;
import com.project.ecommerce.model.Seller;
import com.project.ecommerce.repository.ProductRepository;
import com.project.ecommerce.repository.SellerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final SellerRepository sellerRepository;

    public ProductService(ProductRepository productRepository,
                          SellerRepository sellerRepository) {
        this.productRepository = productRepository;
        this.sellerRepository = sellerRepository;
    }

    public Product addProduct(int sellerId, Product product) {
        Seller seller = sellerRepository.findById(sellerId)
                .orElseThrow(() -> new RuntimeException("Seller not found"));

        product.setSeller(seller);
        return productRepository.save(product);
    }

    public Product updateProduct(int productId, Product updatedProduct) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        product.setName(updatedProduct.getName());
        product.setPrice(updatedProduct.getPrice());
        product.setQuantity(updatedProduct.getQuantity());
        product.setDescription(updatedProduct.getDescription());

        return productRepository.save(product);
    }
    public void deleteProduct(int productId) {
        productRepository.deleteById(productId);
    }
    public List<Product> getProductsBySeller(int sellerId) {
        return productRepository.findBySeller_SellerId(sellerId);
    }
}
