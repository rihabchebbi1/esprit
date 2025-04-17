package tn.esprit.gaspillagezero.services.Supplier_Order_Management_Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import tn.esprit.gaspillagezero.entites.Supplier_Order_Management.IngredientDTO;

@Service
public class IngredientClient {

    @Autowired
    private RestTemplate restTemplate;

    public IngredientDTO getIngredientById(Long ingId) {
        String url = "http://localhost:8089/gaspillagezero/Ingredients/retriveIngredient/" + ingId; // À adapter
        return restTemplate.getForObject(url, IngredientDTO.class);
    }
}