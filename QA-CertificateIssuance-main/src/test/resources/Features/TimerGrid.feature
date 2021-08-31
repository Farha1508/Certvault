Feature: Timer Grid Tests

  Background: Sign In
    Given I am on the login page
    When I enter the email and password for the "super user"
    And I click the "Sign in" button
    Then I will be taken to the apps page
    And I click on the "Work Order Tracking" tile
    Then I will be taken to the homepage for that app
    When I open "Certificate Issuance" for company "Company 019"
    And I click on the "Time Rec Open" link
    And set the tab to "timerecopen"

    # C10568	Verify reset functionality
  #C10569	Grid - Paging Functionality
  #C10570	Grid - View dropdown Functionality
  #C10571	Grid -Verify WO # Column Search Field
  #C10572	Grid -Verify Employee Search column
  #C10573	Grid -Verify End Time Date column functionality
  #C10574	Grid -Verify Company Dropdown functionality
  #C10575	Grid - Verify Service Dropdown functionality
  #C10576	Grid - Verify Task Description dropdown functionality
  #C10577	Grid - Verify Manager dropdown functionality
  #C10578	Grid - Verify Hours Field functionality
  #C10579	Verify the sorting functionality for entire grid
  #C10580	Grid - Verify Team Lead dropdown functionality
  #C10581	Grid -Verify Sr. Team Lead dropdown functionality


  ###Timer Record Open /Admin Grids Validations#####

  @10569
  Scenario: Verify Paging Functionality
    And I move to the next page in the grid
    And I move to the previous page in the grid

  @10568
  Scenario: Verify reset functionality
    When I enter "9999999999" into the "WO #" grid header
    Then The "WO #" grid header is "9999999999"
    And I click the Reset button in the grid header
    Then The "WO #" grid header is ""

  @2433
  Scenario: Verify Worknumber search field in Time Record Open Page Grid
    When I sort the "WO #" grid column by "descending"
    When I get the "WO #" for "row 2" of the grid
    And I enter that information into the grid header
    Then The information in the first row of the grid will match what was entered

  @2434
  Scenario: Verify Employee column in Time Record Open Page Grid
    When I get the "Employee" for "row 5" of the grid
    And I enter that information into the grid header
    Then The information in the first row of the grid will match what was entered

  @10577
  Scenario: Verify Manager column in Time Record Open Page Grid
    And I click on the "Time Rec Open" link
    When I select "Srinivas Korukonda" from the "Manager" header in the grid

    And I select that information from the grid header
    And I click on the "Time Rec Admin" link
    And I set the "From" date picker to 1 month ago
    And I set the "To" date picker to today's date
    And I click on the "Run" link
    And I wait for the grid to be filled
    When I select "Test Test" from the "Manager" header in the grid
    And I select that information from the grid header

  @2445
  Scenario: Verify Team Lead column in Time Record Open Page Grid
    When I sort the "Team Lead" grid column by "descending"
    And I get the "Team Lead" for "row 2" of the grid
    And I select that information from the grid header
    Then The information in the first row of the grid will match what was entered

  @2446
  Scenario: Verify Sr Team Lead column in Time Record Open Page Grid
    When I sort the "Sr. Team Lead" grid column by "descending"
    And I get the "Sr. Team Lead" for "row 2" of the grid
    And I select that information from the grid header
    Then The information in the first row of the grid will match what was entered

  @2435
  Scenario: Verify Paging Functionality Date
    # Test case can fail due to wot-pc#297
    When I get the "Start Time" for "row 5" of the grid
    And I set that date in the header
    Then The information in the first row of the grid will match what was entered

  @2591
  Scenario: Verify Time Records in Time Records Page
    And I click on the "Time Records" link
    And set the tab to "timerecord"
    And The following headers are present
      | Company          |
      | Service          |
      | Task Description |
      | WO #             |
      | Employee         |
      | Start Time       |
      | End Time         |
      | Duration         |
      | Billable         |

  @10574
  Scenario: Verify Company dropdown in Timer grids
    And I click on the "Time Rec Admin" link
    And set the tab to "timerecadmin"
    And I set the "From" date picker to 1 month ago
    And I set the "To" date picker to today's date
    And I click on the "Run" link
    And I wait for the grid to be filled
    When I get the "Company" for "row 4" of the grid
    And I select that information from the grid header
    Then The information in the first row of the grid will match what was entered

  @10575
  Scenario: Verify Service dropdown in timer grids
    When I get the "Service" for "row 3" of the grid
    And I select that information from the grid header
    Then The information in the first row of the grid will match what was entered

    When I click on the "Time Rec Admin" link
    And set the tab to "timerecadmin"
    And I set the "From" date picker to 1 month ago
    And I set the "To" date picker to today's date
    And I click on the "Run" link
    And I wait for the grid to be filled
    When I get the "Service" for "row 3" of the grid
    And I select that information from the grid header
    Then The information in the first row of the grid will match what was entered


  @10576
  Scenario: Verify  Task Description column in timer grids
    When I get the "Task Description" for "row 4" of the grid
    And I select that information from the grid header
    Then The information in the first row of the grid will match what was entered

    When I click on the "Time Rec Admin" link
    And set the tab to "timerecadmin"
    And I set the "From" date picker to 1 month ago
    And I set the "To" date picker to today's date
    And I click on the "Run" link
    And I wait for the grid to be filled
    When I get the "Task Description" for "row 4" of the grid
    And I select that information from the grid header
    Then The information in the first row of the grid will match what was entered

    #   @5476
    #  Scenario: Verify the Time Records count in TimeRecordOpen page
    #
    #  @5509
    #  Scenario: Search columns in Time records admin and Time record open page