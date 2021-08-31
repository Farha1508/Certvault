Feature: AMA-EB Login

  Scenario: Login, Add and Details Policy, KPI's, Grid, Import Policies and Logout

    Given I navigate to login page of EB app
    And I enter login credentials
      | email   | password |
      | XXXXXXXX| XXXXXXXX |
    And I click on Sign In button
    And Pagination value
      | pagevalue |
      | 20        |
    And Get the KPI Count

    ##################--------Add Policy-------####################
    When Click on Add Policy
    Then Enter Add Policy Details
      |AccountManager| Company                 | Status   | BusinessType | NewExisting  | Date       |  EmployerGroup | CoverageType| PriorCarrier| PriorPlanName |ExpiringPremium| ExpiringCommission |RenewalNewCarrier | RenewalNewPlanName |RenewalNewPremium | RenewalNewCommission| LeadSource |
      | renuka Gundu | ONI Risk Partners - PB  | Untouched| Prospect     | New          | 16/04/2019 |  Tes1          | Life        | Aetna       | Test            |12             | 12                 | Aetna            | Test                   | 12               | 12                  | Agency     |
    And Click on Submit button

    ##################--------Details Policy-------####################

    And I select a record under Policies tab
    Then Navigate to Policy Details section
      |AccountManagerDetails| CompanyDetails         | StatusDetails | BusinessTypeDetails | NewExistingDetails | DateDetails|  EmployerGroupDetails | CoverageTypeDetails| PriorCarrierDetails| ExpiringPremiumDetails| ExpiringCommissionDetails |RenewalNewCarrierDetails | RenewalNewPremiumDetails | RenewalNewCommissionDetails| LeadSourceDetails |
      | Prathima M          | ONI Risk Partners - PB | In Progress   | Renewal             | Existing           | 20/04/2019 |  xyz                  | Vision             | Chubb              |                    | 13                        | Chubb                   |  13                      | 13                         | Existing Customer |

    ###########---------Questions,Notes,History Tabs----###########

    Then Click on Add Note Button
    And Input values in the Note pop-up
      | Title | Description |
      | Note  | Note        |
    Then Input values in the Questions tab
      | Question1 | Question2 | Question3 | Question4|
      | Yes       | Yes       | Yes       | Yes      |
    And Click on Notes Tab
    And Click on History tab
    And Click on Submit and Close button

    ################--------Grid Search Filters--------##############

    Then Search with all the filters in the grid
      |AccountManagerDetails| CompanyDetails         | StatusDetails | BusinessTypeDetails | EmployerGroupDetails | CoverageTypeDetails|  ExpiringPremiumDetails| ExpiringCommissionDetails | PriorCarrierDetails|
      | Prathima M          | ONI Risk Partners - PB | In Progress   | Renewal             |  xyz                  | Vision            |       13               | 13                        | Aetna              |

    #######################---------KPI's-----------###############

    And Click on My Prospects KPI
    And I search a record with the Employer Group
      | employergroupsearch |
      |  tes1               |
    And I select a record under Policies tab
    And I move the record to Renewal
      | MovRec  |
      | Renewal |
    Then Click on Submit and Close button
    And Get the My Prospects KPI Count
    And Click on My Renewals KPI
    And I search a record with the Employer Group
      | employergroupsearch |
      |  tes1               |
    And I select a record under Policies tab
    And I move the record to New Business
      | MovRec1  |
      | New      |
    Then Click on Submit and Close button
    And Get the My Renewals KPI Count
    And Click On My New Business KPI
    And I search a record with the Employer Group
      | employergroupsearch |
      |  tes1               |
    And I select a record under Policies tab
    And Move the record to Lost Business
      | MovRec2  |
      | Lost     |
    Then Click on Submit and Close button
    And Get the My New Business KPI Count
    And Click On My Lost Business KPI
    And I search a record with the Employer Group
      | employergroupsearch |
      |  tes1               |
    And I select a record under Policies tab
    And Move the record to Work in Progress
      | MovRec3          |
      | Work in Progress |
    Then Click on Submit and Close button
    And Get the My Lost Business KPI Count
    And Click on reset button
    And I select a record under Policies tab
    And Move the record to YTD
      | MovRec4 |
      | Renewal |
    Then Click on Submit and Close button
    And Click on YTD KPI
    And I search a record with the Employer Group
      | employergroupsearch |
      |  tes1               |
    ###################-------Global Search-------###################

    And user searches with the following global value
      | globalsearch |
      | tes1         |

    ##################--------Delete Policy-------####################

    And Click on Delete button

    ##################--------Import Policy-------####################

    When Click on Import Policies button
    Then Click on Import Submit button
    And Click alert OK button

    ##################--------Reports-------------####################

    Then Click on reports button

    ##################--------Logout-------####################

    When Click on patra corp link
    Then Click on Logout icon
    And Click Logout button
