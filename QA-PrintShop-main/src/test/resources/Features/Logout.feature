Feature: Top Level Navigation

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
    ###---------- App selection ----------###

@6718
Scenario: Logout
  When I Click on user icon
  And I click Logout button
  Then I am redirected to login page

