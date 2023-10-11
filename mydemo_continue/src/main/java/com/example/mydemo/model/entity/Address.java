package com.example.mydemo.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

//import javax.persistence.*;

@Entity
@Table(name = "address")
@Getter
@Setter
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "district")
    private String district;

    @Column(name = "street")
    private String street;
}
