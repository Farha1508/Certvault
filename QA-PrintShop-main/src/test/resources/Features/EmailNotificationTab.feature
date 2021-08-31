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

  @7776
  Scenario: Email Notification Tab Functionality
    When I click on the top record link
    And I click on the "Email Notification" link
    Then The following elements exist
      | Customer Email Address   |
      | Shipping Info             |
      | To Be Printed Email Sent |
      | Printed Email Sent        |
    And The email reminder text is displayed