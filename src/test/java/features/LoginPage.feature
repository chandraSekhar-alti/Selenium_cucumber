Feature: User management

  Scenario Outline: Check login functionality
    Given I am on login page
    When I enter "<username>" and "<password>"
    And I click the login button
    Then I should be logged in with welcome message "<welcome_message>"

    Examples:
      | username          | password  | welcome_message    |
      | jhondon@gmail.com | Test1234@ | Welcome, Jhon Don! |
