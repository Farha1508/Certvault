Feature: Time Tracking Tests

  Background: Sign In
    Given I am on the login page
    When I enter the email and password for the "super user"
    And I click the "Sign in" button
    Then I will be taken to the apps page
    And I click on the "Work Order Tracking" tile
    Then I will be taken to the homepage for that app
    When I open "Certificate Issuance" for company "Company 019"

      # C13039	Timer alert for User base
  #C13040	Timer alert for Team lead base
  #C13041	Timer alert for Senior Team lead base
  #C13042	Timer alert for Manager base role
  #C13043	Timer alert for Manager base role with additional user base role

  @9436
  Scenario: Verifying Timer stop button Presence
    Then The stop button "is not" displayed

  @9435
  Scenario: Verify Time tracking Close(X) button functionality
    When I click the "Start" button
    Then The "Time Tracking" modal "is" displayed
    When I click the "X" button
    Then The "Time Tracking" modal "is not" displayed
    Then The stop button "is not" displayed

  @9437
  Scenario: Verify Start time functionality without enter any data
    When I click the "Start" button
    Then The "Time Tracking" modal "is" displayed
    When I click the "Start" button in the time tracking modal
    Then The "Time Tracking" modal "is" displayed
    And The "Task:" has a red outline

  @9434
  Scenario: Verifying Clear button in Time Tracking
    When I click the "Start" button
    Then The "Time Tracking" modal "is" displayed
    When I select "QA" from the "Task:" drop down
    Then The value of the "Task:" dropdown is "QA"
    When I click the "Clear" button
    Then The value of the "Task:" dropdown is "Select Task"
    And The "Time Tracking" modal "is" displayed

  @9433
  Scenario: Verifying Timer Functionality with Creating Work Order
    Given I click the "Add Work Order" button
    When I click the "Submit" button
    And I get the new work order number from the confirmation modal
    And I search the stored work order number

    And I click the "Start" button
    And I select "QA" from the "Task:" drop down
    And I enter the work order into the work order field
    When I click the "Start" button in the time tracking modal
    Then The "Time Tracking" modal "is not" displayed

    When I click on the "Time Rec Open" link
    Then the timer is for "Company 019"
    # And the open timer for the current user is "QA"
    When I click the "Stop" button

  @1840
  Scenario: Verifying Edit functionality in Time Rec Open
    When I click on the "Time Rec Open" link
    And I enter "Carl" into the "Employee" grid header
    And I click the edit icon for the top timer row
    Then The "Time Record Detail" modal "is" displayed

    When I enter "Training" into the "Task" field in the Time Record Detail modal
    And I click the "Submit" button
    Then The "Time Record Detail" modal "is not" displayed
    And The top timer row "Task Description" value is "Training"

    When I click the edit icon for the top timer row
    And I enter "Creating WO" into the "Task" field in the Time Record Detail modal
    And I click the "Submit" button
    Then The top timer row "Task Description" value is "Creating WO"

  @9311
  Scenario: Verify Edit timer Cancel button functioality.
    When I click on the "Time Rec Open" link
    And I enter "Carl" into the "Employee" grid header
    When I click the edit icon for the top timer row
    And I enter "Creating WO" into the "Task" field in the Time Record Detail modal
    And I click the "Submit" button
    Then The top timer row "Task Description" value is "Creating WO"

    When I click the edit icon for the top timer row
    When I enter "Training" into the "Task" field in the Time Record Detail modal
    And I click the "Cancel" button
    Then The "Time Record Detail" modal "is not" displayed
    And The top timer row "Task Description" value is "Creating WO"

#C9438
      ##########Time Record Admin Page/Time records page Test Scenarios###########

