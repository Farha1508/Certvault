Feature: Handling cross browser testing

  Scenario Outline: Accessing multiple browsers

    Given I am using the "<browsers>" browser
    Given I am on the login page
    And I enter the email and password for the "Agency" user
    When I click the "SIGN IN" button
    Then User should taken to the QBIS home page successfully

    Examples:
      | browsers |
      | chrome|
      |firefox |
      |edge|