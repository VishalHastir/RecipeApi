Feature: Recipe Management
  As a user of the Recipe Application,
  I want to create, retrieve, update, delete, and search for recipes
  So that I can manage recipes efficiently.

  Background:
    Given the application is running

  Scenario: Create a new recipe
    When I create a recipe from file "recipe-create.json"
    Then the recipe should be created successfully
    And the response should contain a valid recipe id

  Scenario: Retrieve all recipes
    Given a recipe with name "Pancakes" exists from file "recipe-create.json"
    When I retrieve all recipes
    Then I should see at least one recipe in the response

  Scenario: Retrieve a recipe by id
    Given a recipe with name "Pancakes" exists from file "recipe-create.json"
    When I retrieve the recipe by id "recipeId"
    Then the response should contain a recipe with name "Pancakes"

  Scenario: Update an existing recipe
    Given a recipe with name "Pancakes" exists from file "recipe-create.json"
    When I update the recipe using file "recipe-update.json"
    Then the recipe should be updated successfully
    And retrieving the recipe by id "recipeId" should return a recipe with name "Blueberry Pancakes"

  Scenario: Delete a recipe
    Given a recipe with name "Blueberry Pancakes" exists from file "recipe-create.json"
    When I delete the recipe by id "recipeId"
    Then the recipe should be deleted successfully
    And retrieving the recipe by id "recipeId" should return a "recipe not found" error

  Scenario: Search recipes
    Given a recipe with name "Pancakes" exists from file "recipe-create.json"
    When I search for recipes using file "recipe-search.json"
    Then I should see at least one recipe in the search results
