Feature: User management

  Scenario Outline: Validating the login functionality
    Given I am on login page
    When I enter "<username>" and "<password>"
    And I click the login button
    Then I should be logged in with welcome message "<welcome_message>"

    Examples:
      | username           | password  | welcome_message    |
      | jhondon@gmail.com  | Test1234@ | Welcome, Jhon Don! |
      | alex.ley@gmail.com | Alex0123* | Welcome, Alex ley! |

    Scenario Outline: Validating invalid password functionality
      Given I am on login page
      When I enter "<username>" and "<password>"
      And I click the login button
      Then I should get the error popup message

      Examples:
        | username           | password  |
        | alex.ley@gmail.com | Test1234@ |