package com.recipe.api.model;


import com.recipe.api.config.EnumValidator;
import com.recipe.api.enums.FilterKeyRequestInput;
import com.recipe.api.enums.SearchOperationRequestInput;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;


/**
 * The type Search conditions request.
 *
 * @author Vishal
 */
@Valid
public class SearchConditionsRequest {

    @Schema(description = "The name of the column you want to search between " +
            "name, " +
            "numberOfServings, " +
            "type, " +
            "instructions, " +
            "ingredients)", example = "name", required = true)
    @EnumValidator(enumClass = FilterKeyRequestInput.class, message = "{filterKey.invalid}")
    private String filterKey;


    @Schema(description = "Recipe Name", example = "Pasta", required = true)
    private String value;

    @Schema(description = "The search operation type (IN - include, " + " EX - exclude)", example = "IN", required = true)
    @EnumValidator(enumClass = SearchOperationRequestInput.class, message = "{searchOperation.invalid}")
    private String operation;

    @Schema(hidden = true)
    private String searchConditionsType;

	/**
	 * Instantiates a new Search conditions request.
	 */
	public SearchConditionsRequest() {
    }

	/**
	 * Instantiates a new Search conditions request.
	 *
	 * @param filterKey the filter key
	 * @param value     the value
	 * @param operation the operation
	 */
	public SearchConditionsRequest(String filterKey, String value, String operation) {
        this.filterKey = filterKey;
        this.value = value;
        this.operation = operation;
    }

	/**
	 * Gets filter key.
	 *
	 * @return the filterKey
	 */
	public String getFilterKey() {
		return filterKey;
	}

	/**
	 * Sets filter key.
	 *
	 * @param filterKey the filterKey to set
	 */
	public void setFilterKey(String filterKey) {
		this.filterKey = filterKey;
	}

	/**
	 * Gets value.
	 *
	 * @return the value
	 */
	public Object getValue() {
		return value;
	}

	/**
	 * Sets value.
	 *
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * Gets operation.
	 *
	 * @return the operation
	 */
	public String getOperation() {
		return operation;
	}

	/**
	 * Sets operation.
	 *
	 * @param operation the operation to set
	 */
	public void setOperation(String operation) {
		this.operation = operation;
	}

	/**
	 * Gets search conditions type.
	 *
	 * @return the searchConditionsType
	 */
	public String getSearchConditionsType() {
		return searchConditionsType;
	}

	/**
	 * Sets search conditions type.
	 *
	 * @param searchConditionsType the searchConditionsType to set
	 */
	public void setSearchConditionsType(String searchConditionsType) {
		this.searchConditionsType = searchConditionsType;
	}

   
}
