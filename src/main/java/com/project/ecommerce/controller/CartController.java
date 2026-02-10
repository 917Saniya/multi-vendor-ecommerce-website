package com.project.ecommerce.controller;

import com.project.ecommerce.model.Cart;
import com.project.ecommerce.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/add")
    public Cart addToCart(@RequestParam int userId,
                          @RequestParam int productId,
                          @RequestParam int quantity) {
        return cartService.addToCart(userId, productId, quantity);
    }

    @GetMapping("/{userId}")
    public Cart getCart(@PathVariable int userId) {
        return cartService.getCartByUser(userId);
    }

    @DeleteMapping("/remove")
    public Cart removeFromCart(@RequestParam int userId,
                               @RequestParam int productId) {
        return cartService.removeFromCart(userId, productId);
    }
}

