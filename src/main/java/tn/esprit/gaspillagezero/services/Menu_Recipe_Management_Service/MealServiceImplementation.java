package tn.esprit.gaspillagezero.services.Menu_Recipe_Management_Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.gaspillagezero.entites.Menu_Recipe_Management.Ingredient;
import tn.esprit.gaspillagezero.entites.Menu_Recipe_Management.Meal;
import tn.esprit.gaspillagezero.repository.Menu_Recipe_Management_Repository.IngredientRepository;
import tn.esprit.gaspillagezero.repository.Menu_Recipe_Management_Repository.MealRepository;
import tn.esprit.gaspillagezero.entites.EventManagement.Menus;
import tn.esprit.gaspillagezero.repository.EventManagement_Repository.MenuRepository;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

@Service
public class MealServiceImplementation implements IMealService {
    @Autowired
    MealRepository mealRepository;
    @Autowired
    MenuRepository menuRepository;
    @Autowired
    IngredientRepository ingredientRepository;

    @Override
    public Meal addMeal(Meal meal) {
        return mealRepository.save(meal);
    }

    @Override

    public Meal updateMeal(Meal meal, Long id, MultipartFile file) {
        Optional<Meal> existingMenuOpt = mealRepository.findById(id);
        if (existingMenuOpt.isPresent()) {
            Meal existingMeal = existingMenuOpt.get();


            existingMeal.setName(meal.getName());
            existingMeal.setDescription(meal.getDescription());
            existingMeal.setCategory(meal.getCategory());
            existingMeal.setPrice(meal.getPrice());

            // Gestion du fichier image
            if (file != null && !file.isEmpty()) {
                try {
                    String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
                    Path filePath = Paths.get("uploads/" + fileName);
                    Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
                    existingMeal.setImagePath("/uploads/" + fileName); // Stocker le chemin relatif
                } catch (IOException e) {
                    throw new RuntimeException("Échec du téléchargement de l'image", e);
                }
            }

            return mealRepository.save(existingMeal);
        }
        return null;
    }

    @Override
    public void deleteMeal(long idM) {
        Meal meal = mealRepository.findById(idM).orElseThrow(() -> new RuntimeException("Meal not found"));

        // Dissocier le meal de tous les menus associés
        /*for (Menus menu : meal.getMenus()) {
            menu.getMeals().remove(meal); // Supprimer l'association du meal avec le menu
        }

        // Sauvegarder les menus après modification
        for (Menu menu : meal.getMenus()) {
            menuRepository.save(menu); // Sauvegarder les menus modifiés
        }*/

        // Supprimer le meal
        mealRepository.deleteById(idM);
    }

    @Override
    public List<Meal> retreiveAllMeals() {
        List<Meal> meals = mealRepository.findAll();
        return meals;
    }
    public List<Meal> getTopMeals() {
        return mealRepository.findTop5ByOrderByOrderCountDesc();
    }

    @Override
    public Meal retreiveMeal(long idM) {
        return mealRepository.findById(idM).get();
    }

    public String saveImageToDisk(MultipartFile file) throws IOException {
        String uploadDir = "C:/Users/USER/MenuRecipe_Mangement/meals/uploads/";

        if (!uploadDir.endsWith(File.separator)) {
            uploadDir += File.separator;
        }

        String fileName = file.getOriginalFilename();
        String filePath = uploadDir + fileName;

        File dir = new File(uploadDir);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        File dest = new File(filePath);
        file.transferTo(dest);

        // Return the relative path (remove the absolute part)
        return "uploads/" + fileName;
    }

    public List<Meal> recommendMeals() {
        return mealRepository.findMealsByAvailableIngredients();
    }

    public List<Meal> searchMealByName(String name) {
        return mealRepository.findByNameContainingIgnoreCase(name);
    }
    public List<Meal> getRecommendedMeals() {
        // Get today's date as LocalDate
        LocalDate today = LocalDate.now();
        LocalDate thresholdDate = today.plusDays(3);

        // Convert LocalDate to java.util.Date
        Date todayDate = Date.from(today.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date thresholdDateConverted = Date.from(thresholdDate.atStartOfDay(ZoneId.systemDefault()).toInstant());


        List<Ingredient> expiringIngredients = ingredientRepository.findByExpirationDateBefore(thresholdDateConverted);


        return mealRepository.findMealsByIngredients(expiringIngredients);
    }

    @Override
    public Meal getMealWithIngredients(Long id) {
        return null;
    }

    public Set<Ingredient> getIngredientsForMeal(Long mealId) {
        Meal meal = mealRepository.findById(mealId)
                .orElseThrow(() -> new RuntimeException("Meal not found"));
        return meal.getIngredients();
    }
    @Override
    public Meal updateIngredients(Long mealId, Set<Long> ingredientIds) {
        Meal meal = mealRepository.findById(mealId).orElseThrow(() -> new RuntimeException("Meal not found"));


        Set<Ingredient> ingredients = new HashSet<>();
        for (Long ingredientId : ingredientIds) {
            Ingredient ingredient = ingredientRepository.findById(ingredientId)
                    .orElseThrow(() -> new RuntimeException("Ingredient not found"));
            ingredients.add(ingredient);
        }


        meal.setIngredients(ingredients);
        return mealRepository.save(meal);
    }
    @Override
    public List<Meal> applyDiscountsForExpiringIngredients() {
        List<Meal> meals = mealRepository.findAll();
        Date today = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(today);
        calendar.add(Calendar.DAY_OF_YEAR, 3);
        Date threeDaysFromNow = calendar.getTime();
        for (Meal meal : meals) {
            boolean hasExpiringIngredient = meal.getIngredients().stream()
                    .anyMatch(ing -> {
                        Date exp = ing.getExpirationDate();
                        return exp != null && !exp.before(today) && exp.before(threeDaysFromNow);
                    });

            if (hasExpiringIngredient) {
                double discount = meal.getPrice() * 0.2;
                meal.setDiscountedPrice(meal.getPrice() - discount);
            } else {
                meal.setDiscountedPrice(meal.getPrice());
            }

            mealRepository.save(meal);
        }

        return meals;
    }


}



