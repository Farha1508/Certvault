Feature: Global Search

  Background: Sign In
    Given I am on the login page
    When I enter the email and password for the "super user"
    And I click the "Sign in" button
    Then I will be taken to the apps page
    And I click on the "Work Order Tracking" tile
    Then I will be taken to the homepage for that app
    When I open "Certificate Issuance" for company "Company 019"

# C9503	Verify the functionality of Global search
#C9504	Verify the Global search functionality with invalid data

  # Global search. Place in different feature file.
  @9504
  Scenario: Grid- Verify Enter text that will give Zero (0) results
    When I enter "00000000dss" into the "Requestor" grid header
    Then There is no data in the grid