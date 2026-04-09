package lk.ijse.gdse.automationservice.client;

import lk.ijse.gdse.automationservice.dto.ZoneResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ZONE-SERVICE")
public interface ZoneServiceClient {
    
    @GetMapping("/api/zones/{id}")
    ZoneResponse getZoneDetails(@PathVariable("id") String zoneId);
}

