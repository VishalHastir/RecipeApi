package com.recipe.api.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import com.recipe.api.entity.Recipe;
import com.recipe.api.model.RecipeRequest;
import com.recipe.api.model.RecipeResponse;
import com.recipe.api.service.RecipeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
public class RecipeControllerTest {

    @Mock
    private RecipeService recipeService;

    @InjectMocks
    private RecipeController recipeController;

    @Test
    public void testCreateRecipe() {
        RecipeRequest request = new RecipeRequest(null, "Spaghetti Carbonara", "NON_VEGETARIAN", 4,
                "spaghetti,eggs,bacon", "Cook spaghetti and mix with eggs and bacon");

        when(recipeService.createRecipe(any(RecipeRequest.class))).thenReturn(1);

        ResponseEntity<RecipeResponse> responseEntity = recipeController.createRecipe(request);
        RecipeResponse response = responseEntity.getBody();

        assertThat(response).isNotNull();
        assertThat(response.getId()).isEqualTo(1);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

    @Test
    public void testGetRecipeById() {
        Recipe recipe = new Recipe();
        recipe.setId(1);
        recipe.setName("Spaghetti Carbonara");

        when(recipeService.getRecipeById(anyInt())).thenReturn(recipe);

        ResponseEntity<RecipeResponse> responseEntity = recipeController.getRecipeById(1);
        RecipeResponse response = responseEntity.getBody();

        assertThat(response.getId()).isEqualTo(recipe.getId());
        assertThat(response.getName()).isEqualTo(recipe.getName());
    }

    @Test
    public void testGetAllRecipes() {
        RecipeResponse recipe1 = new RecipeResponse();
        recipe1.setId(5);
        recipe1.setName("Spaghetti Carbonara");

        RecipeResponse recipe2 = new RecipeResponse();
        recipe2.setId(6);
        recipe2.setName("Chicken Alfredo");

        List<RecipeResponse> storedRecipeList = new ArrayList<>();
        storedRecipeList.add(recipe1);
        storedRecipeList.add(recipe2);

        when(recipeService.getAllRecipes()).thenReturn(storedRecipeList);

        ResponseEntity<List<RecipeResponse>> responseEntity = recipeController.getAllRecipes();
        List<RecipeResponse> recipeList = responseEntity.getBody();

        assertThat(recipeList).isNotNull();
        assertThat(recipeList.size()).isEqualTo(storedRecipeList.size());
        assertThat(recipeList.get(0).getId()).isEqualTo(storedRecipeList.get(0).getId());
        assertThat(recipeList.get(1).getId()).isEqualTo(storedRecipeList.get(1).getId());
    }

    @Test
    public void testUpdateRecipe() {
        RecipeRequest request = new RecipeRequest(1, "Chicken Alfredo", "NON_VEGETARIAN", 4,
                "chicken,alfredo sauce", "Cook chicken and mix with alfredo sauce");

        doNothing().when(recipeService).updateRecipe(any(RecipeRequest.class));
        ResponseEntity<Void> responseEntity = recipeController.updateRecipe(request);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void testDeleteRecipe() {
        doNothing().when(recipeService).deleteRecipe(anyInt());
        ResponseEntity<Void> responseEntity = recipeController.deleteRecipe(5);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
}
