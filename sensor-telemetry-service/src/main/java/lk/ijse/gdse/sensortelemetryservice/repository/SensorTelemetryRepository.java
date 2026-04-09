package lk.ijse.gdse.sensortelemetryservice.repository;

import lk.ijse.gdse.sensortelemetryservice.entity.SensorTelemetry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface SensorTelemetryRepository extends JpaRepository<SensorTelemetry, Long> {

    Optional<SensorTelemetry> findBySensorId(String sensorId);

    List<SensorTelemetry> findBySensorType(String sensorType);

    List<SensorTelemetry> findByLocation(String location);

    List<SensorTelemetry> findByRecordedAtBetween(LocalDateTime startTime, LocalDateTime endTime);

    List<SensorTelemetry> findBySensorIdAndRecordedAtBetween(String sensorId, LocalDateTime startTime, LocalDateTime endTime);

    List<SensorTelemetry> findByIsActiveTrue();
}
