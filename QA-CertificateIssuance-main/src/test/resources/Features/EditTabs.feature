Feature: Work Order Tabs

  Background: Sign In
    Given I am on the login page
    When I enter the email and password for the "super user"
    And I click the "Sign in" button
    Then I will be taken to the apps page
    And I click on the "Work Order Tracking" tile
    Then I will be taken to the homepage for that app
    When I open "Certificate Issuance" for company "Company 019"

  # Detail Tab
  @1377
  Scenario: Verify Detail tab fields
    Given I click the "Add Work Order" button
    And I click the "Submit and Open" button
    And I click on the "Detail" link

    When I select "Resend" from the "Work Order Type" drop down
    Then The "# Holders Dated Off" label is not marked required
    And The "# Holders Updated" label is not marked required

    When I select "New Business" from the "Work Order Type" drop down
    Then The "# Holders Dated Off" label is marked required
    And The "# Holders Updated" label is marked required

    When I select "Pre-Renewal Work" from the "Work Order Type" drop down
    Then The "# Holders Dated Off" label is marked required
    And The "# Holders Updated" label is marked required

    When I select "Renewal" from the "Work Order Type" drop down
    Then The "# Holders Dated Off" label is marked required
    And The "# Holders Updated" label is marked required

    When I clear the "# Holders Dated Off" field
    And I clear the "# Holders Updated" field
    And I click the "Submit" button
    Then The "# Holders Dated Off" field warning is displayed
    # Then The "# Holders Updated" field warning is displayed
  # Todo: check to see if messages should be displayed for both fields.

  # Attachments Tab
#  @1372
#  Scenario: Verify Attachments tab grid
#    When I click the "Add Work Order" button
#    And I click the "Submit and Open" button
#    And I upload an attachment
#    And I click on the "Attachments" link
#  # click on file
#  # new tab is opened displaying file
#
#  # delete icon is displayed (for Team lead and above, but not below)
#  # Todo: this line should probably be its own test case.
#
#  # Click delete icon
#  # Alert is displayed
#
#  # Click cancel
#  # Alert is not displayed.
#  # File is still in the table
#
#  # Click delete icon
#  # Click OK
#  # Alert is not displayed.
#  # File is removed
#
#  # Attachments are sorted in order of upload date
  # Todo: this line should probably be its own test case.

  # QA Tab
#  @1376
#  Scenario: Verify QA grid
#    When I click the "Add Work Order" button
#    And I click the "Submit and Open" button
#    And I click on the "QA" link
#
#  @9524
#  Scenario: QA tab in detail page for User base role
#    When I click the "Add Work Order" button
#    And I click the "Submit and Open" button
#    And I click on the "QA" link
#
#  @9525
#  Scenario: QA tab in detail page for Manager and above role
#    When I click the "Add Work Order" button
#    And I click the "Submit and Open" button
#    And I click on the "QA" link

  # Second QA Tab
#  @1373
#  Scenario: Verify fields in Second QA grid
#    When I click the "Add Work Order" button
#    And I click the "Submit and Open" button
#    And I click on the "Second QA" link

  # History Tab
#  @1374
#  Scenario: Verify History grid
#    When I click the "Add Work Order" button
#    And I click the "Submit and Open" button
#    And I click on the "History" link

  # Time Records Tab
#  @1375
#  Scenario: Verify Time Records tab grid
#    When I click the "Add Work Order" button
#    And I click the "Submit and Open" button
#    And I click on the "Time Records" link

  # Attachment History Tab
#  @3011
#  Scenario: Verify the attachment History tab in Edit Work Order page
#    When I click the "Add Work Order" button
#    And I click the "Submit and Open" button
#    And I click on the "Attachment History" link

  # Send Email tests
  @5510
  Scenario: Verify Send email functionality in detail tab
    When I click on the top work order link
    And I click the "Send Email" button
    Then The "Email" modal "is" displayed
    And All the fields in email pop-up are displayed
    When I enter "adfasfa" into the "To" field in the send email popup
    And I click the "Send" button
    Then The send email error message is displayed

  @7765
  Scenario: Verify Reset button in Send email functionality in detail tab
    When I click on the top work order link
    And I click the "Send Email" button
    Then The "Email" modal "is" displayed
    When I enter "bestboy@carlisbest.com" into the "To" field in the send email popup
    And I click the "Reset" button
    Then The "To" field in the send email popup will be ""

  @7766
  Scenario: Verify Close icon in Send email functionality in detail tab
    When I click on the top work order link
    And I click the "Send Email" button
    Then The "Email" modal "is" displayed
    When I click the "X" button
    Then The "Email" modal "is not" displayed

  @7767
  Scenario: Verify User friendly message for success email
    When I click on the top work order link
    And I click the "Send Email" button
    Then The "Email" modal "is" displayed
    When I enter "bestboy@carlisbest.com" into the "To" field in the send email popup
    And I click the "Send" button
    Then The send email success message is displayed

  @9356
  Scenario: Verify send email button functionality without enter any data
    When I click on the top work order link
    And I click the "Send Email" button
    Then The "Email" modal "is" displayed
    And I click the "Send" button
    Then The send email error message is displayed