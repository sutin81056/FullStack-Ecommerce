package com.example.mydemo.model.entity;

import jakarta.persistence.*;
import lombok.*;


import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="Category")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ID")
    Long id;

    @Column(name="CATEGORY_NAME")
    String name;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "categories")
    @Column(nullable = true)
    Set<Product> products;

    // JPA會擋，所以手動刪，方法被CategoryService調用
    public void removeCategoryFromProducts() {
        for (Product product : products) {
            product.getCategories().remove(this);
        }
    }

}
