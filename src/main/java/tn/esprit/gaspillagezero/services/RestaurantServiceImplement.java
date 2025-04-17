package tn.esprit.gaspillagezero.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.gaspillagezero.entites.Compliance_Food_Safety_Management.ComplianceRecord;
import tn.esprit.gaspillagezero.entites.Restaurant;
import tn.esprit.gaspillagezero.repository.Compliance_Food_Safety_Management_Repository.ComplianceRecordRepository;
import tn.esprit.gaspillagezero.repository.RestaurantRepository;

import java.util.List;

@Service
public class RestaurantServiceImplement implements IRestaurantService{

    @Autowired
    RestaurantRepository restaurantRepository;
    @Override
    public Restaurant addRestaurant(Restaurant object) {
        return restaurantRepository.save(object);
    }

    @Override
    public Restaurant updateRestaurant(Restaurant object) {
        return restaurantRepository.save(object);
    }

    @Override
    public void deleteRestaurant(Integer id) {
        restaurantRepository.deleteById(id);
    }

    @Override
    public List<Restaurant> retreiveAllRestaurant() {
        return restaurantRepository.findAll();
    }

    @Override
    public Restaurant retreiveRestaurant(Integer id) {
        return restaurantRepository.findById(id).get();
    }
}
