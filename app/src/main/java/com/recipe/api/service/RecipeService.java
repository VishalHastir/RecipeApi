package com.recipe.api.service;

import com.recipe.api.entity.Recipe;
import com.recipe.api.model.RecipeRequest;
import com.recipe.api.model.RecipeResponse;
import com.recipe.api.model.RecipeSearchRequest;

import java.util.List;

/**
 * The interface Recipe service.
 *
 * @author Vishal
 */
public interface RecipeService {
    /**
     * Gets all recipes.
     *
     * @return the all recipes
     */
    List<RecipeResponse> getAllRecipes();

    /**
     * Create recipe integer.
     *
     * @param createRecipeRequest the create recipe request
     * @return the integer
     */
    Integer createRecipe(RecipeRequest createRecipeRequest);

    /**
     * Gets recipe by id.
     *
     * @param id the id
     * @return the recipe by id
     */
    Recipe getRecipeById(int id);

    /**
     * Update recipe.
     *
     * @param updateRecipeRequest the update recipe request
     */
    void updateRecipe(RecipeRequest updateRecipeRequest);

    /**
     * Delete recipe.
     *
     * @param id the id
     */
    void deleteRecipe(int id);

    /**
     * Find filtered recipes list.
     *
     * @param recipeSearchRequest the recipe search request
     * @return the list
     */
    List<RecipeResponse> findFilteredRecipes(RecipeSearchRequest recipeSearchRequest);

}
