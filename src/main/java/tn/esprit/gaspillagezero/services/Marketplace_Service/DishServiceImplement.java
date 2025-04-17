package tn.esprit.gaspillagezero.services.Marketplace_Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.gaspillagezero.entites.Marketplace.Dish;
import tn.esprit.gaspillagezero.repository.Marketplace_Repository.DishRepository;
import tn.esprit.gaspillagezero.repository.Marketplace_Repository.PromotionsRepository;

import java.util.List;
@Service
public class DishServiceImplement implements IDishService{
    @Autowired
    DishRepository dishRepository;
    @Override
    public Dish addDish(Dish dish) {
        return dishRepository.save(dish);    }

    @Override
    public Dish updateDish(Dish dish) {
        return dishRepository.save(dish);    }

    @Override
    public void deleteDish(int dishId) {
        dishRepository.deleteById(dishId);

    }

    @Override
    public List<Dish> retieveAlldish() {
        return dishRepository.findAll();
    }

    @Override
    public Dish retieveDish(int dishId) {
        return dishRepository.findById(dishId).get()  ;  }
}
