package tn.esprit.gaspillagezero.services.Menu_Recipe_Management_Service;


import org.springframework.web.multipart.MultipartFile;
import tn.esprit.gaspillagezero.entites.Menu_Recipe_Management.Ingredient;
import tn.esprit.gaspillagezero.entites.Menu_Recipe_Management.Meal;

import java.util.List;
import java.util.Set;

public interface IMealService {
    Meal addMeal(Meal meal);
    //Meal updateMeal(Meal meal);
    Meal updateMeal(Meal meal, Long id, MultipartFile file);
    //public Meal updateMeal(Meal meal, Long id, MultipartFile file, List<Long> ingredientIds);
    void deleteMeal(long idM);
    List<Meal> retreiveAllMeals();
    Meal retreiveMeal(long idM);
    //public  Meal addMealandAffectIngredients(Meal meal,long idI);
    //List<Meal> recommanderRepasSelonStock(List<Long> ingredientIds);
    List<Meal> recommendMeals();
    List<Meal> searchMealByName(String nom);
    public List<Meal> getRecommendedMeals();
    //public Meal getMealWithIngredients(Long id) ;
    public Meal getMealWithIngredients(Long id);
    // public List<Ingredient> getIngredientsForMeal(Long mealId);
    public Set<Ingredient> getIngredientsForMeal(Long mealId);
    public Meal updateIngredients(Long mealId, Set<Long> ingredientIds);
    public List<Meal> applyDiscountsForExpiringIngredients();
    public List<Meal> getTopMeals();

}
