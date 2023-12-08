package com.example.mydemo.model.entity.dto;

import com.example.mydemo.model.entity.Address;
import com.example.mydemo.model.entity.Order;
import com.example.mydemo.model.entity.OrderItem;
import com.example.mydemo.model.entity.User;
import lombok.Data;

import java.util.Set;

@Data
public class Purchase {
    private User user;
    private Address shippingAddress;
    private Address billingAddress;
    private Order order;
    private Set<OrderItem> orderItems;
}