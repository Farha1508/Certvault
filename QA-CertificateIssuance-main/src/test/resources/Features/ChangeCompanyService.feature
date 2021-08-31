Feature: Change Company and Service

  Background: Sign In
    Given I am on the login page
    When I enter the email and password for the "super user"
    And I click the "Sign in" button
    Then I will be taken to the apps page
    And I click on the "Work Order Tracking" tile
    Then I will be taken to the homepage for that app
    When I open "Certificate Issuance" for company "Company 019"

  @9490
  Scenario: Verify Company Selection functionality
    When I select "Company 002" from the "Change Company" navbar dropdown
    Then The "Change Company" change modal "is" displayed
    Then The "Change Company" navbar dropdown displays "Company 002"
    # Should show "Change Company" pop-up saying Are you sure want to change company ? with Confirm and Cancel buttons.

    When I click the "cancel" button
    Then The "Change Company" change modal "is not" displayed
    Then The "Change Company" navbar dropdown displays "Company 019"

    When I select "Company 002" from the "Change Company" navbar dropdown
    Then The "Change Company" change modal "is" displayed
    When I click the "confirm" button
    Then The "Change Company" change modal "is not" displayed
    Then The "Change Company" navbar dropdown displays "Company 002"

  @9491
  Scenario: Verify validation for Company selection
    When I select "Company 021" from the "Change Company" navbar dropdown
    Then The "Change Company" change modal "is" displayed
    Then The "Change Company" navbar dropdown displays "Company 021"

    When I click the "confirm" button
    Then The "Change Note" change modal "is" displayed
    Then The "Change Company" change modal "is not" displayed
    #show "Change Note" pop-up saying "This service not applicable for selected company" with Ok button, if service is not mapped for that company.

    When I click the "OK" button
    Then The "Change Company" change modal "is not" displayed
    Then The "Change Note" change modal "is not" displayed
    Then The "Change Company" navbar dropdown displays "Company 019"

  @9492
  Scenario: Verify service change functionality for one company with 2 services
    When I select "Policy Checking" from the "Change Service" navbar dropdown
    Then The "Change Service" change modal "is" displayed
    Then The "Change Service" navbar dropdown displays "Policy Checking"
    # Should close the "Change Service" pop-up along with the service name should reset in service dropdown.

    When I click the "cancel" button
    Then The "Change Service" change modal "is not" displayed
    Then The "Change Service" navbar dropdown displays "Certificate Issuance"

    When I select "Policy Checking" from the "Change Service" navbar dropdown
    Then The "Change Service" change modal "is" displayed
    When I click the "confirm" button
    Then The "Change Service" change modal "is not" displayed
    Then The "Change Service" navbar dropdown displays "Policy Checking"

  @9493
  Scenario: Verify validation for service selection
    When I select "Commission" from the "Change Service" navbar dropdown
    Then The "Change Service" change modal "is" displayed
    Then The "Change Service" navbar dropdown displays "Commission"

    When I click the "confirm" button
    Then The "Change Note" change modal "is" displayed
    Then The "Change Service" change modal "is not" displayed
    #show "Change Note" pop-up saying "This service not applicable for selected company" with Ok button, if service is not mapped for that company.

    When I click the "OK" button
    Then The "Change Service" change modal "is not" displayed
    Then The "Change Note" change modal "is not" displayed
    Then The "Change Service" navbar dropdown displays "Certificate Issuance"

