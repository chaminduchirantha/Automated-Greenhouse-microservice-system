package lk.ijse.gdse.zonemanagementservice.controller;

import lk.ijse.gdse.zonemanagementservice.dto.ZoneDto;
import lk.ijse.gdse.zonemanagementservice.service.ZoneService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/zones")
@RequiredArgsConstructor
public class ZoneController {

    private final ZoneService zoneService;

    @PostMapping
    public ResponseEntity<ZoneDto> createZone(@RequestBody ZoneDto zoneDto) {
        try {
            ZoneDto created = zoneService.createZone(zoneDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(created);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<ZoneDto> getZoneById(@PathVariable Long id) {
        Optional<ZoneDto> zone = zoneService.getZoneById(id);
        return zone.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    @GetMapping
    public ResponseEntity<List<ZoneDto>> getAllZones() {
        List<ZoneDto> zones = zoneService.getAllZones();
        return ResponseEntity.ok(zones);
    }


    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ZoneDto>> getZonesByUserId(@PathVariable Long userId) {
        List<ZoneDto> zones = zoneService.getZonesByUserId(userId);
        return ResponseEntity.ok(zones);
    }


    @PutMapping("/{id}")
    public ResponseEntity<ZoneDto> updateZone(@PathVariable Long id, @RequestBody ZoneDto zoneDto) {
        try {
            ZoneDto updated = zoneService.updateZone(id, zoneDto);
            return ResponseEntity.ok(updated);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteZone(@PathVariable Long id) {
        try {
            zoneService.deleteZone(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<ZoneDto> getZoneByName(@PathVariable String name) {
        Optional<ZoneDto> zone = zoneService.getZoneByName(name);
        return zone.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/device/{deviceId}")
    public ResponseEntity<ZoneDto> getZoneByDeviceId(@PathVariable String deviceId) {
        Optional<ZoneDto> zone = zoneService.getZoneByDeviceId(deviceId);
        return zone.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/validate")
    public ResponseEntity<Boolean> validateZone(@RequestBody ZoneDto zoneDto) {
        boolean isValid = zoneService.validateZoneThresholds(zoneDto);
        return ResponseEntity.ok(isValid);
    }


    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("Zone Management Service is running");
    }
}
