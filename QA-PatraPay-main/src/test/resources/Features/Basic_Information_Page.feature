Feature: Validating Basic Information page step-1st

  Background: Navigating to patrapay

    Given I am on the login page
    And I enter the email and password for the "Agency" user
    When I click the "SIGN IN" button
    Then User should taken to the QBIS home page successfully

  Scenario Outline: Validation Error message testing on Business Name field
    When I enter multiple entries on basic information page to validate <Business Name> field

    Examples:
      |Business Name|
      |t|
      |The quick, brown fox jumps over a lazy dog. DJs flock by when MTV ax quiz prog. Junk MTV quiz graced by fox whelps. Bawds|
      |%,^,,\,{}|


Scenario Outline: Zip Code field validation

  When I enter invalid data into <Zip Code> field
  Then A validation error message displays on this field

  Examples:

    |Zip Code|
  |ey&&45  |
  |        |

  Scenario Outline: First Name field validation

    When I enter more than 100 characters into <First Name> field
    Then A validation message appears on this field

    Examples:

    |First Name|
    |The quick, brown fox jumps over a lazy dog. DJs flock by when MTV ax quiz prog. Junk MTV quiz graced by fox whelps. Bawds|

  Scenario Outline: Last Name field validation

    When I enter more than 100 characters in the <Last Name> field
    Then A validation message appeared on this field

    Examples:

      |Last Name|
      |The quick, brown fox jumps over a lazy dog. DJs flock by when MTV ax quiz prog. Junk MTV quiz graced by fox whelps. Bawds|

    Scenario Outline: Email field validation

      When i enter multiple invalid entried to validate error message on <Email> field

      Examples:

        |Email|
      |trt56@|

  Scenario Outline: Phone number field validation

    When i enter multiple invalid entried to validate error message on <Phone Number> field

    Examples:

      |Phone Number|
      |123yy&&|


