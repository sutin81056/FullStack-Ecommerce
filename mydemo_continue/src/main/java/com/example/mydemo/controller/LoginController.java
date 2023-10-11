package com.example.mydemo.controller;

import com.example.mydemo.config.UserAuthenticationProvider;
import com.example.mydemo.model.entity.dto.CredentialsDto;
import com.example.mydemo.model.entity.dto.UserDto;
import com.example.mydemo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

//import javax.servlet.http.HttpSession;
//import javax.validation.Valid;

@Controller
@Slf4j
@RequestMapping(value="/admin")
public class LoginController {

    UserService userService;
    UserAuthenticationProvider userAuthenticationProvider;

    @GetMapping("/")
    public String showLoginPage(Model model) {
        return "loginPage";
    }

    @GetMapping("/login")
    public String showForm(Model model) {
        return "loginPage";
    }

//    @PostMapping("/login")
//    public ResponseEntity<UserDto> UserLogin(@RequestBody @Valid CredentialsDto credentialsDto) {
//        UserDto userDto = userService.login(credentialsDto);
//        userDto.setToken(userAuthenticationProvider.createToken(userDto));
//        return ResponseEntity.ok(userDto);
//    }
    @PostMapping("/login")
    public String checkLoginInput(Model model) {
        return "redirect:/admin/lunchAllProductsPage";
    }

}
