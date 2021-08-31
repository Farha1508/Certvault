Feature: PrintShop

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
    When set the tab to "Processing"
    ###---------- App selection ----------###

  @6729
  Scenario: History Tab columns work correctly
    # This fails due to print-shop 262 (columns do not sort).
  # This test case uses a hard-coded Record number. If it's cleared during a reset, needs to be remade.
    # Todo: add KPI changes using a different user for the User Name column
    When I enter "246097" into the "Record ID" grid header
    And I click on the top record link
    When I click on the "History" link
    And set the tab to "History"
    Then I test the following form columns
      | AddDate   |
      | Folder    |
      | User Name |

    @12746
  Scenario: History records can be added
    # This fails due to print-shop 262 (columns do not sort).
  # This test case uses a hard-coded Record number. If it's cleared during a reset, needs to be remade.
    When I enter "246097" into the "Record ID" grid header
    And I click on the top record link

    When I click on the "History" link
    And set the tab to "History"
    When I get the number of records in the "History" form tab
    And I click on the "Details" link
    And I enter the following information into the form
      | Folder | Printed |
    And I click the "Submit" button
    And The record is updated
    And I enter the following information into the form
      | Folder | New Record |
    And I click the "Submit" button
    And The record is updated
    When I click on the "History" link
    Then The number of records in the "History" tab has "increased"

    When I sort the "AddDate" grid column by "descending"
    Then The "AddDate" for "row 1" in the grid is "<current date>"
    Then The "Folder" for "row 1" in the grid is "New Record"
    Then The "User Name" for "row 1" in the grid is "Carl Wahlstrom"
    # Change this to get the current user.

  @12747
  Scenario: History Tab pagination
  # This test case uses a hard-coded Record number. If it's cleared during a reset, needs to be remade.
    When I enter "246097" into the "Record ID" grid header
    And I click on the top record link

    When I click on the "History" link
    And set the tab to "History"
    And I get the current grid page number
    When I move to the previous page in the grid
    Then The page number is "the same"

    When I get the current grid page number
    And I move to the next page in the grid
    Then The page number is "incremented"

    When I get the current grid page number
    And I move to the previous page in the grid
    Then The page number is "decremented"

    When I move to the last page in the grid
    And I move to the next page in the grid
    Then The page number is "the same"

  @12748
  Scenario: History Tab view count
    # This fails due to print-shop 262 (view counts do not work).
  # This test case uses a hard-coded Record number. If it's cleared during a reset, needs to be remade.
    When I enter "246097" into the "Record ID" grid header
    And I click on the top record link
    When I click on the "History" link
    And set the tab to "History"
    When I select the "10" option from the the Viewing drop down
    Then The number of rows displayed will be less than or equal to the number selected
    When I select the "20" option from the the Viewing drop down
    Then The number of rows displayed will be less than or equal to the number selected
    When I select the "50" option from the the Viewing drop down
    Then The number of rows displayed will be less than or equal to the number selected
    When I select the "100" option from the the Viewing drop down
    Then The number of rows displayed will be less than or equal to the number selected


