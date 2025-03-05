package com.recipe.api.constants;

/**
 * The type Common constants.
 *
 * @author Vishal
 */
public class CommonConstants {
    /**
     * The constant INGREDIENT_KEY.
     */
    public static final String INGREDIENT_KEY = "ingredient";
    /**
     * The constant RECIPE_NAME_PATTERN.
     */
    public static final String RECIPE_NAME_PATTERN = "^(?:\\p{L}\\p{M}*|[',. \\-]|\\s)*$";
    /**
     * The constant INGREDIENT_NAME_PATTERN.
     */
    public static final String INGREDIENT_NAME_PATTERN = "^(?:\\p{L}\\p{M}*|[',. \\-]|\\s)*$";
    /**
     * The constant RECIPE_NAME_MAX_LENGTH.
     */
    public static final int RECIPE_NAME_MAX_LENGTH = 100;
    /**
     * The constant INGREDIENT_NAME_MAX_LENGTH.
     */
    public static final int INGREDIENT_NAME_MAX_LENGTH = 100;
    /**
     * The constant DEFAULT_MAX_LENGTH.
     */
    public static final int DEFAULT_MAX_LENGTH = 255;
    /**
     * The constant INSTRUCTIONS_FREE_TEXT_PATTERN.
     */
    public static final String INSTRUCTIONS_FREE_TEXT_PATTERN = "^(?:\\p{L}\\p{M}*|[0-9]*|[\\/\\-+.,?!*();\"]|\\s)*$";


}
