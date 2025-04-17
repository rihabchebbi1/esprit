package tn.esprit.gaspillagezero.controllers.Marketplacecontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.gaspillagezero.entites.Marketplace.Promotions;
import tn.esprit.gaspillagezero.services.Marketplace_Service.IPromotionsService;

import java.util.List;

@RestController
@RequestMapping("/promotions")
public class PromotionsController {

    @Autowired
    IPromotionsService promotionsService;


    @PostMapping("/addpromotion")
    Promotions addPromotion (@RequestBody Promotions promotions ){
        return promotionsService.addPromotion(promotions);
    }
    @PutMapping("/updatepromotion")
    Promotions updatePromotion(@RequestBody Promotions promotions)
    {
        return promotionsService.updatePromotion(promotions) ;
    }
    @GetMapping("/retiveAllpromotions")
    List<Promotions> retieveAllPromotion(){
        return promotionsService.retieveAllPromotion();
    }
    @GetMapping("/retievePromtion/{idPromotion}")
    Promotions retievePromotion(@PathVariable long idPromotion)
    {return promotionsService.retievePromotion(idPromotion);}

    @DeleteMapping("/deletepromotion/{idPromotion}")
    void deletBloc(@PathVariable Long idPromotion){
        promotionsService.deletePromotion(idPromotion);
    }

}
