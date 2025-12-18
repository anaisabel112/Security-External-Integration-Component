//It only exists so our component can test itself.
//Implements 1.3 User Management (mock data).
//Only for testing — simulates the Storage API inside your system.
//(Not required in final architecture.)

package com.example.demo.controller;

import com.example.demo.client.StorageClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/storage")
public class StorageController {

    @Autowired
    private StorageClient storage;

    // POST /storage/auth/credentials
    @PostMapping("/auth/credentials")
    public Map<String, Object> saveCredentials(@RequestBody Map<String, Object> body) {
        return storage.saveCredentials(body);
    }

    // POST /storage/audit/save
    @PostMapping("/audit/save")
    public Map<String, Object> saveAudit(@RequestBody Map<String, Object> body) {
        return storage.saveAuditLog(body);
    }

    // GET /storage/audit/query?userId=&action=&from=&to=
    @GetMapping("/audit/query")
    public Map<String, Object> queryAudit(
            @RequestParam(required = false) String userId,
            @RequestParam(required = false) String action,
            @RequestParam(required = false) String from,
            @RequestParam(required = false) String to
    ) {
        return storage.queryAuditLogs(userId, action, from, to);
    }
}
