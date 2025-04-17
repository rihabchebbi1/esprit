package tn.esprit.gaspillagezero.services.Marketplace_Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.gaspillagezero.entites.Marketplace.Dish;
import tn.esprit.gaspillagezero.entites.Marketplace.Promotions;
import tn.esprit.gaspillagezero.repository.Marketplace_Repository.PromotionsRepository;

import java.util.List;
@Service
public class PromotionsServiceImplement implements IPromotionsService{
    @Autowired
    PromotionsRepository promotionsRepository;
    @Override
    public Promotions addPromotion(Promotions promotions) {
        return promotionsRepository.save(promotions);
    }
    @Override
    public Promotions updatePromotion(Promotions promotions) {
        return promotionsRepository.save(promotions);
    }
    @Override
    public void deletePromotion(long idPromotion) {
        promotionsRepository.deleteById(idPromotion);
    }
    @Override
    public List<Promotions> retieveAllPromotion() {
        return promotionsRepository.findAll();
    }
    @Override
    public Promotions retievePromotion(long idPromotion) {
        return promotionsRepository.findById(idPromotion).get();
    }
}
