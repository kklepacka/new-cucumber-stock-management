@Everything @Feature
Feature: Stock Management To Check Duplicate Execution With Tagged Products

  Background:
    Given I've 2 products
    And I add 3 additional products

    @Everything @Scenario @FeatureChild @FirstScenario
    Scenario: Current stock duplicate
      When I count everything I have in stock
      Then I've at least 5 products in stock

    @Everything @Scenario @FeatureChild @SecondScenario
    Scenario: Error in stock duplicate
      When I count everything I have in stock
      Then I've at least 9 products in stock

    @Everything @ScenarioOutline @FeatureChild
    Scenario Outline: New products duplicate
      Given I need to add some <product>
      And I know how much I have
      When I add it to the stock
      Then I should have more than the minimum needed

      @Ladder_product
      Examples:
      | product |
      | "Ladder" |
 
      @Chest_product
      Examples:
      | product |
      | "Chest" |

      @Table_product
      Examples:
      | product |
      | "Table" |
