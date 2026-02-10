package com.project.ecommerce.controller;

import com.project.ecommerce.model.Order;
import com.project.ecommerce.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/place/{userId}")
    public Order placeOrder(@PathVariable int userId) {
        return orderService.placeOrder(userId);
    }

    @GetMapping("/user/{userId}")
    public List<Order> getOrdersByUser(@PathVariable int userId) {
        return orderService.getOrdersByUser(userId);
    }

    @GetMapping
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }
}

