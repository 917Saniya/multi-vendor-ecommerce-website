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
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private CartRepository cartRepo;

    @Autowired
    private OrderRepository orderRepo;

    @Autowired
    private ProductRepository productRepo;

    @PostMapping("/place/{userId}")
    public Order placeOrder(@PathVariable int userId) {

        Cart cart = cartRepo.findByUserId(userId);

        Order order = new Order();
        order.setUserId(userId);

        double total = 0;

        for (CartItem cartItem : cart.getItems()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setProduct(cartItem.getProduct());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setPrice(cartItem.getProduct().getPrice());

            total += cartItem.getQuantity() * cartItem.getProduct().getPrice();

            // Reduce stock
            Product product = cartItem.getProduct();
            product.setQuantity(product.getQuantity() - cartItem.getQuantity());
            productRepo.save(product);

            orderItem.setOrder(order);
            order.getItems().add(orderItem);
        }

        order.setTotalAmount(total);

        // Clear cart
        cart.getItems().clear();
        cartRepo.save(cart);

        return orderRepo.save(order);
    }
}
