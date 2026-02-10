package com.project.ecommerce.controller;

<<<<<<< HEAD
import com.project.ecommerce.model.Order;
import com.project.ecommerce.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
=======
import org.springframework.web.bind.annotation.*;
import java.util.*;
>>>>>>> b5d71d248b6bcc0562807e56da4617f8e4f23d39

@RestController
@RequestMapping("/api/orders")
public class OrderController {

<<<<<<< HEAD
    @Autowired
    private OrderService orderService;

    @PostMapping("/place/{userId}")
    public Order placeOrder(@PathVariable Integer userId) {
        return orderService.placeOrder(userId);
    }

    @GetMapping("/user/{userId}")
    public List<Order> getOrders(@PathVariable Integer userId) {
        return orderService.getOrdersByUser(userId);
=======
    private final List<Map<String, Object>> orders = new ArrayList<>();

    @GetMapping
    public List<Map<String, Object>> getAllOrders() {
        return orders;
    }

    @PostMapping
    public Map<String, Object> createOrder(@RequestBody Map<String, Object> order) {
        orders.add(order);
        return order;
>>>>>>> b5d71d248b6bcc0562807e56da4617f8e4f23d39
    }
}
