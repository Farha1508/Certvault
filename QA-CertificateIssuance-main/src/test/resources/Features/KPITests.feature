Feature: KPI Tests

  Background: Login to Node and Navigating to PC home page
    Given I am on the login page
    When I enter the email and password for the "super user"
    And I click the Sign In button
    Then I will be taken to the apps page
    And I click on the "Work Order Tracking" tile
    Then I will be taken to the homepage for that app
    When I open "Certificate Issuance" for company "Company 002"
    And I navigate to the "In Process" tab

  @1465 @1546 @1545
  Scenario: Verifying Certificate Issuance folder to Quality Assurance folder movement functionality
    # C1545
    When I get the count for "all" KPI
    When I click the "Add Work Order" button
    And I click the "Submit" button
    And I get the new work order number from the confirmation modal
    And I search the stored work order number and wait up to 20 seconds
    # Refresh page because numbers don't seem to always update right away.
    And I refresh the page
    Then Verify that "Certificate Issuance Total" KPI has "Increased"

    # C1465
    When I get the count for "all" KPI
    #And I search the stored work order number and wait up to 20 seconds
    And I click on the top work order link
    And I select "Quality Assurance" from the "Folder" drop down
    Then The value of the "Status" dropdown is "Available"
    And The value of the "Folder" dropdown is "Quality Assurance"
    And The value of the "Process" dropdown is "Select Process"

    When I click the "Submit" button
    And I click the "Close" button
    And I click on the "Quality Assurance" KPI
    And I search the stored work order number and wait up to 20 seconds
    Then The work order will be displayed in the grid

    # C1546
    # Refresh page because numbers don't seem to always update right away.
    When I refresh the page
    Then Verify that "Certificate Issuance Total" KPI has "Decreased"
    Then Verify that "Quality Assurance Total" KPI has "Increased"

  @1466 @1547
  Scenario: Verifying Quality Assurance folder to Delivery folder movement functionality
    Given I click the "Add Work Order" button
    And I select "Quality Assurance" from the "Folder" drop down
    And I click the "Submit" button
    And I get the new work order number from the confirmation modal
    And I click on the "Quality Assurance" KPI
    And I search the stored work order number and wait up to 20 seconds
    And I wait for "4" seconds
    # Refresh page because numbers don't seem to always update right away.
    And I refresh the page

    When I get the count for "all" KPI
    And I click on the top work order link
    And I select "Document Delivery" from the "Folder" drop down
    And If the confirmation modal displays, dismiss it
    Then The value of the "Status" dropdown is "Available"
    And The value of the "Folder" dropdown is "Document Delivery"
    And The value of the "Process" dropdown is "Select Process"

    When I click the "Submit" button
    And I click the "Close" button
    And I click on the "Delivery" KPI
    And I search the stored work order number and wait up to 20 seconds
    Then The work order will be displayed in the grid

    # C1547
    And I wait for "4" seconds
    # Refresh page because numbers don't seem to always update right away.
    When I refresh the page
    Then Verify that "Quality Assurance Total" KPI has "Decreased"
    Then Verify that "Delivery Total" KPI has "Increased"

  @1467
  Scenario: Verify movement from Document Delivery to TWB On Hold
    Given I click the "Add Work Order" button
    And I select "Document Delivery" from the "Folder" drop down
    And I click the "Submit" button
    And I get the new work order number from the confirmation modal
    And I click on the "Delivery" KPI
    And I search the stored work order number and wait up to 20 seconds
    # Refresh page because numbers don't seem to always update right away.
    And I refresh the page

    When I get the count for "all" KPI
    And I click on the top work order link
    And I select the "Delivered" checkbox
    And If the confirmation modal displays, dismiss it
    Then The value of the "Status" dropdown is "On Hold"
    And The "Put On Hold" checkbox "is" selected
    #And The value of the "Date Delivery" field is "<today>"

    When I click the "Submit" button
    And I select "DEL - TWB" from the "Process" drop down
    Then The value of the "Status" dropdown is "In Progress"
    And The value of the "Folder" dropdown is "TWB On Hold"

    When I click the "Submit" button
    And I click the "Close" button
    And I navigate to the "In Process" tab
    And I click the Reset button in the grid header
    And I search the stored work order number and wait up to 20 seconds
    Then The work order will be displayed in the grid
    And The "Folder" for "row 1" in the grid is "TWB On Hold"

  @1468 @1557
  Scenario: Verify movement from TWB On Hold to Completed
    Given I click the "Add Work Order" button
    And I select "TWB On Hold" from the "Folder" drop down
    And I click the "Submit" button
    And I get the new work order number from the confirmation modal
    And I search the stored work order number and wait up to 20 seconds
    # Refresh page because numbers don't seem to always update right away.
    And I refresh the page

    When I get the count for "all" KPI
    And I click on the top work order link
    And I select the "Task Completed" checkbox
    And If the confirmation modal displays, dismiss it
    Then The value of the "Status" dropdown is "Completed"
    And The value of the "Folder" dropdown is "Completed Work Orders"
    And The value of the "Process" dropdown is "Select Process"
    #And The value of the "Date Completed" field is "<today>"

    #C1557
    When I click the "Submit" button
    And I click the "Close" button
    And I navigate to the "Completed" tab
    And I search the stored work order number and wait up to 20 seconds
    Then The work order will be displayed in the grid
    And The "Status" for "row 1" in the grid is "Completed"

  @1549 @1470
  Scenario: Verify functionality of Email Needed KPI
    When I click on the "Email Needed" KPI
    Then The "Folder" for "row 1" in the grid is "Email Needed"

    Given I click the "Add Work Order" button
    And I select "Email Needed" from the "Folder" drop down
    And I click the "Submit" button
    And I get the new work order number from the confirmation modal
    And I search the stored work order number
    # Refresh page because numbers don't seem to always update right away.
    And I wait for "6" seconds
    And I refresh the page
    And I get the count for "all" KPI

  # C1470
    When I click on the top work order link
    And I select "Email" from the "Process" drop down
    Then The value of the "Folder" dropdown is "Email Needed"
    When I select the "Task Completed" checkbox
    Then The value of the "Process" dropdown is "Select Process"
    And The value of the "Status" dropdown is "On Hold"
    And The value of the "Folder" dropdown is "Pending Responses"
    And The "Put On Hold" checkbox "is" selected
    And The following elements exist
      | Hold Reason |

    When I click the "Submit" button
    And I click the "Close" button
  # Refresh page because numbers don't seem to always update right away.
    And I wait for "4" seconds
    When I refresh the page
    Then Verify that "Email Needed Available" KPI has "Decreased"
    Then Verify that "Pending Responses Available" KPI has "Increased"

  @1550
  Scenario: Verify functionality of Pending Responses KPI
    Given I get the count for "all" KPI
    When I click on the "Pending Responses" KPI
    Then The "Folder" for "row 1" in the grid is "Pending Responses"
    And Verify that "Pending Responses Available" KPI has "not changed"
    And Verify that "Pending Responses Past Due" KPI has "not changed"

  @1471
  Scenario: Verify movement to Discarded Work Orders
    Given I click the "Add Work Order" button
    And I click the "Submit and Open" button
    And I store the work order number
    And I select the "Discard" checkbox
    # Then an alert is displayed stating
    Then A modal is displayed stating "Please be sure you want to discard this work order"

    When I click the "ok" button
    Then The value of the "Status" dropdown is "Discarded"
    Then The value of the "Folder" dropdown is "Discarded Work Orders"

    When I click the "Submit" button
    Then The "Discarded Reason" dropdown warning is displayed

    When I select "Failure notice" from the "Discarded Reason" drop down
    And I click the "Submit" button
    And I click the "Close" button
    And I navigate to the "Discarded" tab
    And I search the stored work order number and wait up to 20 seconds
    Then The work order will be displayed in the grid

  @1472
  Scenario: Verify movement to Important Instructions
    Given I click the "Add Work Order" button
    And I select "Important Instructions" from the "Folder" drop down
    And I click the "Submit" button
    And I get the new work order number from the confirmation modal

    When I navigate to the "Important Instruction" tab
    Then The "Folder" for "row 1" in the grid is "Important Instructions"

    When I search the stored work order number
    Then The work order will be displayed in the grid

  @1473
  Scenario: Verify movement to "To Be Discarded" tab.
    Given I click the "Add Work Order" button
    And I enter "discard" in the "Summary" field
    And I click the "Submit" button
    And I get the new work order number from the confirmation modal

    When I navigate to the "To Be Discarded" tab
    And I search the stored work order number
    Then The work order will be displayed in the grid

  # Todo: implement this scenario. Is a bit of a pain.
#  @1548
#  Scenario: Verify functionality of Incoming Responses KPI
#  When I click on the "Incoming Responses" KPI

  @1475
  Scenario: Verify movement to Incoming Responses
    When I get the count for "all" KPI
    And I click the "Add Work Order" button
    And I select "Incoming Responses" from the "Folder" drop down
    And I click the "Submit" button
    And I get the new work order number from the confirmation modal
    And I wait for "6" seconds
    And I refresh the page
    Then Verify that "Incoming Responses Available" KPI has "Increased"

#    When I click on the "Incoming Responses" KPI
    When I navigate to the "Incoming Responses" tab
    And I search the stored work order number and wait up to 20 seconds
    Then The work order will be displayed in the grid

    When I click on the top work order link
    And I select the "Update" checkbox
    And I click the "Submit" button
    And I click the "Close" button
    Then The "Folder" for "row 1" in the grid is "Incoming Responses"

  @1551
  Scenario: Verify functionality of Due in 30 Minutes KPI
    When I click on the "Due in 30 Minutes" KPI
    Then I check if the following items are selected in the "Folder" header
      | Certificate Requests   |
      | Quality Assurance      |
      | Document Delivery      |
      | Email Needed           |
      | Incoming Responses     |
      | Pending Responses      |
      | Important Instructions |
      | TWB On Hold            |

  @1556
  Scenario: Verify movement to Due in 30 Minutes
    Given I click the "Add Work Order" button
    And I click the "Submit and Open" button
    And I set the "Due Date Override" to 40 minutes after "Add Date:"
    And I click the "Submit" button
    And I store the work order number
    And I click the "Close" button
    And I get the count for "all" KPI
    When I search the stored work order number
    And I click on the top work order link
    And I set the "Due Date Override" to 20 minutes after "Add Date:"
    And I click the "Submit" button
    And I click the "Close" button
    # KPIs take a while to update, so wait and refresh.
    And I wait for "2" seconds
    And I refresh the page
    When I click on the "Due in 30 Minutes" KPI
    And I search the stored work order number
    Then The work order will be displayed in the grid
    And Verify that "Due in 30 Minutes" KPI has "Increased"

  @1552
  Scenario: Verify functionality of Past Due KPI
    When I click on the "Past Due" KPI
    Then I check if the following items are selected in the "Folder" header
      | Certificate Requests |
      | Quality Assurance    |
      | Document Delivery    |
      | TWB On Hold          |
    And I check if the following items are selected in the "Status" header
      | Available   |
      | In Progress |
      | Completed   |
      | Discarded   |

  @1555
  Scenario: Verify movement to Past Due
    Given I click the "Add Work Order" button
    And I click the "Submit and Open" button
    And I set the "Due Date Override" to 40 minutes after "Add Date:"
    And I click the "Submit" button
    And I store the work order number
    And I click the "Close" button
    And I get the count for "all" KPI
    When I search the stored work order number
    And I click on the top work order link
    And I set the "Due Date Override" to -20 minutes after "Add Date:"
    And I click the "Submit" button
    And I click the "Close" button
    # KPIs take a while to update, so wait and refresh.
    And I wait for "2" seconds
    And I refresh the page
    When I click on the "Past Due" KPI
    And I search the stored work order number
    Then The work order will be displayed in the grid
    And Verify that "Past Due" KPI has "Increased"

  @1893
  Scenario: Verify SLA Miss Reason functionality
    Given I click the "Add Work Order" button
    And I click the "Submit and Open" button
    And I select "Document Delivery" from the "Folder" drop down
    And If the confirmation modal displays, dismiss it
    And I select "Delivery" from the "Process" drop down
    And If the confirmation modal displays, dismiss it
    And I set the "Due Date Override" date picker to 1 month ago
    And I select the "Delivered" checkbox
    Then The following elements do not exist
      | SLA Miss Reason |
    When I select the "Task Completed" checkbox
    Then The following elements exist
      | SLA Miss Reason |

    When I click the "Submit" button
    Then The "SLA Miss Reason" dropdown warning is displayed

    When I select "Complex Request" from the "SLA Miss Reason" drop down
    And I click the "Submit" button
    Then The update alert is displayed

  @2875
  Scenario: Verify movement from Incoming Responses To Discarded
    Given I click the "Add Work Order" button
    And I select "Incoming Responses" from the "Folder" drop down
    And I click the "Submit" button
    And I get the new work order number from the confirmation modal
    And I navigate to the "Incoming Responses" tab
    And I search the stored work order number
    And I click on the top work order link

    When I select the "Task Completed" checkbox
    And If the confirmation modal displays, dismiss it
    And I select "Other process request" from the "Discarded Reason" drop down
    And I click the "Submit" button
    And I click the "Close" button
    And I navigate to the "Discarded" tab
    And I search the stored work order number
    Then The work order will be displayed in the grid

    #   @12951
    #  Scenario: Verify "Export" functionality