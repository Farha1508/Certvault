Feature:Automating test-case C11743
  Scenario: Testing Reassign button at the hand-off model
    Given I am on the login page
    And I enter the email and password for the "Owner" user
    When I click the "SIGN IN" button
    Then User should taken to the QBIS home page successfully
    Then Open the application by  hamburger menu
    Then I click the "Continue" button
    Then I click the "Reassign" button
    And I select "Test Finance Agent (10009) - Denver - CO" from the "Select an agency" drop down
    Then I click the "CONTINUE" button
    And I select "QBIS/Test Finance Agent Admin admin+test_finance_agent@qbisins.com" from the "Select an agent" drop down
    Then I click the "CONTINUE" button
    Then Verified that the application has been reassigned successfully
    And I wait for 5 seconds