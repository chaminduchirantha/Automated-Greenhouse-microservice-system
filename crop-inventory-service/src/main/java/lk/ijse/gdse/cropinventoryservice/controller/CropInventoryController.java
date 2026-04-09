package lk.ijse.gdse.cropinventoryservice.controller;

import lk.ijse.gdse.cropinventoryservice.entity.CropInventory;
import lk.ijse.gdse.cropinventoryservice.service.CropInventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/crop-inventory")
@CrossOrigin(origins = "*", maxAge = 3600)
public class CropInventoryController {

    @Autowired
    private CropInventoryService cropInventoryService;

    @PostMapping
    public ResponseEntity<CropInventory> createCropInventory(@Valid @RequestBody CropInventory cropInventory) {
        CropInventory created = cropInventoryService.saveCropInventory(cropInventory);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CropInventory> getCropInventoryById(@PathVariable Long id) {
        Optional<CropInventory> crop = cropInventoryService.getCropInventoryById(id);
        return crop.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/code/{cropCode}")
    public ResponseEntity<CropInventory> getCropInventoryByCode(@PathVariable String cropCode) {
        Optional<CropInventory> crop = cropInventoryService.getCropInventoryByCode(cropCode);
        return crop.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<CropInventory>> getAllCropInventory() {
        List<CropInventory> crops = cropInventoryService.getAllCropInventory();
        return ResponseEntity.ok(crops);
    }

    @GetMapping("/name/{cropName}")
    public ResponseEntity<List<CropInventory>> getCropInventoryByName(@PathVariable String cropName) {
        List<CropInventory> crops = cropInventoryService.getCropInventoryByName(cropName);
        return ResponseEntity.ok(crops);
    }

    @GetMapping("/location/{location}")
    public ResponseEntity<List<CropInventory>> getCropInventoryByLocation(@PathVariable String location) {
        List<CropInventory> crops = cropInventoryService.getCropInventoryByLocation(location);
        return ResponseEntity.ok(crops);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<CropInventory>> getCropInventoryByStatus(@PathVariable String status) {
        List<CropInventory> crops = cropInventoryService.getCropInventoryByStatus(status);
        return ResponseEntity.ok(crops);
    }

    @GetMapping("/condition/{cropCondition}")
    public ResponseEntity<List<CropInventory>> getCropInventoryByCondition(@PathVariable String cropCondition) {
        List<CropInventory> crops = cropInventoryService.getCropInventoryByCondition(cropCondition);
        return ResponseEntity.ok(crops);
    }

    @GetMapping("/planted-date-range")
    public ResponseEntity<List<CropInventory>> getCropInventoryByPlantingDateRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {
        List<CropInventory> crops = cropInventoryService.getCropInventoryByPlantingDateRange(startDate, endDate);
        return ResponseEntity.ok(crops);
    }

    @GetMapping("/harvest-date-range")
    public ResponseEntity<List<CropInventory>> getCropInventoryByHarvestDateRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {
        List<CropInventory> crops = cropInventoryService.getCropInventoryByHarvestDateRange(startDate, endDate);
        return ResponseEntity.ok(crops);
    }

    @GetMapping("/location/{location}/status/{status}")
    public ResponseEntity<List<CropInventory>> getCropInventoryByLocationAndStatus(
            @PathVariable String location,
            @PathVariable String status) {
        List<CropInventory> crops = cropInventoryService.getCropInventoryByLocationAndStatus(location, status);
        return ResponseEntity.ok(crops);
    }

    @GetMapping("/active")
    public ResponseEntity<List<CropInventory>> getActiveCropInventory() {
        List<CropInventory> crops = cropInventoryService.getActiveCropInventory();
        return ResponseEntity.ok(crops);
    }

    @GetMapping("/active/status/{status}")
    public ResponseEntity<List<CropInventory>> getActiveCropInventoryByStatus(@PathVariable String status) {
        List<CropInventory> crops = cropInventoryService.getActiveCropInventoryByStatus(status);
        return ResponseEntity.ok(crops);
    }

    @GetMapping("/count/status/{status}")
    public ResponseEntity<Long> getCropCountByStatus(@PathVariable String status) {
        Long count = cropInventoryService.getCropCountByStatus(status);
        return ResponseEntity.ok(count);
    }

    @GetMapping("/count/location/{location}")
    public ResponseEntity<Long> getCropCountByLocation(@PathVariable String location) {
        Long count = cropInventoryService.getCropCountByLocation(location);
        return ResponseEntity.ok(count);
    }

    @GetMapping("/quantity/location/{location}")
    public ResponseEntity<Double> getTotalCropQuantityByLocation(@PathVariable String location) {
        Double totalQuantity = cropInventoryService.getTotalCropQuantityByLocation(location);
        return ResponseEntity.ok(totalQuantity);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CropInventory> updateCropInventory(@PathVariable Long id, @RequestBody CropInventory cropInventory) {
        CropInventory updated = cropInventoryService.updateCropInventory(id, cropInventory);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCropInventory(@PathVariable Long id) {
        cropInventoryService.deleteCropInventory(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/deactivate")
    public ResponseEntity<Void> deactivateCropInventory(@PathVariable Long id) {
        cropInventoryService.deactivateCropInventory(id);
        return ResponseEntity.noContent().build();
    }
}
