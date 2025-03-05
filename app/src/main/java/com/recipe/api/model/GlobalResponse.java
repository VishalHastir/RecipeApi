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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GlobalResponse that = (GlobalResponse) o;

        return Objects.equals(message, that.message);
    }

    @Override
    public int hashCode() {
        return message != null ? message.hashCode() : 0;
    }
}
