Feature: Demonstrating how to retrieve emails from Gmail

  Scenario: Retrieving link from owner creation email
    Given I am on the login page
    When I enter the email and password for the "Carrier"
    And I click the Sign In button
    And I select the "Owners" option from the hamburger menu
    And I verify the Owners page is displayed
    And I open the Add Owner form
    And I enter the following information into the form
      | Default Commission %                                                                    | 23                       |
      | Producer Code                                                                           | 123456                   |
      | Unique Short Name                                                                       | Test Business - <random> |
      | Email address for receipt of notices from Platform                                      | <gmail>+<random>         |
      | Email address for receipt of New Policy notices from Platform                           | <gmail>+<random>         |
      | Business Name                                                                           | Test Business - <random> |
      | Federal Employment Identification Number (FEIN) or Taxpayer Identification Number (TIN) | 123456789                |
      | Street Address                                                                          | Test street              |
      | City                                                                                    | Phoenix                  |
      | State                                                                                   | Arizona                  |
      | Zip Code                                                                                | 85001                    |
      | Owner Admin First Name                                                                  | Testy                    |
      | Owner Admin Last Name                                                                   | McTest                   |
      | Owner Admin Phone number                                                                | 5552223333               |
      | Owner Admin Email                                                                       | <gmail>+<random>         |
    And I add a signature
    And I click the "Submit Form" button
    And I verify the new owner is added
    And I verify the email for "Owner Creation" was received
    And I open the link from the email
    And I wait for 5 seconds