package com.example.mydemo.controller;

import com.example.mydemo.model.entity.dto.CategoryDTO;
import com.example.mydemo.model.entity.dto.ProductDTO;
import com.example.mydemo.service.CategoryService;
import com.example.mydemo.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@CrossOrigin(origins = "http://localhost:4200") 已被WebConfig中的Filter取代
@Slf4j //仍然建議使用System.out.println();
@RestController // frontend controller
@RequestMapping(value="/api/product")
public class RestProductController {
    @Autowired
    ProductService productService;
    @Autowired
    CategoryService categoryService;

    @GetMapping("/all")
    public ResponseEntity<List<ProductDTO>> getAllProductsExceptCategories() {
        try {
            // image change to classpath
            List<ProductDTO> products = productService.getAllProductsExceptCategories();
            return ResponseEntity.ok(products);
        } catch (Exception e) {
            // 有問題 -> 前端就回傳404，後端寫log，有建議使用println
            System.out.println("Error occurred while fetching all products: {}" + e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/categories")
    public ResponseEntity<List<CategoryDTO>> getAllCategoriesExceptProducts() {
        try {
            List<CategoryDTO> categories = categoryService.getAllCategoriesExceptProducts();
            return ResponseEntity.ok(categories);
        } catch (Exception e) {
            log.error("Error occurred while fetching all categories: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/productsByCategory")
    public ResponseEntity<List<ProductDTO>> getProductsByCategory(@RequestParam Long categoryId) {
        try {
            List<ProductDTO> products = productService.getProductsByCategory(categoryId);
            return ResponseEntity.ok(products);
        } catch (Exception e) {
            log.error("Error occurred while fetching products by category: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}

//    @GetMapping("/productsByCategory")
//    public ResponseEntity<List<Product>> getProductsByCategory(
//            @RequestParam(value = "category", required = true) String category) {
//        List<Product> products = productService.getProductsByCategory(category);
//        return ResponseEntity.ok(products);
//    }
//
//    @GetMapping("/alltest")
//    public ResponseEntity<List<Product>> getAllProducts() {
//        List<Product> products = productService.getAllProducts();
//        return ResponseEntity.ok(products);
//    }