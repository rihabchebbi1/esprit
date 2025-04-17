package tn.esprit.gaspillagezero.services.Marketplace_Service;

import tn.esprit.gaspillagezero.entites.Marketplace.Dish;
import tn.esprit.gaspillagezero.entites.Marketplace.Promotions;

import java.util.List;

public interface IPromotionsService {
    Promotions addPromotion(Promotions promotions);
    Promotions updatePromotion(Promotions promotions);
    void deletePromotion(long idPromotion);
    List<Promotions > retieveAllPromotion();
    Promotions retievePromotion(long idPromotion);

}
