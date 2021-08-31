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

  @6730 @10527
  Scenario: Verify Error Tracking Tab Functionality
    When I sort the "Record ID" grid column by "descending"
    When I click on the top record link
    And I click on the "Error Tracking" link
    Then The following elements exist
      | Error Type         |
      | Error Description |
    When I enter the following information into the form
      | Error Type         | Duplicate Record |
      | Error Description | <current date>  |
    #And I click the "Submit" button
    Then The record is added
      | Error Type         |
      | Error Description |
    When I enter the following information into the form
      | Error Type | Incorrect File Attached |
    Then The record is added
      | Error Type |

    # @10527 Verify fields under Error Tacking tab

    When I click the "Submit" button
    Then The record is updated
    And I click on the "Error Tracking" link
    And The record is added
      | Error Type         |
      | Error Description |