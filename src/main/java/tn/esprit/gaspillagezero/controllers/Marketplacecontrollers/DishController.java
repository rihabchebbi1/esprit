package tn.esprit.gaspillagezero.controllers.Marketplacecontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.gaspillagezero.entites.Marketplace.Dish;
import tn.esprit.gaspillagezero.entites.Marketplace.Promotions;
import tn.esprit.gaspillagezero.services.Marketplace_Service.IDishService;
import tn.esprit.gaspillagezero.services.Marketplace_Service.IPromotionsService;

import java.util.List;

@RestController
@RequestMapping("/dish")
public class DishController {

    @Autowired
    IDishService dishService;

    @PostMapping("/adddish")
    Dish addDish (@RequestBody Dish dish ){
        return dishService.addDish(dish);
    }
    @PutMapping("/updatedish")
    Dish updateDish(@RequestBody Dish dish)
    {
        return dishService.updateDish(dish) ;
    }
    @GetMapping("/retiveAlldish")
    List<Dish> retieveAlldish(){
        return dishService.retieveAlldish();
    }
    @GetMapping("/retievedish/{dishId}")
    Dish retieveDish(@PathVariable int dishId)
    {return dishService.retieveDish(dishId);}
    @DeleteMapping("/deletedish/{dishId}")
    void deleteDish(@PathVariable int dishId){
        dishService.deleteDish(dishId);
    }

}
