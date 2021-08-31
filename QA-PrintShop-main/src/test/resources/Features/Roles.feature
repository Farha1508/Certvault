Feature: PrintShop Roles

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
    # When set the tab to "Processing"
    ###---------- App selection ----------###

  @11888
  Scenario: Verify USI company fields
    When I click the "Add Record" button
    And I enter the following information into the form
      | Company | Company 019          |
      | Service | Certificate Issuance |
      | Folder  | New Record           |
    Then The following fields are mandatory
      | Company |
      | Folder  |

    When I click the "Submit" button
    And Navigate to the new record
    Then The record is added
      | Company |
      | Service |
      | Folder  |

    When I return to the home page
    And I click the "Add Record" button
    And I enter the following information into the form
      | Company | Company 019          |
      | Service | Certificate Issuance |
      | Folder  | To Be Printed        |
    Then The following fields are mandatory
      | Company  |
      | Folder   |
      | Division |
      | Branch   |
    When I click the "Submit" button
    Then I see additional error warnings
    When I enter the following information into the form
      | Division | 100    |
      | Branch   | Albany |
    And I click the "Submit" button
    And Navigate to the new record
    Then The record is added
      | Company  |
      | Service  |
      | Folder   |
      | Division |
      | Branch   |

    When I return to the home page
    And I click the "Add Record" button
    And I enter the following information into the form
      | Company | Company 019          |
      | Service | Certificate Issuance |
      | Folder  | To Be Printed        |
    Then The following fields are mandatory
      | Company  |
      | Folder   |
      | Division |
      | Branch   |
    When I click the "Submit & Open" button
    Then I see additional error warnings
    When I enter the following information into the form
      | Division | 100    |
      | Branch   | Albany |
    And I click the "Submit & Open" button
    Then The record is added
      | Company  |
      | Service  |
      | Folder   |
      | Division |
      | Branch   |

  @10335
  Scenario: Validate Printshop user have full access to the application
    Given I click the "Add Record" button
    And I enter the following information into the form
      | Company | Company 019 |
      | Folder  | New Record  |
    When I click the "Submit" button
    And Navigate to the new record
    Then The record is added
      | Company |
      | Folder  |

  When I enter the following information into the form
    | Folder | To Be Printed |
  And I click the "Submit" button
  Then The record is updated

    When I enter the following information into the form
      | Folder | Printed |
    And I click the "Submit" button
    Then The record is updated

    When I enter the following information into the form
      | Folder | On Hold for Print Shop |
    And I click the "Submit" button
    Then The record is updated

    When I enter the following information into the form
      | Folder | On Hold for India |
    And I click the "Submit" button
    Then The record is updated

    When I enter the following information into the form
      | Folder | Discarded |
    And I click the "Submit" button
    Then The record is updated