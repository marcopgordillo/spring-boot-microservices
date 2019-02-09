package com.example.orderservice.web.controllers;

import com.example.orderservice.entities.Order;
import com.example.orderservice.services.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
  private final OrderService orderService;

  public OrderController(OrderService orderService) {
    this.orderService = orderService;
  }

  @PostMapping
  public Order createOrder(@RequestBody Order order) {
    return orderService.createOrder(order);
  }

  @GetMapping("{id}")
  public Optional<Order> findOrderById(@PathVariable Long id) {
    return orderService.findOrderById(id);
  }
}
