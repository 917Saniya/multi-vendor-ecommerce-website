package com.project.ecommerce.repository;

import com.project.ecommerce.model.Cart;
import com.project.ecommerce.model.CartItem;
import com.project.ecommerce.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Integer> {
    static CartItem save(CartItem item) {
        return null;
    }

    Cart findByUserId(Integer userId);


    List<Cart> findAllByUserId(Integer userId);

    List<CartItem> findByUser(User user);
}
