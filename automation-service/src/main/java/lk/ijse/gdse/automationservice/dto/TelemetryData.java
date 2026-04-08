package lk.ijse.gdse.automationservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TelemetryData {

    private String deviceId;
    private String zoneId;
    private double temperature;
    private String tempUnit;    // CELSIUS or FAHRENHEIT
    private double humidity;
    private String humidityUnit; // PERCENTAGE
    private LocalDateTime capturedAt;
}
