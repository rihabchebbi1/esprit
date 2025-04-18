package tn.esprit.gaspillagezero.controllers.Menu_Recipe_Managementcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.gaspillagezero.entites.Menu_Recipe_Management.Ingredient;
import tn.esprit.gaspillagezero.services.Menu_Recipe_Management_Service.IIngredientService;

import java.util.List;
@CrossOrigin(origins = "http://localhost:4200")// Permet à Angular d'accéder
@RestController
@RequestMapping("/Ingredients")
public class IngredientController {
    @Autowired
    IIngredientService ingredientService;
    // http://localhost:8089/menurecipemanagement/Ingredients/addIngredient
    @PostMapping("/addIngredient")
    public Ingredient addIngredient(@RequestBody Ingredient ingredient ) {
        return ingredientService.addIngredient(ingredient);
    }
    // http://localhost:8089/menurecipemanagement/Ingredients/updateIngredient
    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping("/updateIngredient/{id}")
    public ResponseEntity<Ingredient> updateIngredient(@RequestBody Ingredient ingredient, @PathVariable("id") Long id) {
        Ingredient updatedIngredient = ingredientService.updateIngredient(ingredient,id);
        if (updatedIngredient == null) {
            return ResponseEntity.notFound().build(); // Si l'ingrédient n'a pas été trouvé
        }
        return ResponseEntity.ok(updatedIngredient);
    }
    // http://localhost:8089/menurecipemanagement/Ingredients/retreiveAllIngredients
    @GetMapping("/retreiveAllIngredients")
    public List<Ingredient> retreiveAllIngredients() {
        return ingredientService.retreiveAllIngredients();
    }
    // http://localhost:8089/menurecipemanagement/Ingredients/retriveIngredient/{idI}
    @GetMapping("/retriveIngredient/{idI}")
    public Ingredient retriveIngredient(@PathVariable long idI) {
        return ingredientService.retreiveIngredient(idI);
    }
    // http://localhost:8089/menurecipemanagement/Ingredients/deleteIngredient/{idM}"
    @DeleteMapping("/deleteIngredient/{idI}")
    void deleteIngredient(@PathVariable long idI) {
        ingredientService.deleteIngredient(idI);
    }
    @GetMapping("/search")
    public List<Ingredient> searchIngByName(@RequestParam String name) {
        return ingredientService.searchIngByName(name);
    }
    @GetMapping("/expiring-soon")
    public ResponseEntity<List<Ingredient>> getIngredientsExpiringSoon() {
        List<Ingredient> ingredients = ingredientService.getIngredientsExpiringSoon();
        return ResponseEntity.ok(ingredients);
    }

}

