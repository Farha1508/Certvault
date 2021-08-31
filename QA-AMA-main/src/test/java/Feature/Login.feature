Feature: AMA project for PMA and AMA

  #Add Policy - Happy Path (Selenium script) TestRail C1573
  #SigningIn
  @HappyPath
  Scenario: Testing AMA and moving an opportunity from PMA to AMA
    Given User navigates to login page
    And user enters credentials
      | username                      | password   |
      | xxxxxxxxxxxxxxxxxxxxxxxxxxxxx | xxxxxxxxxx |

  #AddingPolicy
    And user adding the policy into the ama
      | agencymanagementcode | insured              | produceroffice | expiringpremium | expiringcommissionage | expiringcommission | renewalnewpremium | renewalnewcommissionage | renewalnewcommission |
      | 10010001             | kirans businesscode3 | test1          | 100             | 10                    | 10                 | 200               | 10                      | 20                   |

  #EditingRecord
  #History tab - Detail page (TestRail C1536)
  #Questions tab - detail page (TestRail C1519)
    And user edit the record
      | naics     | zipcode | noofemployees | annualgrossrevenue |
      | 651632138 | 44444   | 100           | 100000             |

  #KPIs
  #TestRail C1479/C1513/C1514/C1515/C1516/C1417
    And user moves the record from my expiration KPI to my renewals KPI
    And user moves the record from My Renewals KPI to My New Business KPI
    And user moves the record from My New Business KPI to My Lost Business KPI
    And user moves the record from My Lost Business KPI to My Work in Progress KPI
    And user moves the record from My Work in Progress to My YTD Policy Count KPI
    And user checking for record in My YTD Policy Count KPI

  #GlobalSearch
  #TestRail C1575 (Common test cases)
    And user searching for record using global search

  #ViewYearlySummary
  #TestRail C1481/C1733
    And user checking for view yearly summary

  #ImportingPolicies
  #TestRail C1478/C1640/C1642/C1643
    And user importing policies using import policies functionality

  #GridSortingFunctionality
  #TestRail C1819
    And user checking for polcies tab grid filters and sorting functionality

  #PaginaTion
    And User checking for pages next and previous buttons
    And User checking for pagination of the policies tab

  #UserLogout
    And User logs out of the application






