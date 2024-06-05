Feature: Login page

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

  Scenario Outline: validating the invalid account name functionality
    Given I am on login page
    When I enter invalid "<username>" and valid "<password>"
    And I click the login button
    Then I should get the invalid username error popup message
    Examples:
      | username            | password  |
      | demo.fake@gmail.com | Test1234@ |

  Scenario Outline: validating numbers in email field
    Given I am on login page
    When I enter invalid "<username>" and valid "<password>"
    And I click the login button
    Then I should get the Email error popup message saying "Please enter a valid email address (Ex: johndoe@domain.com)."
    Examples:
      | username | password  |
      | 12345    | Test1234@ |

  Scenario Outline: validating the error message for empty login details
    Given I am on login page
    When I click on the create an account button
    When I enter invalid "<username>" and valid "<password>"
    And I click the login button
    Then I should get an error popup message saying "This is a required field."
    Examples:
      | username | password |
      |          |          |