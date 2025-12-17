package com.projetiw.authservice.controller;

import com.projetiw.authservice.dto.LoginRequest;
import com.projetiw.authservice.dto.RegisterRequest;
import com.projetiw.authservice.dto.AuthResponse;
import com.projetiw.authservice.service.AuthService;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.events.Event;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public AuthResponse register(@RequestBody RegisterRequest request) {
        return authService.register(request);
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest request) {
        return authService.login(request);
    }

    @GetMapping("/test")
    public String test() {
        return "Le microservice Auth fonctionne !";
    }
    
}
