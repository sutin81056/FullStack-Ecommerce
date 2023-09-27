package com.example.mydemo.controller;

import com.example.mydemo.model.entity.Category;
import com.example.mydemo.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value="/admin")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @GetMapping("/lunchAllCategoriesPage")
    public String showAllCategories(Model model) {
        model.addAttribute("categories", categoryService.getAllCategories());
        return "allCategories";
    }

    @GetMapping("/lunchAddCategoryPage")
    public String lunchAddCategoryPage(Model model) {
        model.addAttribute("category", new Category());
        return "addCategory";
    }

    @PostMapping("/addCategory")
    public String addCategory(@ModelAttribute Category category, Model model) {
        categoryService.addCategory(category);
        return "redirect:/admin/lunchAllCategoriesPage";
    }

    @GetMapping("/editCategory/{id}")
    public String lunchEditCategoryPage(Model model, @PathVariable("id") Long id) {
        model.addAttribute("category", categoryService.findCategoryById(id));
        return "editCategory";
    }

    @PostMapping("/updateCategory")
    public String updateCategory(Category category) {
        categoryService.updateCategory(category);
        return "redirect:/admin/lunchAllCategoriesPage";
    }

    @GetMapping("/deleteCategory/{id}")
    public String deleteCategory(@PathVariable("id") Long id) {
        categoryService.deleteCategoryById(id);
        return "redirect:/admin/lunchAllCategoriesPage";
    }

}
