package com.example.demo.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.web.bind.annotation.*;


@RestController
@SecurityRequirement(name = "bearerAuth")
@RequestMapping("/users")
public class AuthController {

    private final UserService service;

    public AuthController(UserService service) {
        this.service = service;
    }

    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return service.register(user);
    }

    @GetMapping("/{email}")
    public User getByEmail(@PathVariable String email) {
        return service.findByEmail(email);
    }
}
