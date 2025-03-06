package com.recipe.api.integration;

import com.recipe.api.model.RecipeRequest;
import com.recipe.api.model.RecipeResponse;
import com.recipe.api.model.RecipeSearchRequest;
import com.recipe.api.model.SearchConditionsRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RecipeControllerIntegrationTest {

    private final TestRestTemplate restTemplate;

    // Constructor injection for TestRestTemplate
    @Autowired
    public RecipeControllerIntegrationTest(TestRestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * Helper method to create a new RecipeRequest.
     */
    private RecipeRequest createRecipeRequest(String name) {
        RecipeRequest request = new RecipeRequest();
        request.setName(name);
        request.setInstructions("Mix all ingredients well.");
        request.setType("VEGETARIAN");
        request.setNumberOfServings(2);
        request.setIngredients("Ingredient1");
        return request;
    }

    @Test
    public void testCreateAndRetrieveRecipe() {
        // Create a recipe
        RecipeRequest recipeRequest = createRecipeRequest("Test Recipe");
        ResponseEntity<RecipeResponse> createResponse = restTemplate.postForEntity("/recipes", recipeRequest, RecipeResponse.class);
        assertThat(createResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        RecipeResponse createdRecipe = createResponse.getBody();
        assertThat(createdRecipe).isNotNull();
        Integer recipeId = createdRecipe.getId();
        assertThat(recipeId).isNotNull();

        // Retrieve the created recipe by ID
        ResponseEntity<RecipeResponse> getResponse = restTemplate.getForEntity("/recipes/" + recipeId, RecipeResponse.class);
        assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        RecipeResponse retrievedRecipe = getResponse.getBody();
        assertThat(retrievedRecipe).isNotNull();
        assertThat(retrievedRecipe.getName()).isEqualTo("Test Recipe");
    }

    @Test
    public void testGetAllRecipes() {
        // Ensure there is at least one recipe by creating one
        RecipeRequest recipeRequest = createRecipeRequest("AllRecipes Test");
        restTemplate.postForEntity("/recipes", recipeRequest, RecipeResponse.class);

        // Retrieve all recipes
        ResponseEntity<RecipeResponse[]> response = restTemplate.getForEntity("/recipes", RecipeResponse[].class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        RecipeResponse[] recipes = response.getBody();
        assertThat(recipes).isNotNull();
        assertThat(recipes.length).isGreaterThan(0);
    }

    @Test
    public void testUpdateRecipe() {
        // Create a recipe first
        RecipeRequest recipeRequest = createRecipeRequest("Old Recipe Name");
        ResponseEntity<RecipeResponse> createResponse = restTemplate.postForEntity("/recipes", recipeRequest, RecipeResponse.class);
        RecipeResponse createdRecipe = createResponse.getBody();
        Integer recipeId = createdRecipe.getId();

        // Update the recipe name
        RecipeRequest updateRequest = createRecipeRequest("Updated Recipe Name");
        updateRequest.setId(recipeId);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<RecipeRequest> requestEntity = new HttpEntity<>(updateRequest, headers);
        ResponseEntity<Void> updateResponse = restTemplate.exchange("/recipes", HttpMethod.PUT, requestEntity, Void.class);
        assertThat(updateResponse.getStatusCode()).isEqualTo(HttpStatus.OK);

        // Verify update by fetching the recipe by id
        ResponseEntity<RecipeResponse> getResponse = restTemplate.getForEntity("/recipes/" + recipeId, RecipeResponse.class);
        RecipeResponse updatedRecipe = getResponse.getBody();
        assertThat(updatedRecipe).isNotNull();
        assertThat(updatedRecipe.getName()).isEqualTo("Updated Recipe Name");
    }

    @Test
    public void testDeleteRecipe() {
        // Create a recipe
        RecipeRequest recipeRequest = createRecipeRequest("Delete Test Recipe");
        ResponseEntity<RecipeResponse> createResponse = restTemplate.postForEntity("/recipes", recipeRequest, RecipeResponse.class);
        RecipeResponse createdRecipe = createResponse.getBody();
        Integer recipeId = createdRecipe.getId();

        // Delete the recipe
        restTemplate.delete("/recipes/" + recipeId);

        // Attempt to retrieve the deleted recipe
        ResponseEntity<RecipeResponse> getResponse = restTemplate.getForEntity("/recipes/" + recipeId, RecipeResponse.class);
        assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    public void testSearchRecipes() {
        RecipeRequest recipeRequest = createRecipeRequest("Pancakes");
        restTemplate.postForEntity("/recipes", recipeRequest, RecipeResponse.class);

        RecipeSearchRequest searchRequest = new RecipeSearchRequest();
        SearchConditionsRequest searchFilter = new SearchConditionsRequest();
        searchFilter.setFilterKey("name");
        searchFilter.setOperation("IN");
        searchFilter.setValue("Pancakes");
        searchRequest.setSearchConditionsRequests(Collections.singletonList(searchFilter));
        searchRequest.setSearchConditionsType("ALL");

        ResponseEntity<RecipeResponse[]> searchResponse = restTemplate.postForEntity(
                "/recipes/search", searchRequest, RecipeResponse[].class);

        assertThat(searchResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        RecipeResponse[] results = searchResponse.getBody();
        assertThat(results).isNotNull();
        assertThat(results.length).isGreaterThan(0);
    }

}
