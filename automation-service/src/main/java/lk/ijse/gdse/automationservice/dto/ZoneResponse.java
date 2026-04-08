package lk.ijse.gdse.automationservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ZoneResponse {

    private String id;
    private String name;
    private double minTemp;
    private double maxTemp;
    private double minHumidity;
    private double maxHumidity;
}
