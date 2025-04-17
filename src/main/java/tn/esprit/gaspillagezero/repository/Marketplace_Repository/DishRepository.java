package tn.esprit.gaspillagezero.repository.Marketplace_Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.gaspillagezero.entites.Marketplace.Dish;

public interface DishRepository  extends JpaRepository<Dish, Integer>{
}
