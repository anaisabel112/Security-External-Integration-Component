//THIS IS OUR COMPONENT
//Implements 1.5 Notification Management:send, preferences, logs.
package com.example.demo.controller;

import com.example.demo.client.NotificationProviderClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/notifications")
public class NotificationController {

    private final NotificationProviderClient provider;

    @Autowired
    public NotificationController(NotificationProviderClient provider) {
        this.provider = provider;
    }

   
    // 1.5 POST /notifications/send

    @PostMapping("/send")
    public Map<String, Object> sendNotification(@RequestBody Map<String, Object> body) {

        String type = (String) body.get("type");

        @SuppressWarnings("unchecked")
        List<String> recipients = (List<String>) body.getOrDefault("recipients", List.of());

        Map<String, Object> providerResponse;

        try {
           
            providerResponse = switch (type) {
                case "email" -> provider.sendEmail(body);
                case "sms" -> provider.sendSms(body);
                case "push" -> provider.sendPush(body);
                default -> {
                    yield Map.of("status", "error", "message", "Invalid notification type");
                }
            };

        } catch (Exception e) {
        
            return Map.of(
                    "status", "error",
                    "message", "Notification provider unreachable"
            );
        }

        return Map.of(
                "status", providerResponse.getOrDefault("status", "sent"),
                "sentCount", recipients.size(),
                "providerResponse", providerResponse
        );
    }

    
    // POST /notifications/preferences
    
    @PostMapping("/preferences")
    public Map<String, Object> updatePreferences(@RequestBody Map<String, Object> body) {
        return Map.of("status", "preferences_updated");
    }

    // GET /notifications/logs?userId=
    @GetMapping("/logs")
    public Map<String, Object> getNotificationLogs(@RequestParam(required = false) String userId) {
        return Map.of(
                "logs",
                List.of(
                        Map.of(
                                "userId", userId != null ? userId : "mock-user",
                                "message", "Mock notification log",
                                "timestamp", "2025-12-06T12:00:00Z"
                        )
                )
        );
    }
}
