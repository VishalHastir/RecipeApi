package com.recipe.api.model;

import com.recipe.api.config.EnumValidator;
import com.recipe.api.constants.CommonConstants;
import com.recipe.api.enums.RecipeType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.util.List;

/**
 * The type Recipe request.
 *
 * @author Vishal
 */
public class RecipeRequest {

	@Positive(message = "{recipe.isNotPositive}")
	@Schema(description = "Recipe Id", example = "1", required = true)
	private Integer id;

	@NotBlank(message = "{recipeName.notNull}")
	@Size(max = CommonConstants.RECIPE_NAME_MAX_LENGTH, message = "{recipeName.size}")
	@Pattern(regexp = CommonConstants.RECIPE_NAME_PATTERN, message = "{recipeName.matchingPattern}")
	@Schema(description = "Recipe Name", example = "Chicken Pasta", required = true)
	private String name;

	@Schema(description = "Recipe Type", example = "VEGETARIAN")
	@EnumValidator(enumClass = RecipeType.class, message = "{recipeType.invalid}")
	private String type;

	@NotNull(message = "{numberOfServings.notNull}")
	@Positive(message = "{numberOfServings.numberValidation}")
	@Schema(description = "Number of servings per recipe", example = "2", required = true)
	private int numberOfServings;

	@Schema(description = "List of ingredients", example = "[chicken, sauce]")
	private List<String> ingredients;

	@NotBlank(message = "{instructions.notEmpty}")
	@Size(max = CommonConstants.DEFAULT_MAX_LENGTH, message = "{instructions.size}")
	@Pattern(regexp = CommonConstants.INSTRUCTIONS_TEXT_PATTERN, message = "{instructions.matchingPattern}")
	@Schema(description = "Instructions to create the recipe", example = "Cook pasta and mix with sauce", required = true)
	private String instructions;

	/**
	 * Instantiates a new Recipe request.
	 */
	public RecipeRequest() {
	}


	/**
	 * Instantiates a new Recipe request.
	 *
	 * @param name             the name
	 * @param type             the type
	 * @param numberOfServings the number of servings
	 * @param ingredients      the ingredients
	 * @param instructions     the instructions
	 */
	public RecipeRequest(
			@NotBlank(message = "{recipeName.notNull}") @Size(max = 100, message = "{recipeName.size}") @Pattern(regexp = "^(?:\\p{L}\\p{M}*|[',. \\-]|\\s)*$", message = "{recipeName.matchingPattern}") String name,
			@EnumValidator(enumClass = RecipeType.class, message = "{recipeType.invalid}") String type,
			@NotNull(message = "{numberOfServings.notNull}") @Positive(message = "{numberOfServings.numberValidation}") int numberOfServings,
			List<String> ingredients,
			@NotBlank(message = "{instructions.notEmpty}") @Size(max = 255, message = "{instructions.size}") @Pattern(regexp = "^(?:\\p{L}\\p{M}*|[0-9]*|[\\/\\-+.,?!*();\"]|\\s)*$", message = "{instructions.matchingPattern}") String instructions) {
		super();
		this.name = name;
		this.type = type;
		this.numberOfServings = numberOfServings;
		this.ingredients = ingredients;
		this.instructions = instructions;
	}


	/**
	 * Instantiates a new Recipe request.
	 *
	 * @param id               the id
	 * @param name             the name
	 * @param type             the type
	 * @param numberOfServings the number of servings
	 * @param ingredients      the ingredients
	 * @param instructions     the instructions
	 */
	public RecipeRequest(@Positive(message = "{recipe.isNotPositive}") Integer id,
			@NotBlank(message = "{recipeName.notNull}") @Size(max = 100, message = "{recipeName.size}") @Pattern(regexp = "^(?:\\p{L}\\p{M}*|[',. \\-]|\\s)*$", message = "{recipeName.matchingPattern}") String name,
			@EnumValidator(enumClass = RecipeType.class, message = "{recipeType.invalid}") String type,
			@NotNull(message = "{numberOfServings.notNull}") @Positive(message = "{numberOfServings.numberValidation}") int numberOfServings,
			List<String> ingredients,
			@NotBlank(message = "{instructions.notEmpty}") @Size(max = 255, message = "{instructions.size}") @Pattern(regexp = "^(?:\\p{L}\\p{M}*|[0-9]*|[\\/\\-+.,?!*();\"]|\\s)*$", message = "{instructions.matchingPattern}") String instructions) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.numberOfServings = numberOfServings;
		this.ingredients = ingredients;
		this.instructions = instructions;
	}


	/**
	 * Gets id.
	 *
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * Sets id.
	 *
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * Gets name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets name.
	 *
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets type.
	 *
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * Sets type.
	 *
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * Gets number of servings.
	 *
	 * @return the numberOfServings
	 */
	public int getNumberOfServings() {
		return numberOfServings;
	}

	/**
	 * Sets number of servings.
	 *
	 * @param numberOfServings the numberOfServings to set
	 */
	public void setNumberOfServings(int numberOfServings) {
		this.numberOfServings = numberOfServings;
	}

	/**
	 * Gets instructions.
	 *
	 * @return the instructions
	 */
	public String getInstructions() {
		return instructions;
	}

	/**
	 * Sets instructions.
	 *
	 * @param instructions the instructions to set
	 */
	public void setInstructions(String instructions) {
		this.instructions = instructions;
	}


	/**
	 * Gets ingredients.
	 *
	 * @return the ingredients
	 */
	public List<String> getIngredients() {
		return ingredients;
	}


	/**
	 * Sets ingredients.
	 *
	 * @param ingredients the ingredients to set
	 */
	public void setIngredients(List<String> ingredients) {
		this.ingredients = ingredients;
	}
	
	

}
