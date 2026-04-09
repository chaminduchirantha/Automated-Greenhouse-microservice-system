package lk.ijse.gdse.cropinventoryservice.repository;

import lk.ijse.gdse.cropinventoryservice.entity.CropInventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface CropInventoryRepository extends JpaRepository<CropInventory, Long> {

    Optional<CropInventory> findByCropCode(String cropCode);

    List<CropInventory> findByCropName(String cropName);

    List<CropInventory> findByLocation(String location);

    List<CropInventory> findByStatus(String status);

    List<CropInventory> findByCropCondition(String cropCondition);

    List<CropInventory> findByPlantedDateBetween(LocalDateTime startDate, LocalDateTime endDate);

    List<CropInventory> findByHarvestDateBetween(LocalDateTime startDate, LocalDateTime endDate);

    List<CropInventory> findByLocationAndStatus(String location, String status);

    List<CropInventory> findByIsActiveTrue();

    List<CropInventory> findByStatusAndIsActiveTrue(String status);

    Long countByStatus(String status);

    Long countByLocation(String location);
}
