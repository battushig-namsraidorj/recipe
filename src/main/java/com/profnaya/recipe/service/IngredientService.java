package com.profnaya.recipe.service;

import com.profnaya.recipe.command.IngredientCommand;

public interface IngredientService {
    IngredientCommand findCommandByRecipeIdAndIngredientId(Long recipeId, Long id);
}
