package tn.esprit.gaspillagezero.services.Compliance_Food_Safety_Management_Service;

import tn.esprit.gaspillagezero.entites.Compliance_Food_Safety_Management.SafetyInspection;

import java.util.List;

public interface ISafetyInspectionService {

    SafetyInspection addSafetyInspection (SafetyInspection  object);
    SafetyInspection  updateSafetyInspection (SafetyInspection  object);
    void deleteSafetyInspection (long id);
    List<SafetyInspection > retreiveAllSafetyInspection();
    SafetyInspection  retreiveSafetyInspection (long id);

}
