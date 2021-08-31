Feature: Grid Row Colours

  Background: Sign In
    Given I am on the login page
    When I enter the email and password for the "super user"
    And I click the "Sign in" button
    Then I will be taken to the apps page
    And I click on the "Work Order Tracking" tile
    Then I will be taken to the homepage for that app
    When I open "Certificate Issuance" for company "Company 019"
    And I navigate to the "In Process" tab

  @14223
  Scenario: Yellow Row
    When I click the "Add Work Order" button
    And I click the "Submit and Open" button
    And I store the work order number
    And I click the "Mark Rush" button
    And I set the "Due Date Override" to 230 minutes after "Add Date:"
    And I click the "Submit" button
    And I click the "Close" button
    And I search the stored work order number
    Then The top row background colour is "Yellow"

  @14224
  Scenario: Green Row
    When I click the "Add Work Order" button
    And I click the "Submit and Open" button
    And I store the work order number
    And I click the "Mark Bulk" button
    And I click the "Submit" button
    And I click the "Close" button
    And I search the stored work order number
    Then The top row background colour is "Green"

  @14225
  Scenario: Orange Row
    When I click the "Add Work Order" button
    And I click the "Submit and Open" button
    And I store the work order number
    And I click the "Mark Bulk" button
    And I click the "Mark Rush" button
    And I click the "Submit" button
    And I click the "Close" button
    And I search the stored work order number and wait up to 20 seconds
    Then The top row background colour is "Orange"

  @14226
  Scenario: Pink Row
    When I click the "Add Work Order" button
    And I click the "Submit and Open" button
    And I store the work order number
    And I set the "Due Date Override" to 300 minutes after "Add Date:"
    And I click the "Submit" button
    And I click the "Close" button
    And I search the stored work order number
    Then The top row background colour is "Pink"

  @14227
  Scenario: Dark Pink Row
    When I click the "Add Work Order" button
    And I click the "Submit and Open" button
    And I store the work order number
    And I set the "Due Date Override" to 50 minutes after "Add Date:"
    And I click the "Submit" button
    And I click the "Close" button
    And I search the stored work order number
    Then The top row background colour is "Dark Pink"

  @14228
  Scenario: Purple Row
    When I click the "Add Work Order" button
    And I click the "Submit and Open" button
    And I store the work order number
    And I set the "Due Date Override" to 20 minutes after "Add Date:"
    And I click the "Submit" button
    And I click the "Close" button
    And I search the stored work order number
    Then The top row background colour is "Purple"

  @14229
  Scenario: Red Row
    When I click the "Add Work Order" button
    And I click the "Submit and Open" button
    And I store the work order number
    And I set the "Due Date Override" to -20 minutes after "Add Date:"
    And I click the "Submit" button
    And I click the "Close" button
    And I search the stored work order number
    Then The top row background colour is "Red"

  @14230
  Scenario: Blue Row
    When I click the "Add Work Order" button
    And I click the "Submit and Open" button
    And I store the work order number
    And I enter "Cancel" in the "Summary" field
    And I click the "Submit" button
    And I click the "Close" button
    And I search the stored work order number
    Then The top row background colour is "Blue"