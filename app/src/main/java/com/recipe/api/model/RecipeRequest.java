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

/**
 * The type Recipe request.
 *
 * @author Vishal
 */
public class RecipeRequest {

	@Positive(message = "{recipe.positive}")
	@Schema(description = "Recipe Id", example = "1", required = true)
	private Integer id;

	@NotBlank(message = "{recipeName.notBlank}")
	@Size(max = CommonConstants.RECIPE_NAME_MAX_LENGTH, message = "{recipeName.size}")
	@Pattern(regexp = CommonConstants.RECIPE_NAME_PATTERN, message = "{recipeName.pattern}")
	@Schema(description = "Recipe Name", example = "Spaghetti Carbonara", required = true)
	private String name;

	@Schema(description = "Recipe Type", example = "VEGETARIAN")
	@EnumValidator(enumClass = RecipeType.class, message = "{recipeType.invalid}")
	private String type;

	@NotNull(message = "{numberOfServings.notNull}")
	@Positive(message = "{numberOfServings.positive}")
	@Schema(description = "Number of servings per recipe", example = "2", required = true)
	private int numberOfServings;

	@Schema(description = "Recipe Ingredients comma separated", example = "spaghetti,eggs,bacon")
	private String ingredients;

	@NotBlank(message = "{instructions.notBlank}")
	@Size(max = CommonConstants.DEFAULT_MAX_LENGTH, message = "{instructions.size}")
	@Pattern(regexp = CommonConstants.INSTRUCTIONS_FREE_TEXT_PATTERN, message = "{instructions.pattern}")
	@Schema(description = "Instructions to create the recipe", example = "Cook spaghetti and mix with eggs and bacon", required = true)
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
			@NotBlank(message = "{recipeName.notBlank}") @Size(max = 100, message = "{recipeName.size}") @Pattern(regexp = "^(?:\\p{L}\\p{M}*|[',. \\-]|\\s)*$", message = "{recipeName.pattern}") String name,
			@EnumValidator(enumClass = RecipeType.class, message = "{recipeType.invalid}") String type,
			@NotNull(message = "{numberOfServings.notNull}") @Positive(message = "{numberOfServings.positive}") int numberOfServings,
			String ingredients,
			@NotBlank(message = "{instructions.notBlank}") @Size(max = 255, message = "{instructions.size}") @Pattern(regexp = "^(?:\\p{L}\\p{M}*|[0-9]*|[\\/\\-+.,?!*();\"]|\\s)*$", message = "{instructions.pattern}") String instructions) {
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
	public RecipeRequest(@Positive(message = "{recipe.positive}") Integer id,
			@NotBlank(message = "{recipeName.notBlank}") @Size(max = 100, message = "{recipeName.size}") @Pattern(regexp = "^(?:\\p{L}\\p{M}*|[',. \\-]|\\s)*$", message = "{recipeName.pattern}") String name,
			@EnumValidator(enumClass = RecipeType.class, message = "{recipeType.invalid}") String type,
			@NotNull(message = "{numberOfServings.notNull}") @Positive(message = "{numberOfServings.positive}") int numberOfServings,
			String ingredients,
			@NotBlank(message = "{instructions.notBlank}") @Size(max = 255, message = "{instructions.size}") @Pattern(regexp = "^(?:\\p{L}\\p{M}*|[0-9]*|[\\/\\-+.,?!*();\"]|\\s)*$", message = "{instructions.pattern}") String instructions) {
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
	public String getIngredients() {
		return ingredients;
	}


	/**
	 * Sets ingredients.
	 *
	 * @param ingredients the ingredients to set
	 */
	public void setIngredients(String ingredients) {
		this.ingredients = ingredients;
	}
	
	

}
