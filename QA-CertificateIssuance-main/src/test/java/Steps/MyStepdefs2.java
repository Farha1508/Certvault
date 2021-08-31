//package Steps;
//
//import Base.BaseUtil;
//import Pages.CertificateIssuance;
//import io.cucumber.java.en.And;
//import io.cucumber.java.en.Then;
//import io.cucumber.java.en.When;
//import org.openqa.selenium.By;
//import org.openqa.selenium.ElementClickInterceptedException;
//import org.openqa.selenium.support.ui.WebDriverWait;
//import org.testng.Assert;
//
//import java.util.Date;
//import java.util.List;
//import java.util.Map;
//
//public class MyStepdefs2 extends BaseUtil {
//    //private CommonForm commonForm;
//    private CertificateIssuance certI;
//
//    public MyStepdefs2(BaseUtil base) {
//        this.base = base;
//        wait = new WebDriverWait(driver, 10);
//        certI = new CertificateIssuance(driver, js);
//    }
//
//    private BaseUtil base;
//    private String latestWOkey = "workOrderNumber";
//
//    @When("I enter {string} in the search field")
//    public void iCanUseTheSearchField(String recordID) {
//        pageLoaded();
//        certI.typeInSearchField(recordID);
//        System.out.println("Entering \"" + recordID + "\" to the search field");
//    }
//
//    @Then("I see {int} result(s) saying {string}")
//    public void confirmSearchResults(int expectedNumberOfResponses, String expectedString) {
//
//        // Number of responses is correct?
//        Assert.assertEquals(certI.numberOfSearchResults(), expectedNumberOfResponses, "Number of results was incorrect.");
//
//        // assert that expected string is present in each response
//        certI.searchResultsContainSearch(expectedString);
//        System.out.println("Checking the search dropdown results");
//    }
//
//    @And("The results are in descending order")
//    public void resultsInDescendingOrder(){
//        certI.searchResultsDescending();
//        System.out.println("Checking the search dropdown results are in order");
//    }
//
//    @When("I click the top search result")
//    public void clickTopSearchResult() {
//        System.out.println("Clicking top search result");
//        // two functions in different files with same name. clickTopSearchResult().
//        valueStore.put(latestWOkey, certI.clickTopSearchResult());
//    }
//
//    @Then("I am taken to the matching work order")
//    public void matchingWorkOrder(){
//        Assert.assertEquals(certI.getWorkOrderNumber(), valueStore.get(latestWOkey), "Work order number did not match.");
//        System.out.println("On the matching work order page");
//    }
//
//    @Then("I see error warnings")
//    public void confirmAddRecordError(){
//        // Todo: change this function to take multiple inputs, similar to folder functions Ian created
//        String cmpErrMsg = "Please select Company";
//        String folErrMsg = "Please select Folder";
//        certI.waitForCmpErrMsg();
//
//        // Look for the added text
//        Assert.assertEquals(certI.companyErrorDisplayed(), cmpErrMsg);
//        Assert.assertEquals(certI.folderErrorDisplayed(), folErrMsg);
//
//        // confirm that the add modal has not closed
//        Assert.assertTrue(certI.addRecordModalDisplayed());
//
//        // Todo: confirm that no record has been added, maybe.
//
//        System.out.println("Observing correct error warnings");
//    }
//
//    @Then("I see additional error warnings")
//    public void confirmAdditionalAddRecordError(){
//        String divErrMsg = "Please select Division";
//        String braErrMsg = "Please select Branch";
//        certI.waitForDivErrMsg();
//
//        // Look for the added text
//        Assert.assertEquals(certI.divisionErrorDisplayed(), divErrMsg);
//        Assert.assertEquals(certI.branchErrorDisplayed(), braErrMsg);
//
//        // confirm that the add modal has not closed
//        Assert.assertTrue(certI.addRecordModalDisplayed());
//
//        System.out.println("Observing correct additional error warnings");
//    }
//
//    @And("Navigate to the new record")
//    public void navigateToNewRecord(){
//        // New Record should be created and the add work pop up should be closed.
//        Assert.assertFalse(certI.waitForAddRecordClosed());
//
//        // Record details pop should be displayed, showing the newly created record unique ID
//        Assert.assertTrue(certI.waitForAddRecordConfirmModal());
//        String confirmMsg = "Created Successfully";
//        Assert.assertTrue(certI.checkAddRecordConfirmModal().contains(confirmMsg));
//
//        String newRecordNumber = certI.getConfirmRecordNum();
//        valueStore.put(latestWOkey, newRecordNumber);
//        certI.clickConfirmOKBtn();
//
//        // search out the new record
//        iCanUseTheSearchField(newRecordNumber);
//        clickTopSearchResult();
//        valueStore.get("Company");
//    }
//
//    @And("I take note of the record ID")
//    public void recordTheRecordID(){
//        valueStore.put(latestWOkey, certI.getWorkOrderNumber());
//    }
//
//    @And("I return to the home page")
//    public void iReturnToTheHomePage() { // lifted from PMA steps. Todo: move to PrintShop.java
//        try {
//            driver.findElement(By.linkText("Home")).click();
//        } catch (ElementClickInterceptedException e) {
//            commonPage.clickErrorHandle(e.toString(), driver.findElement(By.linkText("Home")));
//        }
//        commonPage.pageLoaded();
//        System.out.println("I have returned to the main menu.");
//    }
//
//    @Then("I cannot make a record with the same ID")
//    public void i_cannot_make_a_record_with_the_same_id() {
//        iReturnToTheHomePage();
//        certI.cannotMakeDuplicateRecordsSetup();
//        Assert.assertFalse(certI.waitForAddRecordClosed());
//        Assert.assertTrue(certI.waitForAddRecordConfirmModal());
//        String expected = valueStore.get(latestWOkey);
//        String actual = certI.getConfirmRecordNum();
//        System.out.println("The two record IDs are: " + expected + " and " + actual);
//        Assert.assertNotEquals(certI.getConfirmRecordNum(), valueStore.get(latestWOkey));
//    }
//
//    @Then("The record is closed")
//    public void record_is_added_closed() {
//        System.out.println("The record modal is closed.");
//        Assert.assertFalse(certI.waitForAddRecordClosed());
//    }
//
//    @Then("No record is added")
//    public void no_record_is_added() {
//        record_is_added_closed();
//        Assert.assertTrue(certI.confirmModalNotDisplayed());
//        System.out.println("Add modal is closed, and confirm modal is not displayed.");
//    }
//
//    @Then("Meter Name is not empty")
//    public void meter_name_is_not_empty() {
//        Assert.assertNotEquals(certI.verifyMeterNameFilled(), "Select MeterName", "Meter Name was not set.");
//    }
//
//    @Then("The add record modal is displayed")
//    public void the_add_record_modal_is_displayed() {
//        Assert.assertTrue(certI.addRecordModalDisplayed());
//    }
//
//    @Then("Meter Name is is {string}")
//    public void meter_name_is_is(String expectedMeterValue) {
//        String actualMeterValue = commonForm.commonDropDownRead("Meter Name");
//        if(actualMeterValue.equals(expectedMeterValue)){
//            System.out.println("The Meter value is DFS.");
//        }
//        else {
//            System.out.println("The Meter value is not DFS.");
//        }
//        Assert.assertEquals(commonForm.commonDropDownRead("Meter Name"), expectedMeterValue);
//    }
//
//    @Then("the {string} field is not displayed")
//    public void theFieldIsNotDisplayed(String fieldName) {
//        // Todo: Learn to write this using commonForm functions so string can be used better than the case statememt
//        //boolean thing = commonForm.commonField(fieldName).isDisplayed();
//        boolean displayed = switch (fieldName) {
//            case "Certified Mail Field" -> certI.cmFieldDisplayed();
//            case "Certified Mail with Return Receipt Field" -> certI.cmrrFieldDisplayed();
//            default -> false;
//        };
//        Assert.assertFalse(displayed);
//    }
//
//    @Then("the {string} field is displayed")
//    public void theFieldIsDisplayed(String fieldName) {
//        // Todo: Learn to write this using commonForm functions so string can be used better than the case statememt
//        boolean displayed = switch (fieldName) {
//            case "Certified Mail Field" -> certI.cmFieldDisplayed();
//            case "Certified Mail with Return Receipt Field" -> certI.cmrrFieldDisplayed();
//            default -> false;
//        };
//        Assert.assertTrue(displayed);
//    }
//
//    @When("I click the {string} checkbox")
//    public void iClickTheCheckbox(String checkboxName) {
//        commonForm.commonCheckBox(checkboxName);
//    }
//
//
//    @And("the {string} field only accepts numbers")
//    public void theFieldOnlyAcceptsNumbers(String fieldName) {
//        System.out.println("Checking the allowed characters of the "+fieldName+" field.");
//        switch (fieldName) {
//            case "Certified Mail Field" -> {
//                commonForm.commonFieldEnter("CM #", "Ab3$");
//                Assert.assertEquals(commonForm.commonFieldRead("CM #"), "3");
//            }
//            case "Certified Mail with Return Receipt Field" -> {
//                commonForm.commonFieldEnter("CMRR #", "Ab3$");
//                Assert.assertEquals(commonForm.commonFieldRead("CMRR #"), "3");
//            }
//        }
//    }
//
//    @And("the {string} field hax a max length of {int}")
//    public void theFieldHaxAMaxLengthOf(String fieldName, int fieldLength) {
//        System.out.println("Checking the max length of the "+fieldName+" field.");
//        String testString = "12345678901234567890";
//        switch (fieldName) {
//            case "Certified Mail Field" -> {
//                commonForm.commonFieldEnter("CM #", testString);
//                Assert.assertEquals(commonForm.commonFieldRead("CM #"), testString.substring(0, fieldLength));
//            }
//            case "Certified Mail with Return Receipt Field" -> {
//                commonForm.commonFieldEnter("CMRR #", testString);
//                Assert.assertEquals(commonForm.commonFieldRead("CMRR #"), testString.substring(0, fieldLength));
//            }
//        }
//    }
//
//    @Then("The following fields are mandatory")
//    public void theFollowingFieldsAreMandatory(List<String> fields) {
//        System.out.println("Checking that mandatory fields are indicated.");
//        for(String field : fields){
//            Assert.assertTrue(certI.requiredAsterixDisplayed(field));
//        }
//    }
//
//    @Then("The record is updated")
//    public void theRecordIsUpdated() {
//        System.out.println("Checking that the Confirm Update modal displays and is correct.");
//        Assert.assertTrue(certI.waitForEditRecordConfirmModal());
//        String confirmMsg = "Record Updated Successfully";
//        Assert.assertTrue(certI.checkEditRecordConfirmModal().contains(confirmMsg));
//        certI.clickEditConfirmOKBtn();
//        certI.confirmEditModalNotDisplayed();
//    }
//
//    @And("I update my expectations")
//    public void iUpdateMyExpectations(Map<String, String> table) {
//        String dateString = dateFormat.format(new Date());
//        String timeString = timeFormat.format(new Date());
//        table.forEach((key, value) -> {
//            if (value.contains("<current date>")) {
//                value = value.replaceAll("<current date>", dateString + " " + timeString);
//            }
//            valueStore.put(key, value);
//        });
//    }
//
//    @Then("the PDF page is displayed")
//    public void thePDFPageIsDisplayed() {
//        pageLoaded();
//        String expectedURL = "https://printshopdev.patracorp.net/printshop/ps-detail-pdf/"+valueStore.get(latestWOkey)+"/view";
//        Assert.assertEquals(certI.checkPDFPage(),expectedURL);
//        // todo: maybe add more checks about the PDF page. Only looking at URL at the moment.
//    }
//
//    @And("I prepare the conditional test case")
//    public void iPrepareTheConditionalTestCase() {
//        certI.prepareConditionalTest();
//    }
//}
//
