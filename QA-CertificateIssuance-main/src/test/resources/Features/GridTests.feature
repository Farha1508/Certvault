Feature: Grid Test Cases
  # Before running this suite, make sure there are enough records in each table for proper testing.
  Background: Sign In
    Given I am on the login page
    When I enter the email and password for the "super user"
    And I click the "Sign in" button
    Then I will be taken to the apps page
    And I click on the "Work Order Tracking" tile
    Then I will be taken to the homepage for that app
    When I open "Certificate Issuance" for company "Company 019"

  @1053
  Scenario: Verify Paging Functionality
    When I navigate to the "In Process" tab
    And I move to the next page in the grid
    And I move to the previous page in the grid

  @1054
  Scenario: Verify View dropdown Functionality in PC Grids
    Given I navigate to the "In Process" tab
    When I select the "20" option from the the Viewing drop down
    Then The number of rows displayed will be less than or equal to the number selected
    When I select the "50" option from the the Viewing drop down
    Then The number of rows displayed will be less than or equal to the number selected
    When I select the "100" option from the the Viewing drop down
    Then The number of rows displayed will be less than or equal to the number selected
    When I select the "10" option from the the Viewing drop down
    Then The number of rows displayed will be less than or equal to the number selected

  @1055
  Scenario: Verify Reset Functionality
    When I enter "9999999999" into the "WO #" grid header
    Then The "WO #" grid header is "9999999999"
    And I click the Reset button in the grid header
    Then The "WO #" grid header is ""

  @1073
  Scenario: Grid - Row Colorization
    Given I navigate to the "In Process" tab
    Then The "white" indicator mouseover text is "Normal - Fresh request"
    Then The "yellow" indicator mouseover text is "Rush - Fresh request / Due today,4 hours to TAT"
    Then The "green" indicator mouseover text is "Bulk - Fresh request"
    Then The "orange" indicator mouseover text is "Rush-Bulk - Fresh request - 24 hrs"
    Then The "pink" indicator mouseover text is "Normal and /or Bulk - Due today"
    Then The "dark pink" indicator mouseover text is "Normal and/or Bulk - Due today, 4 hours to TAT"
    Then The "purple" indicator mouseover text is "Normal and/or Bulk - Due today, 30 min to TAT"
    Then The "red" indicator mouseover text is "Normal/Rush/Bulk - TAT crossed/SLA missed"
    Then The "blue" indicator mouseover text is 'Normal/Rush/Bulk - keywords "cancel" etc.'

  @1074
  Scenario: Grid - WO # column
    Given I navigate to the "In Process" tab
    When I sort the "WO #" grid column by "descending"
    Then The "WO #" grid column is sorted by "descending"
    When I sort the "WO #" grid column by "ascending"
    Then The "WO #" grid column is sorted by "ascending"

    When I get the "WO #" for "row 3" of the grid
    And I enter that information into the grid header
    Then The information in the first row of the grid will match what was entered

    Given I navigate to the "Completed" tab
    When I get the "WO #" for "row 3" of the grid
    And I enter that information into the grid header
    Then The information in the first row of the grid will match what was entered

    Given I navigate to the "Discarded" tab
    When I get the "WO #" for "row 3" of the grid
    And I enter that information into the grid header
    Then The information in the first row of the grid will match what was entered

    Given I navigate to the "Incoming Responses" tab
    When I get the "WO #" for "row 3" of the grid
    And I enter that information into the grid header
    Then The information in the first row of the grid will match what was entered

    Given I navigate to the "Important Instruction" tab
    When I get the "WO #" for "row 3" of the grid
    And I enter that information into the grid header
    Then The information in the first row of the grid will match what was entered

    Given I navigate to the "To be Discarded" tab
    When I get the "WO #" for "row 3" of the grid
    And I enter that information into the grid header
    Then The information in the first row of the grid will match what was entered

  @1075 @1561 @1562
  Scenario: Verify Grid - Add Date column in Certs Home Page Grid
    # can fail due to wot-pc#297
    Given I navigate to the "In Process" tab
    When I sort the "Add Date" grid column by "ascending"
    Then The "Add Date" grid column is sorted by "ascending"
    When I sort the "Add Date" grid column by "descending"
    Then The "Add Date" grid column is sorted by "descending"

    When I get the "Add Date" for "row 5" of the grid
    And I set that date in the header
    Then The information in the first row of the grid will match what was entered

    Given I navigate to the "Completed" tab
    When I get the "Add Date" for "row 5" of the grid
    And I set that date in the header
    Then The information in the first row of the grid will match what was entered

    Given I navigate to the "Discarded" tab
    When I get the "Add Date" for "row 5" of the grid
    And I set that date in the header
    Then The information in the first row of the grid will match what was entered

    Given I navigate to the "Incoming Responses" tab
    When I get the "Add Date" for "row 5" of the grid
    And I set that date in the header
    Then The information in the first row of the grid will match what was entered

    Given I navigate to the "Important Instruction" tab
    When I get the "Add Date" for "row 5" of the grid
    And I set that date in the header
    Then The information in the first row of the grid will match what was entered

    Given I navigate to the "To be Discarded" tab
    When I get the "Add Date" for "row 5" of the grid
    And I set that date in the header
    Then The information in the first row of the grid will match what was entered

  @1076
  Scenario: Verify Grid - Folder Drop down
    Given I navigate to the "In Process" tab
    When I sort the "Folder" grid column by "ascending"
    Then The "Folder" grid column is sorted by "ascending"
    When I sort the "Folder" grid column by "descending"
    Then The "Folder" grid column is sorted by "descending"

    When I select "Certificate Requests" from the "Folder" header in the grid
    Then The information in the first row of the grid will match what was entered

    Given I navigate to the "Completed" tab
    Then The "Folder" grid header "is not" displayed

    Given I navigate to the "Discarded" tab
    Then The "Folder" grid header "is not" displayed

    Given I navigate to the "Incoming Responses" tab
    When I select "Incoming Responses" from the "Folder" header in the grid
    Then The information in the first row of the grid will match what was entered

    Given I navigate to the "Important Instruction" tab
    When I select "Important Instructions" from the "Folder" header in the grid
    Then The information in the first row of the grid will match what was entered

  @1077
  Scenario: Grid Client Code field verification
    Given I navigate to the "In Process" tab
    When I sort the "Client Code" grid column by "ascending"
    Then The "Client Code" grid column is sorted by "ascending"
    When I sort the "Client Code" grid column by "descending"
    Then The "Client Code" grid column is sorted by "descending"

    When I get the "Client Code" for "row 5" of the grid
    And I enter that information into the grid header
    Then The information in the first row of the grid will match what was entered

    Given I navigate to the "Completed" tab
    And I sort the "Client Code" grid column by "descending"
    When I get the "Client Code" for "row 5" of the grid
    And I enter that information into the grid header
    Then The information in the first row of the grid will match what was entered

    Given I navigate to the "Discarded" tab
    And I sort the "Client Code" grid column by "descending"
    When I get the "Client Code" for "row 5" of the grid
    And I enter that information into the grid header
    Then The information in the first row of the grid will match what was entered

    Given I navigate to the "Incoming Responses" tab
    And I sort the "Client Code" grid column by "descending"
    When I get the "Client Code" for "row 5" of the grid
    And I enter that information into the grid header
    Then The information in the first row of the grid will match what was entered

    Given I navigate to the "Important Instruction" tab
    And I sort the "Client Code" grid column by "descending"
    When I get the "Client Code" for "row 5" of the grid
    And I enter that information into the grid header
    Then The information in the first row of the grid will match what was entered

    Given I navigate to the "To be Discarded" tab
    And I sort the "Client Code" grid column by "descending"
    When I get the "Client Code" for "row 5" of the grid
    And I enter that information into the grid header
    Then The information in the first row of the grid will match what was entered

  @1078
  Scenario: Verify summary search field functionality in all grids
    Given I navigate to the "In Process" tab
    When I sort the "Summary" grid column by "ascending"
    Then The "Summary" grid column is sorted by "ascending"
    When I sort the "Summary" grid column by "descending"
    Then The "Summary" grid column is sorted by "descending"

    When I get the "Summary" for "row 3" of the grid
    And I enter that information into the grid header
    Then The information in the first row of the grid will match what was entered

    Given I navigate to the "Completed" tab
    And I sort the "Summary" grid column by "descending"
    When I get the "Summary" for "row 5" of the grid
    And I enter that information into the grid header
    Then The information in the first row of the grid will match what was entered

    Given I navigate to the "Discarded" tab
    And I sort the "Summary" grid column by "descending"
    When I get the "Summary" for "row 5" of the grid
    And I enter that information into the grid header
    Then The information in the first row of the grid will match what was entered

    Given I navigate to the "Incoming Responses" tab
    And I sort the "Summary" grid column by "descending"
    When I get the "Summary" for "row 2" of the grid
    And I enter that information into the grid header
    Then The information in the first row of the grid will match what was entered

    Given I navigate to the "Important Instruction" tab
    And I sort the "Summary" grid column by "descending"
    When I get the "Summary" for "row 2" of the grid
    And I enter that information into the grid header
    Then The information in the first row of the grid will match what was entered

    Given I navigate to the "To be Discarded" tab
    And I sort the "Summary" grid column by "descending"
    When I get the "Summary" for "row 3" of the grid
    And I enter that information into the grid header
    Then The information in the first row of the grid will match what was entered

  @1079
  Scenario: Verify Grid - Status Drop down
    Given I navigate to the "In Process" tab
    When I sort the "Available" grid column by "ascending"
    Then The "Available" grid column is sorted by "ascending"
    When I sort the "Available" grid column by "descending"
    Then The "Available" grid column is sorted by "descending"

    When I select "Available" from the "Status" header in the grid
    Then The information in the first row of the grid will match what was entered

    Given I navigate to the "Completed" tab
    When I select "Available" from the "Status" header in the grid
    Then The information in the first row of the grid will match what was entered

    Given I navigate to the "Discarded" tab
    When I select "Available" from the "Status" header in the grid
    Then The information in the first row of the grid will match what was entered

    Given I navigate to the "Incoming Responses" tab
    When I select "Available" from the "Status" header in the grid
    Then The information in the first row of the grid will match what was entered

    Given I navigate to the "Important Instruction" tab
    When I select "Available" from the "Status" header in the grid
    Then The information in the first row of the grid will match what was entered

    Given I navigate to the "To be Discarded" tab
    When I select "Available" from the "Status" header in the grid
    Then The information in the first row of the grid will match what was entered

  @1080
  Scenario: Verify Grid - Assigned To Drop down
    # This can fail if the user in the grid does not exist in the header drop down
    # Logged as wot-certs#838
    Given I navigate to the "In Process" tab
    When I sort the "Assigned To" grid column by "ascending"
    Then The "Assigned To" grid column is sorted by "ascending"
    When I sort the "Assigned To" grid column by "descending"
    Then The "Assigned To" grid column is sorted by "descending"

    When I get the "Assigned To" for "row 3" of the grid
    And I select that information from the grid header
    Then The information in the first row of the grid will match what was entered

    Given I navigate to the "Completed" tab
    Then The "Assigned To" grid header "is not" displayed

    Given I navigate to the "Discarded" tab
    And I sort the "Assigned To" grid column by "descending"
    When I get the "Assigned To" for "row 3" of the grid
    And I select that information from the grid header
    Then The information in the first row of the grid will match what was entered

    Given I navigate to the "Incoming Responses" tab
    And I sort the "Assigned To" grid column by "descending"
    When I get the "Assigned To" for "row 3" of the grid
    And I select that information from the grid header
    Then The information in the first row of the grid will match what was entered

    Given I navigate to the "Important Instruction" tab
    And I sort the "Assigned To" grid column by "descending"
    When I get the "Assigned To" for "row 3" of the grid
    And I select that information from the grid header
    Then The information in the first row of the grid will match what was entered

    Given I navigate to the "To be Discarded" tab
    And I sort the "Assigned To" grid column by "descending"
    When I get the "Assigned To" for "row 3" of the grid
    And I select that information from the grid header
    Then The information in the first row of the grid will match what was entered

  @1081
  Scenario: Verify Grid - Due Date column in Certs Home Page Grid
    # can fail due to wot-pc##297
    Given I navigate to the "In Process" tab
    When I sort the "Due Date" grid column by "ascending"
    Then The "Due Date" grid column is sorted by "ascending"
    When I sort the "Due Date" grid column by "descending"
    Then The "Due Date" grid column is sorted by "descending"

    When I get the "Due Date" for "row 5" of the grid
    And I set that date in the header
    Then The information in the first row of the grid will match what was entered

    Given I navigate to the "Completed" tab
    When I get the "Due Date" for "row 5" of the grid
    And I set that date in the header
    Then The information in the first row of the grid will match what was entered

    Given I navigate to the "Discarded" tab
    When I get the "Due Date" for "row 5" of the grid
    And I set that date in the header
    Then The information in the first row of the grid will match what was entered

    Given I navigate to the "Incoming Responses" tab
    When I get the "Due Date" for "row 5" of the grid
    And I set that date in the header
    Then The information in the first row of the grid will match what was entered

    Given I navigate to the "Important Instruction" tab
    When I get the "Due Date" for "row 5" of the grid
    And I set that date in the header
    Then The information in the first row of the grid will match what was entered

    Given I navigate to the "To be Discarded" tab
    When I get the "Due Date" for "row 5" of the grid
    And I set that date in the header
    Then The information in the first row of the grid will match what was entered

  @1082
  Scenario: Verify Requestor search field functionality in all grids
    Given I navigate to the "In Process" tab
    When I sort the "Requestor" grid column by "ascending"
    Then The "Requestor" grid column is sorted by "ascending"
    When I sort the "Requestor" grid column by "descending"
    Then The "Requestor" grid column is sorted by "descending"

    When I get the "Requestor" for "row 3" of the grid
    And I enter that information into the grid header
    Then The information in the first row of the grid will match what was entered

    Given I navigate to the "Completed" tab
    And I sort the "Requestor" grid column by "descending"
    When I get the "Requestor" for "row 3" of the grid
    And I enter that information into the grid header
    Then The information in the first row of the grid will match what was entered

    Given I navigate to the "Discarded" tab
    And I sort the "Requestor" grid column by "descending"
    When I get the "Requestor" for "row 3" of the grid
    And I enter that information into the grid header
    Then The information in the first row of the grid will match what was entered

    Given I navigate to the "Incoming Responses" tab
    And I sort the "Requestor" grid column by "descending"
    When I get the "Requestor" for "row 3" of the grid
    And I enter that information into the grid header
    Then The information in the first row of the grid will match what was entered

    Given I navigate to the "Important Instruction" tab
    And I sort the "Requestor" grid column by "descending"
    When I get the "Requestor" for "row 3" of the grid
    And I enter that information into the grid header
    Then The information in the first row of the grid will match what was entered

    Given I navigate to the "To be Discarded" tab
    And I sort the "Requestor" grid column by "descending"
    When I get the "Requestor" for "row 3" of the grid
    And I enter that information into the grid header
    Then The information in the first row of the grid will match what was entered

  @1083
  Scenario: Verify Grid - Branch Office Drop down
    Given I navigate to the "In Process" tab
    When I sort the "Branch Office" grid column by "ascending"
    Then The "Branch Office" grid column is sorted by "ascending"
    When I sort the "Branch Office" grid column by "descending"
    Then The "Branch Office" grid column is sorted by "descending"

    When I get the "Branch Office" for "row 3" of the grid
    And I select that information from the grid header
    Then The information in the first row of the grid will match what was entered

    Given I navigate to the "Completed" tab
    And I sort the "Branch Office" grid column by "descending"
    When I get the "Branch Office" for "row 3" of the grid
    And I select that information from the grid header
    Then The information in the first row of the grid will match what was entered

    Given I navigate to the "Discarded" tab
    And I sort the "Branch Office" grid column by "descending"
    When I get the "Branch Office" for "row 3" of the grid
    And I select that information from the grid header
    Then The information in the first row of the grid will match what was entered

    Given I navigate to the "Incoming Responses" tab
    And I sort the "Branch Office" grid column by "descending"
    When I get the "Branch Office" for "row 3" of the grid
    And I select that information from the grid header
    Then The information in the first row of the grid will match what was entered

    Given I navigate to the "Important Instruction" tab
    And I sort the "Branch Office" grid column by "descending"
    When I get the "Branch Office" for "row 3" of the grid
    And I select that information from the grid header
    Then The information in the first row of the grid will match what was entered

    Given I navigate to the "To be Discarded" tab
    And I sort the "Branch Office" grid column by "descending"
    When I get the "Branch Office" for "row 3" of the grid
    And I select that information from the grid header
    Then The information in the first row of the grid will match what was entered

  @1084
  Scenario: Verify Grid - Profit Center Drop down
    Given I navigate to the "In Process" tab
    When I sort the "Profit Center" grid column by "ascending"
    Then The "Profit Center" grid column is sorted by "ascending"
    When I sort the "Profit Center" grid column by "descending"
    Then The "Profit Center" grid column is sorted by "descending"

    When I get the "Profit Center" for "row 3" of the grid
    And I select that information from the grid header
    Then The information in the first row of the grid will match what was entered

    Given I navigate to the "Completed" tab
    And I sort the "Profit Center" grid column by "descending"
    When I get the "Profit Center" for "row 3" of the grid
    And I select that information from the grid header
    Then The information in the first row of the grid will match what was entered

    Given I navigate to the "Discarded" tab
    And I sort the "Profit Center" grid column by "descending"
    When I get the "Profit Center" for "row 3" of the grid
    And I select that information from the grid header
    Then The information in the first row of the grid will match what was entered

    Given I navigate to the "Incoming Responses" tab
    And I sort the "Profit Center" grid column by "descending"
    When I get the "Profit Center" for "row 3" of the grid
    And I select that information from the grid header
    Then The information in the first row of the grid will match what was entered

    Given I navigate to the "Important Instruction" tab
    And I sort the "Profit Center" grid column by "descending"
    When I get the "Profit Center" for "row 3" of the grid
    And I select that information from the grid header
    Then The information in the first row of the grid will match what was entered

    Given I navigate to the "To be Discarded" tab
    And I sort the "Profit Center" grid column by "descending"
    When I get the "Profit Center" for "row 3" of the grid
    And I select that information from the grid header
    Then The information in the first row of the grid will match what was entered

  @1085
  Scenario: Verify No of Certs search field functionality in all grids
    Given I navigate to the "In Process" tab
    When I sort the "# of Certs" grid column by "ascending"
    Then The "# of Certs" grid column is sorted by "ascending"
    When I sort the "# of Certs" grid column by "descending"
    Then The "# of Certs" grid column is sorted by "descending"

    When I get the "# of Certs" for "row 3" of the grid
    And I enter that information into the grid header
    Then The information in the first row of the grid will match what was entered

    Given I navigate to the "Completed" tab
    And I sort the "# of Certs" grid column by "descending"
    When I get the "# of Certs" for "row 3" of the grid
    And I enter that information into the grid header
    Then The information in the first row of the grid will match what was entered

    Given I navigate to the "Discarded" tab
    And I sort the "# of Certs" grid column by "descending"
    When I get the "# of Certs" for "row 3" of the grid
    And I enter that information into the grid header
    Then The information in the first row of the grid will match what was entered

    Given I navigate to the "Incoming Responses" tab
    And I sort the "# of Certs" grid column by "descending"
    When I get the "# of Certs" for "row 3" of the grid
    And I enter that information into the grid header
    Then The information in the first row of the grid will match what was entered

    Given I navigate to the "Important Instruction" tab
    And I sort the "# of Certs" grid column by "descending"
    When I get the "# of Certs" for "row 3" of the grid
    And I enter that information into the grid header
    Then The information in the first row of the grid will match what was entered

    Given I navigate to the "To be Discarded" tab
    And I sort the "# of Certs" grid column by "descending"
    When I get the "# of Certs" for "row 3" of the grid
    And I enter that information into the grid header
    Then The information in the first row of the grid will match what was entered

  @1086
  Scenario: Verify Grid - Status Drop down
    Given I navigate to the "In Process" tab
    When I sort the "Rush / Bulk" grid column by "ascending"
    Then The "Rush / Bulk" grid column is sorted by "ascending"
    When I sort the "Rush / Bulk" grid column by "descending"
    Then The "Rush / Bulk" grid column is sorted by "descending"

    When I select "Normal" from the "Rush / Bulk" header in the grid
    Then The information in the first row of the grid will match what was entered

    Given I navigate to the "Completed" tab
    When I select "Rush" from the "Rush / Bulk" header in the grid
    Then The information in the first row of the grid will match what was entered

    Given I navigate to the "Discarded" tab
    When I select "Bulk" from the "Rush / Bulk" header in the grid
    Then The information in the first row of the grid will match what was entered

    Given I navigate to the "Incoming Responses" tab
    When I select "Normal" from the "Rush / Bulk" header in the grid
    Then The information in the first row of the grid will match what was entered

    Given I navigate to the "Important Instruction" tab
    When I select "Normal" from the "Rush / Bulk" header in the grid
    Then The information in the first row of the grid will match what was entered

    Given I navigate to the "To be Discarded" tab
    When I select "Normal" from the "Rush / Bulk" header in the grid
    Then The information in the first row of the grid will match what was entered

  @1087
  Scenario: Verify Grid - Pending Drop down
    Given I navigate to the "In Process" tab
    When I sort the "Pending" grid column by "ascending"
    Then The "Pending" grid column is sorted by "ascending"
    When I sort the "Pending" grid column by "descending"
    Then The "Pending" grid column is sorted by "descending"

    When I select "Yes" from the "Pending" header in the grid
    Then The information in the first row of the grid will match what was entered

    Given I navigate to the "Completed" tab
    When I select "Yes" from the "Pending" header in the grid
    Then The information in the first row of the grid will match what was entered

    Given I navigate to the "Discarded" tab
    When I select "No" from the "Pending" header in the grid
    Then The information in the first row of the grid will match what was entered

    Given I navigate to the "Incoming Responses" tab
    When I select "Yes" from the "Pending" header in the grid
    Then The information in the first row of the grid will match what was entered

    Given I navigate to the "Important Instruction" tab
    When I select "No" from the "Pending" header in the grid
    Then The information in the first row of the grid will match what was entered

    Given I navigate to the "To be Discarded" tab
    When I select "No" from the "Pending" header in the grid
    Then The information in the first row of the grid will match what was entered

  @1088
  Scenario: Verify Grid - WO Type Drop down
    Given I navigate to the "In Process" tab
    When I sort the "WO Type" grid column by "ascending"
    Then The "WO Type" grid column is sorted by "ascending"
    When I sort the "WO Type" grid column by "descending"
    Then The "WO Type" grid column is sorted by "descending"

    When I select "New Cert Request" from the "WO Type" header in the grid
    Then The information in the first row of the grid will match what was entered

    Given I navigate to the "Completed" tab
    When I select "New Cert Request" from the "WO Type" header in the grid
    Then The information in the first row of the grid will match what was entered

    Given I navigate to the "Discarded" tab
    When I select "Renewal" from the "WO Type" header in the grid
    Then The information in the first row of the grid will match what was entered

    Given I navigate to the "Incoming Responses" tab
    When I select "New Cert Request" from the "WO Type" header in the grid
    Then The information in the first row of the grid will match what was entered

    Given I navigate to the "Important Instruction" tab
    When I select "New Business" from the "WO Type" header in the grid
    Then The information in the first row of the grid will match what was entered

    Given I navigate to the "To be Discarded" tab
    When I select "Evidence On Demand" from the "WO Type" header in the grid
    Then The information in the first row of the grid will match what was entered

  @1089
  Scenario: Verify Update search field functionality in all grids
    Given I navigate to the "In Process" tab
    When I sort the "Update" grid column by "ascending"
    Then The "Update" grid column is sorted by "ascending"
    When I sort the "Update" grid column by "descending"
    Then The "Update" grid column is sorted by "descending"

    When I enter "Yes" into the "Update" grid header
    Then The information in the first row of the grid will match what was entered

    Given I navigate to the "Completed" tab
    When I enter "No" into the "Update" grid header
    Then The information in the first row of the grid will match what was entered

    Given I navigate to the "Discarded" tab
    When I enter "No" into the "Update" grid header
    Then The information in the first row of the grid will match what was entered

    Given I navigate to the "Incoming Responses" tab
    When I enter "Yes" into the "Update" grid header
    Then The information in the first row of the grid will match what was entered

    Given I navigate to the "Important Instruction" tab
    When I enter "No" into the "Update" grid header
    Then The information in the first row of the grid will match what was entered

    Given I navigate to the "To be Discarded" tab
    When I enter "No" into the "Update" grid header
    Then The information in the first row of the grid will match what was entered

  @1090
  Scenario: Verify Grid - SLA Met Drop down
    Given I navigate to the "Completed" tab
    When I sort the "SLA MET" grid column by "ascending"
    Then The "SLA MET" grid column is sorted by "ascending"
    When I sort the "SLA MET" grid column by "descending"
    Then The "SLA MET" grid column is sorted by "descending"

    When I select "Yes" from the "SLA MET" header in the grid
    Then The information in the first row of the grid will match what was entered

    Given I navigate to the "In Process" tab
    Then The "SLA MET" grid header "is not" displayed

    Given I navigate to the "Discarded" tab
    When I select "No" from the "SLA MET" header in the grid
    Then The information in the first row of the grid will match what was entered

    Given I navigate to the "Incoming Responses" tab
    When I select "No" from the "SLA MET" header in the grid
    Then The information in the first row of the grid will match what was entered

    Given I navigate to the "Important Instruction" tab
    Then The "SLA MET" grid header "is not" displayed

    Given I navigate to the "To Be Discarded" tab
    Then The "SLA MET" grid header "is not" displayed

  @7858
  Scenario: Verify SLA Time search field functionality in all grids
    # fails due to wot-certs#743
    Given I navigate to the "In Process" tab
    When I sort the "SLA Miss Time" grid column by "ascending"
    Then The "SLA Miss Time" grid column is sorted by "ascending"
    When I sort the "SLA Miss Time" grid column by "descending"
    Then The "SLA Miss Time" grid column is sorted by "descending"

    When I get the "SLA Miss Time" for "row 3" of the grid
    And I enter that information into the grid header
    Then The information in the first row of the grid will match what was entered

    Given I navigate to the "Completed" tab
    And I sort the "SLA Miss Time" grid column by "descending"
    When I get the "SLA Miss Time" for "row 3" of the grid
    And I enter that information into the grid header
    Then The information in the first row of the grid will match what was entered

    Given I navigate to the "Discarded" tab
    Then The "SLA Miss Time" grid header "is not" displayed

    Given I navigate to the "Incoming Responses" tab
    Then The "SLA Miss Time" grid header "is not" displayed

    Given I navigate to the "Important Instruction" tab
    Then The "SLA Miss Time" grid header "is not" displayed

    Given I navigate to the "To Be Discarded" tab
    Then The "SLA Miss Time" grid header "is not" displayed

  @14181
  Scenario: Verify Grid - Date Completed column in Certs Home Page Grid
    # can fail due to wot-pc##297
    Given I navigate to the "Completed" tab
    When I sort the "Date Completed" grid column by "ascending"
    Then The "Date Completed" grid column is sorted by "ascending"
    When I sort the "Date Completed" grid column by "descending"
    Then The "Date Completed" grid column is sorted by "descending"

    When I get the "Date Completed" for "row 5" of the grid
    And I set that date in the header
    Then The information in the first row of the grid will match what was entered

    Given I navigate to the "In Process" tab
    Then The "Date Completed" grid header "is not" displayed

    # Todo: currently this column is displayed in the Discarded tab. All blank. Trying to figure out if this is intended
    # or not
#    Given I navigate to the "Discarded" tab
#    When I get the "Date Completed" for "row 5" of the grid
#    And I set that date in the header
#    Then The information in the first row of the grid will match what was entered

    Given I navigate to the "Incoming Responses" tab
    When I get the "Date Completed" for "row 5" of the grid
    And I set that date in the header
    Then The information in the first row of the grid will match what was entered

    Given I navigate to the "Important Instruction" tab
    Then The "Date Completed" grid header "is not" displayed

    Given I navigate to the "To be Discarded" tab
    Then The "Date Completed" grid header "is not" displayed

  @1092
  Scenario: Verify Holder Delivery search field functionality in all grids
    Given I navigate to the "In Process" tab
    When I sort the "Holder Delivery" grid column by "ascending"
    Then The "Holder Delivery" grid column is sorted by "ascending"
    When I sort the "Holder Delivery" grid column by "descending"
    Then The "Holder Delivery" grid column is sorted by "descending"

    When I get the "Holder Delivery" for "row 3" of the grid
    And I enter that information into the grid header
    Then The information in the first row of the grid will match what was entered

    Given I navigate to the "Completed" tab
    And I sort the "Holder Delivery" grid column by "descending"
    When I get the "Holder Delivery" for "row 3" of the grid
    And I enter that information into the grid header
    Then The information in the first row of the grid will match what was entered

    Given I navigate to the "Discarded" tab
    Then The "Holder Delivery" grid header "is not" displayed

    Given I navigate to the "Incoming Responses" tab
    Then The "Holder Delivery" grid header "is not" displayed

    Given I navigate to the "Important Instruction" tab
    And I sort the "Holder Delivery" grid column by "descending"
    When I get the "Holder Delivery" for "row 3" of the grid
    And I enter that information into the grid header
    Then The information in the first row of the grid will match what was entered

    Given I navigate to the "To Be Discarded" tab
    Then The "Holder Delivery" grid header "is not" displayed

  @1093
  Scenario: Verify Insured Delivery search field functionality in all grids
    Given I navigate to the "In Process" tab
    When I sort the "Insured Delivery" grid column by "ascending"
    Then The "Insured Delivery" grid column is sorted by "ascending"
    When I sort the "Insured Delivery" grid column by "descending"
    Then The "Insured Delivery" grid column is sorted by "descending"

    When I get the "Insured Delivery" for "row 3" of the grid
    And I enter that information into the grid header
    Then The information in the first row of the grid will match what was entered

    Given I navigate to the "Completed" tab
    And I sort the "Insured Delivery" grid column by "descending"
    When I get the "Insured Delivery" for "row 3" of the grid
    And I enter that information into the grid header
    Then The information in the first row of the grid will match what was entered

    Given I navigate to the "Discarded" tab
    Then The "Insured Delivery" grid header "is not" displayed

    Given I navigate to the "Incoming Responses" tab
    Then The "Insured Delivery" grid header "is not" displayed

    Given I navigate to the "Important Instruction" tab
    And I sort the "Insured Delivery" grid column by "descending"
    When I get the "Insured Delivery" for "row 3" of the grid
    And I enter that information into the grid header
    Then The information in the first row of the grid will match what was entered

    Given I navigate to the "To Be Discarded" tab
    Then The "Insured Delivery" grid header "is not" displayed

  @11601
  Scenario: Filters should remain applied until clicks on Reset button
    Given I navigate to the "In Process" tab
    When I get the "WO #" for "row 3" of the grid
    And I enter that information into the grid header
    And I click on the top work order link
    And I click the "Close" button
    Then The "WO #" grid header is "<headerInfo>"

#	C1558	Select All
    #	C1559	Select single choice
    #	C1560	Select multiple choices
    #	C1579	Search Bar in drop down menu
    #	C1580	Clear search option in drop down menu

  # C14034	Open any grid in new tab with right click function.
