package com.recipe.api.enums;

import java.util.Optional;

/**
 * The enum Search operation.
 *
 * @author Vishal
 */
public enum SearchOperation {

    /**
     * Include search operation.
     */
    INCLUDE,
    /**
     * Exclude search operation.
     */
    EXCLUDE;


    /**
     * Gets operation.
     *
     * @param input the input
     * @return the operation
     */
    public static Optional<SearchOperation> getOperation(final String input) {
        String lowerInput = input.toLowerCase();
        switch (lowerInput) {
            case "in":
                return Optional.of(INCLUDE);
            case "ex":
                return Optional.of(EXCLUDE);
        }
        return Optional.empty();
    }
}
