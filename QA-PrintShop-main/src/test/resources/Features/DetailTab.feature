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
    When set the tab to "Processing"
    ###---------- App selection ----------###

  @6728
  Scenario: Detail Tab Functionality
    # Click on any Record Id under Processing tab grid
    #Verify the details tab
    When I click on the top record link
    # top row
    Then The following elements exist
      | Folder                                             |
      | (CERTS) # of Pages Printed                         |
      | (NON-CERT) # of Pages Printed                      |
      | Color # of Pages                                   |
      | Print on PATRA Special Paper # of Pages (NON-CERT) |
    # second row
    And The following elements exist
      | Non-Certificate Mailing Type |
      | Benefit Document Type        |
      | Benefit Set Qty              |
      | Binder Type Patra            |
    # third row
    And The following elements exist
      | Custom Tabs                     |
      | CD                              |
      | Flash Drive                     |
      | Manual Processing               |
      | Insert Notices                  |
      | Insert Customer Return Envelope |
    # fourth row
    And The following elements exist
      | Notes |

  @10530
  Scenario: Details Fields Updated on Submit
    When I click the "Add Record" button
    And I enter the following information into the form
      | Date of Customer Request (for Print Shop use only) | <current date> |
      | Mail Date                                          | <current date> |
      | Company                                            | Company 019    |
      | Service                                            | Indexing       |
      | Division                                           | 705            |
      | Branch                                             | Seattle        |
      | Department                                         | 407            |
      | Associated WO #                                    | 123            |
      | Client Code                                        | 123tesy        |
      | # of Holder Certs                                  | 12             |
      | # of Insured Certs                                 | 12             |
      | # of Carrier Certs                                 | 12             |
      | # of Letters/Notices/Documents                     | 12             |
      | Meter Name                                         | USI            |
    And I click the "Submit" button
    And Navigate to the new record
    And I enter the following information into the form
      | Date of Customer Request (for Print Shop use only) | <current date> |
      | Mail Date                                          | <current date> |
      | Company                                            | Company 002    |
      | Service                                            | Submissions    |
      | Division                                           | Division-1     |
      | Branch                                             | Irvine         |
      | Department                                         | Dept & Sec-1   |
      | Associated WO #                                    | 124            |
      | Client Code                                        | 124tesy        |
      | # of Holder Certs                                  | 13             |
      | # of Insured Certs                                 | 13             |
      | # of Carrier Certs                                 | 13             |
      | # of Letters/Notices/Documents                     | 13             |
      | Meter Name                                         | HEFFERNAN      |
    And I click the "Submit" button
    Then The record is updated
    And The record is added
      | Date of Customer Request (for Print Shop use only) |
      | Mail Date                                          |
      | Company                                            |
      | Service                                            |
      | Division                                           |
      | Branch                                             |
      | Department                                         |
      | Associated WO #                                    |
      | Client Code                                        |
      | # of Holder Certs                                  |
      | # of Insured Certs                                 |
      | # of Carrier Certs                                 |
      | # of Letters/Notices/Documents                     |
      | Meter Name                                         |

  @10467
  Scenario: Non-Cert Number of Pages Printed Field
    When I sort the "Record ID" grid column by "descending"
    When I click on the top record link
    And I enter the following information into the form
      | (NON-CERT) # of Pages Printed | 1234 |
    And I click the "Submit" button
    Then The "Manual Processing Alert" modal "is" displayed
    When I click the "OK" button
    Then The "Manual Processing Alert" modal "is not" displayed
    When I clear the "(NON-CERT) # of Pages Printed" field
    #  Entering data into the Notes field is just to move focus away from the # of Pages Printed field
    And I enter the following information into the form
      | Notes | Move focus |
    Then The "Manual Processing Alert" modal "is" displayed
    When I click the "OK" button
    Then The "Manual Processing Alert" modal "is not" displayed
    When I click the "Submit" button
    Then The record is updated

  @12751
  Scenario: Print/Mail dates are set when moved to Printed
    When I click the "Add Record" button
    And I enter the following information into the form
      | Folder | Printed |
    Then The "Add Date" field contains "<current date>"
    And The "Mail Date" field contains "<current date>"
    And The "Print Date" field contains "<current date>"
