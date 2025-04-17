package tn.esprit.gaspillagezero.services.Supplier_Order_Management_Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.gaspillagezero.entites.Menu_Recipe_Management.Ingredient;
import tn.esprit.gaspillagezero.entites.Supplier_Order_Management.IngredientDTO;
import tn.esprit.gaspillagezero.repository.Menu_Recipe_Management_Repository.IngredientRepository;

@Service
public class IngredientStockChecker {

    @Autowired
    private IngredientRepository ingredientRepository;  // Le repository pour la table Ingredient

    public int checkStock(Long ingredientId) {
        // Recherche de l'ingrédient en fonction de l'ID
        Ingredient ingredient = ingredientRepository.findById(ingredientId).orElse(null);

        if (ingredient != null) {
            return (int) ingredient.getQuantity();  // Retourne la quantité en stock
        } else {
            throw new RuntimeException("Ingrédient non trouvé");
        }
    }

    // Méthode pour créer une commande si le stock est insuffisant
    public void checkAndCreateOrder(Long ingredientId) {
        int stock = checkStock(ingredientId);

        if (stock <= 0) {
            // Si le stock est insuffisant, créer une commande pour le réapprovisionnement
            // Tu peux implémenter la logique de commande ici
            System.out.println("Stock insuffisant pour l'ingrédient ID: " + ingredientId);
        } else {
            System.out.println("Stock suffisant pour l'ingrédient ID: " + ingredientId);
        }
    }}