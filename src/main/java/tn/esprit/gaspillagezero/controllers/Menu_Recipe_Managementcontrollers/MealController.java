package tn.esprit.gaspillagezero.controllers.Menu_Recipe_Managementcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.gaspillagezero.entites.Menu_Recipe_Management.Ingredient;
import tn.esprit.gaspillagezero.entites.Menu_Recipe_Management.Meal;
import tn.esprit.gaspillagezero.repository.Menu_Recipe_Management_Repository.IngredientRepository;
import tn.esprit.gaspillagezero.repository.Menu_Recipe_Management_Repository.MealRepository;
import tn.esprit.gaspillagezero.services.Menu_Recipe_Management_Service.IMealService;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.*;

@CrossOrigin(origins = "http://localhost:4201") // Permet à Angular d'accéder
@RestController
@RequestMapping("/Meal")
public class MealController {
    @Autowired
    private IngredientRepository ingredientRepository;
    @Autowired
    IMealService mealService;
    @Autowired
    private MealRepository mealRepository;
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/addMeal")
    public ResponseEntity<String> addMeal(
            @RequestPart("meal") Meal mealRequest,
            @RequestPart(value = "imageFile", required = false) MultipartFile file,
            @RequestParam(value = "ingredientIds") Long[] ingredientIds) {
        try {

            if (ingredientIds == null || ingredientIds.length == 0) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Aucun ID d'ingrédient fourni.");
            }


            List<Long> ingredientIdList = Arrays.asList(ingredientIds);


            List<Ingredient> ingredients = ingredientRepository.findAllByIngIdIn(ingredientIdList);

            if (ingredients.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Aucun ingrédient trouvé avec les IDs fournis.");
            }

            Meal meal = new Meal();
            meal.setName(mealRequest.getName());
            meal.setDescription(mealRequest.getDescription());
            meal.setCategory(mealRequest.getCategory());
            meal.setPrice(mealRequest.getPrice());

            String imagePath = null;
            if (file != null && !file.isEmpty()) {
                String baseDir = System.getProperty("user.dir");
                Path uploadDir = Paths.get(baseDir,"uploads");
                Files.createDirectories(uploadDir);

                String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
                Path filePath = uploadDir.resolve(fileName);
                Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

                imagePath = "/uploads/" + fileName;
            }

            meal.setImagePath(imagePath);
            meal.setIngredients(new HashSet<>(ingredients));

            mealService.addMeal(meal);
            return ResponseEntity.ok().body("{\"message\": \"Meal added successfully!\"}");

        } catch (IOException | NumberFormatException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing request.");
        }
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping(value = "/updateMeal/{id}", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<Meal> updateMeal(
            @RequestPart("meal") Meal meal,
            @RequestParam(value = "file", required = false) MultipartFile file,
            @PathVariable("id") Long id) {

        try {
            Meal updateMeal = mealService.updateMeal(meal, id, file);
            if (updateMeal == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(updateMeal);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @GetMapping("/retreiveAllMeals")
    public List<Meal> retreiveAllMeals() {
        return mealService.retreiveAllMeals(); }


    // Récupérer l'image du repas
    @GetMapping("/retrieveImage/{imageName}")
    public ResponseEntity<Resource> getImage(@PathVariable String imageName) {
        // File path on the server
        String filePath = "C:/Users/USER/MenuRecipe_Mangement/meals/uploads/" + imageName;
        File file = new File(filePath);

        if (!file.exists()) {
            return ResponseEntity.notFound().build();  // Handle missing files
        }

        // Return the file as a resource
        Resource resource = new FileSystemResource(file);
        return ResponseEntity.ok().body(resource);
    }


    // http://localhost:8089/menurecipemanagement/Meal/retriveMeal/{idM}
    @GetMapping("/retriveMeal/{idM}")
    public Meal retriveMeal(@PathVariable long idM) {
        return mealService.retreiveMeal(idM);
    }
    // http://localhost:8089/menurecipemanagement/Meal/deleteMeal/{idM}
    @DeleteMapping("/deleteMeal/{idM}")
    void deleteMeal(@PathVariable long idM) {
        mealService.deleteMeal(idM);
    }


    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/recommended")
    public ResponseEntity<List<Meal>> getRecommendedMeals() {
        List<Meal> meals = mealService.getRecommendedMeals();
        return ResponseEntity.ok(meals);
    }

    @GetMapping("/{mealId}/ingredients")
    public Set<Ingredient> getIngredientsForMeal(@PathVariable Long mealId) {
        return mealService.getIngredientsForMeal(mealId);
    }
    @GetMapping("/{mealId}")
    public ResponseEntity<?> getMealWithIngredients(@PathVariable Long mealId) {
        Meal meal = mealRepository.findById(mealId).orElse(null);

        if (meal == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Meal not found");
        }

        // Créer une réponse contenant le meal et ses ingrédients
        Map<String, Object> response = new HashMap<>();
        response.put("id", meal.getMealId());

        response.put("ingredients", meal.getIngredients()); // Liste des ingrédients associés

        return ResponseEntity.ok(response);
    }
    @PutMapping("/{mealId}/updateingredients")
    public Meal updateIngredients(@PathVariable Long mealId, @RequestBody Set<Long> ingredientIds) {
        return mealService.updateIngredients(mealId, ingredientIds);
    }
    @GetMapping("/searchM")
    public List<Meal> searchMealByName(@RequestParam String name) {
        return mealService.searchMealByName(name);
    }
    @GetMapping("/with-discounts")
    public List<Meal> getMealsWithDiscounts() {
        return mealService.applyDiscountsForExpiringIngredients();
    }
    @GetMapping("/top")
    public ResponseEntity<List<Meal>> getTopMeals() {
        return ResponseEntity.ok(mealService.getTopMeals());
    }
    @PutMapping("/{id}/rating")
    public ResponseEntity<?> updateMealRating(@PathVariable Long id, @RequestBody Map<String, Integer> body) {
        int rating = body.get("rating");
        Optional<Meal> optionalMeal = mealRepository.findById(id);
        if (!optionalMeal.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        Meal meal = optionalMeal.get();
        meal.setRating(rating);
        mealRepository.save(meal);
        return ResponseEntity.ok().build();
    }



}

