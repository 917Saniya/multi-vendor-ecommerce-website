package com.project.ecommerce.controller;

import com.project.ecommerce.model.Cart;
import com.project.ecommerce.model.CartItem;
import com.project.ecommerce.model.Product;
import com.project.ecommerce.repository.CartItemRepository;
import com.project.ecommerce.repository.CartRepository;
import com.project.ecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private CartRepository cartRepo;

    @Autowired
    private ProductRepository productRepo;

    @Autowired
    private CartItemRepository cartItemRepo;

    // Add to cart
    @PostMapping("/add")
    public Cart addToCart(
            @RequestParam int userId,
            @RequestParam int productId,
            @RequestParam int quantity) {

        Cart cart = cartRepo.findByUserId(userId);
        if (cart == null) {
            cart = new Cart();
            cart.setUserId(userId);
            cartRepo.save(cart);
        }

        Product product = productRepo.findById(productId).orElseThrow();

        CartItem item = new CartItem();
        item.setProduct(product);
        item.setQuantity(quantity);
        item.setCart(cart);

        cartItemRepo.save(item);
        cart.getItems().add(item);

        return cartRepo.save(cart);
    }

    // View cart
    @GetMapping("/{userId}")
    public Cart viewCart(@PathVariable int userId) {
        return cartRepo.findByUserId(userId);
    }
}
