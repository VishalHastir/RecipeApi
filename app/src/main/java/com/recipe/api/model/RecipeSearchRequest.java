package com.recipe.api.model;

import java.util.List;
import com.recipe.api.config.EnumValidator;
import com.recipe.api.enums.SearchConditionRequestInput;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;

/**
 * The type Recipe search request.
 *
 * @author Vishal
 */
public class RecipeSearchRequest {
    @JsonProperty("searchFilter")
	@Schema(description = "Search conditions to filter recipes", required = true)
    @Valid
    private List<SearchConditionsRequest> searchConditionsRequests;

	@Schema(description = "Search type (all or any condition)", example = "all", required = true)
    @EnumValidator(enumClass = SearchConditionRequestInput.class, message = "{searchCondition.invalid}")
    @JsonProperty("type")
    private String searchConditionsType;


	/**
	 * Instantiates a new Recipe search request.
	 */
	public RecipeSearchRequest() {
    }

	/**
	 * Instantiates a new Recipe search request.
	 *
	 * @param searchConditionsRequests the search conditions requests
	 * @param searchConditionsType     the search conditions type
	 */
	public RecipeSearchRequest(List<SearchConditionsRequest> searchConditionsRequests, String searchConditionsType) {
        this.searchConditionsRequests = searchConditionsRequests;
        this.searchConditionsType = searchConditionsType;
    }

	/**
	 * Gets search conditions requests.
	 *
	 * @return the searchConditionsRequests
	 */
	public List<SearchConditionsRequest> getSearchConditionsRequests() {
		return searchConditionsRequests;
	}

	/**
	 * Sets search conditions requests.
	 *
	 * @param searchConditionsRequests the searchConditionsRequests to set
	 */
	public void setSearchConditionsRequests(List<SearchConditionsRequest> searchConditionsRequests) {
		this.searchConditionsRequests = searchConditionsRequests;
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
