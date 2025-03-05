package com.recipe.api.search;

import com.recipe.api.model.SearchConditionsRequest;

/**
 * The type Search conditions.
 *
 * @author Vishal
 */
public class SearchConditions {
    private String filterKey;
    private Object value;
    private String operation;
    private String searchConditionsType;

	/**
	 * Instantiates a new Search conditions.
	 */
	public SearchConditions() {
    }

	/**
	 * Instantiates a new Search conditions.
	 *
	 * @param filterKey the filter key
	 * @param operation the operation
	 * @param value     the value
	 */
	public SearchConditions(String filterKey, String operation, Object value){
        super();
        this.filterKey = filterKey;
        this.operation = operation;
        this.value = value;
    }

	/**
	 * Instantiates a new Search conditions.
	 *
	 * @param searchConditionsRequest the search conditions request
	 */
	public SearchConditions(SearchConditionsRequest searchConditionsRequest) {
        this.searchConditionsType = searchConditionsRequest.getSearchConditionsType();
        this.filterKey = searchConditionsRequest.getFilterKey();
        this.operation = searchConditionsRequest.getOperation();
        this.value = searchConditionsRequest.getValue();
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
	public void setValue(Object value) {
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
