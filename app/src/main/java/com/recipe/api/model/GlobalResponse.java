package com.recipe.api.model;

import java.util.Objects;

/**
 * The type Global response.
 *
 * @author Vishal
 */
public class GlobalResponse {

    private final String message;

    /**
     * Instantiates a new Global response.
     *
     * @param message the message
     */
    public GlobalResponse(String message) {
        this.message = message;
    }

    /**
     * Gets message.
     *
     * @return the message
     */
    public String getMessage() {
        return message;
    }

}
