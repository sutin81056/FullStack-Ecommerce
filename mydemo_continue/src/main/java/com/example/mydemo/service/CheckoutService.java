//package com.example.mydemo.service;
//
//import com.example.mydemo.model.entity.OrderItem;
//import org.springframework.stereotype.Service;
//
//import javax.transaction.Transactional;
//import java.util.Set;
//import java.util.UUID;
//
//public class CheckoutService {
//
//    @Service
//    public class CheckoutServiceImpl implements CheckoutService {
//
//        private CustomerRepository customerRepository;
//
//        public CheckoutServiceImpl(CustomerRepository customerRepository){
//            this.customerRepository = customerRepository;
//        }
//
//        @Override
//        @Transactional
//        public PurchaseResponse placeOrder(Purchase purchase) {
//            Order order = purchase.getOrder();
//            String orderTrackingNumber = generateOrderTrackingNumber();
//            order.setOrderTrackingNumber(orderTrackingNumber);
//            Set<OrderItem> orderItems=purchase.getOrderItems();
//            orderItems.forEach(item -> order.add(item));
//            order.setBillingAddress(purchase.getBillingAddress());
//            order.setShippingAddress(purchase.getShippingAddress());
//            Customer customer = purchase.getCustomer();
////        確認Email是否已存在
//            String theEmail = customer.getEmail();
//            Customer customerFromDB = customerRepository.findByEmail(theEmail);
//            if (customerFromDB!=null){
//                customer=customerFromDB;
//            }
//            customer.add(order);
//            customerRepository.save(customer);
//            return new PurchaseResponse(orderTrackingNumber);
//        }
//
//        private String generateOrderTrackingNumber() {
//            return UUID.randomUUID().toString();
//        }
//    }
//
//}
