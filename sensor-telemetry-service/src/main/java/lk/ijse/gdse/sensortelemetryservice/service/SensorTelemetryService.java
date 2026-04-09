package lk.ijse.gdse.sensortelemetryservice.service;

import lk.ijse.gdse.sensortelemetryservice.entity.SensorTelemetry;
import lk.ijse.gdse.sensortelemetryservice.repository.SensorTelemetryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class SensorTelemetryService {

    @Autowired
    private SensorTelemetryRepository sensorTelemetryRepository;

    public SensorTelemetry saveSensorTelemetry(SensorTelemetry sensorTelemetry) {
        return sensorTelemetryRepository.save(sensorTelemetry);
    }

    public Optional<SensorTelemetry> getSensorTelemetryById(Long id) {
        return sensorTelemetryRepository.findById(id);
    }

    public Optional<SensorTelemetry> getSensorTelemetryBySensorId(String sensorId) {
        return sensorTelemetryRepository.findBySensorId(sensorId);
    }

    public List<SensorTelemetry> getAllSensorTelemetry() {
        return sensorTelemetryRepository.findAll();
    }

    public List<SensorTelemetry> getSensorTelemetryBySensorType(String sensorType) {
        return sensorTelemetryRepository.findBySensorType(sensorType);
    }

    public List<SensorTelemetry> getSensorTelemetryByLocation(String location) {
        return sensorTelemetryRepository.findByLocation(location);
    }

    public List<SensorTelemetry> getSensorTelemetryByTimeRange(LocalDateTime startTime, LocalDateTime endTime) {
        return sensorTelemetryRepository.findByRecordedAtBetween(startTime, endTime);
    }

    public List<SensorTelemetry> getSensorTelemetryBySensorIdAndTimeRange(String sensorId, LocalDateTime startTime, LocalDateTime endTime) {
        return sensorTelemetryRepository.findBySensorIdAndRecordedAtBetween(sensorId, startTime, endTime);
    }

    public List<SensorTelemetry> getActiveSensorTelemetry() {
        return sensorTelemetryRepository.findByIsActiveTrue();
    }

    public SensorTelemetry updateSensorTelemetry(Long id, SensorTelemetry sensorTelemetry) {
        Optional<SensorTelemetry> existing = sensorTelemetryRepository.findById(id);
        if (existing.isPresent()) {
            SensorTelemetry telemetry = existing.get();
            if (sensorTelemetry.getReadingValue() != null) {
                telemetry.setReadingValue(sensorTelemetry.getReadingValue());
            }
            if (sensorTelemetry.getLocation() != null) {
                telemetry.setLocation(sensorTelemetry.getLocation());
            }
            if (sensorTelemetry.getIsActive() != null) {
                telemetry.setIsActive(sensorTelemetry.getIsActive());
            }
            return sensorTelemetryRepository.save(telemetry);
        }
        return null;
    }

    public void deleteSensorTelemetry(Long id) {
        sensorTelemetryRepository.deleteById(id);
    }

    public void deactivateSensorTelemetry(Long id) {
        Optional<SensorTelemetry> existing = sensorTelemetryRepository.findById(id);
        if (existing.isPresent()) {
            SensorTelemetry telemetry = existing.get();
            telemetry.setIsActive(false);
            sensorTelemetryRepository.save(telemetry);
        }
    }
}
