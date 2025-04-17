package tn.esprit.gaspillagezero.services.Menu_Recipe_Management_Service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.gaspillagezero.entites.Menu_Recipe_Management.Ingredient;
import tn.esprit.gaspillagezero.entites.Menu_Recipe_Management.Meal;
import tn.esprit.gaspillagezero.repository.Menu_Recipe_Management_Repository.IngredientRepository;
import tn.esprit.gaspillagezero.repository.Menu_Recipe_Management_Repository.MealRepository;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class IngredientServiceImplementaion implements IIngredientService{
    @Autowired
    IngredientRepository ingredientRepository;
    @Autowired
    MealRepository mealRepository;
    @Override
    public Ingredient addIngredient(Ingredient ingredient) {
        return ingredientRepository.save(ingredient);
    }

    @Override
    public Ingredient updateIngredient(Ingredient ingredient,Long id) {
        if (!ingredientRepository.existsById(id)) {
            return null;
        }

        ingredient.setIngId(id);
        return ingredientRepository.save(ingredient);
    }

    @Override
    public void deleteIngredient(long idI) {
        Ingredient ingredient = ingredientRepository.findById(idI)
                .orElseThrow(() -> new RuntimeException("Ingredient not found"));

        // Dissocier l'ingredient de tous les meals associés
        for (Meal meal : ingredient.getMeals()) {
            meal.getIngredients().remove(ingredient);  // Supprimer l'association de l'ingredient avec le meal
        }

        // Sauvegarder les meals après modification
        for (Meal meal : ingredient.getMeals()) {
            mealRepository.save(meal);  // Sauvegarder les meals modifiés
        }

        // Supprimer l'ingredient
        ingredientRepository.deleteById(idI);
    }


    @Override
    public List<Ingredient> retreiveAllIngredients() {
        return ingredientRepository.findAll();
    }

    @Override
    public Ingredient retreiveIngredient(long idI) {
        return ingredientRepository.findById(idI).get();
    }
    public List<Ingredient> searchIngByName(String name) {
        return ingredientRepository.findByNameContainingIgnoreCase(name);
    }
    @Transactional
    public List<Ingredient> getIngredientsExpiringSoon() {
        Date currentDate = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(currentDate);
        cal.add(Calendar.DAY_OF_YEAR, 7);
        Date expirationThreshold = cal.getTime();

        return ingredientRepository.findByExpirationDateBefore(expirationThreshold);
    }
}
