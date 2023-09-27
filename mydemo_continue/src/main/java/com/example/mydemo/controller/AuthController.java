package com.example.mydemo.controller;

import com.example.mydemo.config.UserAuthenticationProvider;
import com.example.mydemo.model.entity.dto.CredentialsDto;
import com.example.mydemo.model.entity.dto.SignUpDto;
import com.example.mydemo.model.entity.dto.UserDto;
import com.example.mydemo.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RequestMapping(value="/user")
@RequiredArgsConstructor
@RestController
public class AuthController {
    private final UserService userService;
    private final UserAuthenticationProvider userAuthenticationProvider;

    @PostMapping("/login")
    public ResponseEntity<UserDto> UserLogin(@RequestBody @Valid CredentialsDto credentialsDto) {
        UserDto userDto = userService.login(credentialsDto);
        userDto.setToken(userAuthenticationProvider.createToken(userDto));
        return ResponseEntity.ok(userDto);
    }

    @PostMapping("/register")
    public ResponseEntity<Object> UserRegister(@RequestBody @Valid SignUpDto user) {
        UserDto createdUser = userService.register(user);
        createdUser.setToken(userAuthenticationProvider.createToken(createdUser));
        return ResponseEntity.created(URI.create("/users/" + createdUser.getId())).body(createdUser);
//        Map<String, Object> response = new HashMap<>();
//        response.put("message", "register success!");
//        return ResponseEntity.ok("register success!");
    }
    @GetMapping("/forUser")
    public ResponseEntity<Object> messages() {
        try {
            System.out.println("frontend user get successful!");
            Map<String, Object> response = new HashMap<>();
            response.put("message", "ROLE_USER");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("error: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}
