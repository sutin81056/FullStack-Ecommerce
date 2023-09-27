package com.example.mydemo.model.entity;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="Product")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ID")
    Long id;

    @Column(name="PRODUCT_NAME")
    String name;

    @Column(name="PRODUCT_PRICE")
    Integer price;

    @Column(name="PRODUCT_INVENTORY")
    Integer inventory;

    @Lob
    @Column(columnDefinition = "MEDIUMBLOB")
    String image;

    // @OneToMany
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="product_category",joinColumns = {
            @JoinColumn(name="product_id", referencedColumnName = "id")}
            ,inverseJoinColumns = {@JoinColumn(name="category_id",referencedColumnName = "id")})
    Set<Category> categories = new HashSet<>();

}
