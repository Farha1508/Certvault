Feature: Demonstrating how to retrieve emails from Gmail

  Scenario: Retrieving link from owner creation email
    Given I am on the login page
    When I click on the "Forgot Your Password?" link
    ## For the reset password step, you can enter any email that has an account.
    ## If you want to use the Gmail email located in logins.properties you can
    ## use "<gmail>" and it will autofill that. If your Gmail account has a substring
    ## (e.g., my.email+substring@gmail.com), you can enter "<gmail>+yoursubstring" and it
    ## will automatically insert it.
    And I reset the password for "<gmail>"
    ## You will need to generate an auth token for Gmail email and place it in the logins.properties
    ## file for this to work.
    ## The email check is run through an API and will not display in a browser window.
    And I verify the email for "Forgot Password" was received
    And I open the link from the email
    And I wait for 5 seconds

  Scenario: Send email
    ## If an item from the table is not needed to send an email, remove that line from the step.
    ## In the case that CC and Attachment are not needed, you just need to modify the step as below:
    ##| To         | fakeemail@patracorp.net                            |
    ##| Subject    | This is the subject                                |
    ##| Body       | This is the message body                           |
    And I send an email with the following information
      | To         | fakeemail@patracorp.net                            |
      | CC         | fakeemail2@patracorp.net                           |
      | Subject    | This is the subject                                |
      | Body       | This is the message body                           |
      | Attachment | src/test/resources/attachments/sir_fluffington.jpg |