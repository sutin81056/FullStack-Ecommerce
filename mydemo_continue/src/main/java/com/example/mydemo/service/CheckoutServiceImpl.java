package com.example.mydemo.service;

import com.example.mydemo.model.entity.Order;
import com.example.mydemo.model.entity.OrderItem;
import com.example.mydemo.model.entity.User;
import com.example.mydemo.model.entity.dto.Purchase;
import com.example.mydemo.model.entity.dto.PurchaseResponse;
import com.example.mydemo.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.mapstruct.control.MappingControl;
import org.springframework.stereotype.Service;
import java.util.Set;
import java.util.UUID;

@Service
public class CheckoutServiceImpl implements CheckoutService {

    private UserRepository userRepository;

    public CheckoutServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public PurchaseResponse placeOrder(Purchase purchase) {

        Order order = purchase.getOrder();
        String orderTrackingNumber = generateOrderTrackingNumber();
        order.setOrderTrackingNumber(orderTrackingNumber);

        Set<OrderItem> orderItems = purchase.getOrderItems();
        orderItems.forEach(item -> order.add(item));
        order.setBillingAddress(purchase.getBillingAddress());
        order.setShippingAddress(purchase.getShippingAddress());

        User user = purchase.getUser();
//        String username = user.getUsername();
//        User userFromDB = userRepository.findByUsernameU(username);
//        if (userFromDB != null){
//            user = userFromDB;
//        }
        user.add(order);
        userRepository.save(user);
        return new PurchaseResponse(orderTrackingNumber);

    }

    // 產生訂單編號
    private String generateOrderTrackingNumber() {
        return UUID.randomUUID().toString();
    }
}
