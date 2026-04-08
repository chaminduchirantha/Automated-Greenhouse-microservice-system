package lk.ijse.gdse.zonemanagementservice.service;

import lk.ijse.gdse.zonemanagementservice.dto.ZoneDto;

import java.util.List;
import java.util.Optional;

public interface ZoneService {

    /**
     * Create a new zone
     */
    ZoneDto createZone(ZoneDto zoneDto);

    /**
     * Get zone by ID
     */
    Optional<ZoneDto> getZoneById(Long id);

    /**
     * Get all zones
     */
    List<ZoneDto> getAllZones();

    /**
     * Get all zones for a specific user
     */
    List<ZoneDto> getZonesByUserId(Long userId);

    /**
     * Update an existing zone
     */
    ZoneDto updateZone(Long id, ZoneDto zoneDto);

    /**
     * Delete a zone by ID
     */
    void deleteZone(Long id);

    /**
     * Get zone by name
     */
    Optional<ZoneDto> getZoneByName(String name);

    /**
     * Get zone by device ID
     */
    Optional<ZoneDto> getZoneByDeviceId(String deviceId);

    /**
     * Validate zone thresholds (minTemp < maxTemp, etc.)
     */
    boolean validateZoneThresholds(ZoneDto zoneDto);
}
