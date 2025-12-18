// This is consumed by the endpoints
//It calls the mock external notification services.

package com.example.demo.client;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Component
public class NotificationProviderClient {

    // HTTP client for external calls
    private final RestTemplate rest = new RestTemplate();
    private final String BASE_URL = "http://localhost:9999";

    //// Sends email using the external provider simulation
    // 2.2 POST /external/notifications/email
    public Map<String, Object> sendEmail(Map<String, Object> payload) {
        try {
            return rest.postForObject(BASE_URL + "/external/notifications/email", payload, Map.class);
        } catch (Exception e) {
            return Map.of("status", "email_sent_mock");
        }
    }

    //// Sends SMS via mock external service
    // 2.2 POST /external/notifications/sms
    public Map<String, Object> sendSms(Map<String, Object> payload) {
        try {
            return rest.postForObject(BASE_URL + "/external/notifications/sms", payload, Map.class);
        } catch (Exception e) {
            return Map.of("status", "sms_sent_mock");
        }
    }

    //// Sends push notifications via mock external provider
    // 2.2 POST /external/notifications/push
    public Map<String, Object> sendPush(Map<String, Object> payload) {
        try {
            return rest.postForObject(BASE_URL + "/external/notifications/push", payload, Map.class);
        } catch (Exception e) {
            return Map.of("status", "push_sent_mock");
        }
    }
}
