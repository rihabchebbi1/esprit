package tn.esprit.gaspillagezero.repository.Menu_Recipe_Management_Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.esprit.gaspillagezero.entites.Menu_Recipe_Management.Ingredient;
import tn.esprit.gaspillagezero.entites.Menu_Recipe_Management.Meal;

import java.util.List;
import java.util.Optional;

@Repository
public interface MealRepository extends JpaRepository<Meal,Long> {
    List<Meal> findByIngredientsIn(List<Ingredient> ingredients);
    @Query("SELECT m FROM Meal m JOIN m.ingredients i WHERE i.stock > 0 GROUP BY m HAVING COUNT(i) = SIZE(m.ingredients)")
    List<Meal> findMealsByAvailableIngredients();
    List<Meal> findByNameContainingIgnoreCase(String name);
    @Query("SELECT DISTINCT m FROM Meal m JOIN m.ingredients i WHERE i IN :ingredients")
    List<Meal> findMealsByIngredients(@Param("ingredients") List<Ingredient> ingredients);
    @Query("SELECT m FROM Meal m LEFT JOIN FETCH m.ingredients WHERE m.mealId = :id")
    Optional<Meal> findMealWithIngredients(@Param("id") Long mealId);
    List<Meal> findTop5ByOrderByOrderCountDesc();

}