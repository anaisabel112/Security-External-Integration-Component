
//It calls the mock energy provider at localhost:9999
package com.example.demo.client;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;

@Component
public class ExternalEnergyProviderClient {

    private final RestTemplate rest = new RestTemplate();// to perform http calls
    private final String BASE_URL = "http://localhost:9999";

    // Calls GET /provider/consumption from the mock energy provider
    public Map<String, Object> getConsumption(String buildingId, String from, String to) {
        List<String> params = new ArrayList<>();
        params.add("buildingId=" + URLEncoder.encode(buildingId, StandardCharsets.UTF_8));
        if (from != null) {
            params.add("from=" + URLEncoder.encode(from, StandardCharsets.UTF_8));
        }
        if (to != null) {
            params.add("to=" + URLEncoder.encode(to, StandardCharsets.UTF_8));
        }
        String url = BASE_URL + "/provider/consumption?" + String.join("&", params);

        try {
            // Real HTTP call
            return rest.getForObject(url, Map.class);
        } catch (Exception e) {
            return Map.of(
                    "buildingId", buildingId,
                    "data", List.of()
            );
        }
    }
    // Calls GET /provider/tariffs
    public Map<String, Object> getTariffs(String region) {
        String url = BASE_URL + "/provider/tariffs?region=" +
                URLEncoder.encode(region, StandardCharsets.UTF_8);

        try {
            return rest.getForObject(url, Map.class);
        } catch (Exception e) {
            return Map.of(
                    "region", region,
                    "tariffs", List.of()
            );
        }
    }
    // Calls POST /provider/refresh-token
    public Map<String, Object> refreshToken(Map<String, Object> payload) {
        try {
            return rest.postForObject(BASE_URL + "/provider/refresh-token", payload, Map.class);
        } catch (Exception e) {
            return Map.of("status", "external_token_mock");
        }
    }
}
