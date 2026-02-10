package com.project.ecommerce.service;

import com.project.ecommerce.model.*;
import com.project.ecommerce.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    public Order placeOrder(Integer userId) {

        Cart cart = cartRepository.findByUserId(userId);

        if (cart == null || cart.getItems().isEmpty()) {
            throw new RuntimeException("Cart is empty or not found");
        }

        Order order = new Order();
        order.setUserId(userId);

        double total = 0;
        List<OrderItem> orderItems = new ArrayList<>();

        for (CartItem cartItem : cart.getItems()) {

            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setProductId(cartItem.getProductId());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setPrice(cartItem.getPrice());

            order.getItems().add(orderItem);
        }


        order.setTotalAmount(total);
        order.setItems(orderItems);

        Order savedOrder = orderRepository.save(order);
        orderItemRepository.saveAll(orderItems);

        // clear cart
        cart.getItems().clear();
        cartRepository.save(cart);

        return savedOrder;
    }

    public List<Order> getOrdersByUser(Integer userId) {
        return orderRepository.findByUserId(userId);
    }
}
