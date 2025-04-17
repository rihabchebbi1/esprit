package tn.esprit.gaspillagezero.services.Compliance_Food_Safety_Management_Service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.gaspillagezero.entites.Compliance_Food_Safety_Management.SafetyInspection;
import tn.esprit.gaspillagezero.entites.Restaurant;
import tn.esprit.gaspillagezero.repository.Compliance_Food_Safety_Management_Repository.SafetyInspectionRepository;
import tn.esprit.gaspillagezero.repository.RestaurantRepository;

import java.util.List;
@Service

public class SafetyInspectionServiceImplement implements ISafetyInspectionService{

    @Autowired
    SafetyInspectionRepository safetyInspectionRepository;
    @Autowired
    RestaurantRepository restaurantRepository;
    @Override
    public SafetyInspection addSafetyInspection(SafetyInspection object) {
        if (object.getRestaurant() != null && object.getRestaurant().getRestaurantid() != null) {
            Restaurant existingRestaurant = restaurantRepository.findById(object.getRestaurant().getRestaurantid())
                    .orElseThrow(() -> new RuntimeException("Restaurant not found"));
            object.setRestaurant(existingRestaurant);
        } else {
            throw new RuntimeException("Restaurant must be provided with a valid ID.");
        }

        return safetyInspectionRepository.save(object);
    }

    @Override
    public SafetyInspection updateSafetyInspection(SafetyInspection updated) {
        SafetyInspection existing = safetyInspectionRepository.findById(updated.getInspectionID())
                .orElseThrow(() -> new EntityNotFoundException("Inspection not found"));

        // Update fields manually
        existing.setInspector_name(updated.getInspector_name());
        existing.setInspection_date(updated.getInspection_date());
        existing.setPremises_name(updated.getPremises_name());
        existing.setAddress(updated.getAddress());
        existing.setInspectionType(updated.getInspectionType());
        existing.setInspectionStatus(updated.getInspectionStatus());
        existing.setInfractions(updated.getInfractions());
        existing.setCrucial_infractions(updated.getCrucial_infractions());
        existing.setSignificant_infractions(updated.getSignificant_infractions());
        existing.setMinor_infractions(updated.getMinor_infractions());
        existing.setCorrected_during_inspection(updated.getCorrected_during_inspection());
        existing.setReport_time(updated.getReport_time());
        existing.setDescription(updated.getDescription());
        existing.setReinspectionDate(updated.getReinspectionDate());
        existing.setOutOfBusiness(updated.getOutOfBusiness());

        // Handle restaurant separately to avoid creating duplicates
        if (updated.getRestaurant() != null && updated.getRestaurant().getRestaurantid() != null) {
            Restaurant restaurant = restaurantRepository.findById(updated.getRestaurant().getRestaurantid())
                    .orElseThrow(() -> new EntityNotFoundException("Restaurant not found"));
            existing.setRestaurant(restaurant);
        }

        return safetyInspectionRepository.save(existing);
    }

    @Override
    public void deleteSafetyInspection(long id) {
        safetyInspectionRepository.deleteById(id);

    }

    @Override
    public List<SafetyInspection> retreiveAllSafetyInspection() {
        return safetyInspectionRepository.findAll();
    }

    @Override
    public SafetyInspection retreiveSafetyInspection(long id) {
        return safetyInspectionRepository.findById(id).get();
    }
}
