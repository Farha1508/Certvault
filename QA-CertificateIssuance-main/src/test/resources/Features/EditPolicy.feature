Feature: Edit Work Orders

  Background: Sign In
    Given I am on the login page
    When I enter the email and password for the "super user"
    And I click the "Sign in" button
    Then I will be taken to the apps page
    And I click on the "Work Order Tracking" tile
    Then I will be taken to the homepage for that app
    When I open "Certificate Issuance" for company "Company 019"

  @14109
  Scenario: Verify new field on PDF.
    Given I click the "Add Work Order" button
    And I select the "Discard" checkbox
    And If the confirmation modal displays, dismiss it
    And I select "Out of Office Emails" from the "Discarded Reason" drop down
    And I click the "Submit and Open" button

    When I click the "Print PDF" button
    And I wait for "2" seconds
    # wait for the new tab to open and load
    And I switch to new tab opened
    Then The following strings are displayed on the page
      | Discard              |
      | Discarded Reason     |
      | Out of Office Emails |

  @14 @1377
    # @1377 is just checking that fields exist
  Scenario: Verify Submit button functionality in WO
    Given I click the "Add Work Order" button
    And I enter the following information into the form
      | Requestor | Test@patracorp.com |
      | Sent To   | sentto@patracorp   |
      | Summary   | <current date>     |
    And I click on the "Work Order Details" link
    And I enter the following information into the form
      | # Of Certs           | 15                 |
      | Client Code          | TEST code1         |
      | Client Name          | TEST Rami          |
      | Work Order Type      | New Business       |
      | Branch Office        | Albany             |
      | Department           | 201                |
      | Division             | 100                |
      | Profit Center        | Test               |
      | Cert Master          | Test               |
      | Cert Issuance System | Test               |
      | # of Holders Issued  | 5                  |
      | # Holders Dated Off  | 2                  |
      | # Holders Updated    | 2                  |
      | Cert Issued By       | Anusha E.          |
      | QA By                | Chitti Raju        |
      | Delivery By          | Boddeda Vanaja     |
      | Complexity           | Easy               |
      | Reasons for Email    | Incomplete Request |
    And I click the "Submit and Open" button
    Then The record is added
      | Requestor            |
      | Sent To              |
      | Summary              |
      | # Of Certs           |
      | Client Code          |
      | Client Name          |
      | Work Order Type      |
      | Branch Office        |
      | Department           |
      | Division             |
      | Profit Center        |
      | Cert Master          |
      | Cert Issuance System |
      | # of Holders Issued  |
      | # Holders Dated Off  |
      | # Holders Updated    |
      | Cert Issued By       |
      | QA By                |
      | Delivery By          |
      | Complexity           |
      | Reasons for Email    |

    When I enter the following information into the form
      | Requestor            | Samantha Traynor              |
      | Sent To              | Commander Shepard             |
      | Summary              | Cision Pro Mark IV Toothbrush |
      | # Of Certs           | 6000                          |
      | Client Code          | QUASAR                        |
      | Client Name          | Doran                         |
      | Work Order Type      | Special Project               |
      | Branch Office        | New York                      |
      | Department           | 614                           |
      | Division             | 702                           |
      | Profit Center        | CyberSure                     |
      | Cert Master          | Barla Von                     |
      | Cert Issuance System | Shadow Broker                 |
      | # of Holders Issued  | 10                            |
      | # Holders Dated Off  | 4                             |
      | # Holders Updated    | 8                             |
      | Cert Issued By       | Carl Wahlstrom                |
      | QA By                | Carl Wahlstrom                |
      | Delivery By          | Carl Wahlstrom                |
      | Complexity           | Difficult                     |
      | Reasons for Email    | Notification Email            |
    And I click the "Submit" button
    And I store the work order number
    And I click the "Close" button
    And I search the stored work order number
    And I click on the top work order link
    Then The record is added
      | Requestor            |
      | Sent To              |
      | Summary              |
      | # Of Certs           |
      | Client Code          |
      | Client Name          |
      | Work Order Type      |
      | Branch Office        |
      | Department           |
      | Division             |
      | Profit Center        |
      | Cert Master          |
      | Cert Issuance System |
      | # of Holders Issued  |
      | # Holders Dated Off  |
      | # Holders Updated    |
      | Cert Issued By       |
      | QA By                |
      | Delivery By          |
      | Complexity           |
      | Reasons for Email    |

#  @2238
#  Scenario: SLA Missed field
    # blocked by wot-certs#757
#    Given I click the "Add Work Order" button
#    When I enter the following information into the form
#      | Status              | Completed        |
#      | Start Date Override | 06/28/2021 9:00 AM |
#      | Due Date Overrride  | 05/28/2021 9:00 AM |
#
#    And I select "No" from the "SLA MET" header in the grid
#    And I click on the top work order link

  @9813
  Scenario: Verify the work order status when user enter the bulk/rush in the summary
    When I click the "Add Work Order" button
    And I click the "Submit and Open" button
    And I enter "Rush" in the "Summary" field
    When I click the "Submit" button
    Then The "Rush/ Bulk :" label is "Normal"
    Then The "From Pending :" label is "No"

  @1369
  Scenario: Verify Add & view Attachments Functionality
    When I click on the top work order link
    And I upload an attachment
    Then The file will be displayed in the Attachments grid

  @9368
  Scenario: Verify Add Attachments Cancel Button Functionality
    When I click the "Add Work Order" button
    And I click the "Submit and Open" button
    And I cancel an attachment
    Then The file will not be displayed in the Attachments grid

  @1368
  Scenario: Verify original email field for existing workorder
    When I click on the top work order link
    And I click on the "Original Email" link
    Then The Original Email field is displayed
    And The Original Email field contents are not null
    When I enter "dfafaffafa" into the Original Email field
    Then The Original Email field contents are unchanged

#  @11745
#  Scenario: Upload attachments manually using "S3" attachment server

  @16
  Scenario: Verify “Cancel Changes” button functionality in a WO
    Given I click the "Add Work Order" button
    And I enter the following information into the form
      | Requestor | Test@patracorp.com  |
      | Sent To   | sentto@patracorp    |
      | Summary   | A Normal toothbrush |
    And I click on the "Work Order Details" link
    And I enter the following information into the form
      | # Of Certs           | 15                 |
      | Client Code          | TEST code1         |
      | Client Name          | TEST Rami          |
      | Work Order Type      | New Business       |
      | Branch Office        | Albany             |
      | Department           | 201                |
      | Division             | 100                |
      | Profit Center        | Test               |
      | Cert Master          | Test               |
      | Cert Issuance System | Test               |
      | # of Holders Issued  | 5                  |
      | # Holders Dated Off  | 2                  |
      | # Holders Updated    | 2                  |
      | Cert Issued By       | Anusha E.          |
      | QA By                | Chitti Raju        |
      | Delivery By          | Boddeda Vanaja     |
      | Complexity           | Easy               |
      | Reasons for Email    | Incomplete Request |
    And I click the "Submit and Open" button
    Then The record is added
      | Requestor            |
      | Sent To              |
      | Summary              |
      | # Of Certs           |
      | Client Code          |
      | Client Name          |
      | Work Order Type      |
      | Branch Office        |
      | Department           |
      | Division             |
      | Profit Center        |
      | Cert Master          |
      | Cert Issuance System |
      | # of Holders Issued  |
      | # Holders Dated Off  |
      | # Holders Updated    |
      | Cert Issued By       |
      | QA By                |
      | Delivery By          |
      | Complexity           |
      | Reasons for Email    |

    When I enter the following information into the form
      | Requestor            | Samantha Traynor              |
      | Sent To              | Commander Shepard             |
      | Summary              | Cision Pro Mark IV Toothbrush |
      | # Of Certs           | 6000                          |
      | Client Code          | QUASAR                        |
      | Client Name          | Doran                         |
      | Work Order Type      | Special Project               |
      | Branch Office        | New York                      |
      | Department           | 614                           |
      | Division             | 702                           |
      | Profit Center        | CyberSure                     |
      | Cert Master          | Barla Von                     |
      | Cert Issuance System | Shadow Broker                 |
      | # of Holders Issued  | 10                            |
      | # Holders Dated Off  | 4                             |
      | # Holders Updated    | 8                             |
      | Cert Issued By       | Carl Wahlstrom                |
      | QA By                | Carl Wahlstrom                |
      | Delivery By          | Carl Wahlstrom                |
      | Complexity           | Difficult                     |
      | Reasons for Email    | Notification Email            |
    And I click the "Cancel Changes" button
    And I update my expectations
      | Requestor            | Test@patracorp.com  |
      | Sent To              | sentto@patracorp    |
      | Summary              | A Normal toothbrush |
      | # Of Certs           | 15                  |
      | Client Code          | TEST code1          |
      | Client Name          | TEST Rami           |
      | Work Order Type      | New Business        |
      | Branch Office        | Albany              |
      | Department           | 201                 |
      | Division             | 100                 |
      | Profit Center        | Test                |
      | Cert Master          | Test                |
      | Cert Issuance System | Test                |
      | # of Holders Issued  | 5                   |
      | # Holders Dated Off  | 2                   |
      | # Holders Updated    | 2                   |
      | Cert Issued By       | Anusha E.           |
      | QA By                | Chitti Raju         |
      | Delivery By          | Boddeda Vanaja      |
      | Complexity           | Easy                |
      | Reasons for Email    | Incomplete Request  |
    Then The record is added
      | Requestor            |
      | Sent To              |
      | Summary              |
      | # Of Certs           |
      | Client Code          |
      | Client Name          |
      | Work Order Type      |
      | Branch Office        |
      | Department           |
      | Division             |
      | Profit Center        |
      | Cert Master          |
      | Cert Issuance System |
      | # of Holders Issued  |
      | # Holders Dated Off  |
      | # Holders Updated    |
      | Cert Issued By       |
      | QA By                |
      | Delivery By          |
      | Complexity           |
      | Reasons for Email    |

  @42
  Scenario: Verify Work Order Close Button Functionality
    When I click on the top work order link
    Then The "Work Order Detail" page is displayed
    When I click the "Close" button
    Then The "Certificate Issuance" page is displayed