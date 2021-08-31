Feature: Automating Test Case C10092

  Scenario: Policy type field validation
    Given I am on the login page
    And I enter the email and password for the "Agency" user
    When I click the "SIGN IN" button
    Then User should taken to the QBIS home page successfully
    Given I am on the Basic Information page
    And I edit the following fields
      | Business Name              | Zip Code | First Name | Last Name | Email                                                 | Phone      |
      | testing app agent portal | 85001    | ROopali    | Sharma    | roopali.sharma+testingapp14@jellyfishtechnologies.com | 9876543210 |
    Then I click the "Continue" button
    And Verified that the Policy details page is displayed
    And I click the "Add" button
  #  And I enter "      " in the "Select policy type for the payment to be made" field
    Then A proper validation error message should be displayed for the blank field
    And I enter "hull" in the "Select policy type to be included in payment agreement" field
    Then A proper validation error message should be display
    And I enter "12#$" in the "Select policy type to be included in payment agreement" field
    Then A proper validation error message should be display
    And I enter "Z" in the "Select policy type to be included in payment agreement" field
    Then A proper validation error message should be display