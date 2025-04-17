package tn.esprit.gaspillagezero.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.gaspillagezero.entites.Restaurant;
import tn.esprit.gaspillagezero.services.IRestaurantService;

import java.util.List;
@RestController
@RequestMapping("/Restaurant")
public class RestaurantController {

    @Autowired
    IRestaurantService iRestaurantService;

    @PostMapping("/addRestaurant")
    Restaurant addRestaurant(@RequestBody Restaurant obj) {
        return iRestaurantService.addRestaurant(obj);
    }

    @PutMapping("/updateRestaurant")
    Restaurant updateRestaurant(@RequestBody Restaurant obj) {
        return iRestaurantService.updateRestaurant(obj);
    }

    @GetMapping("/retreiveAllRestaurant")
    List<Restaurant> retreiveAllRestaurant() {
        return iRestaurantService.retreiveAllRestaurant();
    }

    @GetMapping("/retreiveRestaurant/{id}")
    Restaurant retreiveRestaurant(@PathVariable Integer id) {
        return iRestaurantService.retreiveRestaurant(id);
    }

    @DeleteMapping("/deleteRestaurant/{id}")
    void deleteRestaurant(@PathVariable Integer id){
        iRestaurantService.deleteRestaurant(id);
    }
}

