package com.recipe.api.utils;

import com.recipe.api.entity.Recipe;

public class RecipeTestData {
    public static Recipe createRecipe() {
        return createRecipe(null);
    }

    public static Recipe createRecipe(Integer id) {
        Recipe recipe = new Recipe();
        recipe.setId(id);
        recipe.setName("pesto pasta");
        recipe.setNumberOfServings(5);
        recipe.setType("OTHER");
        recipe.setInstructions("Instruction");

        return recipe;
    }
}
