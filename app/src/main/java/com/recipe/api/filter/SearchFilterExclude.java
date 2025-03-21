package com.recipe.api.filter;





import com.recipe.api.entity.Recipe;
import com.recipe.api.enums.SearchOperation;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

/**
 * The type Search filter exclude.
 *
 * @author Vishal
 */
public class SearchFilterExclude implements SearchFilter {

    @Override
    public boolean getSearchFilter(SearchOperation opt) {
        return opt == SearchOperation.EXCLUDE;
    }

    @Override
    public Predicate applySearchFilter(CriteriaBuilder cb, String filterKey, String filterValue, Root<Recipe> root) {
        return cb.notLike(cb.lower(root.get(filterKey).as(String.class)), "%" + filterValue + "%");
    }
}
