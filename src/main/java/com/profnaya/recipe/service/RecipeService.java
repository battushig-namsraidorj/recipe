package com.profnaya.recipe.service;

import com.profnaya.recipe.domain.Recipe;

import java.util.Set;

public interface RecipeService {
    Set<Recipe> getRecipes();
}
