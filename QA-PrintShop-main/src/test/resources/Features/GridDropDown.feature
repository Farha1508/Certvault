Feature: PrintShop Tabs and Grid Common Test Cases

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
    And I navigate to the "Processing" tab
    ###---------- App selection ----------###

  @5457
  Scenario: Select Multiple Values
    When I select "Company 002" from the "Company" header in the grid
    And I select "Company 019" from the "Company" header in the grid
    Then I check if the following items are selected in the "Company" header
      | Company 002 | Company 019 |
    Then I check if the following items are not selected in the "Company" header
      | Company 021 |

    When I sort the "Company" grid column by "ascending"
    Then The "Company" for "row 1" in the grid is "Company 002"

    When I sort the "Company" grid column by "descending"
    Then The "Company" for "row 1" in the grid is "Company 019"

  @5456
  Scenario: Select One Value
    When I select "Company 002" from the "Company" header in the grid
    Then I check if the following items are selected in the "Company" header
    | Company 002 |
    Then I check if the following items are not selected in the "Company" header
    | Company 019  | Company 021 |

    When I sort the "Company" grid column by "ascending"
    Then The "Company" for "row 1" in the grid is "Company 002"

    When I sort the "Company" grid column by "descending"
    Then The "Company" for "row 1" in the grid is "Company 002"

  @5455
  Scenario: Select All
    Then I check if the following items are not selected in the "Company" header
      | Company 002 | Company 019 | Company 021 | Company 748 |
    When I select "Select all" from the "Company" header in the grid
    Then I check if the following items are selected in the "Company" header
      | Company 002 | Company 019 | Company 021 | Company 748 |
    # Sometimes large numbers (Eg. Company 748) time out.
    # checking a subset of all, and extrapolating.