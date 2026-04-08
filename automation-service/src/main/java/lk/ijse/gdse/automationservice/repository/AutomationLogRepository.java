package lk.ijse.gdse.automationservice.repository;

import lk.ijse.gdse.automationservice.entity.AutomationLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AutomationLogRepository extends JpaRepository<AutomationLog, Long> {

    // Find all logs for a specific zone
    List<AutomationLog> findByZoneId(String zoneId);

    // Find all logs for a specific action
    List<AutomationLog> findByAction(String action);
}
