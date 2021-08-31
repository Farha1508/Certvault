Feature: Add Record

  Background: Print Shop Login
    ###---------- Login component ----------###
    Given I am on the login page
    When I enter the email and password for the "India User"
    And I click the Sign In button
    Then I will be taken to the apps page
    ###---------- Login component ----------###
    ###---------- App selection ----------###
    When I click on the "Print Shop" tile
    Then I will be taken to the homepage for that app
    # When set the tab to "Processing"
    ###---------- App selection ----------###

  @10328
  Scenario: Verify Print Shop homepage
    # Run C10017 (Top level navigation)

    # Run C6718 (Top level navigation)
    When I enter "245718" in the search field
    Then I see 1 result saying "245718"

    When I click the top search result
    Then I am taken to the matching work order

    When I enter "24571" in the search field
    Then I see 5 results saying "24571"
    And The results are in descending order

    When I click the top search result
    Then I am taken to the matching work order

    When I enter "999999999999" in the search field
    Then I see 1 result saying "Record Doesn't Exist."

    When I click the "Close" button
    Then The following KPIs exist
      | On Hold for India |
      | New Records       |
      | Certified Mail    |
      | Overnight         |
      | Rush              |
    And The folowing KPIs do not exist
      | Printed                         |
      | On Hold for Print Shop          |
      | Assigned to Print Shop Carriers |
      | Assigned to Print Shop          |

  @10327
  Scenario: Validate user is able to create new record
    # run C5633 (Add record)
    Given I click the "Add Record" button
    And I enter the following information into the form
      | Company | Company 019 |
      | Folder  | New Record  |
    Then Meter Name is not empty
    When I click the "Submit" button
    And Navigate to the new record
    Then The record is added
      | Company |
      | Folder  |
    And I cannot make a record with the same ID

  @10329 @10884
  Scenario: Validate user Can only edit records in the "New Record" folder or "To be Printed" folder or "On Hold for India" folder
    # This fails due to https://github.com/patracorp/print-shop/issues/120
    Given I navigate to the "Processing" tab
    And I click the "Add Record" button
    And I enter the following information into the form
      | Company | Company 019 |
      | Folder  | New Record  |
    When I click the "Submit" button
    And I get the count for "New Records" KPI
    And I search the new record
    And I click on the top record link
    Then The record is added
      | Company |
      | Folder  |
    And The following buttons are "enabled"
      | Submit                   |
      | Submit and Close         |
      | Cancel                   |
      | Close                    |
      | Print PDF                |
      | Add Attachment           |
      | Add Multiple Attachments |

    When I enter the following information into the form
      | Folder | On Hold for India |
    And I click the "Submit and Close" button
    Then Verify that "New Records" KPI has "decreased"

    #When I search the new record (no need to search again. Filters are not reset)
    When I click on the top record link
    Then The following buttons are "enabled"
      | Submit                   |
      | Submit and Close         |
      | Cancel                   |
      | Close                    |
      | Print PDF                |
      | Add Attachment           |
      | Add Multiple Attachments |

    When I enter the following information into the form
      | Folder | Printed |
    Then The India Folder warning is displayed
    And The following buttons are "enabled"
      | Close     |
      | Print PDF |
    And The following buttons are "disabled"
      | Submit                   |
      | Submit and Close         |
      | Cancel                   |
      | Add Attachment           |
      | Add Multiple Attachments |

    When I enter the following information into the form
      | Folder | On Hold for Print Shop |
    Then The India Folder warning is displayed
    And The following buttons are "enabled"
      | Close     |
      | Print PDF |
    And The following buttons are "disabled"
      | Submit                   |
      | Submit and Close         |
      | Cancel                   |
      | Add Attachment           |
      | Add Multiple Attachments |

    When I enter the following information into the form
      | Folder | Discarded |
    Then The India Folder warning is displayed
    And The following buttons are "enabled"
      | Close     |
      | Print PDF |
    And The following buttons are "disabled"
      | Submit                   |
      | Submit and Close         |
      | Cancel                   |
      | Add Attachment           |
      | Add Multiple Attachments |

    # This is the part that fails due to issue #120
    When I enter the following information into the form
      | Folder | To Be Printed |
    Then The India Folder warning is displayed
    And The following buttons are "enabled"
      | Close     |
      | Print PDF |
    And The following buttons are "disabled"
      | Submit                   |
      | Submit and Close         |
      | Cancel                   |
      | Add Attachment           |
      | Add Multiple Attachments |



  @10334
  Scenario: Validate user cannot edit records in the other folders
    # Run C6719 (Tabs and Grids Common Test Cases)
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