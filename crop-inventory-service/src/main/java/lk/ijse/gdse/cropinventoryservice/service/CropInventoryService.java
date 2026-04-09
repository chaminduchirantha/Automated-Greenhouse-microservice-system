package lk.ijse.gdse.cropinventoryservice.service;

import lk.ijse.gdse.cropinventoryservice.entity.CropInventory;
import lk.ijse.gdse.cropinventoryservice.repository.CropInventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CropInventoryService {

    @Autowired
    private CropInventoryRepository cropInventoryRepository;

    public CropInventory saveCropInventory(CropInventory cropInventory) {
        return cropInventoryRepository.save(cropInventory);
    }

    public Optional<CropInventory> getCropInventoryById(Long id) {
        return cropInventoryRepository.findById(id);
    }

    public Optional<CropInventory> getCropInventoryByCode(String cropCode) {
        return cropInventoryRepository.findByCropCode(cropCode);
    }

    public List<CropInventory> getAllCropInventory() {
        return cropInventoryRepository.findAll();
    }

    public List<CropInventory> getCropInventoryByName(String cropName) {
        return cropInventoryRepository.findByCropName(cropName);
    }

    public List<CropInventory> getCropInventoryByLocation(String location) {
        return cropInventoryRepository.findByLocation(location);
    }

    public List<CropInventory> getCropInventoryByStatus(String status) {
        return cropInventoryRepository.findByStatus(status);
    }

    public List<CropInventory> getCropInventoryByCondition(String cropCondition) {
        return cropInventoryRepository.findByCropCondition(cropCondition);
    }

    public List<CropInventory> getCropInventoryByPlantingDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        return cropInventoryRepository.findByPlantedDateBetween(startDate, endDate);
    }

    public List<CropInventory> getCropInventoryByHarvestDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        return cropInventoryRepository.findByHarvestDateBetween(startDate, endDate);
    }

    public List<CropInventory> getCropInventoryByLocationAndStatus(String location, String status) {
        return cropInventoryRepository.findByLocationAndStatus(location, status);
    }

    public List<CropInventory> getActiveCropInventory() {
        return cropInventoryRepository.findByIsActiveTrue();
    }

    public List<CropInventory> getActiveCropInventoryByStatus(String status) {
        return cropInventoryRepository.findByStatusAndIsActiveTrue(status);
    }

    public CropInventory updateCropInventory(Long id, CropInventory cropInventory) {
        Optional<CropInventory> existing = cropInventoryRepository.findById(id);
        if (existing.isPresent()) {
            CropInventory crop = existing.get();
            if (cropInventory.getQuantity() != null) {
                crop.setQuantity(cropInventory.getQuantity());
            }
            if (cropInventory.getStatus() != null) {
                crop.setStatus(cropInventory.getStatus());
            }
            if (cropInventory.getCropCondition() != null) {
                crop.setCropCondition(cropInventory.getCropCondition());
            }
            if (cropInventory.getHarvestDate() != null) {
                crop.setHarvestDate(cropInventory.getHarvestDate());
            }
            if (cropInventory.getExpectedYield() != null) {
                crop.setExpectedYield(cropInventory.getExpectedYield());
            }
            if (cropInventory.getIsActive() != null) {
                crop.setIsActive(cropInventory.getIsActive());
            }
            return cropInventoryRepository.save(crop);
        }
        return null;
    }

    public void deleteCropInventory(Long id) {
        cropInventoryRepository.deleteById(id);
    }

    public void deactivateCropInventory(Long id) {
        Optional<CropInventory> existing = cropInventoryRepository.findById(id);
        if (existing.isPresent()) {
            CropInventory crop = existing.get();
            crop.setIsActive(false);
            cropInventoryRepository.save(crop);
        }
    }

    public Long getCropCountByStatus(String status) {
        return cropInventoryRepository.countByStatus(status);
    }

    public Long getCropCountByLocation(String location) {
        return cropInventoryRepository.countByLocation(location);
    }

    public Double getTotalCropQuantityByLocation(String location) {
        List<CropInventory> crops = getCropInventoryByLocation(location);
        return crops.stream().mapToDouble(CropInventory::getQuantity).sum();
    }
}
