//THIS IS OUR COMPONENT
//Currently returns mock tokens (because no real JWT implementation).
package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    // 1.1 POST /auth/login
    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody Map<String, String> body) {
        String username = body.get("username");
        String password = body.get("password");

        

        return Map.of(
                "accessToken", "jwt-token",
                "refreshToken", "refresh-token",
                "roles", List.of("Admin"),
                "expiresIn", 3600
        );
    }

    // 1.1 POST /auth/refresh
    @PostMapping("/refresh")
    public Map<String, Object> refreshToken(@RequestBody Map<String, String> body) {
        String refreshToken = body.get("refreshToken");
        
        return Map.of(
                "accessToken", "new-jwt-token",
                "expiresIn", 3600
        );
    }

    // 1.1 POST /auth/logout
    @PostMapping("/logout")
    public Map<String, Object> logout(@RequestBody(required = false) Map<String, String> body) {
        
        return Map.of("status", "logged_out");
    }

    // 1.2 GET /auth/validate-token
    @GetMapping("/validate-token")
    public Map<String, Object> validateToken() {
        return Map.of(
                "valid", true,
                "userId", "user-001",
                "roles", List.of("Engineer")
        );
    }

    // 1.2 POST /auth/check-permissions
    @PostMapping("/check-permissions")
    public Map<String, Object> checkPermissions(@RequestBody Map<String, Object> body) {
       
        return Map.of("allowed", true);
    }

    // 1.7 GET /auth/user-info
    @GetMapping("/user-info")
    public Map<String, Object> getUserInfo(@RequestHeader("Authorization") String authHeader) {

        String token = authHeader.replace("Bearer ", "");

        return Map.of(
                "userId", "user-001",
                "username", "admin",
                "fullName", "System Administrator",
                "roles", List.of("Admin"),
                "permissions", List.of("DEVICE_CONTROL", "VIEW_ANALYTICS", "MANAGE_USERS"),
                "lastLoginAt", "2025-12-05T22:15:00Z",
                "receivedToken", token
        );
    }
}
