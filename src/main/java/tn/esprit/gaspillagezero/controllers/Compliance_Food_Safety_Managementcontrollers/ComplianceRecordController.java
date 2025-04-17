package tn.esprit.gaspillagezero.controllers.Compliance_Food_Safety_Managementcontrollers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.gaspillagezero.entites.Compliance_Food_Safety_Management.ComplianceRecord;
import tn.esprit.gaspillagezero.services.Compliance_Food_Safety_Management_Service.IComplianceRecordService;

import java.util.List;

@RestController
@RequestMapping("/ComplianceRecord")
public class ComplianceRecordController {
    @Autowired
    IComplianceRecordService iComplianceRecordService;

    @PostMapping("/addComplianceRecord")
    ComplianceRecord addComplianceRecord(@RequestBody ComplianceRecord obj) {
        return iComplianceRecordService.addComplianceRecord(obj);
    }

    @PutMapping("/updateComplianceRecord")
    ComplianceRecord updateEquipe(@RequestBody ComplianceRecord obj) {
        return iComplianceRecordService.updateComplianceRecord(obj);
    }

    @GetMapping("/retreiveAllComplianceRecord")
    List<ComplianceRecord> retreiveAllComplianceRecord() {
        return iComplianceRecordService.retreiveAllComplianceRecord();
    }

    @GetMapping("/retreiveComplianceRecord/{id}")
    ComplianceRecord retreiveComplianceRecord(@PathVariable long id) {
        return iComplianceRecordService.retreiveComplianceRecord(id);
    }

    @DeleteMapping("/deleteComplianceRecord/{id}")
    void deleteComplianceRecord(@PathVariable long id){
        iComplianceRecordService.deleteComplianceRecord(id);
    }
}
