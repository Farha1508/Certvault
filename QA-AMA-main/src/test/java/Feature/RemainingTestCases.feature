@RemainingTestCases
Feature: AMA Submit and Open button testing

  Scenario: Checking for automating remaining test cases in TestRail
    Given User navigates to login page
    And user enters credentials
      | username                      | password   |
      | xxxxxxxxxxxxxxxxxxxxxxxxxxxxx | xxxxxxxxxx |

    #Add Policy - Mandatory fields not filled and click Submit and Open (TestRail C1572)
    And user adding policy for checking mandatory fields with submit and open button

    #Add Policy - Fields default values (C1526)
    And user checking for fields are defaulting to blank and select after adding a record

    #Add Policy - Cancel Button (TestRail C1495)
    And user checking for cancel button in add policy popup

    #Edit Record - Cancel Changes (TestRail C1501)
    And user checking for cancel changes button in detail page

    #Edit Record - Mandatory Fields not filled and click Submit (TestRail C1477)
    And user checking for mandatory fields in detail or edit page
      | detailpageinsured       |
      | Mandatory field testing |

    #Edit Record - Mandatory Fields not filled and click Submit and Next (TestRail C1500)
    And user checking for submit next button in detail page

    #Edit Record - Mandatory Fields not filled and click Submit and close(TestRail C1499)
    And user checking for mandatory field with submit and close button in detail page

    #Add Note - Cancel button (TestRail C1705)
    And user checking for cancel button in add note popup

