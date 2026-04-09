package lk.ijse.gdse.sensortelemetryservice.controller;

import lk.ijse.gdse.sensortelemetryservice.entity.SensorTelemetry;
import lk.ijse.gdse.sensortelemetryservice.service.SensorTelemetryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/sensor-telemetry")
@CrossOrigin(origins = "*", maxAge = 3600)
public class SensorTelemetryController {

    @Autowired
    private SensorTelemetryService sensorTelemetryService;

    @PostMapping
    public ResponseEntity<SensorTelemetry> createSensorTelemetry(@Valid @RequestBody SensorTelemetry sensorTelemetry) {
        SensorTelemetry created = sensorTelemetryService.saveSensorTelemetry(sensorTelemetry);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SensorTelemetry> getSensorTelemetryById(@PathVariable Long id) {
        Optional<SensorTelemetry> telemetry = sensorTelemetryService.getSensorTelemetryById(id);
        return telemetry.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/sensor/{sensorId}")
    public ResponseEntity<SensorTelemetry> getSensorTelemetryBySensorId(@PathVariable String sensorId) {
        Optional<SensorTelemetry> telemetry = sensorTelemetryService.getSensorTelemetryBySensorId(sensorId);
        return telemetry.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<SensorTelemetry>> getAllSensorTelemetry() {
        List<SensorTelemetry> telemetries = sensorTelemetryService.getAllSensorTelemetry();
        return ResponseEntity.ok(telemetries);
    }

    @GetMapping("/type/{sensorType}")
    public ResponseEntity<List<SensorTelemetry>> getSensorTelemetryByType(@PathVariable String sensorType) {
        List<SensorTelemetry> telemetries = sensorTelemetryService.getSensorTelemetryBySensorType(sensorType);
        return ResponseEntity.ok(telemetries);
    }

    @GetMapping("/location/{location}")
    public ResponseEntity<List<SensorTelemetry>> getSensorTelemetryByLocation(@PathVariable String location) {
        List<SensorTelemetry> telemetries = sensorTelemetryService.getSensorTelemetryByLocation(location);
        return ResponseEntity.ok(telemetries);
    }

    @GetMapping("/time-range")
    public ResponseEntity<List<SensorTelemetry>> getSensorTelemetryByTimeRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startTime,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endTime) {
        List<SensorTelemetry> telemetries = sensorTelemetryService.getSensorTelemetryByTimeRange(startTime, endTime);
        return ResponseEntity.ok(telemetries);
    }

    @GetMapping("/sensor/{sensorId}/time-range")
    public ResponseEntity<List<SensorTelemetry>> getSensorTelemetryBySensorIdAndTimeRange(
            @PathVariable String sensorId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startTime,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endTime) {
        List<SensorTelemetry> telemetries = sensorTelemetryService.getSensorTelemetryBySensorIdAndTimeRange(sensorId, startTime, endTime);
        return ResponseEntity.ok(telemetries);
    }

    @GetMapping("/active")
    public ResponseEntity<List<SensorTelemetry>> getActiveSensorTelemetry() {
        List<SensorTelemetry> telemetries = sensorTelemetryService.getActiveSensorTelemetry();
        return ResponseEntity.ok(telemetries);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SensorTelemetry> updateSensorTelemetry(@PathVariable Long id, @RequestBody SensorTelemetry sensorTelemetry) {
        SensorTelemetry updated = sensorTelemetryService.updateSensorTelemetry(id, sensorTelemetry);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSensorTelemetry(@PathVariable Long id) {
        sensorTelemetryService.deleteSensorTelemetry(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/deactivate")
    public ResponseEntity<Void> deactivateSensorTelemetry(@PathVariable Long id) {
        sensorTelemetryService.deactivateSensorTelemetry(id);
        return ResponseEntity.noContent().build();
    }
}
