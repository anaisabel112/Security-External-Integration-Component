//THIS IS OUR COMPONENT
//It takes audit logs from EMSIB services → transforms them → stores them via StorageClient.
package com.example.demo.controller;

import com.example.demo.client.StorageClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/audit")
public class AuditController {

    private final StorageClient storage;

    @Autowired
    public AuditController(StorageClient storage) {
        this.storage = storage;
    }

    // POST /audit/log 
    @PostMapping("/log")
    public Map<String, Object> saveAudit(@RequestBody Map<String, Object> body) {
       
       // We adapt to the format expected by the storage (/storage/audit/save)
        Map<String, Object> storagePayload = new HashMap<>();
        storagePayload.put("userId", body.get("userId"));
        storagePayload.put("action", body.get("action"));
       
        storagePayload.put("resource", body.get("service"));

       
        Map<String, Object> detailsJson = Map.of(
                "message", body.get("details")
        );
        storagePayload.put("details", detailsJson);
        storagePayload.put("timestamp", body.get("timestamp"));

        
        storage.saveAuditLog(storagePayload);

        
        return Map.of("status", "recorded");
    }

    // GET /audit/logs?from=&to=&userId=&service=
    @GetMapping("/logs")
    public Map<String, Object> getAuditLogs(
            @RequestParam(required = false) String from,
            @RequestParam(required = false) String to,
            @RequestParam(required = false) String userId,
            @RequestParam(required = false) String service
    ) {
        
        return storage.queryAuditLogs(userId, null, from, to);
    }
}
