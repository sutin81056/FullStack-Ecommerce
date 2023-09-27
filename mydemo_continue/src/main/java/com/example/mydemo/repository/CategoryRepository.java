package com.example.mydemo.repository;

import com.example.mydemo.model.entity.Category;
import com.example.mydemo.model.entity.Product;
import com.example.mydemo.model.entity.dto.CategoryDTO;
import com.example.mydemo.model.entity.dto.ProductDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Query("SELECT new com.example.mydemo.model.entity.dto.CategoryDTO(c.id, c.name) FROM Category c")
    List<CategoryDTO> findAllCategoryDTOs();
    @Query("SELECT c.id FROM Category c WHERE c.name = :name")
    Long findCategoryIdByName(String name);
}
