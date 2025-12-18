//THIS IS OUR COMPONENT
//Uses ExternalEnergyProviderClient to reach mock external provider.
package com.example.demo.controller;

import com.example.demo.client.ExternalEnergyProviderClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/external-energy")
public class ExternalEnergyController {

    private final ExternalEnergyProviderClient energyClient;

    @Autowired
    public ExternalEnergyController(ExternalEnergyProviderClient energyClient) {
        this.energyClient = energyClient;
    }

    // 1.7 GET /external-energy/consumption?buildingId=&from=&to=
    @GetMapping("/consumption")
    public Map<String, Object> consumption(
            @RequestParam String buildingId,
            @RequestParam(required = false) String from,
            @RequestParam(required = false) String to
    ) {
        return energyClient.getConsumption(buildingId, from, to);
    }

    // 1.7 GET /external-energy/tariffs?region=
    @GetMapping("/tariffs")
    public Map<String, Object> getTariffs(@RequestParam String region) {
        return energyClient.getTariffs(region);
    }

    // 1.7 POST /external-energy/refresh-token
    @PostMapping("/refresh-token")
    public Map<String, Object> refreshExternalToken(@RequestBody Map<String, Object> body) {
        return energyClient.refreshToken(body);
    }
}
