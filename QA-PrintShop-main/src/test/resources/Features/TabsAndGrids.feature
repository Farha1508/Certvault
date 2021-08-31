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

  @6719
  Scenario: Grid Paging
    Given I navigate to the "Processing" tab
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

  @6720
  Scenario: Grid number of records displayed
    When I select the "20" option from the the Viewing drop down
    Then The number of rows displayed will be less than or equal to the number selected
    When I select the "50" option from the the Viewing drop down
    Then The number of rows displayed will be less than or equal to the number selected
    When I select the "100" option from the the Viewing drop down
    Then The number of rows displayed will be less than or equal to the number selected
    When I select the "10" option from the the Viewing drop down
    Then The number of rows displayed will be less than or equal to the number selected

  @6721
  Scenario: Grid Reset button
    When I enter "239658" into the "Record ID" grid header
    Then The "Record ID" grid header is "239658"
    When I enter "TEST" into the "Client Code" grid header
    Then The "Client Code" grid header is "TEST"
    When I enter "Notes 239658" into the "Notes" grid header
    Then The "Notes" grid header is "Notes 239658"
   # 'And I click the "Reset" button' does not find the correct button because it's a div, not a button.
    When I click the PrintShop Reset button
    Then The "Record ID" grid header is ""
    And The "Client Code" grid header is ""
    And The "Notes" grid header is ""

  @6722
  Scenario: Processing Grid - Text Field Headers
    # Check each header's ability to be sorted and to filter.
    Then I test the following columns
      | Record ID                   |
      | Client Code                 |
      | # of Holder Certs           |
      | # of Insured Certs          |
      | # of Letters /Notices /Docs |
      | Notes                       |

  @6723
  Scenario: Processing Grid - Dropdown Headers
    # todo: why are header selectors not opening?
    Then I test the following columns
      | Company      |
      | Service      |
      | Branch       |
      | Mailing Type |

  @6724
  Scenario: Printed Grid - Text Field Headers
    When I navigate to the "Printed" tab
    Then I test the following columns
      | Record ID                   |
      | Client Code                 |
      | # of Holder Certs           |
      | # of Insured Certs          |
      | # of Letters /Notices /Docs |
      | # Of Cert Pages Printed     |
      | # Of Non-Cert Pages Printed |
      | WO#                         |
      | Notes                       |

  @6725
  Scenario: Printed Grid - Dropdown Headers
    When I navigate to the "Printed" tab
    Then I test the following columns
      | Company      |
      | Service      |
      | Branch       |
      | Mailing Type |

  @6726
  Scenario: Discarded Grid - Text Field Headers
    When I navigate to the "Discarded" tab
    Then I test the following columns
      | Record ID                   |
      | Client Code                 |
      | # of Holder Certs           |
      | # of Insured Certs          |
      | # of Letters /Notices /Docs |
      | WO#                         |
      | Notes                       |

  @6727
  Scenario: Discarded Grid - Dropdown Headers
    When I navigate to the "Discarded" tab
    Then I test the following columns
      | Company      |
      | Service      |
      | Branch       |
      | Mailing Type |

#  @11362
#  Scenario: Grid sorting arrows

  # This scenario is mostly concerned about which direction arrows are pointing when sorting a column.
  # The only indication Selenium would have is if the header had the "sorting_asc" or "sorting_desc" class
  # These are already used for selectors in the "Then I test the following columns" steps in other Scenarios
  # Sorting is also checked in the "Then I test the following columns" steps
  # Because this is a visual inspection that can only be inferred in Selenium, I suggest we leave it as a manual case.

  # Verify all headers have both sorting arrows (class = "sorting")
  # click on a header
  # Verify only one sorting arrow (correct one) is displayed (class = "sorting_asc")
  # Verify column is sorted correctly (look at a handful of entries?) (done in "Then I test the following columns")
  # click header again
  # Verify only one sorting arrow (the other one) is displayed (class = "sorting_desc")
  # Verify column is sorted correctly (look at a handful of entries?) (done in "Then I test the following columns")

  @11363
  Scenario: Grid Date Columns
    When I navigate to the "Processing" tab
    And I sort the "Mail Date" grid column by "descending"
    Then The "Mail Date" entry has a date and time
    And The "Mail Date" timestamp is "3:15 PM"

    When I sort the "Assigned To PS Date" grid column by "descending"
    Then The "Assigned To PS Date" entry has a date and time

    When I navigate to the "Printed" tab
    And I sort the "Mail Date" grid column by "descending"
    Then The "Mail Date" entry has a date and time
    And The "Mail Date" timestamp is "3:15 PM"

    When I sort the "Print Date" grid column by "descending"
    Then The "Print Date" entry has a date and time

    When I navigate to the "Discarded" tab
    And I sort the "Mail Date" grid column by "descending"
    Then The "Mail Date" entry has a date and time
    And The "Mail Date" timestamp is "3:15 PM"

    When I sort the "Assigned To PS Date" grid column by "descending"
    Then The "Assigned To PS Date" entry has a date and time

  @12237
  Scenario: Grid Filtering
    When I navigate to the "Printed" tab
    And I sort the "Record ID" grid column by "descending"
    Then The "Record ID" column is sorted by "descending"

    When I navigate to the "Processing" tab
    And I navigate to the "Printed" tab
    Then The "Record ID" column is sorted by "descending"

    When I click on the top record link
    And I click the "Close" button
    Then The "Record ID" column is sorted by "descending"
