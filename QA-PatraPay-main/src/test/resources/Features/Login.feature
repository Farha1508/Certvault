Feature: Login into account

  Background: Login into account with correct credentials
    Given I am on the login page
    And I enter the email and password for the "Agency" user
    When I click the "SIGN IN" button
    Then User should taken to the QBIS home page successfully

  Scenario: Creating an Application
    Given I am on the Basic Information page
    And I edit the following fields
      | Business Name              | Zip Code | First Name | Last Name | Email                                                 | Phone      |
      | testing app agent portal | 85001    | ROopali    | Sharma    | roopali.sharma+testingapp14@jellyfishtechnologies.com | 9876543210 |
    Then I click the "Continue" button
    And Verified that the Policy details page is displayed
    And I click the "Add" button
    Then Enter all the valid information to add the policy(skip optional fields)
      | Policy Type | Insurance Carrier                                 | General Agent    | Pure Premium | Minimum Earned Percentage |
      | Hull        | Houston Casualty Company (71750) - Farmington, CT | No General Agent | 2000         | 10                        |
    And I click the "Continue" button
    Then Verified that the final details page is displayed
    And Click on "No" button
    Then I edit the following fields
      | Actual Street address (No P.O. Box) | Mailing street address | Mailing city | Mailing zip |
      | st. Maple             | Bridge Road Lane-2     | Little Rock  | 72201       |
    And Select the Mailing state
    And I click the "Continue" button
    Then Verified that the Application Review page is displayed
    Given On the "Accept and Send" page click on the "View the application details"
    Then Click on the Close button
    And I click the "Continue" button
    Then  Verified that the Accept and Send page is displayed
    And Select the check-box
   Then At the payment page ,click on the send link
    Then I click the "Send Payment Link" button




