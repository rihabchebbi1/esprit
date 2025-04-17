package tn.esprit.gaspillagezero.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.gaspillagezero.entites.Restaurant;
import tn.esprit.gaspillagezero.entites.Supplier_Order_Management.Supplier;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {

}
