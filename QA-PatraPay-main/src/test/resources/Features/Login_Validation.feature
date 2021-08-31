Feature: Testing login validation

  Background:Navigates to PatraPay Login Page
    Given I am on the login page

  Scenario: Wrong Email Format

    When I enter an email without proper formatting
    Then A proper browser message displays showing the correct formatting

  Scenario: Missing Password

    When I enter an email with no password
    Then A browser message displays about the blank password field

  Scenario: Missing Email

    When I enter a password with no Email
    Then A browser message displays about the blank Email field

  Scenario: Performing In-active account validation

    When I enter an Email with a random password which is not created
    Then A proper error message displays

