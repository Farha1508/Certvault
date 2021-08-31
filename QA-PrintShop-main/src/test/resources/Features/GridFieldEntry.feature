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

  @5454
  Scenario: Enter text using non-allowed characters
    # It appears that non-allowed characters can be typed in,
    # and the expectation is that no entries will be displayed in the grid.
    When I enter "Letters" into the "# of Holder Certs" grid header
    Then There are no entries in the grid

    When I clear the "# of Holder Certs" header
    And I enter "Letters" into the "# of Insured Certs" grid header
    Then There are no entries in the grid

    When I clear the "# of Insured Certs" header
    And I enter "Letters" into the "# of Letters /Notices /Docs" grid header
    Then There are no entries in the grid

  @5453
  Scenario: Enter text that will give 0 results
    When I enter "Carl is the best" into the "Client Code" grid header
    Then There are no entries in the grid

  @5451
  Scenario: Enter text that will give unique result
    # sort first because small record id numbers can yield more than one result.
    When I sort the "Record ID" grid column by "descending"
    When I get the "Record ID" for the "row 4" row of the grid
    And I enter that information into the grid header
    Then The information in the first row of the grid will match what was entered
    Then There is 1 row filtered

  @5452
  Scenario: Enter text that will give multiple results
    When I enter "24" into the "Record ID" grid header
    Then There is more than 1 row filtered