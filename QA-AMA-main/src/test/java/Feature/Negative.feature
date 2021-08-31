@CompleteAmaNegative
Feature: AMA project negative testing in add policy pop up

  #SigningIn
  Scenario: Checking for AMA negative cases mandatory and numeric fields
    Given User navigates to login page
    And user enters credentials
      | username                      | password   |
      | xxxxxxxxxxxxxxxxxxxxxxxxxxxxx | xxxxxxxxxx |

    #Add policy - Checking for mandatory fields (TestRail C1474)
    And user checking for mandatory fields in add policy pop up
      | insuredfield     |
      | kiranchakrapani1 |

    #Adding policy - Checking for numeric fields (TestRail C1476)
    And user checking for numeric fields in add policy pop up
      | insuredfield     | expiringpremium | expcommissionpercentage | renewalnewpremium | renewalnewcommissionpercentage |
      | kiranchakrapani2 | 1234            | 10                      | 1256              | 10                             |

    #Edit policy - add note mandatory fields (TestRail C1518)
    #Notes tab - detail page (TestRail C1523)
    And user checking mandatory fields in add note popup for detail page

    #Edit policy - Delete button (TestRail C1540)
    And user checking for deleting a record from the existing records

    #Import policies - Excel sheet with incorrect information (TestRail C1639)
    And user importing excel sheet with incorrect information