package Steps;

import Base.BaseUtil;
import Pages.CertificateIssuance;
import Pages.CommonForm;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.*;

public class CertIssuanceSteps extends BaseUtil {

    public static CertificateIssuance certIss;

    public CertIssuanceSteps() {
        certIss = new CertificateIssuance(driver, js);
    }

    @Then("The {string} modal {string} displayed")
    public void theModalDisplayed(String modalName, String displayExpectation) throws InterruptedException {
        // displayExpectation should be "is" or "is not"
        System.out.println("Confirming that the " + modalName + " modal " + displayExpectation + " displayed.");
        pageLoaded();

        long startTime = System.currentTimeMillis();
        if (displayExpectation.equalsIgnoreCase("is not")) {
            while (commonForm.elementFound(modalName) && ((System.currentTimeMillis() - startTime) < 5000)) {
                Thread.sleep(100);
            }
            Assert.assertFalse(commonForm.elementFound(modalName));
        }
        else{
            while (!commonForm.elementFound(modalName) && ((System.currentTimeMillis() - startTime) < 5000)) {
                Thread.sleep(100);
            }
            Assert.assertTrue(commonForm.elementFound(modalName));
        }
    }

    @Then("The {string} change modal {string} displayed")
    public void theModalIsDisplayed(String modalName, String displayExpectation) {
        // todo: try to combine this method with the previous one?
        // displayExpectation should be "is" or "is not"
        System.out.println("Confirming that the " + modalName + " modal " + displayExpectation + " displayed.");
        pageLoaded();
        boolean expectation = false;
        if (!displayExpectation.equals("is not")) {
            expectation = true;
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("jconfirm-open")));
        }

        try {
            WebElement modal = driver.findElement(By.className("jconfirm-open"));
            Assert.assertEquals(modal.getText().contains(modalName), expectation, "Modal text did not contain '" + modalName + "' or expectation was: " + expectation + ".");
        } catch (NoSuchElementException e) {
            Assert.assertFalse(expectation);
        }
    }

    @When("I select {string} from the {string} navbar dropdown")
    public void iSelectFromTheNavbarDropdown(String selection, String dropDown) {
        System.out.println("Selecting " + selection + "from the " + dropDown + " dropdown.");
        dropDown = dropDown.toLowerCase().replaceAll("\\s+", "");
        try {
            WebElement selectedList = driver.findElement(By.id(dropDown));
            Select list = new Select(selectedList);
            list.selectByVisibleText(selection);
        } catch (NullPointerException e) {
            Assert.fail();
        }
        pageLoaded();
    }

    @Then("The {string} navbar dropdown displays {string}")
    public void theNavbarDropdownDisplays(String dropDown, String selection) {
        System.out.println("Confirming that '" + selection + "' is displayed in the " + dropDown + " dropdown.");
        pageLoaded();
        dropDown = dropDown.toLowerCase().replaceAll("\\s+", "");
        try {
            WebElement selectedList = driver.findElement(By.id(dropDown));
            Select list = new Select(selectedList);
            Assert.assertEquals(list.getFirstSelectedOption().getText(), selection);
        } catch (NullPointerException e) {
            Assert.fail();
        }
    }

//    @And("I click the Time Record Open Reset button")
//    public void iClickThePolicyCheckingResetButton() {
//        System.out.println("Clicking the Reset button to clear all filters");
//        certIss.clickTimeRecordOpenResetButton();
//    }

//    @And("I click the Certificate Issuance Reset button")
//    public void iClickTheCertIssResetButton() {
//        System.out.println("Clicking the Reset button to clear all filters");
//        certIss.clickCertIssResetButton();
//    }

    // Selectors
    @FindBy(how = How.ID, using = "addWorkOrder")
    private WebElement workOrderModal;


    @Then("I see error warnings")
    public void iSeeErrorWarnings() {
        // copied from PrintShop project
        // Todo: change this function to take multiple inputs, similar to folder functions Ian created
        String hdoErrMsg = "Please enter # Holders Dated Off";
        String hupErrMsg = "Please enter # Holders Updated";
        certIss.waitForHdoErrMsg();

        // Look for the added text
        Assert.assertEquals(certIss.hdoErrorDisplayed(), hdoErrMsg);
        Assert.assertEquals(certIss.hupErrorDisplayed(), hupErrMsg);

        // confirm that the add modal has not closed
        Assert.assertTrue(certIss.addWorkOrderModalDisplayed());

        // Todo: confirm that no record has been added, maybe.

        System.out.println("Observing correct error warnings");
    }

    @When("I click on the top work order link")
    public void iClickOnTheTopWorkOrderLink() {
        commonGrid.clickTopWO(currentTab);
    }

    @Then("The {string} page is displayed")
    public void thePageIsDisplayed(String pageName) {
        String urlFragment = switch (pageName) {
            case "Certificate Issuance" -> "wot.dev.patracloud.net/certs";
            case "Work Order Detail" -> "wot.dev.patracloud.net/woDetailCerts";
            case "Certificate Report" -> "wot.dev.patracloud.net/pdf/certsReport";
            default -> "Never found the page name in the switch statement.";
        };
        Assert.assertTrue(driver.getCurrentUrl().contains(urlFragment));
    }

    @Then("The update alert is displayed")
    public void theUpdateAlertIsDisplayed() {
        String confirmMsg = "Updated Successfully";
        Assert.assertTrue(certIss.checkEditRecordConfirmModal().contains(confirmMsg));
    }

    @Then("^I switch to new tab opened$")
    public void now_switch_to_new_tab_opened() {
        System.out.println("Switching to the newly opened tab.");
        String parentTab = driver.getWindowHandle();
        Set<String> allTabs = driver.getWindowHandles();
        for (String tab : allTabs) {
            if (!tab.equals(parentTab)) {
                driver.switchTo().window(tab);
            }
        }
    }

    @And("All the fields in email pop-up are displayed")
    public void allTheFieldsInEmailPopUp() {
        Assert.assertTrue(certIss.emailPopUpFields(), "At least one of the send email fields is not displayed.");
    }

    @Then("The send email error message is displayed")
    public void theSendEmailErrorMessageIsDisplayed() {
        Assert.assertTrue(certIss.emailToErrorMsg(), "The error message is not displayed.");
    }

    @Then("The send email success message is displayed")
    public void theSendEmailSuccessMessageIsDisplayed() {
        Assert.assertTrue(certIss.emailSuccessMsg().contains("Success! Email Sent Successfully."), "The error message is not displayed.");
    }

    @When("I enter {string} into the {string} field in the send email popup")
    public void iEnterIntoTheFieldInTheSendEmailPopup(String value, String field) {
        certIss.emailEnterField(value, field);
    }

    @Then("The {string} field in the send email popup will be {string}")
    public void theFieldInTheSendEmailPopupWillBe(String field, String expectation) {
        Assert.assertEquals(certIss.emailReadField(field), expectation, "The " + field + " field did not have the expected contents: " + expectation + ". Instead it was: " + certIss.emailReadField(field));
    }

    @Then("The Original Email field is displayed")
    public void theOriginalEmailFieldIsDisplayed() {
        Assert.assertTrue(certIss.originalEmailFieldDisplayed());
    }

    @And("The Original Email field contents are not null")
    public void theOriginalEmailFieldContentsAreNotNull() {
        Assert.assertNotNull(certIss.originalEmailFieldContents());
        valueStore.put("originalEmailContents", certIss.originalEmailFieldContents());
    }

    @When("I enter {string} into the Original Email field")
    public void iEnterIntoTheOriginalEmailField(String typingInput) {
        certIss.originalEmailFieldEnter(typingInput);
    }

    @Then("The Original Email field contents are unchanged")
    public void theOriginalEmailFieldContentsAreUnchanged() {
        Assert.assertEquals(certIss.originalEmailFieldContents(), valueStore.get("originalEmailContents"));
    }

    @Then("The work order will be displayed in the grid")
    public void theWorkOrderWillBeDisplayedInTheGrid() {
        System.out.println("Checking that the most recent work order is displayed in the grid.");
        String topWorkOrder = commonGrid.gridEntry("row 1", "WO #").getText();
        Assert.assertEquals(topWorkOrder, valueStore.get("workOrder"), "The actual work order (" + topWorkOrder + ") did not match the expected work order (" + valueStore.get("workOrder") + ")");
    }

    @When("I refresh the page")
    public void iRefreshThePage() {
        driver.navigate().refresh();
        pageLoaded();
    }

    @And("If the confirmation modal displays, dismiss it")
    public void ifTheConfirmationModalDisplaysDismissIt() {
        // When changing the Folder to Document Delivery, sometimes there is a confirmation modal
        pageLoaded();
        if (commonForm.commonButton("ok")) {
            System.out.println("Modal was dismissed.");
        } else {
            System.out.println("Modal was not displayed.");
        }
        pageLoaded();
    }

    @And("The {string} has a red outline")
    public void theHasARedOutline(String fieldDropDown) {
        if (commonForm.commonField(fieldDropDown) != null) {
            Assert.assertTrue(certIss.elementHasError(commonForm.commonField(fieldDropDown)));
        } else if (commonForm.commonDropDown(fieldDropDown) != null) {
            Assert.assertTrue(certIss.elementHasError(commonForm.commonDropDown(fieldDropDown)));
        } else if (fieldDropDown.contains("Task")) {// Because for some reason, the dropdown isn't captured here.
            Assert.assertTrue(certIss.elementHasError(driver.findElement(By.id("timer_task"))));
        } else {
            System.out.println("Could not find field or dropdown.");
            Assert.fail();
        }
    }

    @And("the open timer for the current user is {string}")
    public void theOpenTimerForTheCurrentUserIs(String task) {
        // Get the current user
        String currentUser = certIss.currentUserOpenTimer();
        commonGrid.gridHeaderField("Employee", currentUser);
        BaseUtil.pageLoaded();

        //Find employee in table
        //Check task for employee row matches submit
        Assert.assertEquals(certIss.userOpenTask(currentUser), task);
    }

    @And("I click the edit icon for the top timer row")
    public void iClickTheEditIconForTheTopTimerRow() {
        certIss.topTimerRowEditIcon();
    }

    @And("The top timer row {string} value is {string}")
    public void theTopTimerRowValueIs(String header, String expectation) {
        System.out.println("Checking that the first row " + header + " column is " + expectation);
        String actualValue = commonGrid.gridEntry("row 1", header).getText();
        Assert.assertEquals(actualValue, expectation, "The " + header + " entry (" + actualValue + ") did not match the expectation (" + expectation + ").");
    }

    @When("I enter {string} into the {string} field in the Time Record Detail modal")
    public void iEnterIntoTheFieldInTheTimeRecordDetailModal(String value, String field) {
        System.out.println("Setting the " + field + " field to " + value);
        certIss.timeEnterField(value, field);
    }
}
