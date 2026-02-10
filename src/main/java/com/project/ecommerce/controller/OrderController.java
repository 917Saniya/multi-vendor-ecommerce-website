package com.project.ecommerce.controller;

import com.project.ecommerce.model.*;
import com.project.ecommerce.repository.CartRepository;
import com.project.ecommerce.repository.OrderRepository;
import com.project.ecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private CartRepository cartRepo;

    @Autowired
    private OrderRepository orderRepo;

    @PostMapping("/place/{userId}")
    public Order placeOrder(@PathVariable Integer userId) {

        Cart cart = cartRepo.findByUserId(userId);

        if (cart == null || cart.getItems().isEmpty()) {
            throw new RuntimeException("Cart is empty");
        }

        Order order = new Order();
        order.setUserId(userId);

        double total = 0;

        for (CartItem cartItem : cart.getItems()) {

            Product product = cartItem.getProduct();

            OrderItem orderItem = new OrderItem();
            orderItem.setProductId(product.getId());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setPrice(product.getPrice());
            orderItem.setOrder(order);

            order.getItems().add(orderItem);

            total += product.getPrice() * cartItem.getQuantity();
        }

        order.setTotalAmount(total);

        Order savedOrder = orderRepo.save(order);

        // clear cart
        cart.getItems().clear();
        cartRepo.save(cart);

        return savedOrder;
    }
}
