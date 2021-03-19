package com.profnaya.recipe.converter;

import com.profnaya.recipe.command.CategoryCommand;
import com.profnaya.recipe.command.IngredientCommand;
import com.profnaya.recipe.command.NotesCommand;
import com.profnaya.recipe.command.RecipeCommand;
import com.profnaya.recipe.domain.Difficulty;
import com.profnaya.recipe.domain.Recipe;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RecipeCommandToRecipeTest {

    public static final Long RECIPE_ID = 1L ;
    public static final String RECIPE_DESCRIPTION = "recipe description";
    public static final Integer PREP_TIME = 30;
    public static final Integer COOK_TIME = 30;
    public static final Integer SERVINGS = 4;
    public static final String SOURCE = "source";
    public static final String URL = "url";
    public static final String DIRECTIONS = "directions";
    public static final Difficulty DIFFICULTY = Difficulty.EASY;

    public static final Long NOTES_ID = 2L ;

    public static final Long CAT_ID_1 = 3L;
    public static final Long CAT_ID_2 = 4L;

    public static final Long INGRED_ID_1 = 5L;
    public static final Long INGRED_ID_2 = 6L ;

    RecipeCommandToRecipe converter;

    @Before
    public void setUp() throws Exception {
        converter = new RecipeCommandToRecipe(new CategoryCommandToCategory(),
                new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure()),
                new NotesCommandToNotes());
    }

    @Test
    public void testNullObject() throws Exception {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() throws Exception {
        assertNotNull(converter.convert(new RecipeCommand()));
    }


    @Test
    public void convert() throws Exception {
        //given
        RecipeCommand command = new RecipeCommand();
        command.setId(RECIPE_ID);
        command.setDescription(RECIPE_DESCRIPTION);
        command.setPrepTime(PREP_TIME);
        command.setCookTime(COOK_TIME);
        command.setServings(SERVINGS);
        command.setSource(SOURCE);
        command.setUrl(URL);
        command.setDirections(DIRECTIONS);
        command.setDifficulty(DIFFICULTY);

        NotesCommand notes = new NotesCommand();
        notes.setId(NOTES_ID);
        command.setNotes(notes);

        CategoryCommand category = new CategoryCommand();
        category.setId(CAT_ID_1);

        CategoryCommand category2 = new CategoryCommand();
        category.setId(CAT_ID_2);

        command.getCategories().add(category);
        command.getCategories().add(category2);

        IngredientCommand ingredient = new IngredientCommand();
        ingredient.setId(INGRED_ID_1);

        IngredientCommand ingredient2 = new IngredientCommand();
        ingredient.setId(INGRED_ID_2);

        command.getIngredients().add(ingredient);
        command.getIngredients().add(ingredient2);


        //when
        Recipe recipe = converter.convert(command);
        
        //then
        assertNotNull(recipe);
        assertEquals(RECIPE_ID, recipe.getId());
        assertEquals(RECIPE_DESCRIPTION, recipe.getDescription());
        assertEquals(PREP_TIME, recipe.getPrepTime());
        assertEquals(COOK_TIME, recipe.getCookTime());
        assertEquals(SERVINGS, recipe.getServings());
        assertEquals(SOURCE, recipe.getSource());
        assertEquals(URL, recipe.getUrl());
        assertEquals(DIRECTIONS, recipe.getDirections());
        assertEquals(DIFFICULTY, recipe.getDifficulty());
        assertEquals(NOTES_ID, recipe.getNotes().getId());
        assertEquals(2, recipe.getCategories().size());
        assertEquals(2, recipe.getIngredients().size());

    }
}