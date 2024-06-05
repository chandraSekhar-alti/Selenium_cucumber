Feature: Create new user to the application
  Scenario: Creating a new user
    Given I am on login page
    When I click on the create an account button
    And I fill out the registration form with valid details
    And I submit the registration form
    Then I should be registered as a new user