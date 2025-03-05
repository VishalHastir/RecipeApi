package com.recipe.api.controller;

import java.util.List;
import com.recipe.api.entity.Recipe;
import com.recipe.api.model.RecipeRequest;
import com.recipe.api.model.RecipeResponse;
import com.recipe.api.model.RecipeSearchRequest;
import com.recipe.api.service.RecipeService;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * The type Recipe controller.
 */
@Tag(name = "Recipe Controller", description = "Create, Update, Delete, and Retrieve Recipes")
@RestController
@RequestMapping("/recipes")
public class RecipeController {

	private final Logger logger = LoggerFactory.getLogger(RecipeController.class);
	private final RecipeService recipeService;

	/**
	 * Instantiates a new Recipe controller.
	 *
	 * @param recipeService the recipe service
	 */

	public RecipeController(RecipeService recipeService) {
		this.recipeService = recipeService;
	}

	/**
	 * Gets all recipes.
	 *
	 * @return the all recipes
	 */
	@Operation(
			summary = "Get all recipes",
			description = "Retrieve a list of all recipes",
			responses = {
					@ApiResponse(responseCode = "200", description = "Success",
							content = @Content(array = @ArraySchema(schema = @Schema(implementation = RecipeResponse.class)))),
					@ApiResponse(responseCode = "404", description = "Recipes not found"),
					@ApiResponse(responseCode = "500", description = "Internal Server Error")
			}
	)
	@GetMapping
	public ResponseEntity<List<RecipeResponse>> getAllRecipes() {
		logger.info("Fetching all recipes");
		List<RecipeResponse> recipes = recipeService.getAllRecipes();
		return ResponseEntity.ok(recipes);
	}

	/**
	 * Gets recipe by id.
	 *
	 * @param id the id
	 * @return the recipe by id
	 */
	@Operation(
			summary = "Get recipe by ID",
			description = "Retrieve a recipe by its unique ID",
			responses = {
					@ApiResponse(responseCode = "200", description = "Success",
							content = @Content(schema = @Schema(implementation = RecipeResponse.class))),
					@ApiResponse(responseCode = "404", description = "Recipe not found"),
					@ApiResponse(responseCode = "500", description = "Internal Server Error")
			}
	)
	@GetMapping("/{id}")
	public ResponseEntity<RecipeResponse> getRecipeById(
			@Parameter(description = "Recipe ID", required = true) @PathVariable Integer id) {
		logger.info("Fetching recipe by id: {}", id);
		Recipe recipe = recipeService.getRecipeById(id);
		return ResponseEntity.ok(new RecipeResponse(recipe));
	}

	/**
	 * Create recipe response entity.
	 *
	 * @param request the request
	 * @return the response entity
	 */
	@Operation(
			summary = "Create a recipe",
			description = "Create a new recipe with the provided details",
			responses = {
					@ApiResponse(responseCode = "201", description = "Recipe created",
							content = @Content(schema = @Schema(implementation = RecipeResponse.class))),
					@ApiResponse(responseCode = "400", description = "Bad request"),
					@ApiResponse(responseCode = "500", description = "Internal Server Error")
			}
	)
	@PostMapping
	public ResponseEntity<RecipeResponse> createRecipe(
			@Parameter(description = "Recipe request", required = true) @Valid @RequestBody RecipeRequest request) {
		logger.info("Creating recipe");
		Integer id = recipeService.createRecipe(request);
		return new ResponseEntity<>(new RecipeResponse(id), HttpStatus.CREATED);
	}

	/**
	 * Update recipe response entity.
	 *
	 * @param request the request
	 * @return the response entity
	 */
	@Operation(
			summary = "Update a recipe",
			description = "Update an existing recipe with the specified ID",
			responses = {
					@ApiResponse(responseCode = "200", description = "Success"),
					@ApiResponse(responseCode = "400", description = "Bad request"),
					@ApiResponse(responseCode = "404", description = "Recipe not found"),
					@ApiResponse(responseCode = "500", description = "Internal Server Error")
			}
	)
	@PutMapping
	public ResponseEntity<Void> updateRecipe(
			@Parameter(description = "Recipe request", required = true) @Valid @RequestBody RecipeRequest request) {
		logger.info("Updating recipe with id: {}", request.getId());
		recipeService.updateRecipe(request);
		return ResponseEntity.ok().build();
	}

	/**
	 * Delete recipe response entity.
	 *
	 * @param id the id
	 * @return the response entity
	 */
	@Operation(
			summary = "Delete a recipe",
			description = "Delete a recipe identified by the given ID",
			responses = {
					@ApiResponse(responseCode = "200", description = "Success"),
					@ApiResponse(responseCode = "400", description = "Bad request"),
					@ApiResponse(responseCode = "404", description = "Recipe not found"),
					@ApiResponse(responseCode = "500", description = "Internal Server Error")
			}
	)
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteRecipe(
			@Parameter(description = "Recipe ID", required = true) @PathVariable Integer id) {
		logger.info("Deleting recipe with id: {}", id);
		recipeService.deleteRecipe(id);
		return ResponseEntity.ok().build();
	}

	/**
	 * Search recipes response entity.
	 *
	 * @param searchRequest the search request
	 * @return the response entity
	 */
	@Operation(
			summary = "Search recipes",
			description = "Search recipes using given search filters",
			responses = {
					@ApiResponse(responseCode = "200", description = "Success",
							content = @Content(array = @ArraySchema(schema = @Schema(implementation = RecipeResponse.class)))),
					@ApiResponse(responseCode = "400", description = "Bad request"),
					@ApiResponse(responseCode = "404", description = "Search conditions or recipe not found"),
					@ApiResponse(responseCode = "500", description = "Internal Server Error")
			}
	)
	@PostMapping("/search")
	public ResponseEntity<List<RecipeResponse>> searchRecipes(
			@Parameter(description = "Search Filter Request", required = true) @Valid @RequestBody RecipeSearchRequest searchRequest) {
		logger.info("Searching recipes with given filters");
		List<RecipeResponse> results = recipeService.findFilteredRecipes(searchRequest);
		return ResponseEntity.ok(results);
	}
}
