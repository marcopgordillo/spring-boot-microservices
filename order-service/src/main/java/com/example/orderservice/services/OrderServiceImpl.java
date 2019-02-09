package com.example.orderservice.services;

import com.example.orderservice.entities.Order;
import com.example.orderservice.repositories.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

  private final OrderRepository orderRepository;

  public OrderServiceImpl(OrderRepository orderRepository) {
    this.orderRepository = orderRepository;
  }

  @Override
  @Transactional
  public Order createOrder(Order order) {
    return orderRepository.save(order);
  }

  @Override
  @Transactional(readOnly = true)
  public Optional<Order> findOrderById(Long id) {
    return orderRepository.findById(id);
  }
}
