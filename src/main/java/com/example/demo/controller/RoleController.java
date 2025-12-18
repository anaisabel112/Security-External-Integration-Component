//THIS IS OUR COMPONENT
//Implements 1.4 Role & Permission Management (mock data).
package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/roles")
public class RoleController {

    //GET /roles
    @GetMapping
    public List<Map<String, Object>> getRoles() {
        return List.of(
            Map.of("role", "Admin", "permissions", List.of("MANAGE_USERS", "DEVICE_CONTROL")),
            Map.of("role", "Engineer", "permissions", List.of("VIEW_ANALYTICS")),
            Map.of("role", "Tenant", "permissions", List.of("VIEW_CONSUMPTION"))
        );
    }

    //POST /roles
    @PostMapping
    public Map<String, Object> createRole(@RequestBody Map<String, Object> body) {
        return Map.of("status", "role_created");
    }

    //PUT /roles/{roleName}
    @PutMapping("/{roleName}")
    public Map<String, Object> updateRole(@PathVariable String roleName, @RequestBody Map<String, Object> body) {
        return Map.of(
            "status", "role_updated",
            "role", roleName
        );
    }

    //DELETE /roles/{roleName}
    @DeleteMapping("/{roleName}")
    public Map<String, Object> deleteRole(@PathVariable String roleName) {
        return Map.of(
            "status", "role_deleted",
            "role", roleName
        );
    }
}
