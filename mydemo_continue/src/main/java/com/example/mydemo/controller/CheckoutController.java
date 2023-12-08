package com.example.mydemo.controller;

import com.example.mydemo.model.entity.dto.Purchase;
import com.example.mydemo.model.entity.dto.PurchaseResponse;
import com.example.mydemo.service.CheckoutService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

import javax.xml.crypto.KeySelector;

@Slf4j
@RestController
@RequestMapping(value="/api/checkout")
public class CheckoutController {
    private CheckoutService checkoutService;
    public CheckoutController(CheckoutService checkoutService) {
        this.checkoutService = checkoutService;
    }

    @PostMapping("/purchase")
    public PurchaseResponse purchaseResponse(@RequestBody Purchase purchase) {
        return checkoutService.placeOrder(purchase);
    }

}
