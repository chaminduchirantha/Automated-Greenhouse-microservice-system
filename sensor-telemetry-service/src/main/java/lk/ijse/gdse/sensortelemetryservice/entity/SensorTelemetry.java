package lk.ijse.gdse.sensortelemetryservice.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "sensor_telemetry")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SensorTelemetry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Sensor ID cannot be blank")
    @Column(unique = true, nullable = false)
    private String sensorId;

    @NotBlank(message = "Sensor type cannot be blank")
    @Column(nullable = false)
    private String sensorType; // e.g., TEMPERATURE, HUMIDITY, SOIL_MOISTURE, etc.

    @NotNull(message = "Reading value cannot be null")
    @Column(nullable = false)
    private Double readingValue;

    @Column(nullable = false)
    private String unit; // e.g., Celsius, Percentage, pH, etc.

    @NotBlank(message = "Location cannot be blank")
    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    private LocalDateTime recordedAt;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column
    private LocalDateTime updatedAt;

    @Column(columnDefinition = "BOOLEAN DEFAULT true")
    private Boolean isActive = true;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        recordedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
