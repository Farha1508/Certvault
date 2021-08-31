Feature: KPI Test Cases

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
    ###---------- App selection ----------###
    When I navigate to the "Processing" tab

  @6754
  Scenario: Verify the functionality of 'Assigned To Print' KPI
    Given I click the "Add Record" button
    And I enter the following information into the form
      | Company | Company 019 |
      | Folder  | New Record  |
    When I click the "Submit" button
    And I get the count for "Assigned to Print Shop" KPI
    And I search the new record
    And I click on the top record link
    And I enter the following information into the form
      | Folder | To Be Printed |
    And I click the "Submit and Close" button
    Then Verify that "Assigned to Print Shop" KPI has "increased"

  @6755
  Scenario: Verify the functionality of 'Assigned To Print Shop Carriers' KPI
    Given I click the "Add Record" button
    And I enter the following information into the form
      | Company | Company 019 |
      | Folder  | New Record  |
    When I click the "Submit" button
    And I get the count for "Assigned to Print Shop Carriers" KPI
    And I search the new record
    And I click on the top record link
    And I enter the following information into the form
      | Folder | To Be Printed |
    And I click the "Carrier Cert File Only" checkbox
    And I click the "Submit and Close" button
    Then Verify that "Assigned to Print Shop Carriers" KPI has "increased"

  @6756
  Scenario: Verify the functionality of 'On Hold For India' KPI
    Given I click the "Add Record" button
    And I enter the following information into the form
      | Company | Company 019 |
      | Folder  | New Record  |
    When I click the "Submit" button
    And I get the count for "On Hold For India" KPI
    And I search the new record
    And I click on the top record link
    And I enter the following information into the form
      | Folder | On Hold for India |
    And I click the "Submit and Close" button
    Then Verify that "On Hold For India" KPI has "increased"

  @6757
  Scenario: Verify the functionality of 'On Hold For Print Shop' KPI
    Given I click the "Add Record" button
    And I enter the following information into the form
      | Company | Company 019 |
      | Folder  | New Record  |
    When I click the "Submit" button
    And I get the count for "On Hold for Print Shop" KPI
    And I search the new record
    And I click on the top record link
    And I enter the following information into the form
      | Folder | On Hold for Print Shop |
    And I click the "Submit and Close" button
    Then Verify that "On Hold for Print Shop" KPI has "increased"

  @6758
  Scenario: Verify the functionality of 'Printed' KPI
    Given I click the "Add Record" button
    And I enter the following information into the form
      | Company | Company 019 |
      | Folder  | New Record  |
    When I click the "Submit" button
    And I get the count for "All" KPI
    And I search the new record
    And I click on the top record link
    And I enter the following information into the form
      | Folder | Printed |
    And I click the "Submit and Close" button
    Then Verify that "Printed Today" KPI has "increased"

  @10336
  Scenario: Validate records are displayed under all KPI folders when clicked
    # The name of this case does not match its contents at all
    Then The "Folder" header does not exist

    When I navigate to the "Printed" tab
    Then The "Folder" header does not exist

    When I navigate to the "Discarded" tab
    Then The "Folder" header does not exist

  @12243
  Scenario: Add new KPI's based on row colorisation
    Given I get the count for "All" KPI
    And I click the "Add Record" button
    And I enter the following information into the form
      | Company | Company 019   |
      | Folder  | To Be Printed |
    And I click the "Certified Mail" checkbox
    When I click the "Submit" button
    And I click the "OK" button
    And I refresh the page
    Then Verify that "Certified Mail" KPI has "increased"

    When I click the "Add Record" button
    And I enter the following information into the form
      | Company | Company 019   |
      | Folder  | To Be Printed |
    And I click the "Overnight" checkbox
    When I click the "Submit" button
    And I click the "OK" button
    And I refresh the page
    Then Verify that "Overnight" KPI has "increased"

    When I click the "Add Record" button
    And I enter the following information into the form
      | Company | Company 019   |
      | Folder  | To Be Printed |
    And I click the "Rush" checkbox
    When I click the "Submit" button
    And I click the "OK" button
    And I refresh the page
    Then Verify that "Rush" KPI has "increased"

  @12464
  Scenario: Verify the functionality of "Certified Mail" KPI
    When I get the count for "Certified Mail" KPI
    And I click on the "Certified Mail" KPI
    Then I verify the number of records in the "Processing" grid match the "Certified Mail" KPI
  # There are colour precidence checking here, but that's actually covered in the GridColour.feature tests

  @6753
  Scenario: Verify the functionality of "Rush" KPI
    When I get the count for "Rush" KPI
    And I click on the "Rush" KPI
    Then I verify the number of records in the "Processing" grid match the "Rush" KPI
  # There are colour precidence checking here, but that's actually covered in the GridColour.feature tests

  @12465
  Scenario: Verify the functionality of "Overnight" KPI.
    When I get the count for "Overnight" KPI
    And I click on the "Overnight" KPI
    Then I verify the number of records in the "Processing" grid match the "Overnight" KPI
  # There are colour precidence checking here, but that's actually covered in the GridColour.feature tests

  @12752
  Scenario: Verify the functionality of 'New Records' KPI
    When I get the count for "New Records" KPI
    And I click on the "New Records" KPI
    Then I verify the number of records in the "Processing" grid match the "New Records" KPI

    When I click the "Add Record" button
    And I enter the following information into the form
      | Company | Company 019   |
      | Folder  | New Record |
    And I click the "Submit" button
    And I click the "OK" button
    Then Verify that "New Records" KPI has "increased"

  @12753
  Scenario: Certified Mail, Rush, and Overnight records should only be displayed in the KPI if they are set to To Be Printed
    # Records are added during 12243, so we should not have to worry about having empty grids
    When I get the count for "Certified Mail" KPI
    When I click on the "Certified Mail" KPI
    And I click on the top record link
    And I update my expectations
    | Folder | To Be Printed |
    Then The record is added
      | Folder |

    When I enter the following information into the form
    | Folder | New Record |
    And I click the "Submit and Close" button
    Then Verify that "Certified Mail" KPI has "decreased"

    When I get the count for "Overnight" KPI
    When I click on the "Overnight" KPI
    And I click on the top record link
    And I update my expectations
      | Folder | To Be Printed |
    Then The record is added
      | Folder |

    When I enter the following information into the form
      | Folder | New Record |
    And I click the "Submit and Close" button
    Then Verify that "Overnight" KPI has "decreased"

    When I get the count for "Rush" KPI
    When I click on the "Rush" KPI
    And I click on the top record link
    And I update my expectations
      | Folder | To Be Printed |
    Then The record is added
      | Folder |

    When I enter the following information into the form
      | Folder | New Record |
    And I click the "Submit and Close" button
    Then Verify that "Rush" KPI has "decreased"
