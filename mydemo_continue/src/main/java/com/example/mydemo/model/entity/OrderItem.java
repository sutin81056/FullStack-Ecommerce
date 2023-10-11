package com.example.mydemo.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


import java.math.BigDecimal;

@Entity
@Table(name = "order_item")
@Getter
@Setter
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    // no image(db or URL)

    // 一個多少錢
    @Column(name = "unit_price")
    private BigDecimal unitPrice;

    // 多少個
    @Column(name = "quantity")
    private int quantity;

    // 哪個
    @Column(name = "product_id")
    private Long productId;

    // 哪一筆訂單的
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
}
