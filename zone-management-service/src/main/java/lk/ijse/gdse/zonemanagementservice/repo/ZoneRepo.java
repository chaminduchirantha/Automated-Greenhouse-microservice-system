package lk.ijse.gdse.zonemanagementservice.repo;

import lk.ijse.gdse.zonemanagementservice.entitiy.ZoneEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ZoneRepo extends JpaRepository<ZoneEntity, Long> {

    // Find zone by name
    Optional<ZoneEntity> findByName(String name);

    // Find all zones for a specific user
    List<ZoneEntity> findByCreatedBy(Long userId);

    // Find zone by device ID
    Optional<ZoneEntity> findByDeviceId(String deviceId);

    // Check if zone exists by name
    boolean existsByName(String name);
}
