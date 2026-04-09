package lk.ijse.gdse.cropinventoryservice.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "crop_inventory")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CropInventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Crop name cannot be blank")
    @Column(nullable = false)
    private String cropName;

    @NotBlank(message = "Crop code cannot be blank")
    @Column(unique = true, nullable = false)
    private String cropCode;

    @NotBlank(message = "Crop variety cannot be blank")
    @Column(nullable = false)
    private String cropVariety;

    @NotNull(message = "Quantity cannot be null")
    @Positive(message = "Quantity must be positive")
    @Column(nullable = false)
    private Double quantity;

    @NotBlank(message = "Unit cannot be blank")
    @Column(nullable = false)
    private String unit; // e.g., kg, tons, bags, liters

    @NotBlank(message = "Location/Zone cannot be blank")
    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    private LocalDateTime plantedDate;

    @Column
    private LocalDateTime expectedHarvestDate;

    @Column
    private LocalDateTime harvestDate;

    @NotBlank(message = "Status cannot be blank")
    @Column(nullable = false)
    private String status; // GROWING, READY_FOR_HARVEST, HARVESTED, DAMAGED

    @Column
    private String cropCondition; // HEALTHY, WEAK, DISEASED

    @Column
    private Double expectedYield;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column
    private LocalDateTime updatedAt;

    @Column(columnDefinition = "BOOLEAN DEFAULT true")
    private Boolean isActive = true;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        if (status == null) {
            status = "GROWING";
        }
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
