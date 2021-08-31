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

  @10465 @10466
  Scenario: Validate Changing Folder to To Be Printed
    Given I click the "Add Record" button
    And I enter the following information into the form
      | Company | Company 019 |
      | Folder  | New Record  |
    And I click the "Overnight" checkbox
    And I click the "Submit & Open" button

    When I enter the following information into the form
      | Folder | To Be Printed |
    Then The "Assigned to PS Date" field contains "<current date>"

    # Field is not editable
    When I enter the following information into the form
      | Assigned to PS Date | 03/08/1990 |
    Then The "Assigned to PS Date" field contains "<current date>"

  # C10466
    When I enter the following information into the form
      | Folder | Printed |
    Then The "Print Date" field contains "<current date>"
    And The "Mail Date" field contains "<current date>"
    And The "Mail Date" field contains "3:15 PM"

    # Print Date Field is not editable, but Mail Date is
    When I enter the following information into the form
      | Print Date | 03/08/1990 |
      | Mail Date  | 03/08/1990 |
    Then The "Print Date" field contains "<current date>"
    Then The "Mail Date" field contains "03/08/1990"

  @10462 @10464
  Scenario: Verify Client Code entered causes the Bulk/Renewal check box to be checked / Verify Client Code if deleted causes the Bulk/Renewal check box to be unchecked
    Given I click the "Add Record" button
    And I enter the following information into the form
      | Company | Company 019 |
      | Folder  | New Record  |
    And I click the "Overnight" checkbox
    And I click the "Submit & Open" button
    Then The "Bulk/Renewal" checkbox is "not checked"

    When I enter the following information into the form
      | Client Code | Carl is pretty |
      | Notes       | Carl is pretty |
    # put in a second entry so focus is moved away from the Client Code field
    Then The "Bulk/Renewal" checkbox is "checked"

    When I clear the "Client Code" field
    And I enter the following information into the form
      | Notes | Carl is pretty |
    # put in a second entry so focus is moved away from the Client Code field
    Then The "Bulk/Renewal" checkbox is "not checked"

  @10463
  Scenario: Verify Checking Bulk/Renewal makes Client Code be required
    Given I click the "Add Record" button
    And I enter the following information into the form
      | Company | Company 019 |
      | Folder  | New Record  |
    And I click the "Overnight" checkbox
    And I click the "Submit & Open" button
    Then The "Bulk/Renewal" checkbox is "not checked"

  When I click the "Bulk/Renewal" checkbox
  And I click the "Submit" button
  Then The validation error is displayed
