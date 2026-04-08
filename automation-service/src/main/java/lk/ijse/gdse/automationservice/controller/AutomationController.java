package lk.ijse.gdse.automationservice.controller;

import lk.ijse.gdse.automationservice.dto.TelemetryData;
import lk.ijse.gdse.automationservice.entity.AutomationLog;
import lk.ijse.gdse.automationservice.repository.AutomationLogRepository;
import lk.ijse.gdse.automationservice.service.AutomationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/automation")
@RequiredArgsConstructor
public class AutomationController {

    private final AutomationService automationService;
    private final AutomationLogRepository logRepository;

    @PostMapping("/process")
    public ResponseEntity<Void> processTelemetry(@RequestBody TelemetryData data) {
        automationService.processTelemetry(data);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/logs")
    public ResponseEntity<List<AutomationLog>> getAllLogs() {
        List<AutomationLog> logs = automationService.getAllLogs();
        return ResponseEntity.ok(logs);
    }

    @GetMapping("/logs/zone/{zoneId}")
    public ResponseEntity<List<AutomationLog>> getLogsByZone(@PathVariable String zoneId) {
        List<AutomationLog> logs = automationService.getLogsByZone(zoneId);
        return ResponseEntity.ok(logs);
    }

    @GetMapping("/logs/action/{action}")
    public ResponseEntity<List<AutomationLog>> getLogsByAction(@PathVariable String action) {
        List<AutomationLog> logs = automationService.getLogsByAction(action);
        return ResponseEntity.ok(logs);
    }

    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("Automation Service is running");
    }
}
