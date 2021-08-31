Feature: Login

  Background: Landing Page Login
    Given I am on the login page

  @5
  Scenario: Log in to the node application with a valid username and a valid password
#    When I enter the email and password for the "Super User"
    When I enter an "valid" email address and "valid" password
    And I click the "Sign in" button
    Then I will be taken to the apps page

  @6
  Scenario: Log in to the node application with an invalid username and a valid password
    When I enter an "invalid" email address and "valid" password
    And I click the "Sign in" button
    Then I see the Login Failed message

  @7
  Scenario: Log in to the node application with an valid username and an invalid password
    When I enter an "valid" email address and "invalid" password
    And I click the "Sign in" button
    Then I see the Login Failed message

  @8
  Scenario: Log in to the node application with an empty username and a valid password
    When I enter an "empty" email address and "valid" password
    And I click the "Sign in" button
    Then I see the empty "email" field warning

  @9
  Scenario: Log in to the node application with a valid username and an empty password
    When I enter an "valid" email address and "empty" password
    And I click the "Sign in" button
    Then I see the empty "password" field warning

  # C10: Verify Reset button functionality is a manually tested case.
  # Involves checking email.

  @11
  Scenario: Close the Forgot Password screen
    When I click on the "Forgot Your Password?" link
    Then I am taken to the "Reset Password" page
    When I click the "Back" button on the Reset Password page
    Then I am taken to the "Please sign in" page

  @2470
  Scenario: Log out of the node application
    When I enter the email and password for the "Super User"
    And I click the "Sign in" button
    Then I will be taken to the apps page
    When I click the user icon
    And I click the Logout button
    Then I am redirected to login page

  @9502
  Scenario: Verifying username and user roles
    When I enter the email and password for the "Super User"
    And I click the "Sign in" button
    Then I will be taken to the apps page
    When I click the user icon
    Then The following roles exist
      | Printshop User |
      | Administrator  |
      | Expense User   |
      | Node Admin     |


# This case is in Select Company Service section. May be moved to a new feature file later.
  @9489
  Scenario: Login to node application With InvalidUserName and ValidPassword.
    When I enter the email and password for the "Super User"
    And I click the "Sign in" button
    Then I will be taken to the apps page
    When I click on the "Work Order Tracking" tile
    Then I will be taken to the homepage for that app
    When I open "Certificate Issuance" for company "Company 019"

### These scenarios are not present in the Certificate Issuance test case bank
#  @
#  Scenario: Log in to the node application with an invalid username and an invalid password
#    When I enter an "invalid" email address and "invalid" password
#    And I click the "Sign in" button
#    Then I see the Login Failed message
#
#  @
#  Scenario: Log in to the node application with an empty username and an empty password
#    When I enter an "empty" email address and "empty" password
#    And I click the "Sign in" button
#    Then I see the empty "email" field warning
#
#  @
#  Scenario: Log in to the node application with an incorrect (missing @) username and a valid password
#    When I enter an "incorrect" email address and "valid" password
#    And I click the "Sign in" button
#    Then I see the email error message
