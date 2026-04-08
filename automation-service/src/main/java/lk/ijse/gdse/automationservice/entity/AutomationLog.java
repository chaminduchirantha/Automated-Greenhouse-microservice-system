package lk.ijse.gdse.automationservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "automation_logs")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AutomationLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String zoneId;

    @Column(nullable = false)
    private String action;

    @Column(nullable = false)
    private String reason;

    @Column(nullable = false)
    private LocalDateTime triggeredAt;

    public AutomationLog(String zoneId, String action, String reason, LocalDateTime triggeredAt) {
        this.zoneId = zoneId;
        this.action = action;
        this.reason = reason;
        this.triggeredAt = triggeredAt;
    }
}
