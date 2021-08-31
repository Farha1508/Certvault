Feature: Demonstrating the date picker for forms

  Scenario: Pick date from PO item
    ###---------- Login component ----------###
    Given I am on the login page
    When I enter the email and password for the "Super User"
    And I click the Sign In button
    Then I will be taken to the apps page
    ###---------- Login component ----------###
    And I click on the "Print Shop" tile
    And I click the "Add Record" button
    And I set the "Date of Customer Request" date picker to today's date
    And I wait for "5" seconds
    And I set the date in the "Date of Customer Request" date picker to
      | Month  | Day | Year |
      | August | 11  | 2020 |
    And I wait for 5 seconds

  Scenario: Pick date from add implementation
    ###---------- Login component ----------###
    Given I am on the login page
    When I enter the email and password for the "Super User"
    And I click the Sign In button
    Then I will be taken to the apps page
    ###---------- Login component ----------###
    And I click on the "Implementations" tile
    And I click the "Add Implementation" button
    And I set the "Implementation Start Date" date picker to today's date
    And I set the date in the "Customer Launch Expectation" date picker to
      | Month | Day | Year |
      | April | 1   | 2021 |
    And I wait for 5 seconds


  Scenario: Pick date from time rec - Policy Checking
    ###---------- Login component ----------###
    Given I am on the login page
    When I enter the email and password for the "Super User"
    And I click the Sign In button
    Then I will be taken to the apps page
    ###---------- Login component ----------###
    And I click on the "Work Order Tracking" tile
    And I open "Policy Checking" for company "Company 002"
    And I click on the "Time Rec Admin" link
    And I set the date in the "From" date picker to
      | Month | Day | Year |
      | May   | 1   | 2021 |
    And I set the "To" date picker to today's date
    And I click on the "Run" link

  Scenario: Pick date from Add Policy - Policy Checking
    ###---------- Login component ----------###
    Given I am on the login page
    When I enter the email and password for the "Super User"
    And I click the Sign In button
    Then I will be taken to the apps page
    ###---------- Login component ----------###
    And I click on the "Work Order Tracking" tile
    And I open "Policy Checking" for company "Company 002"
    And I click the "Add Policy" button
    And I set the date in the "Start Date Override" date picker to
      | Month | Day | Year | Time | AM / PM |
      | May   | 11  | 2018 | 6:45 | AM      |
    And I set the date in the "Due Date Override" date picker to
      | Month | Day | Year | Time | AM / PM |
      | July  | 5   | 2020 | 9:15 | PM      |
    And I wait for 5 seconds

  Scenario: Pick date from time rec - Certificate Issuance
    ###---------- Login component ----------###
    Given I am on the login page
    When I enter the email and password for the "Super User"
    And I click the Sign In button
    Then I will be taken to the apps page
    ###---------- Login component ----------###
    And I click on the "Work Order Tracking" tile
    And I open "Certificate Issuance" for company "Company 019"
    And I click on the "Time Rec Admin" link
    And I set the date in the "From" date picker to
      | Month | Day | Year | Time | AM / PM |
      | May   | 1   | 2021 | 6:27 | AM      |
    And I set the date in the "To" date picker to
      | Month | Day | Year | Time | AM / PM |
      | May   | 8   | 2021 | 9:15 | PM      |
    And I click on the "Run" link
    And I wait for 10 seconds

  Scenario: Shift date for Print Shop
    ###---------- Login component ----------###
    Given I am on the login page
    When I enter the email and password for the "Super User"
    And I click the Sign In button
    Then I will be taken to the apps page
    ###---------- Login component ----------###
    And I click on the "Print Shop" tile
    And I click the "Add Record" button
    And I set the "Date of Customer Request" date picker to 2 "years" in the "past"
      | Has Time |
      | No       |
    And I wait for 5 seconds

  Scenario: Shift date for Implementations
    ###---------- Login component ----------###
    Given I am on the login page
    When I enter the email and password for the "Super User"
    And I click the Sign In button
    Then I will be taken to the apps page
    ###---------- Login component ----------###
    And I click on the "Implementations" tile
    And I click the "Add Implementation" button
    And I set the "Implementation Start Date" date picker to today's date
    And I set the "Customer Launch Expectation" date picker to 19 "years" in the "past"
      | Has Time |
      | No       |
    And I wait for 5 seconds


  Scenario: Shift date for Time Rec Admin - Policy Checking
    ###---------- Login component ----------###
    Given I am on the login page
    When I enter the email and password for the "Super User"
    And I click the Sign In button
    Then I will be taken to the apps page
    ###---------- Login component ----------###
    And I click on the "Work Order Tracking" tile
    And I open "Policy Checking" for company "Company 002"
    And I click on the "Time Rec Admin" link
    And I set the "From" date picker to 88 "days" in the "past"
      | Has Time |
      | No       |
    And I set the "To" date picker to 1 "month" in the "past"
      | Has Time |
      | No       |
    And I click on the "Run" link

  Scenario: Shift date for Add Policy - Policy Checking
    ###---------- Login component ----------###
    Given I am on the login page
    When I enter the email and password for the "Super User"
    And I click the Sign In button
    Then I will be taken to the apps page
    ###---------- Login component ----------###
    And I click on the "Work Order Tracking" tile
    And I open "Policy Checking" for company "Company 002"
    And I click the "Add Policy" button
    And I set the "Start Date Override" date picker to 5 "days" in the "past"
      | Has Time |
      | Yes      |
    And I set the "Due Date Override" date picker to 2 "months" in the "future"
      | Has Time |
      | Yes      |

    And I wait for 10 seconds

  Scenario: Shift date for Time Rec Admin - Certificate Issuance
    ###---------- Login component ----------###
    Given I am on the login page
    When I enter the email and password for the "Super User"
    And I click the Sign In button
    Then I will be taken to the apps page
    ###---------- Login component ----------###
    And I click on the "Work Order Tracking" tile
    And I open "Certificate Issuance" for company "Company 019"
    And I click on the "Time Rec Admin" link
    And I set the "From" date picker to 2 "months" in the "past"
      | Has Time |
      | Yes      |
    And I set the "To" date picker to 28 "days" in the "future"
      | Has Time |
      | Yes      |
    And I click on the "Run" link
    And I wait for 10 seconds