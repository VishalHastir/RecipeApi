package com.recipe.api.search;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import com.recipe.api.entity.Recipe;
import com.recipe.api.enums.SearchOperation;
import com.recipe.api.filter.SearchFilter;
import com.recipe.api.filter.SearchFilterExclude;
import com.recipe.api.filter.SearchFilterInclude;

/**
 * The type Recipe specification.
 *
 * @author Vishal
 */
public class RecipeSpecification implements Specification<Recipe> {
    
	private static final long serialVersionUID = 12L;

	private final SearchConditions searchConditions;

    private static final List<SearchFilter> searchFilters = new ArrayList<>();

    /**
     * Instantiates a new Recipe specification.
     *
     * @param searchConditions the search conditions
     */
    public RecipeSpecification(SearchConditions searchConditions) {
        super();
        filterList();
        this.searchConditions = searchConditions;
    }

    @Override
    public Predicate toPredicate(Root<Recipe> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        Optional<SearchOperation> operation = SearchOperation.getOperation(searchConditions.getOperation());
        String filterValue = searchConditions.getValue().toString().toLowerCase();
        String filterKey = searchConditions.getFilterKey();

        return operation.flatMap(searchOperation -> searchFilters
                .stream()
                .filter(searchFilter -> searchFilter.getSearchFilter(searchOperation))
                .findFirst()
                .map(searchFilter -> searchFilter.applySearchFilter(cb, filterKey, filterValue, root))).orElse(null);
    }

    private void filterList() {
        searchFilters.add(new SearchFilterInclude());
        searchFilters.add(new SearchFilterExclude());
    }
}
