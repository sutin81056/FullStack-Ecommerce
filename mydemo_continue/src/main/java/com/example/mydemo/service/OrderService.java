package com.example.mydemo.service;

import com.example.mydemo.model.entity.Order;
import com.example.mydemo.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    OrderRepository repository;
    public List<Order> findallOrders() {
        return (List<Order>) repository.findAll();
    }

    public Order findOrderById(Long id) {
        Optional<Order> result = repository.findById(id);
        if (result.isPresent()) {
            return result.get();
        }
        return new Order();
    }

    public Order addOrder(Order order) {
        return repository.save(order);
    }

    public Order updateOrder(Order order) {
        Optional<Order> result = repository.findById(order.getId());
        Order existing = result.get();

//        只能改status
        existing.setStatus(order.getStatus());
        return repository.save(existing);
    }
    // not in use
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
