package tn.esprit.gaspillagezero.services;

import tn.esprit.gaspillagezero.entites.Restaurant;

import java.util.List;

public interface IRestaurantService {
    Restaurant addRestaurant(Restaurant object);

    Restaurant updateRestaurant(Restaurant object);

    void deleteRestaurant(Integer id);

    List<Restaurant> retreiveAllRestaurant();

    Restaurant retreiveRestaurant(Integer id);

}
