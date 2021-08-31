Feature: Processing the Insurance_Payment_Link
  Scenario: ENTERING THE PAYMENTS PORTAL
    Given I am in the  inbox of respected mail id
    |Id|Password|
    |abc@gmail.com|abc|
    Then Click on the insurance_payment_Link
    And  Click on the "CLICK HERE TO PROCEED BUTTON"
    Then Select the checkbox on the Application_Review button
    And Click on the view button to view the policy details
    Then Click on the continue button
    Given I am on the payment option page
    Then select any payment option
    And Click on the Continue button to reach the next step
    Then click on the view_and_sign_the_document link
    Then sign the document and click on the continue button
    And downloaded the PDF
    And Click on the continue button to reach the 4th-step
    Then Select either ACH or credit card transaction option
    Then Enter all the valid information
    And click on the pay and continue button
    Then Go back to the mail inbox to check the payment receipt
    And Now click on the payment receipt to view it
