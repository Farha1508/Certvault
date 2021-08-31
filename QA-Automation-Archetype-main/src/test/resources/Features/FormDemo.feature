Feature: Demonstration of steps and methods for filling out form items

  These scenarios will show the use of the methods to fill out text fields, drop down lists, text areas and date pickers

  Background: Print Shop Login
    ###---------- Login component ----------###
    Given I am on the login page
    When I enter the email and password for the "Super User"
    And I click the Sign In button
    Then I will be taken to the apps page
    ###---------- Login component ----------###
    ###---------- App selection ----------###
    When I click on the "Print Shop" tile
    Then I will be taken to the homepage for that app
    ###---------- App selection ----------###

  Scenario: Fill form with individual steps
    ###---------- Fill Form ----------###
    And I click the "Add Record" button
    And I select "Company 748" from the "Company" drop down
    And I select "Policy Checking" from the "Service" drop down
    And I enter "Text field test <current date>" in the "Client Code" field
    And I set the date in the "Date of Customer Request" date picker to
      | Month | Day | Year |
      | May   | 9   | 2021 |
    And I set the "Mail Date" date picker to today's date
    And I enter "Text area test <current date>" in the "Notes" text box
    And I wait for 5 seconds

  Scenario: Fill in text, drop down, and text area with single data table
    And I click the "Add Record" button
    And I enter the following information into the form
      | Company     | Company 748                    |
      | Service     | Policy Checking                |
      | Client Code | Text field test <current date> |
      | Notes       | Text area test <current date>  |
    And I set the date in the "Date of Customer Request" date picker to
      | Month | Day | Year |
      | May   | 9   | 2021 |
    And I wait for 5 seconds

  Scenario: Fill in text, drop down, and text area with separate data tables
    And I click the "Add Record" button
    And I edit the following fields
      | Associated WO # | Client Code                    |
      | 12345           | Text field test <current date> |
    And I edit the following drop downs
      | Company     | Folder        |
      | Company 748 | To Be Printed |
    And I edit the following text areas
      | Notes                         |
      | Text area test <current date> |
    And I set the date in the "Date of Customer Request" date picker to
      | Month | Day | Year |
      | May   | 9   | 2021 |
    And I wait for 5 seconds

  Scenario: Certificate Issuance - Add Attachment
    And I clear the valueStore
    Given I am on the login page
    When I enter the email and password for the "Super User"
    And I click the Sign In button
    And I click on the "Work Order Tracking" tile
    And I open "Certificate Issuance" for company "Company 748"
    Then The page for the selected company and service will be displayed
    When I click the "Add Work Order" button
    And I enter "Attachments Test <current date>" in the "Summary" field
    And I click the "Submit and Open" button
    And I upload an attachment
    Then The file will be displayed in the Attachments grid
    And I wait for 5 seconds

  Scenario: EB Eligibility - Add Attachment
    And I clear the valueStore
    Given I am on the login page
    When I enter the email and password for the "Super User"
    And I click the Sign In button
    And I click on the "Work Order Tracking" tile
    And I open "EB - Eligibility" for company "Company 748"
    And I click the "Add Work Order" button
    And I enter the following information into the form
      | Assign To         | Ian Maclachlan |
      | Processor         | Ian Maclachlan |
      | Request Type      | Electronic     |
      | # of Transactions | 999            |

    And I click on the "Work Order Details" link
    And I enter the following information into the form
      | Employee Name   | TEST PLEASE IGNORE              |
      | Work Order Type | QA                              |
      | Comments        | Attachments Test <current date> |

    And I click the "Submit and Open" button
    And I upload an attachment
    Then The file will be displayed in the Attachments grid
    And I wait for 5 seconds