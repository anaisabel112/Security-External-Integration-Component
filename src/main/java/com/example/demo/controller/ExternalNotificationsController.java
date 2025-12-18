//THIS IS NOT OUT COMPONENT
//This exposes OUR consumed API, only for debugging.
///external/notifications/email
///external/notifications/sms
///external/notifications/push
package com.example.demo.controller;

import com.example.demo.client.NotificationProviderClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/external/notifications")
public class ExternalNotificationsController {

    @Autowired
    private NotificationProviderClient provider;

    @PostMapping("/email")
    public Map<String, Object> sendEmail(@RequestBody Map<String, Object> body) {
        return provider.sendEmail(body);
    }

    @PostMapping("/sms")
    public Map<String, Object> sendSms(@RequestBody Map<String, Object> body) {
        return provider.sendSms(body);
    }

    @PostMapping("/push")
    public Map<String, Object> sendPush(@RequestBody Map<String, Object> body) {
        return provider.sendPush(body);
    }
}
