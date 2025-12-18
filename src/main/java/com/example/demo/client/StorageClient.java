//It sends audit logs and credentials to the mock storage service.
//It calls the mock external notification services.


package com.example.demo.client;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;

@Component
public class StorageClient {

    //// HTTP client for external calls
    private final RestTemplate rest = new RestTemplate();
    private final String BASE_URL = "http://localhost:9999";


    // Sends credentials to storage service
    // 2.1 POST /storage/auth/credentials
    public Map<String, Object> saveCredentials(Map<String, Object> payload) {
        try {
            return rest.postForObject(BASE_URL + "/storage/auth/credentials", payload, Map.class);
        } catch (Exception e) {
            return Map.of("status", "saved (mock)");
        }
    }

    // Sends audit logs to storage
    // 2.1 POST /storage/audit/save
    public Map<String, Object> saveAuditLog(Map<String, Object> payload) {
        try {
            return rest.postForObject(BASE_URL + "/storage/audit/save", payload, Map.class);
        } catch (Exception e) {
            return Map.of("status", "saved (mock)");
        }
    }

    // Retrieves audit logs from storage
    // 2.1 GET /storage/audit/query?userId=&action=&from=&to=
    public Map<String, Object> queryAuditLogs(String userId, String action, String from, String to) {

        List<String> params = new ArrayList<>();
        if (userId != null) {
            params.add("userId=" + URLEncoder.encode(userId, StandardCharsets.UTF_8));
        }
        if (action != null) {
            params.add("action=" + URLEncoder.encode(action, StandardCharsets.UTF_8));
        }
        if (from != null) {
            params.add("from=" + URLEncoder.encode(from, StandardCharsets.UTF_8));
        }
        if (to != null) {
            params.add("to=" + URLEncoder.encode(to, StandardCharsets.UTF_8));
        }

        String queryString = params.isEmpty() ? "" : "?" + String.join("&", params);
        String url = BASE_URL + "/storage/audit/query" + queryString;

        try {
            return rest.getForObject(url, Map.class);
        } catch (Exception e) {
        
            return Map.of(
                    "logs", List.of()
            );
        }
    }
}
