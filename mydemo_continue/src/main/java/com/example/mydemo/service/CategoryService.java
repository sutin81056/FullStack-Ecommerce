package com.example.mydemo.service;

import com.example.mydemo.model.entity.Category;
import com.example.mydemo.model.entity.dto.CategoryDTO;
import com.example.mydemo.model.entity.dto.ProductDTO;
import com.example.mydemo.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Category findCategoryById(Long id) {
        Optional<Category> result = categoryRepository.findById(id);
        if (result.isPresent()) {
            return result.get();
        }
        return new Category();
    }

    public Category addCategory(Category category) {
        return categoryRepository.save(category);
    }

    public Category updateCategory(Category category) {
        Optional<Category> result = categoryRepository.findById(category.getId());
        Category existing = result.get();

        existing.setName(category.getName());
        return categoryRepository.save(existing);
    }

//    從非所有者實體中移除
//    從不是關係所有者的實體中刪除記錄時，我們應該注意手動刪除關係, 無法像product一樣使用clear()
    public void deleteCategoryById(Long id) {
        // 1. delete all relationship
        Category categoryToDelete = this.findCategoryById(id);
        categoryToDelete.removeCategoryFromProducts();
        // 2. delete by id
        categoryRepository.deleteById(id);
    }

    public List<CategoryDTO> getAllCategoriesExceptProducts() {
        return categoryRepository.findAllCategoryDTOs();
    }

}
