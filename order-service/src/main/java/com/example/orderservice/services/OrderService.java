package com.example.orderservice.services;

import com.example.orderservice.entities.Order;

import java.util.Optional;

public interface OrderService {

  Order createOrder(Order order);

  Optional<Order> findOrderById(Long id);
}
