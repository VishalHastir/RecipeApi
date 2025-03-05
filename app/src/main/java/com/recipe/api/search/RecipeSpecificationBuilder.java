package com.recipe.api.search;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.domain.Specification;

import com.recipe.api.entity.Recipe;
import com.recipe.api.enums.SearchConditionsType;

/**
 * The type Recipe specification builder.
 *
 * @author Vishal
 */
public class RecipeSpecificationBuilder {
    private final List<SearchConditions> searchConditionsList;

    /**
     * Instantiates a new Recipe specification builder.
     *
     * @param searchConditionsList the search conditions list
     */
    public RecipeSpecificationBuilder(List<SearchConditions> searchConditionsList) {
        this.searchConditionsList = searchConditionsList;
    }

    /**
     * Add criteria recipe specification builder.
     *
     * @param searchConditions the search conditions
     * @return recipe specification builder
     */
    public final RecipeSpecificationBuilder addCriteria(SearchConditions searchConditions) {
    	searchConditionsList.add(searchConditions);
        return this;
    }

    /**
     * Build optional.
     *
     * @return optional
     */
    public Optional<Specification<Recipe>> build() {
        if (searchConditionsList.size() == 0) return Optional.empty();

        Specification<Recipe> result = new RecipeSpecification(searchConditionsList.get(0));

        for (int i = 1; i < searchConditionsList.size(); i++) {
            SearchConditions criteria = searchConditionsList.get(i);
            Optional<SearchConditionsType> searchConditionsType = SearchConditionsType.getSearchConditionsType(criteria.getSearchConditionsType());
            if (searchConditionsType.isPresent()) {
                result = (searchConditionsType.get() == SearchConditionsType.ALL)
                        ? Specification.where(result).and(new RecipeSpecification(criteria))
                        : Specification.where(result).or(new RecipeSpecification(criteria));
            }
        }
        return Optional.of(result);
    }
}
