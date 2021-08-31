Feature: Add Record

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
    # When set the tab to "Processing"
    ###---------- App selection ----------###


  @5631 @5632
  Scenario: Add Record without mandatory fields
    When I click the "Add Record" button
    And I edit the following drop downs
      | Company        | Folder        |
      | Select Company | Select Folder |
    #5631
    And I click the "Submit" button
    Then I see error warnings
    #5632
    And I click the "Submit & Open" button
    Then I see error warnings

  @10019
  Scenario: Add Record with mandatory fields
    When I click the "Add Record" button
    And I enter the following information into the form
      | Date of Customer Request (for Print Shop use only) | <current date> |
      | Mail Date                                          | <current date> |
      | Company                                            | Company 019    |
      | Service                                            | Indexing       |
      | Division                                           | 705            |
      | Branch                                             | Seattle        |
      | Department                                         | 407            |
      | Associated WO #                                    | 123            |
      | Client Code                                        | 123tesy        |
      | # of Holder Certs                                  | 12             |
      | # of Insured Certs                                 | 12             |
      | # of Carrier Certs                                 | 12             |
      | # of Letters/Notices/Documents                     | 12             |
      | Meter Name                                         | USI            |
    And I click the "Submit" button
    And Navigate to the new record
    Then The record is added
      | Date of Customer Request (for Print Shop use only) |
      | Mail Date                                          |
      | Company                                            |
      | Service                                            |
      | Division                                           |
      | Branch                                             |
      | Department                                         |
      | Associated WO #                                    |
      | Client Code                                        |
      | # of Holder Certs                                  |
      | # of Insured Certs                                 |
      | # of Carrier Certs                                 |
      | # of Letters/Notices/Documents                     |
      | Meter Name                                         |
    And I cannot make a record with the same ID

  @5633
  Scenario: Add Record - Submit
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

  @5634
  Scenario: Add Record - Submit and Open
    Given I click the "Add Record" button
    And I enter the following information into the form
      | Company | Company 019 |
      | Folder  | New Record  |
    Then Meter Name is not empty
    When I click the "Submit & Open" button
    And I take note of the record ID
    Then The record is added
      | Company |
      | Folder  |
    And I cannot make a record with the same ID

  @5635
  Scenario: Add Record - Cancel
    Given I click the "Add Record" button
    And I enter the following information into the form
      | Company | Company 019 |
      | Folder  | New Record  |
    Then Meter Name is not empty
    When I click the "Cancel" button
    Then No record is added

  @12238
  Scenario: Add Record - Conditional field Certified Mail
      # blocked by https://github.com/patracorp/print-shop/issues/200
    Given I click the "Add Record" button
    Then the "Certified Mail Field" field is not displayed
    And the "Certified Mail with Return Receipt Field" field is not displayed

    When I click the "Certified Mail" checkbox
    Then the "Certified Mail Field" field is displayed
    And the "Certified Mail Field" field only accepts numbers
    And the "Certified Mail Field" field hax a max length of 9

    When I click the "Certified Mail with Return Receipt" checkbox
    Then the "Certified Mail with Return Receipt Field" field is displayed
    And the "Certified Mail with Return Receipt Field" field only accepts numbers
    And the "Certified Mail with Return Receipt Field" field hax a max length of 9

    And I enter the following information into the form
      | Company | Company 019 |
      | Folder  | New Record  |
      | CM #    | 12345       |
      | CMRR #  | 123456      |
    When I click the "Cancel" button
    Then No record is added

    Given I click the "Add Record" button
    Then the "Certified Mail Field" field is not displayed
    And the "Certified Mail with Return Receipt Field" field is not displayed

    When I click the "Certified Mail" checkbox
    Then the "Certified Mail Field" field is displayed
    And the "Certified Mail Field" field only accepts numbers
    And the "Certified Mail Field" field hax a max length of 9

    When I click the "Certified Mail with Return Receipt" checkbox
    Then the "Certified Mail with Return Receipt Field" field is displayed
    And the "Certified Mail with Return Receipt Field" field only accepts numbers
    And the "Certified Mail with Return Receipt Field" field hax a max length of 9

    And I enter the following information into the form
      | Company | Company 019 |
      | Folder  | New Record  |
      | CM #    | 12345       |
      | CMRR #  | 123456      |
    When I click the "Submit & Open" button
    Then No record is added
    Then The record is added
      | Company |
      | Folder  |
      | CM #    |
      | CMRR #  |

  @12081
  Scenario: Select Meter Name
    When I click the "Add Record" button
    Then The add record modal is displayed

    When I enter the following information into the form
      | Company | Company 822 |
    Then Meter Name is is "DFS"

        # not 822 -> not DFS
    When I enter the following information into the form
      | Company | Company 019 |
    Then Meter Name is is "USI"

        # Open existing record and make same observations while editing
    When I click the "Submit & Open" button
    And I enter the following information into the form
      | Company | Company 822 |
    Then Meter Name is is "DFS"

    When I enter the following information into the form
      | Company | Company 019 |
    Then Meter Name is is "USI"

  @12749
  Scenario: Assigned To dropdown exists in new record modal
    When I click the "Add Record" button
    Then The following elements exist
      | Assigned To |
    # Implicitly, if there are no errors when I try to set the Assigned To dropdown, then that input exists.
    When I enter the following information into the form
      | Assigned To | Lisa Grey |
      # Make sure this employee is inactive in Setup
    Then "Priyanka Patra" is not in the "Assigned To" drop down
