package com.project.ecommerce.service;

import com.project.ecommerce.model.*;
import com.project.ecommerce.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    public Cart addToCart(int userId, int productId, int quantity) {

        Cart cart = cartRepository.findByUserId(userId)
                .orElseGet(() -> {
                    Cart newCart = new Cart();
                    newCart.setUserId(userId);
                    return cartRepository.save(newCart);
                });

        for (CartItem item : cart.getItems()) {
            if (item.getProductId() == productId) {
                item.setQuantity(item.getQuantity() + quantity);
                return cartRepository.save(cart);
            }
        }

        CartItem cartItem = new CartItem();
        cartItem.setProductId(productId);
        cartItem.setQuantity(quantity);
        cartItem.setPrice(0.0); // later calculate from Product service
        cartItem.setCart(cart);

        cart.getItems().add(cartItem);
        return cartRepository.save(cart);
    }

    public Cart getCartByUser(int userId) {
        return cartRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));
    }

    public Cart removeFromCart(int userId, int productId) {

        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        cart.getItems().removeIf(item -> item.getProductId() == productId);

        return cartRepository.save(cart);
    }
}
