package com.project.ecommerce.controller;

import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final List<Map<String, Object>> orders = new ArrayList<>();

    @GetMapping
    public List<Map<String, Object>> getAllOrders() {
        return orders;
    }

    @PostMapping
    public Map<String, Object> createOrder(@RequestBody Map<String, Object> order) {
        orders.add(order);
        return order;
    }
}
