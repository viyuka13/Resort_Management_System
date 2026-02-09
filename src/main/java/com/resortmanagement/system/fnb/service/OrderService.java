package com.resortmanagement.system.fnb.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.resortmanagement.system.fnb.entity.Order;
import com.resortmanagement.system.fnb.repository.OrderRepository;

@Service
public class OrderService {

    private final OrderRepository repository;

    public OrderService(OrderRepository repository) {
        this.repository = repository;
    }

    public List<Order> findAll() {
        return repository.findAll();
    }

    public Optional<Order> findById(Long id) {
        return repository.findById(id);
    }

    public Order save(Order entity) {
        return repository.save(entity);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
