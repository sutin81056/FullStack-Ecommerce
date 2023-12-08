package com.example.mydemo.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "frontend_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Size(max = 100)
    private String username;

    @Column(nullable = false)
    @Size(max = 100)
    private String password;

    @Column(nullable = false)
    private String role;

    // orders, User刪了訂單全刪
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private Set<Order> orders = new HashSet<>();

    // 在service中
    public void add(Order order){
        if (order!=null){
            if (orders == null) {
                orders = new HashSet<>();
            }
            orders.add(order);
            order.setUser(this);
        }
    }

}
