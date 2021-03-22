package com.profnaya.recipe.bootstrap;

import com.profnaya.recipe.domain.*;
import com.profnaya.recipe.repository.CategoryRepository;
import com.profnaya.recipe.repository.RecipeRepository;
import com.profnaya.recipe.repository.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class DataLoader implements CommandLineRunner {
    private final UnitOfMeasureRepository unitOfMeasureRepository;
    private final CategoryRepository categoryRepository;
    private final RecipeRepository recipeRepository;

    public DataLoader(UnitOfMeasureRepository unitOfMeasureRepository, CategoryRepository categoryRepository, RecipeRepository recipeRepository) {
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.categoryRepository = categoryRepository;
        this.recipeRepository = recipeRepository;
    }


    @Override
    @Transactional
    public void run(String... args) throws Exception {
        loadData();
    }

    private void loadData() {
        List<Recipe> recipes = new ArrayList<>();
        UnitOfMeasure eachUom = unitOfMeasureRepository.findByDescription("Each").get();
        UnitOfMeasure teaSpoon = unitOfMeasureRepository.findByDescription("Teaspoon").get();
        UnitOfMeasure tableSpoon = unitOfMeasureRepository.findByDescription("Tablespoon").get();

        Category mexicanCategory = categoryRepository.findByDescription("Mexican").get();
        Category italianCategory = categoryRepository.findByDescription("Italian").get();
        Category americanCategory = categoryRepository.findByDescription("American").get();
        Category mongolianCategory = categoryRepository.findByDescription("Mongolian").get();


        Recipe guacRecipe = new Recipe();
        guacRecipe.setDescription("Perfect Guacamole");
        guacRecipe.setPrepTime(10);
        guacRecipe.setCookTime(0);
        guacRecipe.setServings(4);
        guacRecipe.setUrl("https://www.simplyrecipes.com/recipes/perfect_guacamole/");
        guacRecipe.setDirections("Cut the avocado, remove flesh: Mash with a fork: Add salt, lime juice, and the rest: Serve:");
        guacRecipe.setDifficulty(Difficulty.EASY);

        Notes guacNotes = new Notes();
        guacNotes.setRecipeNotes("Be careful handling chiles if using. Wash your hands thoroughly after handling and do not touch your eyes or the area near your eyes with your hands for several hours.");
        guacRecipe.setNotes(guacNotes);

        guacRecipe.addIngredient(new Ingredient("salt", new BigDecimal(0.25), teaSpoon));
        guacRecipe.addIngredient(new Ingredient("lemon juice", new BigDecimal(1), tableSpoon));
        guacRecipe.addIngredient(new Ingredient("Avocado", new BigDecimal(3), eachUom));

        guacRecipe.getCategories().add(mexicanCategory);

        recipes.add(guacRecipe);

        Recipe tacosRecipe = new Recipe();
        tacosRecipe.setDescription("Spicy Grilled Chicken Tacos");
        tacosRecipe.setPrepTime(20);
        tacosRecipe.setCookTime(15);
        tacosRecipe.setServings(6);
        tacosRecipe.setUrl("https://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/");
        tacosRecipe.setDirections("Prepare a gas or charcoal grill for medium-high, direct heat: Make the marinade and coat the chicken: Grill the chicken: Warm the tortillas: Assemble the tacos:");
        tacosRecipe.setDifficulty(Difficulty.MODERATE);

        Notes tacosNotes = new Notes();
        tacosNotes.setRecipeNotes("Look for ancho chile powder with the Mexican ingredients at your grocery store, on buy it online. (If you can't find ancho chili powder, you replace the ancho chili, the oregano, and the cumin with 2 1/2 tablespoons regular chili powder, though the flavor won't be quite the same.)");
        tacosRecipe.setNotes(tacosNotes);

        tacosRecipe.addIngredient(new Ingredient("salt", new BigDecimal(0.5), teaSpoon));
        tacosRecipe.addIngredient(new Ingredient("orange zest", new BigDecimal(1), tableSpoon));

        tacosRecipe.getCategories().add(americanCategory);
        tacosRecipe.getCategories().add(mexicanCategory);

        recipes.add(tacosRecipe);

        recipeRepository.saveAll(recipes);
        log.debug("Loading Bootstrap Data");

    }
}
