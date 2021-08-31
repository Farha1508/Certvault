Feature: Edit Work Orders Actions

  Background: Sign In
    Given I am on the login page
    When I enter the email and password for the "super user"
    And I click the "Sign in" button
    Then I will be taken to the apps page
    And I click on the "Work Order Tracking" tile
    Then I will be taken to the homepage for that app
    When I open "Certificate Issuance" for company "Company 019"

  @700
  Scenario: Verify Print PDF Button Functionality
    When I navigate to the "In Process" tab
    When I click on the top work order link
    And I click the "Print PDF" button
    And I switch to new tab opened
    Then The "Certificate Report" page is displayed

  @701 @703 @16
  Scenario: Rush Button
    When I click the "Add Work Order" button
    When I click the "Submit and Open" button
    Then The "Rush/ Bulk :" label is "Normal"

    #701
    When I click the "Mark Rush" button
    And I click the "Submit" button
    Then The update alert is displayed
    And The "Rush/ Bulk :" label is "Rush"

    #703
    When I click the "Remove Rush" button
    Then The "Rush/ Bulk :" label is "Normal"
    When I click the "Submit" button
    Then The update alert is displayed
    Then The "Rush/ Bulk :" label is "Normal"

    # C16
    When I click the "Mark Rush" button
    Then The "Rush/ Bulk :" label is "Rush"
    When I click the "Cancel Changes" button
    Then The "Rush/ Bulk :" label is "Normal"

  @702 @1370
  Scenario: Bulk Button
    When I click the "Add Work Order" button
    When I click the "Submit and Open" button
    Then The "Rush/ Bulk :" label is "Normal"

    #702
    When I click the "Mark Bulk" button
    And I click the "Submit" button
    Then The update alert is displayed
    And The "Rush/ Bulk :" label is "Bulk"

    #1370
    When I click the "Remove Bulk" button
    Then The "Rush/ Bulk :" label is "Normal"
    When I click the "Submit" button
    Then The update alert is displayed
    Then The "Rush/ Bulk :" label is "Normal"

  @9778 @9779
  Scenario: Rush Button user role
    Given I click the "Add Work Order" button
    And I click the "Submit and Open" button
    And I click the "Mark Bulk" button
    And I click the "Mark Rush" button
    And I click the "Submit" button
    And I store the work order number

    And I click the user icon
    And I click the Logout button
    And I enter the email and password for the "base user"
    And I click the "Sign in" button
    And I click on the "Work Order Tracking" tile
    And I open "Certificate Issuance" for company "Company 019"
    And If the "Timer Alert" modal is displayed, dismiss it

    When I search the stored work order number
    And I click on the top work order link
    Then The following elements do not exist
      | Remove Bulk |
      | Remove Rush |

  @14074
  Scenario: Verify "Discard" check box functionality on detail page
    When I click the "Add Work Order" button
    And I click the "Submit and Open" button
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