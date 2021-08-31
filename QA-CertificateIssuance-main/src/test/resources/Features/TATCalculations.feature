Feature: TAT Calculations

  Background: Login to Node and Navigating to PC home page
    Given I am on the login page
    When I enter the email and password for the "super user"
    And I click the Sign In button
    Then I will be taken to the apps page
    And I click on the "Work Order Tracking" tile
    Then I will be taken to the homepage for that app
    When I open "Certificate Issuance" for company "Company 002"

#C7931	TAT calculation for Add WO for non USI company
#C7934	TAT calculation for Add and Edit WO for non USI company

  # C12845	TAT for "Bulk" WO (Normal Bulk)
  #C12846	TAT for "Rush Bulk" WO (Rush / Priority Bulk)
  #C12959	TAT for "Rush" WO
  #C12960	TAT for Normal WO
  #C12961	Row Colorisation for "Rush / Priority Bulk" WO.

  # C9666	Verify start date Override functionality