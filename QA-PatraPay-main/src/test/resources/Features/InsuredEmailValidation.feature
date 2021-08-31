Feature: Validation Testing at step-8th

  Scenario: Insured Email validation to send payment link
    Given I am on the login page
    And I enter the email and password for the "Agency" user
    When I click the "SIGN IN" button
    Then User should taken to the QBIS home page successfully
    Then Open the application by  hamburger menu
    And Go to send payment link pop-up
    When Removing insured email id from the email box
    Then A proper validation error message should be displayed
    And Enter a invalid email id
    Then A error message should be displayed
    When click on cancel button

