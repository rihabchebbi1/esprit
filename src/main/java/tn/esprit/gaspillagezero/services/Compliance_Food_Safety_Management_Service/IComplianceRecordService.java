package tn.esprit.gaspillagezero.services.Compliance_Food_Safety_Management_Service;

import tn.esprit.gaspillagezero.entites.Compliance_Food_Safety_Management.ComplianceRecord;

import java.util.List;

public interface IComplianceRecordService {

    ComplianceRecord addComplianceRecord (ComplianceRecord  object);
    ComplianceRecord  updateComplianceRecord (ComplianceRecord  object);
    void deleteComplianceRecord (long id);
    List<ComplianceRecord > retreiveAllComplianceRecord();
    ComplianceRecord  retreiveComplianceRecord (long id);
}



