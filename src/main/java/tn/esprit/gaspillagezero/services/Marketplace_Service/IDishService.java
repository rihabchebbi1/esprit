package tn.esprit.gaspillagezero.services.Marketplace_Service;

import tn.esprit.gaspillagezero.entites.Marketplace.Dish;
import tn.esprit.gaspillagezero.entites.Marketplace.Promotions;

import java.util.List;

public interface IDishService {
    Dish addDish(Dish dish);
    Dish updateDish(Dish dish);
    void deleteDish(int dishId);
    List<Dish > retieveAlldish();
    Dish retieveDish(int dishId);

}
