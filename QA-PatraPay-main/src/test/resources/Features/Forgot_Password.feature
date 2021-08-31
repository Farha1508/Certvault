Feature:Forgot password

  Scenario: Testing Forgot password happy path
    Given I am on the login page
    And I click the "Unable to sign in?" button
    And I enter "roopali.sharma+testingstaffone@jellyfishtechnologies.com" in the "Email" field
    And I click the "SEND INSTRUCTIONS" button
    Then I go to my gmail mail inbox
    And I enter valid gmail credentials to login
      | Id                                       | Password  |
      | roopali.sharma@jellyfishtechnologies.com | Mumma1103 |
    And I open the reset password mail in gmail
    Then I click the reset password link in the email
    And I enter "password" in the "New Password" field
    And I enter "password" in the "Confirm Password" field
    Then I click the "UPDATE" button

