package com.profnaya.recipe.controller;

import com.profnaya.recipe.command.IngredientCommand;
import com.profnaya.recipe.command.RecipeCommand;
import com.profnaya.recipe.service.IngredientService;
import com.profnaya.recipe.service.RecipeService;
import com.profnaya.recipe.service.UnitOfMeasureService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class IngredientControllerTest {

    @Mock
    RecipeService recipeService;

    @Mock
    IngredientService ingredientService;

    @Mock
    UnitOfMeasureService unitOfMeasureService;

    IngredientController controller;

    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
        controller = new IngredientController(recipeService, ingredientService, unitOfMeasureService);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void testListIngredients() throws Exception {
        //given
        RecipeCommand recipeCommand = new RecipeCommand();
        when(recipeService.findCommandById(anyLong())).thenReturn(recipeCommand);

        //when
        mockMvc.perform(get("/recipe/1/ingredients"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/ingredient/list"))
                .andExpect(model().attributeExists("recipe"));

        //then
        verify(recipeService, times(1)).findCommandById(anyLong());

    }

    @Test
    public void testShowIngredientByRecipeIdAndIngredientId() throws Exception {
        //given
        IngredientCommand ingredientCommand = new IngredientCommand();

        when(ingredientService.findCommandByRecipeIdAndIngredientId(anyLong(), anyLong())).thenReturn(ingredientCommand);

        //when
        mockMvc.perform(get("/recipe/1/ingredient/2/show"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/ingredient/show"))
                .andExpect(model().attributeExists("ingredient"));

        //then
        verify(ingredientService, times(1)).findCommandByRecipeIdAndIngredientId(anyLong(), anyLong());

    }

    @Test
    public void testUpdateIngredientByRecipeIdAndIngredientId() throws Exception {
        //given
        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setId(2L);
        RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(1L);
        ingredientCommand.setRecipeId(1L);

        when(ingredientService.findCommandByRecipeIdAndIngredientId(anyLong(), anyLong())).thenReturn(ingredientCommand);

        //when
        mockMvc.perform(get("/recipe/1/ingredient/2/update"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/ingredient/ingredientform"))
                .andExpect(model().attributeExists("ingredient"))
                .andExpect(model().attributeExists("uomList"));

        //then
        assertEquals(ingredientCommand.getId(), ingredientService.findCommandByRecipeIdAndIngredientId(anyLong(), anyLong()).getId());
        assertEquals(ingredientCommand.getRecipeId(), ingredientService.findCommandByRecipeIdAndIngredientId(anyLong(), anyLong()).getRecipeId());

        //verify(ingredientService, times(1)).findCommandByRecipeIdAndIngredientId(anyLong(), anyLong());
    }

    @Test
    public void testNewIngredientByRecipeId() throws Exception {
        //given
        IngredientCommand ingredientCommand = new IngredientCommand();

       // when(ingredientService.findCommandByRecipeIdAndIngredientId(anyLong(), anyLong())).thenReturn(ingredientCommand);

        //when
        mockMvc.perform(get("/recipe/1/ingredients/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/ingredient/ingredientform"));

        //then
        //verify(ingredientService, times(1)).findCommandByRecipeIdAndIngredientId(anyLong(), anyLong());

    }



}