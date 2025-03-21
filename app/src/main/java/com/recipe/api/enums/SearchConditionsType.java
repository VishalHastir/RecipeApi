package com.recipe.api.enums;

import java.util.Optional;

/**
 * The enum Search conditions type.
 *
 * @author Vishal
 */
public enum SearchConditionsType {
    /**
     * OR search conditions type.
     */
    OR,
    /**
     * AND search conditions type.
     */
    AND;

    /**
     * Gets search conditions type.
     *
     * @param dataOption the data option
     * @return the search conditions type
     */
    public static Optional<SearchConditionsType> getSearchConditionsType(final String dataOption) {
        String lowerDataOption = dataOption.toLowerCase();
        switch (lowerDataOption) {
            case "and":
                return Optional.of(AND);
            case "or":
                return Optional.of(OR);
        }
        return Optional.empty();
    }
}
