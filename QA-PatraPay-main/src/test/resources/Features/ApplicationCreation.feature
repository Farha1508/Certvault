Feature: Application Creation

  @10040
  Scenario: Basic information page happy path- Step 1st
    Given I am on the login page
    When I enter the email and password for the "DirectPay_Agency" user
    And I click the Sign In button
    And I open the New Application form
    And I enter "Test Business Name" in the "Business Name" field
    And I enter "98666" in the "Zip Code" field
    And I enter "TestFirst" in the "First Name" field
    And I enter "TestLast" in the "Last Name" field
    And I enter "roopali.sharma+2505445@jellyfishtechnologies.com" in the "Email" field
    And I enter "5555555555" in the "Phone" field
    And I click the "Continue" button
    And I wait for 5 seconds
    And Ensure I am taken to the Pick Your Product page
    And I click "Full Pay Insurance Payments" checkbox
    And I click the "Continue" button
    And Verified that the Policy details page is displayed
    And I click the "Add" button
    #And I select "Endorsement" from the "What type of transaction is this?" drop down
    #Then I edit the following fields
      #| Select policy type for the payment to be made| Enter amount of pure premium for this policy | Enter amount of broker fee (if applicable) (optional)| Enter notes for this transaction (optional)|
    #  | Hull             | 2000    | 1  | Testing purpose transaction notes|
    And I select "Agent/Broker Fee" from the "What type of transaction is this?" drop down
    Then I edit the following fields
      | Select policy type for the payment to be made| Enter amount of broker fee| Enter notes for this transaction (optional)|
      | Hull             | 2000 | Testing purpose transaction notes|
    And Verify the policy block display name and value
    And I click the "Continue" button
    And I wait for 5 seconds
    Then Verified that the final details page is displayed
    And I enter "St. vence" in the "Actual Street address (No P.O. Box)" field
    And I enter "Lane-02" in the "Apartment, suite, unit, building (optional)" field
    And I click the "Continue" button
    Then Verified that the Application Review page is displayed
    And I click on the "view the application details" link
    And I click the "Close" button
    And I click the "Continue" button
    Then  Verified that the Accept and Send page is displayed
    And I click the "Send Link" button
    And I click the "Send Payment Link" button
    And I wait for 5 seconds



