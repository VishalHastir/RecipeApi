package com.recipe.api.enums;

import java.util.Optional;

/**
 * The enum Search conditions type.
 *
 * @author Vishal
 */
public enum SearchConditionsType {
    /**
     * Any search conditions type.
     */
    ANY,
    /**
     * All search conditions type.
     */
    ALL;

    /**
     * Gets search conditions type.
     *
     * @param dataOption the data option
     * @return the search conditions type
     */
    public static Optional<SearchConditionsType> getSearchConditionsType(final String dataOption) {
        String lowerDataOption = dataOption.toLowerCase();
        switch (lowerDataOption) {
            case "all":
                return Optional.of(ALL);
            case "any":
                return Optional.of(ANY);
        }
        return Optional.empty();
    }
}
