package com.example.mydemo.model.entity;

import lombok.Getter;
import lombok.Setter;
import org.apache.tomcat.jni.Address;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "orders")
@Getter
@Setter
public class Order {
    // 我的顧客編號
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "order_tracking_number")
    private String orderTrackingNumber;

    @Column(name = "total_quantity")
    private int totalQuantity;

    @Column(name = "total_price")
    private BigDecimal totalPrice;

    @Column(name = "status")
    private String status;

    @Column(name = "date_created")
    @CreationTimestamp
    private Date dateCreated;

    @Column(name = "last_updated")
    @UpdateTimestamp
    private Date lastUpdated;

    // 一筆訂單中有 多種產品(OrderItem)
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "order")
    private Set<OrderItem> orderItems = new HashSet<>();

    // 這些訂單都是同一個人的，也有可能不是。
    // 但一筆訂單不會是很多人共有的
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // 一筆訂單一個地址
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "shipping_address_id",referencedColumnName = "id")
    private Address shippingAddress;

    // 同上
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "billing_address_id",referencedColumnName = "id")
    private Address billingAddress;

    public void add(OrderItem item){
        if (item!=null){
            if (orderItems == null) {
                orderItems = new HashSet<>();
            }
            orderItems.add(item);
            item.setOrder(this);
        }
    }
}
