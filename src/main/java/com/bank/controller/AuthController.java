package com.bank.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.bank.dto.LoginRequest;
import com.bank.security.JwtUtils;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private JwtUtils jwtUtils;

    public AuthController(JwtUtils jwtUtils) {
        this.jwtUtils = jwtUtils;
    }

    // Replace with DB lookup/service later
    private static final String DEMO_USER = "admin";
    private static final String DEMO_PASS = "adminpass";

    @RequestMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        if (DEMO_USER.equals(request.getUsername()) && DEMO_PASS.equals(request.getPassword())) {
            String token = jwtUtils.generateToken(request.getUsername());
            return ResponseEntity.ok(Map.of("token", token));
        } else {
            return ResponseEntity.status(401).body(Map.of("error", "Invalid credentials"));
        }
    }
}
