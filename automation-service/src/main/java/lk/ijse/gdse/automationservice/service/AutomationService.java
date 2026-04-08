package lk.ijse.gdse.automationservice.service;

import lk.ijse.gdse.automationservice.client.ZoneServiceClient;
import lk.ijse.gdse.automationservice.dto.TelemetryData;
import lk.ijse.gdse.automationservice.dto.ZoneResponse;
import lk.ijse.gdse.automationservice.entity.AutomationLog;
import lk.ijse.gdse.automationservice.repository.AutomationLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AutomationService {

    private final AutomationLogRepository logRepository;
    private final ZoneServiceClient zoneClient;

    /**
     * Process telemetry data and apply automation rules
     * @param data TelemetryData containing temperature, humidity, and zone information
     */
    public void processTelemetry(TelemetryData data) {
        try {
            // 1. Fetch thresholds from Zone Service
            ZoneResponse limits = zoneClient.getZoneDetails(data.getZoneId());

            if (limits == null) {
                System.out.println("Zone not found for zone ID: " + data.getZoneId());
                return;
            }

            // 2. Apply Temperature Rules
            if (data.getTemperature() > limits.getMaxTemp()) {
                String reason = "Temperature is " + data.getTemperature() + "°C (Max: " + limits.getMaxTemp() + "°C)";
                saveLog(data.getZoneId(), "TURN_FAN_ON", reason);
            } 
            else if (data.getTemperature() < limits.getMinTemp()) {
                String reason = "Temperature is " + data.getTemperature() + "°C (Min: " + limits.getMinTemp() + "°C)";
                saveLog(data.getZoneId(), "TURN_HEATER_ON", reason);
            }

            // 3. Apply Humidity Rules (Optional: can be extended)
            if (data.getHumidity() > limits.getMaxHumidity()) {
                String reason = "Humidity is " + data.getHumidity() + "% (Max: " + limits.getMaxHumidity() + "%)";
                saveLog(data.getZoneId(), "ACTIVATE_DEHUMIDIFIER", reason);
            }
            else if (data.getHumidity() < limits.getMinHumidity()) {
                String reason = "Humidity is " + data.getHumidity() + "% (Min: " + limits.getMinHumidity() + "%)";
                saveLog(data.getZoneId(), "ACTIVATE_HUMIDIFIER", reason);
            }

        } catch (Exception e) {
            System.out.println("Error processing telemetry: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Save automation action log to database
     */
    private void saveLog(String zoneId, String action, String reason) {
        AutomationLog log = new AutomationLog(zoneId, action, reason, LocalDateTime.now());
        logRepository.save(log);
        System.out.println("ACTION LOGGED: " + action + " for Zone " + zoneId + " - Reason: " + reason);
    }

    /**
     * Retrieve all automation logs
     */
    public List<AutomationLog> getAllLogs() {
        return logRepository.findAll();
    }

    /**
     * Retrieve logs for a specific zone
     */
    public List<AutomationLog> getLogsByZone(String zoneId) {
        return logRepository.findByZoneId(zoneId);
    }

    /**
     * Retrieve logs of a specific action type
     */
    public List<AutomationLog> getLogsByAction(String action) {
        return logRepository.findByAction(action);
    }
}
