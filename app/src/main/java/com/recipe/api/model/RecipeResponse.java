package com.recipe.api.model;


import java.time.LocalDateTime;

import com.recipe.api.entity.Recipe;
import io.swagger.v3.oas.annotations.media.Schema;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

/**
 * The type Recipe response.
 *
 * @author Vishal
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RecipeResponse {

	@Schema(description = "Recipe Id", example = "1")
	private int id;

	@Schema(description = "Recipe name", example = "Pasta")
	private String name;

	@Schema(description = "Recipe Type", example = "VEGETARIAN")
	private String type;

	@Schema(description = "Number of servings", example = "1")
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private int numberOfServings;

	@Schema(description = "Ingredients used in recipe", example = "spaghetti,eggs,bacon")
    @JsonIgnoreProperties("ingredients")
    private String ingredients;

    @Schema(description = "Recipe instructions", example = "Cook spaghetti and mix with eggs and bacon")
    private String instructions;





    @CreationTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime updatedAt;

	/**
	 * Instantiates a new Recipe response.
	 */
	public RecipeResponse() {
    }

	/**
	 * Instantiates a new Recipe response.
	 *
	 * @param recipe the recipe
	 */
	public RecipeResponse(Recipe recipe) {
        this.id = recipe.getId();
        this.name = recipe.getName();
        this.type = recipe.getType();
        this.instructions = recipe.getInstructions();
        this.createdAt = recipe.getCreatedAt();
        this.updatedAt = recipe.getUpdatedAt();
        this.numberOfServings = recipe.getNumberOfServings();
        this.ingredients = recipe.getIngredients();
    }

	/**
	 * Instantiates a new Recipe response.
	 *
	 * @param id the id
	 */
	public RecipeResponse(Integer id) {
    	 this.id = id;
	}

	/**
	 * Gets id.
	 *
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Sets id.
	 *
	 * @param id the id to set
	 */
	public void setId(int id) {
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
	 * Gets created at.
	 *
	 * @return the createdAt
	 */
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	/**
	 * Sets created at.
	 *
	 * @param createdAt the createdAt to set
	 */
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	/**
	 * Gets updated at.
	 *
	 * @return the updatedAt
	 */
	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	/**
	 * Sets updated at.
	 *
	 * @param updatedAt the updatedAt to set
	 */
	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	
}
