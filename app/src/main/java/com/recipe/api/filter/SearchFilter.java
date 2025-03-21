package com.recipe.api.filter;



import com.recipe.api.entity.Recipe;
import com.recipe.api.enums.SearchOperation;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

/**
 * The interface Search filter.
 *
 * @author Vishal
 */
public interface SearchFilter  {
    /**
     * Gets search filter.
     *
     * @param opt the opt
     * @return the search filter
     */
    boolean getSearchFilter(SearchOperation opt);

    /**
     * Apply search filter predicate.
     *
     * @param cb          the cb
     * @param filterKey   the filter key
     * @param filterValue the filter value
     * @param root        the root
     * @return the predicate
     */
    Predicate applySearchFilter(CriteriaBuilder cb, String filterKey, String filterValue, Root<Recipe> root);
}