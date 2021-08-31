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
    #And set the tab to "Processing"
    ###---------- App selection ----------###

  @6678
  Scenario: Verify Submit functionality
    When I enter "245632" in the search field
    And I click the top search result
    And I enter the following information into the form
      | Company | Company 019 |
      | Service | Audits      |
    Then Meter Name is is "USI"
    When I click the "Submit" button
    Then The record is updated
    And The record is added
      | Company |
      | Service |

    When I enter the following information into the form
      | Company | Company 002 |
      | Service | DBR         |
    Then Meter Name is is "HEFFERNAN"
    When I click the "Submit" button
    Then The record is updated
    And The record is added
      | Company |
      | Service |

  @6679
  Scenario: Verify Submit and Close functionality
    When I enter "245632" in the search field
    And I click the top search result
    And I enter the following information into the form
      | Company | Company 019 |
      | Service | Audits      |
    Then Meter Name is is "USI"
    When I click the "Submit and Close" button
    Then The record is closed
    When I enter "245632" in the search field
    And I click the top search result
    Then The record is added
      | Company |
      | Service |

    When I enter the following information into the form
      | Company | Company 002 |
      | Service | DBR         |
    Then Meter Name is is "HEFFERNAN"
    When I click the "Submit and Close" button
    Then The record is closed
    When I enter "245632" in the search field
    And I click the top search result
    Then The record is added
      | Company |
      | Service |

  @10529
  Scenario: Verify Mandatory fields not filled and click 'Submit and Close'/ 'Submit' button
    When I enter "245632" in the search field
    And I click the top search result
    And I enter the following information into the form
      | Company | Select Company |
      | Folder  | Select Folder  |
    And I click the "Submit" button
    Then I see error warnings
    When I click the "Submit and Close" button
    Then I see error warnings

  @6680
  Scenario: Verify the Cancel functionality
    # prep the test case
    When I enter "245632" in the search field
    And I click the top search result
    And I enter the following information into the form
      | Company | Company 019 |
      | Service | Audits      |
    When I click the "Submit" button
    Then The record is updated
    And The record is added
      | Company |
      | Service |

    When I enter the following information into the form
      | Company | Company 002 |
      | Service | DBR         |
    And I update my expectations
      | Company | Company 019 |
      | Service | Audits      |
    And I click the "Cancel" button
    Then The record is added
      | Company |
      | Service |

  @6681
  Scenario: Verify the Close functionality
    # prep the test case
    When I enter "245632" in the search field
    And I click the top search result
    And I enter the following information into the form
      | Company | Company 019 |
      | Service | Audits      |
    When I click the "Submit" button
    Then The record is updated
    And The record is added
      | Company |
      | Service |

    When I enter the following information into the form
      | Company | Company 002 |
      | Service | DBR         |
    And I update my expectations
      | Company | Company 019 |
      | Service | Audits      |
    And I click the "Close" button
    Then The record is closed
    When I enter "245632" in the search field
    And I click the top search result
    Then The record is added
      | Company |
      | Service |

  @11389
  Scenario: Verify the Print PDF Functionality
    When I enter "245632" in the search field
    And I click the top search result
    And I take note of the record ID
    And I click the "Print PDF" button
    Then the PDF page is displayed
    # Todo: the "Assigned To" field is displayed in the PDF page

  @12240
    # failing due to https://github.com/patracorp/print-shop/issues/200
  Scenario: Edit Record - Verify conditional fields.
    # Step 1 (Submit)
    # prep the test case
    When I enter "245632" in the search field
    And I click the top search result
    And I prepare the conditional test case
    Then the "Certified Mail Field" field is not displayed
    And the "Certified Mail with Return Receipt Field" field is not displayed

    # Test
    When I click the "Certified Mail" checkbox
    Then the "Certified Mail Field" field is displayed
    And the "Certified Mail Field" field only accepts numbers
    And the "Certified Mail Field" field hax a max length of 9

    When I click the "Certified Mail with Return Receipt" checkbox
    Then the "Certified Mail with Return Receipt Field" field is displayed
    And the "Certified Mail with Return Receipt Field" field only accepts numbers
    And the "Certified Mail with Return Receipt Field" field hax a max length of 9

    When I enter the following information into the form
      | CM #   | 12345  |
      | CMRR # | 123456 |
    And I click the "Submit" button
    And I click the "OK" button
    Then The record is added
      | CM #   |
      | CMRR # |

    # Step 1 (Submit and close)
    # prep the test case
    Given I prepare the conditional test case

      # Test
    When I click the "Certified Mail" checkbox
    And I click the "Certified Mail with Return Receipt" checkbox
    And I enter the following information into the form
      | CM #   | 123459  |
      | CMRR # | 1234569 |
    And I click the "Submit and Close" button
    And I enter "245632" in the search field
    And I click the top search result
    Then The record is added
      | CM #   |
      | CMRR # |

    # Step 2 (Cancel)
    # prep the test case
    Given I prepare the conditional test case

      # Test
    When I click the "Certified Mail" checkbox
    And I click the "Certified Mail with Return Receipt" checkbox
    And I enter the following information into the form
      | CM #   | 12345  |
      | CMRR # | 123456 |
    And I click the "Cancel" button
    Then the "CM #" field is not displayed
    And the "CMRR #" field is not displayed
    # checkboxes are set to off. Fields are cleared.

    # Step 2 (Close)
    # prep the test case
    Given I prepare the conditional test case

      # Test
    When I click the "Certified Mail" checkbox
    And I click the "Certified Mail with Return Receipt" checkbox
    And I enter the following information into the form
      | CM #   | 1234599  |
      | CMRR # | 12345699 |
    And I click the "Close" button
    And I enter "245632" in the search field
    And I click the top search result
    Then the "CM #" field is not displayed
    And the "CMRR #" field is not displayed

    # Step 2 (Cancel with fields filled)
    # prep the test case
    Given I prepare the conditional test case

      # Test
    When I click the "Certified Mail" checkbox
    And I click the "Certified Mail with Return Receipt" checkbox
    And I enter the following information into the form
      | CM #   | 1234511  |
      | CMRR # | 12345611 |
    And I click the "Submit" button
    And I click the "OK" button
    And I click the "Certified Mail" checkbox
    And I click the "Certified Mail with Return Receipt" checkbox
    Then the "Certified Mail Field" field is not displayed
    And the "Certified Mail with Return Receipt Field" field is not displayed
    When I click the "Cancel" button
    Then The record is added
      | CM #   |
      | CMRR # |

    # Step 2 (Close with fields filled)
    # prep the test case
    Given I prepare the conditional test case

      # Test
    When I click the "Certified Mail" checkbox
    And I click the "Certified Mail with Return Receipt" checkbox
    And I enter the following information into the form
      | CM #   | 1234521  |
      | CMRR # | 12345621 |
    And I click the "Submit" button
    And I click the "OK" button
    And I click the "Certified Mail" checkbox
    And I click the "Certified Mail with Return Receipt" checkbox
    Then the "Certified Mail Field" field is not displayed
    And the "Certified Mail with Return Receipt Field" field is not displayed
    When I click the "Close" button
    And I enter "245632" in the search field
    And I click the top search result
    Then The record is added
      | CM #   |
      | CMRR # |

  @12750
  Scenario: Assigned To dropdown exists on the Edit Record page
    When I navigate to the "Processing" tab
    When I click on the top record link
    Then The following elements exist
      | Assigned To |
    # Implicitly, if there are no errors when I try to set the Assigned To dropdown, then that input exists.
    When I enter the following information into the form
      | Assigned To | Lisa Grey |
      # Make sure this employee is inactive in Setup
    Then "Priyanka Patra" is not in the "Assigned To" drop down

