Feature: Add Policy

  Background: Login to Node and Navigating to PC home page
    Given I am on the login page
    When I enter the email and password for the "super user"
    And I click the Sign In button
    Then I will be taken to the apps page
    And I click on the "Work Order Tracking" tile
    Then I will be taken to the homepage for that app
    When I open "Certificate Issuance" for company "Company 019"

  @1365
  Scenario: Verify Add Work Order functionality with Submit and open button
    When I click the "Add Work Order" button
    Then The "Add Work Order" modal "is" displayed
    And The following elements exist
      | Work Order Entry      |
      | Work Order Details    |
      | Original Email        |
      | Delivery Instructions |
      | Second QA             |
    When I click the "Submit and Open" button
    Then The "Add Work Order" modal "is not" displayed
    And I enter the following information into the form
      | Requestor | Test@patracorp.com |

  @13 #@1545 @9503 @1377
  Scenario: Verifying Add Work Order functionality
    When I click the "Add Work Order" button
    And I enter the following information into the form
      | Requestor | Test@patracorp.com |
      | Sent To   | sentto@patracorp   |
      | Summary   | <current date>     |
    And I click on the "Work Order Details" link
    And I enter the following information into the form
      | # Of Certs           | 15                 |
      | Client Code          | TEST code1         |
      | Client Name          | TEST Rami          |
      | Work Order Type      | New Business       |
      | Branch Office        | Albany             |
      | Department           | 201                |
      | Division             | 100                |
      | Profit Center        | Test               |
      | Cert Master          | Test               |
      | Cert Issuance System | Test               |
      | # of Holders Issued  | 5                  |
      | # Holders Dated Off  | 2                  |
      | # Holders Updated    | 2                  |
      | Cert Issued By       | Anusha E.          |
      | QA By                | Chitti Raju        |
      | Delivery By          | Boddeda Vanaja     |
      | Complexity           | Easy               |
      | Reasons for Email    | Incomplete Request |
    And I click on the "Original Email" link
    And I enter the following information into the form
      | Original Email | This is some test text. |
    And I click the "Submit" button
    And I get the new work order number from the confirmation modal
    And I search the stored work order number and wait up to 20 seconds
    And I click on the top work order link
    Then The record is added
      | Requestor |
      | Sent To   |
      | Summary   |
    Then The record is added
      | # Of Certs           |
      | Client Code          |
      | Client Name          |
      | Work Order Type      |
      | Branch Office        |
      | Department           |
      | Division             |
      | Profit Center        |
      | Cert Master          |
      | Cert Issuance System |
      | # of Holders Issued  |
      | # Holders Dated Off  |
      | # Holders Updated    |
      | Cert Issued By       |
      | QA By                |
      | Delivery By          |
      | Complexity           |
      | Reasons for Email    |
    When I click on the "Original Email" link
    Then the Original Email contents are "This is some test text."

  @3062 @3063
  Scenario: Verify Add Work Order Submit Button functionality without entering mandatory fields
    Given I click the "Add Work Order" button
    And I click on the "Work Order Details" link
    And I select "Renewal" from the "Work Order Type" drop down
    When I click the "Submit" button
    Then I see error warnings
    And The "Add Work Order" modal "is" displayed
    When I click the "Submit and Open" button
    Then I see error warnings
    And The "Add Work Order" modal "is" displayed

  @7422
  Scenario: Verifying the cancel button Functionality
    Given I click the "Add Work Order" button
    Then The "Add Work Order" modal "is" displayed
    When I click the "Cancel" button
    Then The "Add Work Order" modal "is not" displayed

  @9811
  Scenario: Verify Add Bulk work order functionality
    When I click the "Add Work Order" button
    And I enter "Bulk" in the "Summary" field
    When I click the "Submit and Open" button
    Then The "Rush/ Bulk :" label is "Bulk"

  @9812
  Scenario: Verify Add Rush work order functionality
    When I click the "Add Work Order" button
    And I enter "Rush" in the "Summary" field
    When I click the "Submit and Open" button
    Then The "Rush/ Bulk :" label is "Rush"

  @11338
  Scenario: Verify From Pending Status in work order
    When I click the "Add Work Order" button
    And I enter "From Pending" in the "Summary" field
    And I click the "Submit and Open" button
    Then The "From Pending :" label is "Yes"

  @14112
  Scenario: Verify "Discard" check box functionality on Add Work Order page
    When I click the "Add Work Order" button
    Then The value of the "Folder" dropdown is "Certificate Requests"
    And The value of the "Status" dropdown is "Available"
    When I select the "Discard" checkbox
    # An alert modal will be displayed
    And If the confirmation modal displays, dismiss it
    Then The value of the "Folder" dropdown is "Discarded Work Orders"
    And The value of the "Status" dropdown is "Discarded"
    When I deselect the "Discard" checkbox
    Then The value of the "Folder" dropdown is "Certificate Requests"
    And The value of the "Status" dropdown is "Available"

  @3061
  Scenario: Delivery instructions tab
    When I click the "Add Work Order" button
    And I click on the "Delivery Instructions" link
    And I enter 1100 characters into the "Comments" field
    Then There will be 1000 characters in the "Comments" field

  @3064
  Scenario: Second QA tab
    When I click the "Add Work Order" button
    And I click on the "Second QA" link
    Then The following elements exist
      | 2nd QA Needed  |
      | 2nd QA Done    |
      | 2nd QA Done By |
    When I select the "2nd QA Needed" checkbox
    Then The "2nd QA Needed" checkbox "is" selected
    When I select the "2nd QA Done" checkbox
    Then The "2nd QA Done" checkbox "is" selected
    When I select "Carl Wahlstrom" from the "2nd QA Done By" drop down
    Then The value of the "2nd QA Done By" dropdown is "Carl Wahlstrom"
    When I click the "Submit and Open" button
    Then The "Add Work Order" modal "is not" displayed