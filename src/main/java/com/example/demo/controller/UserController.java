//THIS IS OUR COMPONENT
package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/users")
public class UserController {

    //GET /users
    @GetMapping
    public List<Map<String, Object>> getAllUsers() {
        return List.of(
            Map.of("id", "1", "username", "admin", "roles", List.of("Admin")),
            Map.of("id", "2", "username", "engineer1", "roles", List.of("Engineer"))
        );
    }

    //GET /users/{id}
    @GetMapping("/{id}")
    public Map<String, Object> getUser(@PathVariable String id) {
        return Map.of(
            "id", id,
            "username", "mock-user",
            "roles", List.of("Tenant")
        );
    }

    //POST /users
    @PostMapping
    public Map<String, Object> createUser(@RequestBody Map<String, Object> body) {
        return Map.of(
            "status", "created",
            "userId", "new-user-123"
        );
    }

    //PUT /users/{id}
    @PutMapping("/{id}")
    public Map<String, Object> updateUser(@PathVariable String id, @RequestBody Map<String, Object> body) {
        return Map.of(
            "status", "updated",
            "userId", id
        );
    }

    //DELETE /users/{id}
    @DeleteMapping("/{id}")
    public Map<String, Object> deleteUser(@PathVariable String id) {
        return Map.of(
            "status", "deleted",
            "userId", id
        );
    }
}
