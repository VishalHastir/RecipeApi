package com.recipe.api.cucumber.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class RecipeSteps {

    private Response response;
    private int recipeId; // Shared across steps
    private final String BASE_URI = "http://localhost:8000";

    @Given("the application is running")
    public void application_is_running() {
        RestAssured.baseURI = BASE_URI;
    }

    /**
     * Helper method to load payloads from the payloads folder.
     */
    private String loadPayload(String fileName) throws IOException {
        String filePath = "src/test/resources/payloads/" + fileName;
        return new String(Files.readAllBytes(Paths.get(filePath)), StandardCharsets.UTF_8);
    }

    /**
     * Helper method to replace placeholders in the payload.
     */
    private String replacePlaceholders(String payload, Map<String, String> replacements) {
        for (Map.Entry<String, String> entry : replacements.entrySet()) {
            payload = payload.replace("{{" + entry.getKey() + "}}", entry.getValue());
        }
        return payload;
    }

    @When("I create a recipe from file {string}")
    public void i_create_a_recipe_from_file(String fileName) throws IOException {
        String payload = loadPayload(fileName);
        response = given()
                .header("Content-Type", "application/json")
                .body(payload)
                .when()
                .post("/recipes")
                .then()
                .extract().response();
        if (response.getStatusCode() == 201) {
            recipeId = response.jsonPath().getInt("id");
        }
    }

    @Then("the recipe should be created successfully")
    public void the_recipe_should_be_created_successfully() {
        response.then().statusCode(201);
    }

    @Then("the response should contain a valid recipe id")
    public void the_response_should_contain_a_valid_recipe_id() {
        response.then().body("id", notNullValue());
    }

    @Given("a recipe with name {string} exists from file {string}")
    public void a_recipe_with_name_exists_from_file(String name, String fileName) throws IOException {
        // Create the recipe using the provided payload file
        i_create_a_recipe_from_file(fileName);
    }

    @When("I retrieve all recipes")
    public void i_retrieve_all_recipes() {
        response = when().get("/recipes").then().extract().response();
    }

    @Then("I should see at least one recipe in the response")
    public void i_should_see_at_least_one_recipe() {
        response.then().statusCode(200).body("size()", greaterThan(0));
    }

    @When("I retrieve the recipe by id {string}")
    public void i_retrieve_the_recipe_by_id(String idStr) {
        int idToRetrieve = idStr.equals("recipeId") ? recipeId : Integer.parseInt(idStr);
        response = when().get("/recipes/" + idToRetrieve).then().extract().response();
    }

    @Then("the response should contain a recipe with name {string}")
    public void the_response_should_contain_a_recipe_with_name(String expectedName) {
        response.then().statusCode(200).body("name", equalTo(expectedName));
    }

    @When("I update the recipe using file {string}")
    public void i_update_the_recipe_using_file(String fileName) throws IOException {
        // Load the update payload and replace the placeholder with the actual recipeId
        String payload = loadPayload(fileName);
        Map<String, String> replacements = new HashMap<>();
        replacements.put("recipeId", String.valueOf(recipeId));
        payload = replacePlaceholders(payload, replacements);
        response = given()
                .header("Content-Type", "application/json")
                .body(payload)
                .when()
                .put("/recipes")
                .then()
                .extract().response();
    }

    @Then("the recipe should be updated successfully")
    public void the_recipe_should_be_updated_successfully() {
        response.then().statusCode(200);
    }

    @When("I delete the recipe by id {string}")
    public void i_delete_the_recipe_by_id(String idStr) {
        int idToDelete = idStr.equals("recipeId") ? recipeId : Integer.parseInt(idStr);
        response = when().delete("/recipes/" + idToDelete).then().extract().response();
    }

    @Then("the recipe should be deleted successfully")
    public void the_recipe_should_be_deleted_successfully() {
        response.then().statusCode(200);
    }

    @Then("retrieving the recipe by id {string} should return a {string} error")
    public void retrieving_the_recipe_by_id_should_return_an_error(String idStr, String expectedErrorMessage) {
        int idToRetrieve = idStr.equals("recipeId") ? recipeId : Integer.parseInt(idStr);
        response = when().get("/recipes/" + idToRetrieve).then().extract().response();
        response.then().statusCode(404);
        // Uncomment the following if you want to check for an error message in the response:
        // response.then().body("message", equalTo(expectedErrorMessage));
    }

    @When("I search for recipes using file {string}")
    public void i_search_for_recipes_using_file(String fileName) throws IOException {
        String payload = loadPayload(fileName);
        response = given()
                .header("Content-Type", "application/json")
                .body(payload)
                .when()
                .post("/recipes/search")
                .then()
                .extract().response();
    }

    @Then("I should see at least one recipe in the search results")
    public void i_should_see_at_least_one_recipe_in_the_search_results() {
        response.then().statusCode(200).body("size()", greaterThan(0));
    }

    @Then("retrieving the recipe by id {string} should return a recipe with name {string}")
    public void retrieving_the_recipe_by_id_should_return_a_recipe_with_name(String idStr, String expectedName) {
        int idToRetrieve = idStr.equals("recipeId") ? recipeId : Integer.parseInt(idStr);

        response = when()
                .get("/recipes/" + idToRetrieve)
                .then()
                .extract().response();

        response.then().statusCode(200).body("name", equalTo(expectedName));
    }

}
