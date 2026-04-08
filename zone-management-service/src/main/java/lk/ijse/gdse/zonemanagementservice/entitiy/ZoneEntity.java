package lk.ijse.gdse.zonemanagementservice.entitiy;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "zones")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ZoneEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Double minTemp;

    @Column(nullable = false)
    private Double maxTemp;

    @Column(nullable = false)
    private Double minHumidity;

    @Column(nullable = false)
    private Double maxHumidity;

    @Column(nullable = true)
    private String deviceId;

    @Column(nullable = true)
    private String description;

    @Column(nullable = false)
    private Long createdBy;
}