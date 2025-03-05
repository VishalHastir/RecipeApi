package com.recipe.api.service;

import com.recipe.api.entity.Recipe;
import com.recipe.api.exception.DataNotFoundException;
import com.recipe.api.model.RecipeRequest;
import com.recipe.api.model.RecipeResponse;
import com.recipe.api.model.RecipeSearchRequest;
import com.recipe.api.model.SearchConditionsRequest;
import com.recipe.api.repository.RecipeRepository;
import com.recipe.api.util.CustomMessagesUtil;
import com.recipe.api.search.RecipeSpecificationBuilder;
import com.recipe.api.search.SearchConditions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.CollectionUtils;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

class RecipeServiceImplTest {

    @Mock
    private RecipeRepository recipeRepository;

    @Mock
    private CustomMessagesUtil customMessagesUtil;

    @InjectMocks
    private RecipeServiceImpl recipeService;

    private RecipeRequest createRecipeRequest;
    private RecipeRequest updateRecipeRequest;
    private RecipeSearchRequest searchRequest;

    private Recipe existingRecipe;
    private RecipeResponse recipeResponse;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Mock RecipeRequest for create and update
        createRecipeRequest = new RecipeRequest();
        createRecipeRequest.setName("Pancakes");
        createRecipeRequest.setInstructions("Mix ingredients");
        createRecipeRequest.setType("Dessert");
        createRecipeRequest.setNumberOfServings(2);
        createRecipeRequest.setIngredients("Flour,Eggs,Milk");

        updateRecipeRequest = new RecipeRequest();
        updateRecipeRequest.setId(1);
        updateRecipeRequest.setName("Updated Pancakes");
        updateRecipeRequest.setInstructions("New instructions");
        updateRecipeRequest.setType("Breakfast");
        updateRecipeRequest.setNumberOfServings(4);
        updateRecipeRequest.setIngredients("Flour,Eggs,Milk,Sugar");

        existingRecipe = new Recipe();
        existingRecipe.setId(1);
        existingRecipe.setName("Pancakes");
        existingRecipe.setInstructions("Mix ingredients");
        existingRecipe.setType("Dessert");
        existingRecipe.setNumberOfServings(2);
        existingRecipe.setIngredients("Flour,Eggs,Milk");

        recipeResponse = new RecipeResponse(existingRecipe);
    }

    @Test
    void testCreateRecipe() {
        // Given
        when(recipeRepository.save(any(Recipe.class))).thenReturn(existingRecipe);

        // When
        Integer recipeId = recipeService.createRecipe(createRecipeRequest);

        // Then
        assertThat(recipeId).isEqualTo(existingRecipe.getId());
        verify(recipeRepository, times(1)).save(any(Recipe.class));
    }

    @Test
    void testUpdateRecipe() {
        // Given
        when(recipeRepository.findById(anyInt())).thenReturn(Optional.of(existingRecipe));
        when(recipeRepository.save(any(Recipe.class))).thenReturn(existingRecipe);

        // When
        recipeService.updateRecipe(updateRecipeRequest);

        // Then
        verify(recipeRepository, times(1)).findById(anyInt());
        verify(recipeRepository, times(1)).save(any(Recipe.class));
    }

    @Test
    void testDeleteRecipe() {
        // Given
        when(recipeRepository.existsById(anyInt())).thenReturn(true);

        // When
        recipeService.deleteRecipe(1);

        // Then
        verify(recipeRepository, times(1)).deleteById(anyInt());
    }

    @Test
    void testDeleteRecipe_NotFound() {
        // Given
        when(recipeRepository.existsById(anyInt())).thenReturn(false);
        when(customMessagesUtil.getMessage("recipe.notFound")).thenReturn("Recipe not found");
        // Then
        assertThatThrownBy(() -> recipeService.deleteRecipe(1))
                .isInstanceOf(DataNotFoundException.class)
                .hasMessageContaining("Recipe not found");
    }

    @Test
    void testGetRecipeById() {
        // Given
        when(recipeRepository.findById(anyInt())).thenReturn(Optional.of(existingRecipe));

        // When
        Recipe recipe = recipeService.getRecipeById(1);

        // Then
        assertThat(recipe).isNotNull();
        assertThat(recipe.getName()).isEqualTo("Pancakes");
    }

    @Test
    void testFindFilteredRecipes() {
        // Given
        SearchConditionsRequest searchCondition = new SearchConditionsRequest();
        searchCondition.setFilterKey("name");
        searchCondition.setOperation("LIKE");
        searchCondition.setValue("Pancakes");

        searchRequest = new RecipeSearchRequest();
        searchRequest.setSearchConditionsRequests(Collections.singletonList(searchCondition));

        RecipeSpecificationBuilder builder = mock(RecipeSpecificationBuilder.class);
        when(builder.build()).thenReturn(Optional.of(Specification.where(null)));

        List<Recipe> recipeList = Collections.singletonList(existingRecipe);
        when(recipeRepository.findAll(any(Specification.class))).thenReturn(recipeList);

        // When
        List<RecipeResponse> recipes = recipeService.findFilteredRecipes(searchRequest);

        // Then
        assertThat(recipes).isNotEmpty();
        assertThat(recipes.get(0).getName()).isEqualTo("Pancakes");
    }

    @Test
    void testFindFilteredRecipes_NoResults() {
        // Given
        SearchConditionsRequest searchCondition = new SearchConditionsRequest();
        searchCondition.setFilterKey("name");
        searchCondition.setOperation("LIKE");
        searchCondition.setValue("NonExistent");

        searchRequest = new RecipeSearchRequest();
        searchRequest.setSearchConditionsRequests(Collections.singletonList(searchCondition));

        RecipeSpecificationBuilder builder = mock(RecipeSpecificationBuilder.class);
        when(builder.build()).thenReturn(Optional.of(Specification.where(null)));

        List<Recipe> recipeList = new ArrayList<>();
        when(customMessagesUtil.getMessage("recipe.notFound")).thenReturn("Recipe not found");
        when(recipeRepository.findAll(any(Specification.class))).thenReturn(recipeList);

        // When & Then
        assertThatThrownBy(() -> recipeService.findFilteredRecipes(searchRequest))
                .isInstanceOf(DataNotFoundException.class)
                .hasMessageContaining("Recipe not found");
    }
}
