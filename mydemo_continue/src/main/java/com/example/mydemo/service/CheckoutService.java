package com.example.mydemo.service;

import com.example.mydemo.model.entity.dto.Purchase;
import com.example.mydemo.model.entity.dto.PurchaseResponse;

public interface CheckoutService {
    PurchaseResponse placeOrder(Purchase purchase);
}