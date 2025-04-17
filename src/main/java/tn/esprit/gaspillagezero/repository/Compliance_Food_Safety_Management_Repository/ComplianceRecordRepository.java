package tn.esprit.gaspillagezero.repository.Compliance_Food_Safety_Management_Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.gaspillagezero.entites.Compliance_Food_Safety_Management.ComplianceRecord;

@Repository
public interface ComplianceRecordRepository extends JpaRepository<ComplianceRecord,Long> {
}
