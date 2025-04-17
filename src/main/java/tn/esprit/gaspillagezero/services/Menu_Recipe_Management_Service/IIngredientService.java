package tn.esprit.gaspillagezero.services.Menu_Recipe_Management_Service;



import tn.esprit.gaspillagezero.entites.Menu_Recipe_Management.Ingredient;

import java.util.List;

public interface IIngredientService {
    Ingredient addIngredient(Ingredient ingredient);
    Ingredient updateIngredient(Ingredient ingredient,Long id);
    void deleteIngredient(long idI);
    List<Ingredient> retreiveAllIngredients();
    Ingredient retreiveIngredient(long idI);
    List<Ingredient> searchIngByName(String nom);
    List<Ingredient> getIngredientsExpiringSoon();
}
