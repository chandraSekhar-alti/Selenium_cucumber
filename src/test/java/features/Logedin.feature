Feature:
  Scenario: validate the login functionality
    Given user navigates to the login page
    When the user enters valid login credentials
    Then the user should be redirected to the homepage