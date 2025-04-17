package tn.esprit.gaspillagezero.repository.Inventory_Managemen_Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.gaspillagezero.entites.Inventory_Managemen.SalesRecord;
@Repository
public interface SalesRecordRepository extends JpaRepository<SalesRecord, Long> {
}
