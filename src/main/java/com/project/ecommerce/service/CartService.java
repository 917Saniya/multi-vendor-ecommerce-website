package com.project.ecommerce.service;

import com.project.ecommerce.model.CartItem;
import com.project.ecommerce.model.User;
import com.project.ecommerce.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    public CartItem addToCart(CartItem item) {
        return CartRepository.save(item);
    }

    public List<CartItem> viewCart(User user) {
        return cartRepository.findByUser(user);
    }

    public void removeFromCart(int cartItemId) {
        cartRepository.deleteById(cartItemId);
    }
}

