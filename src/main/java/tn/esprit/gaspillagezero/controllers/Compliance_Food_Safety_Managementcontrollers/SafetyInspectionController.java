package tn.esprit.gaspillagezero.controllers.Compliance_Food_Safety_Managementcontrollers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.gaspillagezero.entites.Compliance_Food_Safety_Management.SafetyInspection;
import tn.esprit.gaspillagezero.services.Compliance_Food_Safety_Management_Service.ISafetyInspectionService;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4201")
@RestController
@RequestMapping("/SafetyInspection")
public class SafetyInspectionController {

    @Autowired
    ISafetyInspectionService iSafetyInspectionService;

    @PostMapping("/addSafetyInspection")
    SafetyInspection addESafetyInspection(@RequestBody SafetyInspection obj) {
        return iSafetyInspectionService.addSafetyInspection(obj);
    }

    @PutMapping("/updateSafetyInspection")
    SafetyInspection updateSafetyInspection(@RequestBody SafetyInspection obj) {
        return iSafetyInspectionService.updateSafetyInspection(obj);
    }

    @GetMapping("/retreiveAllSafetyInspection")
    List<SafetyInspection> retreiveAllSafetyInspection() {
        return iSafetyInspectionService.retreiveAllSafetyInspection();
    }

    @GetMapping("/retreiveSafetyInspection/{id}")
    SafetyInspection retreiveSafetyInspection(@PathVariable long id) {
        return iSafetyInspectionService.retreiveSafetyInspection(id);
    }

    @DeleteMapping("/deleteSafetyInspection/{id}")
    void deleteSafetyInspection(@PathVariable long id){
        iSafetyInspectionService.deleteSafetyInspection(id);
    }



}
