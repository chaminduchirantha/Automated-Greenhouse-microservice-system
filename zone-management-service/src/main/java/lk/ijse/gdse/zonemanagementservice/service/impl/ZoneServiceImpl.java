package lk.ijse.gdse.zonemanagementservice.service.impl;

import lk.ijse.gdse.zonemanagementservice.dto.ZoneDto;
import lk.ijse.gdse.zonemanagementservice.entitiy.ZoneEntity;
import lk.ijse.gdse.zonemanagementservice.repo.ZoneRepo;
import lk.ijse.gdse.zonemanagementservice.service.ZoneService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ZoneServiceImpl implements ZoneService {

    private final ZoneRepo zoneRepo;

    private ZoneDto entityToDto(ZoneEntity entity) {
        return new ZoneDto(
            entity.getId(),
            entity.getName(),
            entity.getMinTemp(),
            entity.getMaxTemp(),
            entity.getMinHumidity(),
            entity.getMaxHumidity(),
            entity.getDeviceId(),
            entity.getDescription(),
            entity.getCreatedBy()
        );
    }


    private ZoneEntity dtoToEntity(ZoneDto dto) {
        return new ZoneEntity(
            dto.getId(),
            dto.getName(),
            dto.getMinTemp(),
            dto.getMaxTemp(),
            dto.getMinHumidity(),
            dto.getMaxHumidity(),
            dto.getDeviceId(),
            dto.getDescription(),
            dto.getCreatedBy()
        );
    }

    @Override
    public ZoneDto createZone(ZoneDto zoneDto) {
        // Validate thresholds
        if (!validateZoneThresholds(zoneDto)) {
            throw new IllegalArgumentException("Invalid zone thresholds: minTemp must be less than maxTemp, minHumidity must be less than maxHumidity");
        }

        // Check if zone name already exists
        if (zoneRepo.existsByName(zoneDto.getName())) {
            throw new IllegalArgumentException("Zone with name '" + zoneDto.getName() + "' already exists");
        }

        ZoneEntity entity = dtoToEntity(zoneDto);
        ZoneEntity saved = zoneRepo.save(entity);
        return entityToDto(saved);
    }

    @Override
    public Optional<ZoneDto> getZoneById(Long id) {
        return zoneRepo.findById(id)
                .map(this::entityToDto);
    }

    @Override
    public List<ZoneDto> getAllZones() {
        return zoneRepo.findAll()
                .stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ZoneDto> getZonesByUserId(Long userId) {
        return zoneRepo.findByCreatedBy(userId)
                .stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public ZoneDto updateZone(Long id, ZoneDto zoneDto) {
        ZoneEntity entity = zoneRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Zone not found with id: " + id));

        // Validate thresholds
        if (!validateZoneThresholds(zoneDto)) {
            throw new IllegalArgumentException("Invalid zone thresholds: minTemp must be less than maxTemp, minHumidity must be less than maxHumidity");
        }

        // Update fields
        entity.setName(zoneDto.getName());
        entity.setMinTemp(zoneDto.getMinTemp());
        entity.setMaxTemp(zoneDto.getMaxTemp());
        entity.setMinHumidity(zoneDto.getMinHumidity());
        entity.setMaxHumidity(zoneDto.getMaxHumidity());
        entity.setDescription(zoneDto.getDescription());
        entity.setDeviceId(zoneDto.getDeviceId());

        ZoneEntity updated = zoneRepo.save(entity);
        return entityToDto(updated);
    }

    @Override
    public void deleteZone(Long id) {
        if (!zoneRepo.existsById(id)) {
            throw new IllegalArgumentException("Zone not found with id: " + id);
        }
        zoneRepo.deleteById(id);
    }

    @Override
    public Optional<ZoneDto> getZoneByName(String name) {
        return zoneRepo.findByName(name)
                .map(this::entityToDto);
    }

    @Override
    public Optional<ZoneDto> getZoneByDeviceId(String deviceId) {
        return zoneRepo.findByDeviceId(deviceId)
                .map(this::entityToDto);
    }

    @Override
    public boolean validateZoneThresholds(ZoneDto zoneDto) {
        // minTemp must be less than maxTemp
        if (zoneDto.getMinTemp() >= zoneDto.getMaxTemp()) {
            System.out.println("Validation failed: minTemp (" + zoneDto.getMinTemp() + 
                             ") must be less than maxTemp (" + zoneDto.getMaxTemp() + ")");
            return false;
        }

        // minHumidity must be less than maxHumidity
        if (zoneDto.getMinHumidity() >= zoneDto.getMaxHumidity()) {
            System.out.println("Validation failed: minHumidity (" + zoneDto.getMinHumidity() + 
                             ") must be less than maxHumidity (" + zoneDto.getMaxHumidity() + ")");
            return false;
        }

        // All values must be non-negative
        if (zoneDto.getMinTemp() < 0 || zoneDto.getMinHumidity() < 0) {
            System.out.println("Validation failed: Temperature and humidity values cannot be negative");
            return false;
        }

        // Humidity must be between 0 and 100
        if (zoneDto.getMaxHumidity() > 100) {
            System.out.println("Validation failed: maxHumidity cannot exceed 100%");
            return false;
        }

        return true;
    }
}
