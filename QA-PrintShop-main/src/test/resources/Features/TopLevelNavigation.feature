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
Scenario: Global Search
When I enter "245718" in the search field
Then I see 1 result saying "245718"

When I click the top search result
Then I am taken to the matching work order

When I enter "24571" in the search field
Then I see 5 results saying "24571"
And The results are in descending order

When I click the top search result
Then I am taken to the matching work order

When I enter "999999999999" in the search field
Then I see 1 result saying "Record Doesn't Exist."

