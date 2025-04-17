package tn.esprit.gaspillagezero.repository.Menu_Recipe_Management_Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.gaspillagezero.entites.Menu_Recipe_Management.Ingredient;

import java.util.Date;
import java.util.List;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient,Long> {
    List<Ingredient> findByNameContainingIgnoreCase(String name);
    List<Ingredient> findByExpirationDateBefore(Date expirationDate);
    List<Ingredient> findAllByIngIdIn(List<Long> ingIds);


}
