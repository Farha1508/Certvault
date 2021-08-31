package Steps;

import Base.BaseUtil;
import Pages.*;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class MyStepdefs extends BaseUtil {
    //private CommonForm commonForm;
    private PrintShop printShop;
    private CommonGrid commonGrid = new CommonGrid(driver,js);

    public MyStepdefs(BaseUtil base) {
        this.base = base;
        wait = new WebDriverWait(driver, 10);
        printShop = new PrintShop(driver, js);
    }

    private BaseUtil base;
    private String latestWOkey = "workOrderNumber";

    @When("I enter {string} in the search field")
    public void iCanUseTheSearchField(String recordID) {
        pageLoaded();
        printShop.typeInSearchField(recordID);
        System.out.println("Entering \"" + recordID + "\" to the search field");
    }

    @Then("I see {int} result(s) saying {string}")
    public void confirmSearchResults(int expectedNumberOfResponses, String expectedString) {

        // Number of responses is correct?
        Assert.assertEquals(printShop.numberOfSearchResults(), expectedNumberOfResponses, "Number of results was incorrect.");

        // assert that expected string is present in each response
        printShop.searchResultsContainSearch(expectedString);
        System.out.println("Checking the search dropdown results");
    }

    @And("The results are in descending order")
    public void resultsInDescendingOrder(){
        printShop.searchResultsDescending();
        System.out.println("Checking the search dropdown results are in order");
    }

    @When("I click the top search result")
    public void clickTopSearchResult() {
        System.out.println("Clicking top search result");
        // two functions in different files with same name. clickTopSearchResult().
        valueStore.put(latestWOkey, printShop.clickTopSearchResult());
    }

    @Then("I am taken to the matching work order")
    public void matchingWorkOrder(){
        Assert.assertEquals(printShop.getWorkOrderNumber(), valueStore.get(latestWOkey), "Work order number did not match.");
        System.out.println("On the matching work order page");
    }

    @Then("I see error warnings")
    public void confirmAddRecordError(){
        // Todo: change this function to take multiple inputs, similar to folder functions Ian created
        String cmpErrMsg = "Please select Company";
        String folErrMsg = "Please select Folder";
        printShop.waitForCmpErrMsg();

        // Look for the added text
        Assert.assertEquals(printShop.companyErrorDisplayed(), cmpErrMsg);
        Assert.assertEquals(printShop.folderErrorDisplayed(), folErrMsg);

        // confirm that the add modal has not closed
        Assert.assertTrue(printShop.addRecordModalDisplayed());

        // Todo: confirm that no record has been added, maybe.

        System.out.println("Observing correct error warnings");
    }

    @Then("I see additional error warnings")
    public void confirmAdditionalAddRecordError(){
        String divErrMsg = "Please select Division";
        String braErrMsg = "Please select Branch";
        printShop.waitForDivErrMsg();

        // Look for the added text
        Assert.assertEquals(printShop.divisionErrorDisplayed(), divErrMsg);
        Assert.assertEquals(printShop.branchErrorDisplayed(), braErrMsg);

        // confirm that the add modal has not closed
        Assert.assertTrue(printShop.addRecordModalDisplayed());

        System.out.println("Observing correct additional error warnings");
    }

    @And("Navigate to the new record")
    public void navigateToNewRecord(){
        // New Record should be created and the add work pop up should be closed.
        Assert.assertFalse(printShop.waitForAddRecordClosed());

        // Record details pop should be displayed, showing the newly created record unique ID
        Assert.assertTrue(printShop.waitForAddRecordConfirmModal());
        String confirmMsg = "Created Successfully";
        Assert.assertTrue(printShop.checkAddRecordConfirmModal().contains(confirmMsg));

        String newRecordNumber = printShop.getConfirmRecordNum();
        valueStore.put(latestWOkey, newRecordNumber);
        printShop.clickConfirmOKBtn();

        // search out the new record
        iCanUseTheSearchField(newRecordNumber);
        clickTopSearchResult();
        valueStore.get("Company");
    }

    @And("I search the new record")
    public void searchNewRecord(){
        // New Record should be created and the add work pop up should be closed.
        Assert.assertFalse(printShop.waitForAddRecordClosed());

        // Record details pop should be displayed, showing the newly created record unique ID
        Assert.assertTrue(printShop.waitForAddRecordConfirmModal());
        String confirmMsg = "Created Successfully";
        Assert.assertTrue(printShop.checkAddRecordConfirmModal().contains(confirmMsg));

        String newRecordNumber = printShop.getConfirmRecordNum();
        valueStore.put(latestWOkey, newRecordNumber);
        printShop.clickConfirmOKBtn();

        // search out the new record
        System.out.println("Searching for the new record: "+valueStore.get(latestWOkey));
        String currentRow1 = commonGrid.gridEntry("row 1", "Record ID").getText();
        commonGrid.gridHeaderField("Record ID",valueStore.get(latestWOkey));
        commonGrid.waitForFilter(currentRow1);
    }

    // These next two steps are the previous step split into two
    @And("I save the new record ID")
    public void saveNewRecordID(){
        // New Record should be created and the add work pop up should be closed.
        Assert.assertFalse(printShop.waitForAddRecordClosed());

        // Record details pop should be displayed, showing the newly created record unique ID
        Assert.assertTrue(printShop.waitForAddRecordConfirmModal());
        String confirmMsg = "Created Successfully";
        Assert.assertTrue(printShop.checkAddRecordConfirmModal().contains(confirmMsg));

        String newRecordNumber = printShop.getConfirmRecordNum();
        valueStore.put(latestWOkey, newRecordNumber);
        printShop.clickConfirmOKBtn();
    }

    @And("I search the saved record")
    public void searchSavedRecordID(){
        System.out.println("Searching for the new record: "+valueStore.get(latestWOkey));
        String currentRow1 = commonGrid.gridEntry("row 1", "Record ID").getText();
        commonGrid.gridHeaderField("Record ID",valueStore.get(latestWOkey));
        commonGrid.waitForFilter(currentRow1);
    }

    @And("I take note of the record ID")
    public void recordTheRecordID(){
        // When editing a record.
        valueStore.put(latestWOkey, printShop.getWorkOrderNumber());
    }

    @Then("The record is added")
    public void confirmAddRecord(List<String> fields){
        // confirm record details match what were entered
        fields.forEach(key -> {
            boolean foundItem = false;
            if (commonForm.commonField(key) != null) {
                foundItem = true;
                String found = commonForm.commonFieldRead(key);
                if(key.contains("Date")){ // The way dates are stored and displayed are different. Need to do some parsing.
                    // Convert the expected format into the actual format, and then compare with the actual date.
                    // expected: "19-02-2021 103851", actual: "02/19/2021 10:38 AM"
                    SimpleDateFormat formatActual = new SimpleDateFormat("MM/dd/yyyy h:mm aa", Locale.ENGLISH); // need locale to catch AM/PM component
                    SimpleDateFormat formatExpected = new SimpleDateFormat("dd-MM-yyyy HHmmss");
                    try {
                        Date expectedDate = formatExpected.parse(valueStore.get(key));
                        String reformattedExpectedDate = formatActual.format(expectedDate);
                        // "Mail Date" in Print Shop is special. It is always set to 15:15 PT.
                        // Git issue: https://github.com/patracorp/print-shop/issues/34
                        // Test Case: https://patra.testrail.io/index.php?/cases/view/10466
                        if(key.equals("Mail Date")){
                            reformattedExpectedDate = reformattedExpectedDate.substring(0,11) + "3:15 PM";
                        }
                        System.out.println("Comparing actual (\"" + found + "\") to expected (\"" + reformattedExpectedDate + "\") in \"" + key + "\" field");
                        Assert.assertEquals(found,reformattedExpectedDate);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
                else{
                    System.out.println("Comparing actual (\"" + found + "\") to expected (\"" + valueStore.get(key) + "\") in \"" + key + "\" field");
                    Assert.assertEquals(found, valueStore.get(key));
                }
            } else if (commonForm.commonDropDown(key) != null) {
                foundItem = true;
                String found = commonForm.commonDropDownRead(key);
                System.out.println("Comparing actual (\"" + found + "\") to expected (\"" + valueStore.get(key) + "\") in \"" + key + "\" drop down");
                Assert.assertEquals(found, valueStore.get(key));
            } else if (commonForm.commonTextArea(key) != null) {
                foundItem = true;
                String found = commonForm.commonTextAreaRead(key);
                System.out.println("Comparing actual (\"" + found + "\") to expected (\"" + valueStore.get(key) + "\") in \"" + key + "\" test area");
                Assert.assertEquals(found, valueStore.get(key));
            }
            Assert.assertTrue(foundItem, "Could not find " + key + " field, drop down, or text area!");
        });
        System.out.println("Record has been successfully read");
    }

    @And("I return to the home page")
    public void iReturnToTheHomePage() { // lifted from PMA steps. Todo: move to PrintShop.java
        try {
            driver.findElement(By.linkText("Home")).click();
        } catch (ElementClickInterceptedException e) {
            commonPage.clickErrorHandle(e.toString(), driver.findElement(By.linkText("Home")));
        }
        commonPage.pageLoaded();
        System.out.println("I have returned to the main menu.");
    }

    @Then("I cannot make a record with the same ID")
    public void i_cannot_make_a_record_with_the_same_id() {
        iReturnToTheHomePage();
        printShop.cannotMakeDuplicateRecordsSetup();
        Assert.assertFalse(printShop.waitForAddRecordClosed());
        Assert.assertTrue(printShop.waitForAddRecordConfirmModal());
        String expected = valueStore.get(latestWOkey);
        String actual = printShop.getConfirmRecordNum();
        System.out.println("The two record IDs are: " + expected + " and " + actual);
        Assert.assertNotEquals(printShop.getConfirmRecordNum(), valueStore.get(latestWOkey));
    }

    @Then("The record is closed")
    public void record_is_added_closed() {
        System.out.println("The record modal is closed.");
        Assert.assertFalse(printShop.waitForAddRecordClosed());
    }

    @Then("No record is added")
    public void no_record_is_added() {
        record_is_added_closed();
        Assert.assertTrue(printShop.confirmModalNotDisplayed());
        System.out.println("Add modal is closed, and confirm modal is not displayed.");
    }

    @Then("Meter Name is not empty")
    public void meter_name_is_not_empty() {
        Assert.assertNotEquals(printShop.verifyMeterNameFilled(), "Select MeterName", "Meter Name was not set.");
    }

    @Then("The add record modal is displayed")
    public void the_add_record_modal_is_displayed() {
        Assert.assertTrue(printShop.addRecordModalDisplayed());
    }

    @Then("Meter Name is is {string}")
    public void meter_name_is_is(String expectedMeterValue) {
        String actualMeterValue = commonForm.commonDropDownRead("Meter Name");
        if(actualMeterValue.equals(expectedMeterValue)){
            System.out.println("The Meter value is DFS.");
        }
        else {
            System.out.println("The Meter value is not DFS.");
        }
        Assert.assertEquals(commonForm.commonDropDownRead("Meter Name"), expectedMeterValue);
    }

    @Then("the {string} field is not displayed")
    public void theFieldIsNotDisplayed(String fieldName) {
        // Todo: Learn to write this using commonForm functions so string can be used better than the case statememt
        //boolean thing = commonForm.commonField(fieldName).isDisplayed();
        boolean displayed = switch (fieldName) {
            case "Certified Mail Field" -> printShop.cmFieldDisplayed();
            case "Certified Mail with Return Receipt Field" -> printShop.cmrrFieldDisplayed();
            default -> false;
        };
        Assert.assertFalse(displayed);
    }

    @Then("the {string} field is displayed")
    public void theFieldIsDisplayed(String fieldName) {
        // Todo: Learn to write this using commonForm functions so string can be used better than the case statememt
        boolean displayed = switch (fieldName) {
            case "Certified Mail Field" -> printShop.cmFieldDisplayed();
            case "Certified Mail with Return Receipt Field" -> printShop.cmrrFieldDisplayed();
            default -> false;
        };
        Assert.assertTrue(displayed);
    }

    @When("I click the {string} checkbox")
    public void iClickTheCheckbox(String checkboxName) {
        commonForm.commonCheckBox(checkboxName);
        pageLoaded();
    }


    @And("the {string} field only accepts numbers")
    public void theFieldOnlyAcceptsNumbers(String fieldName) {
        System.out.println("Checking the allowed characters of the "+fieldName+" field.");
        switch (fieldName) {
            case "Certified Mail Field" -> {
                commonForm.commonFieldEnter("CM #", "Ab3$");
                Assert.assertEquals(commonForm.commonFieldRead("CM #"), "3");
            }
            case "Certified Mail with Return Receipt Field" -> {
                commonForm.commonFieldEnter("CMRR #", "Ab3$");
                Assert.assertEquals(commonForm.commonFieldRead("CMRR #"), "3");
            }
        }
    }

    @And("the {string} field hax a max length of {int}")
    public void theFieldHaxAMaxLengthOf(String fieldName, int fieldLength) {
        System.out.println("Checking the max length of the "+fieldName+" field.");
        String testString = "12345678901234567890";
        switch (fieldName) {
            case "Certified Mail Field" -> {
                commonForm.commonFieldEnter("CM #", testString);
                Assert.assertEquals(commonForm.commonFieldRead("CM #"), testString.substring(0, fieldLength));
            }
            case "Certified Mail with Return Receipt Field" -> {
                commonForm.commonFieldEnter("CMRR #", testString);
                Assert.assertEquals(commonForm.commonFieldRead("CMRR #"), testString.substring(0, fieldLength));
            }
        }
    }

    @Then("The following fields are mandatory")
    public void theFollowingFieldsAreMandatory(List<String> fields) {
        System.out.println("Checking that mandatory fields are indicated.");
        for(String field : fields){
            Assert.assertTrue(printShop.requiredAsterixDisplayed(field));
        }
    }

    @Then("The record is updated")
    public void theRecordIsUpdated() {
        System.out.println("Checking that the Confirm Update modal displays and is correct.");
        Assert.assertTrue(printShop.waitForEditRecordConfirmModal());
        String confirmMsg = "Record Updated Successfully";
        Assert.assertTrue(printShop.checkEditRecordConfirmModal().contains(confirmMsg));
        printShop.clickEditConfirmOKBtn();
        printShop.confirmEditModalNotDisplayed();
    }

    @Then("The validation error is displayed")
    public void theValidationErrorIsDisplayed() {
        // Confirm modal and Validation Error modal have the same selectors.
        System.out.println("Checking that the validation error modal is displayed.");
        Assert.assertTrue(printShop.waitForEditRecordConfirmModal());
        String confirmMsg = "If Bulk/Renewal is checked, Client Code required";
        Assert.assertTrue(printShop.checkEditRecordConfirmModal().contains(confirmMsg));
        printShop.clickEditConfirmOKBtn();
        printShop.confirmEditModalNotDisplayed();
    }

    @And("I update my expectations")
    public void iUpdateMyExpectations(Map<String, String> table) {
        String dateString = dateFormat.format(new Date());
        String timeString = timeFormat.format(new Date());
        table.forEach((key, value) -> {
            if (value.contains("<current date>")) {
                value = value.replaceAll("<current date>", dateString + " " + timeString);
            }
            valueStore.put(key, value);
        });
    }

    @Then("the PDF page is displayed")
    public void thePDFPageIsDisplayed() {
        pageLoaded();
        String expectedURL = "https://printshopdev.patracorp.net/printshop/ps-detail-pdf/"+valueStore.get(latestWOkey)+"/view";
        Assert.assertEquals(printShop.checkPDFPage(),expectedURL);
        // todo: maybe add more checks about the PDF page. Only looking at URL at the moment.
    }

    @And("I prepare the conditional test case")
    public void iPrepareTheConditionalTestCase() {
        printShop.prepareConditionalTest();
    }

    @Then("The {string} modal {string} displayed")
    public void theModalDisplayed(String modalName, String displayExpectation) throws InterruptedException {
        // displayExpectation should be "is" or "is not"
        System.out.println("Confirming that the " + modalName + " modal " + displayExpectation + " displayed.");
        pageLoaded();
        boolean displayStatus = true;
        if(displayExpectation.equalsIgnoreCase("is not")){
            displayStatus = false;
        }
        Thread.sleep(1000);// sometimes evaluated too fast, waiting for a modal to disappear.
        Assert.assertEquals(displayStatus, commonForm.elementFound(modalName));
    }

    //todo: maybe move this step and associated methods to FormSteps.
    @When("I clear the {string} field")
    public void iClearTheField(String fieldLabel) {
        System.out.println("Clearing the "+fieldLabel+" field.");
        printShop.clearField(commonForm.commonField(fieldLabel));
    }

    @And("I click the PrintShop Reset button")
    public void iClickThePrintShopResetButton() {
        System.out.println("Clicking the Reset button to clear all filters");
        printShop.clickPrintShopResetButton();
    }

    @Then("The {string} indicator mouseover text is {string}")
    public void theIndicatorMouseoverTextIs(String colour, String expectedText) {
        System.out.println("Checking the mouseover text of the "+colour+" box.");
        Assert.assertEquals(printShop.getIndicatorMouseoverText(colour),expectedText);
    }

    @And("The email reminder text is displayed")
    public void theEmailReminderTextIsDisplayed() {
        System.out.println("Checking the Email Notification tab reminder text.");
        Assert.assertEquals(printShop.emailNotificationReminderText(),"(Multiple mail separated by ; )");
    }

    @Then("The India Folder warning is displayed")
    public void confirmIndiaFolderWarning(){
        // Todo: change this function to take multiple inputs, similar to folder functions Ian created
        System.out.println("Observing India Folder error warning");
        String folErrMsg = "You dont have permission to change folder other than New Record/OnHold -India/To be Printed";
        // Look for the added text
        printShop.waitForIndiaFolderErrMsg();
        Assert.assertEquals(printShop.folderIndiaErrorDisplayed(), folErrMsg);
    }
}

