package com.example.mydemo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
@Slf4j
public class LoginController {

    @GetMapping("/")
    public String showLoginPage(Model model) {
        return "loginPage";
    }

    @GetMapping("/login")
    public String showForm(Model model) {
        return "loginPage";
    }

    @PostMapping("/login")
    public String checkLoginInput(Model model) {
        return "redirect:/admin/lunchAllProductsPage";
    }

}
