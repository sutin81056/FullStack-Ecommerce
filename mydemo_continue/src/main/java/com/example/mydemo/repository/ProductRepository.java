package com.example.mydemo.repository;

import com.example.mydemo.model.entity.Product;
import com.example.mydemo.model.entity.dto.ProductDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("SELECT new com.example.mydemo.model.entity.dto.ProductDTO(p.id, p.name, p.price, p.image) FROM Product p")
    List<ProductDTO> findAllProductDTOs();

    @Query("SELECT new com.example.mydemo.model.entity.dto.ProductDTO(p.id, p.name, p.price, p.image) FROM Product p WHERE p.id IN :productIds")
    List<ProductDTO> findProductDTOsById(@Param("productIds") List<Long> productIds);

    @Query("SELECT p.id FROM Product p JOIN p.categories c WHERE c.id = :catId")
    List<Long> findProductIdByCategoryId(@Param("catId") Long catId);
//    @Query("SELECT p.id FROM Product p WHERE c.id = :catId")
//    List<Long> findProductIdByCategoryId(Long catId);
}
