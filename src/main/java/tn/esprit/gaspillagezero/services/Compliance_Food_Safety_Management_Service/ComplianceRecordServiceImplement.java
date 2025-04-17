package tn.esprit.gaspillagezero.services.Compliance_Food_Safety_Management_Service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.gaspillagezero.entites.Compliance_Food_Safety_Management.ComplianceRecord;
import tn.esprit.gaspillagezero.repository.Compliance_Food_Safety_Management_Repository.ComplianceRecordRepository;

import java.util.List;
@Service
public class ComplianceRecordServiceImplement implements IComplianceRecordService {
    @Autowired
     ComplianceRecordRepository complianceRecordRepository;
    @Override
    public ComplianceRecord addComplianceRecord(ComplianceRecord object) {
        return complianceRecordRepository.save(object);
    }

    @Override
    public ComplianceRecord updateComplianceRecord(ComplianceRecord object) {
        return complianceRecordRepository.save(object);
    }

    @Override
    public void deleteComplianceRecord(long id) {
        complianceRecordRepository.deleteById(id);

    }

    @Override
    public List<ComplianceRecord> retreiveAllComplianceRecord() {
        return complianceRecordRepository.findAll();
    }

    @Override
    public ComplianceRecord retreiveComplianceRecord(long id) {
        return complianceRecordRepository.findById(id).get();
    }
}
