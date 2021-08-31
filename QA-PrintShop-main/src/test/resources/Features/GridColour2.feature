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
    When set the tab to "Processing"
    ###---------- App selection ----------###

  @10027
  Scenario: Row color white for normal work orders
    Given I click the "Add Record" button
    And I enter the following information into the form
      | Company | Company 019 |
      | Folder  | New Record  |
    When I click the "Submit" button
    And I search the new record
    # maybe reconfigure to use "I enter that information into the grid header" step
    Then The top row background colour is "White"

  @10021
  Scenario: Row color Green for Certified Mail work orders
    Given I click the "Add Record" button
    And I enter the following information into the form
      | Company | Company 019 |
      | Folder  | New Record  |
    And I click the "Certified Mail" checkbox
    When I click the "Submit" button
    And I search the new record
    Then The top row background colour is "Certified Mail - Green"

    # Then The "Green" indicator mouseover displays "Certified Mail"

  @10022
  Scenario: Row purple for Rush work orders
    Given I click the "Add Record" button
    And I enter the following information into the form
      | Company | Company 019 |
      | Folder  | New Record  |
    And I click the "Rush" checkbox
    When I click the "Submit" button
    And I search the new record
    Then The top row background colour is "Rush - Purple"

    When I click on the top record link
    And I click the "Certified Mail" checkbox
    When I click the "Submit and Close" button
    Then The top row background colour is "Rush - Purple"

  @10024
  Scenario: Row Red for Overnight work orders
    Given I click the "Add Record" button
    And I enter the following information into the form
      | Company | Company 019 |
      | Folder  | New Record  |
    And I click the "Overnight" checkbox
    When I click the "Submit" button
    And I search the new record
    Then The top row background colour is "Overnight - Red"

    When I click on the top record link
    And I click the "Certified Mail" checkbox
    And I click the "Rush" checkbox
    When I click the "Submit and Close" button
    Then The top row background colour is "Overnight - Red"

  @12716
  Scenario: Row colour indicator mouseover text
    Then The "green" indicator mouseover text is "Certified Mail"
    Then The "red" indicator mouseover text is "Overnight"
    Then The "purple" indicator mouseover text is "Rush"