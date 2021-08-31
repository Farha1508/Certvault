package Steps;

import Base.BaseUtil;
import Base.KpiClass;
import Pages.Login;
import Pages.CertificateIssuance;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class CommonSteps extends BaseUtil {
    CertificateIssuance certIss = new CertificateIssuance(driver, js);

    @And("I open the URL {string}")
    public void iOpenTheURL(String url) {
        driver.get(url);
        pageLoaded();
    }

    @When("I click the user icon")
    public void iClickOnUserIcon() {
        System.out.println("Clicking User Icon");
        login.ClickUserIcon();
    }

    @And("I click the Logout button")
    public void iClickLogoutButton() {
        System.out.println("Clicking Logout button");
        login.ClickLogOut();
    }

    @When("I open {string} for company {string}")
    public void iOpenForCompany(String service, String company) {
        WebElement selectCompany = driver.findElement(By.id("companyStartId"));
        selectCompany.click();
        Select list = new Select(selectCompany);
        list.selectByVisibleText(company);
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(company + " - " + service))).click();
        } catch (ElementClickInterceptedException e) {
            commonForm.clickErrorHandle(e.toString(), driver.findElement(By.linkText(company + " - " + service)));
        }

        valueStore.put("currentCompany", company);
        valueStore.put("currentService", company);
        pageLoaded();
    }

    @And("I get the new work order number from the confirmation modal")
    public void iGetTheNewWorkOrderNumberFromTheConfirmationModal() {
        System.out.println("The new Work order number is: " + certIss.getPopupWorkOrderNumber());
        valueStore.put("workOrder", certIss.getPopupWorkOrderNumber());
        certIss.closeNewWorkOrderPopup();
    }


    @And("I wait for {string} seconds")
    public void iWaitForSeconds(String seconds) throws InterruptedException {
        int timeToWait = Integer.parseInt(seconds) * 1000;
        Thread.sleep(timeToWait);
    }

    //
//    @And("Return to the apps page")
//    public void returnToTheAppsPage() {
//        driver.findElement(By.id("index")).click();
//    }
//
//    @And("I take a screenshot")
//    public void iTakeAScreenshot() {
//        String dateString = dateFormat.format(new Date());
//        String timeString = timeFormat.format((new Date()));
//        System.out.println("attempting to add to file: " + filePath + "\\screenshots\\" + BaseUtil.scenarioName + " " + dateString + " " + timeString + ".png");
//
//        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
//        try {
//            FileUtils.copyFile(srcFile, new File(filePath + "\\screenshots\\" + BaseUtil.scenarioName + dateString + timeString + ".png"));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @And("I get the {string} info from valueStore")
//    public void iGetTheInfoFromValueStore(String storeItem) throws InterruptedException {
//        js.executeScript("alert('" + storeItem + " in valueStore: " + valueStore.get(storeItem) + "')");
//        Thread.sleep(5000);
//        driver.switchTo().alert().accept();
//        System.out.println(storeItem + " in valueStore: " + valueStore.get(storeItem));
//    }
//
//    @And("I clear the valueStore")
//    public void iClearTheValueStore() {
//        valueStore.clear();
//        editedValues.clear();
//    }
//
//
//    @And("I add {string} key and {string} value to valueMap")
//    public void iAddKeyAndValueToValueMap(String key, String value) {
//        valueStore.put(key, value);
//    }
//
//    @And("I print out the valueStore")
//    public void iPrintOutTheValueStore() {
//        valueStore.forEach((key, value) -> System.out.println(key + " - " + value));
//    }
//
//
//    @When("I upload an attachment")
//    public void iUploadAnAttachment() {
//        commonPage.commonButton("Add Attachments");
//        driver.findElement(By.id("filename")).sendKeys(attachLocation);
//    }
//
//    @Then("The file will be displayed in the Attachments grid")
//    public void theFileWillBeDisplayedInTheAttachmentsGrid() {
//        try {
//            driver.findElement(By.linkText("Attachments")).click();
//        } catch (ElementClickInterceptedException e) {
//            commonPage.clickErrorHandle(e.toString(), driver.findElement(By.linkText("Attachments")));
//        }
//
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText(attachName))).click();
//
//
//        System.out.println("File " + attachName + " found in Attachment grid");
//
//        Set<String> handles = driver.getWindowHandles();
//        String parentWindow = driver.getWindowHandle();
//
//        for (String windowHandle : handles) {
//            if (!windowHandle.equals(parentWindow)) {
//                driver.switchTo().window(windowHandle);
//            }
//        }
//
//        String download = driver.getCurrentUrl();
//
//        if (download.contains(attachName)) {
//            System.out.println("Attachment URL verified as " + download);
//        }
//
//        String dateString = dateFormat.format(new Date());
//        String timeString = timeFormat.format((new Date()));
//        System.out.println("attempting to add to file: " + filePath + "\\screenshots\\" + "PMA opened attachment" + " " + dateString + " " + timeString + ".png");
//
//        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
//        try {
//            FileUtils.copyFile(srcFile, new File(filePath + "\\screenshots\\" + "PMA opened attachment" + dateString + timeString + ".png"));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        driver.switchTo().window(parentWindow);
//    }
//
//
//    @Then("The update success message will be displayed")
//    public void theUpdateSuccessMessageWillBeDisplayed() {
//        wait.until(ExpectedConditions.attributeContains(By.id("oppor_succ_msg"), "style", "block"));
//    }
//
//    @And("I get all the KPI elements")
//    public void iGetAllTheKPIElements() {
//        ArrayList<KpiClass> testKpi = new ArrayList<>();
//        List<WebElement> kpi = driver.findElements(By.xpath("//a[contains(translate(@id, 'KPI', 'kpi'), 'kpi')]"));
//        KpiClass listEntry;
//        String numExtraction;
//        StringBuilder stringBuilder;
//        String title;
//        int intValue;
//        float floatValue;
//        for (WebElement element : kpi) {
//            title = element.findElement(By.className("desc")).getText();
//            List<WebElement> values = element.findElements(By.xpath(".//descendant::div[@class=\"number\"]/span"));
//            for (WebElement ele2 : values) {
//                String cTitle = title.replaceAll("\\n", " ");
//                StringBuilder vName;
//                numExtraction = ele2.getText().replaceAll("\\s+", "");
//                if (numExtraction.matches(".*\\d\\.\\d.*")) {
//                    stringBuilder = new StringBuilder();
//                    vName = new StringBuilder();
//                    for (int i = 0; i < numExtraction.length(); i++) {
//                        if (Character.isDigit(numExtraction.charAt(i)) || numExtraction.charAt(i) == '.') {
//                            stringBuilder.append(numExtraction.charAt(i));
//                        } else if (Character.isLetter(numExtraction.charAt(i))) {
//                            vName.append(numExtraction.charAt(i));
//                        }
//                    }
//                    if (vName.length() > 0) {
//                        cTitle += " " + vName.toString();
//                    }
//                    floatValue = Float.parseFloat(stringBuilder.toString());
//                    listEntry = new KpiClass(cTitle, floatValue);
//                } else {
//                    stringBuilder = new StringBuilder();
//                    vName = new StringBuilder();
//                    for (int i = 0; i < numExtraction.length(); i++) {
//                        if (Character.isDigit(numExtraction.charAt(i))) {
//                            stringBuilder.append(numExtraction.charAt(i));
//                        } else if (Character.isLetter(numExtraction.charAt(i))) {
//                            vName.append(numExtraction.charAt(i));
//                        }
//                    }
//                    if (vName.length() > 0) {
//                        cTitle += " " + vName.toString();
//                    }
//                    intValue = Integer.parseInt(stringBuilder.toString());
//                    listEntry = new KpiClass(cTitle, intValue);
//                }
//                testKpi.add(listEntry);
//            }
//
//        }
//        System.out.println("Printing testKpi array!");
//        for (KpiClass kpiClass : testKpi) {
//            System.out.println("KPI Title: " + kpiClass.getKpiTitle());
//            if (kpiClass.getKpiValueInt() < 0) {
//                System.out.println("KPI Value: " + kpiClass.getKpiValueFloat());
//            } else {
//                System.out.println("KPI Value: " + kpiClass.getKpiValueInt());
//            }
//        }
//    }
//
//    @And("I choose {string} from the list")
//    public void iChooseFromTheList(String choice) {
//        driver.findElement(By.xpath("//ul[@class=\"dropdown-menu inner selectpicker\"]/descendant::span[text()=\"" + choice + "\"]")).click();
//    }
//
//    @And("I get the text from the {string} field")
//    public void iGetTheTextFromTheField(String field) {
//        String fieldText = commonPage.commonFieldRead(field);
//        System.out.println("Text currently visible in " + field + " field: " + fieldText);
//        valueStore.put(field, fieldText);
//    }
//
//    @And("I return to the Apps homepage")
//    public void iReturnToTheAppsHomepage() {
//        driver.get("https://dev.patracorp.net/home");
//        commonPage.pageLoaded();
//    }
//
//    @And("I open DBR for Company 002")
//    public void iOpenDBRForCompany() {
//        commonPage.pageLoaded();
//        WebElement selectedList = driver.findElement(By.id("companyStartId"));
//        Select list = new Select(selectedList);
//        list.selectByVisibleText("Company 002");
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(" Company 002 - DBR"))).click();
//        commonPage.pageLoaded();
//    }
//
//    @And("I open {string} for Company 002")
//    public void iOpenForCompany(String service) {
//        commonPage.pageLoaded();
//        WebElement selectedList = driver.findElement(By.id("companyStartId"));
//        Select list = new Select(selectedList);
//        list.selectByVisibleText("Company 002");
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Company 002 - " + service + ""))).click();
//        commonPage.pageLoaded();
//    }
//
//    @Then("I verify the number of records in the {string} grid match the {string} KPI")
//    public void iVerifyTheNumberOfRecordsInTheGridMatchTheKPI(String tabName, String kpi) {
//        commonPage.clickKpi(kpi);
//        commonPage.pageLoaded();
//        String rowNum;
//        rowNum = Integer.toString(commonPage.gridRecordNumber(tabName));
//
//        Assert.assertEquals(rowNum, valueStore.get(kpi), "Rows in grid do not match number of " + kpi + " KPI");
//        System.out.println("Number of records in grid (" + rowNum + ") match the number shown in the " + kpi + " KPI (" + valueStore.get(kpi) + ")");
//    }
//
//    @Then("The log out version of the sign in page will be displayed")
//    public void theLogOutVersionOfTheSignInPageWillBeDisplayed() {
//        commonPage.pageLoaded();
//        Assert.assertTrue(driver.getCurrentUrl().contains("auth/logout"), "Not redirected to log out page!");
//        Assert.assertEquals(driver.findElement(By.xpath("/html/body/div[1]")).getText(), "You are now logged out", "You are now logged out message not displayed!");
//        System.out.println("Log out screen displayed");
//    }
//
//    @And("I fail the test")
//    public void iFailTheTest() {
//        Assert.fail("Failing test for testing purpose");
//    }
//
//    @And("I add the following items to the valueMap")
//    public void iAddTheFollowingItemsToTheValueMap(Map<String, String> table) {
//        table.forEach((key, value) -> valueStore.put(key, value));
//    }
//
//    @When("I edit the following items")
//    public void iEditTheFollowingItems(Map<String, String> table) {
//        String dateString = dateFormat.format(new Date());
//        String timeString = timeFormat.format(new Date());
//        table.forEach((key, value) -> {
//            boolean foundItem = false;
//            if (value.contains("<current date>")) {
//                foundItem = true;
//                value = value.replaceAll("<current date>", dateString + " " + timeString);
//            }
//            if (commonPage.commonFieldEnter(key, value)) {
//                foundItem = true;
//                System.out.println("Entering \"" + value + "\" into \"" + key + "\" field");
//            } else if (commonPage.commonDropDownSelect(key, value)) {
//                foundItem = true;
//                System.out.println("Selecting \"" + value + "\" from \"" + key + "\" drop down");
//            } else if (commonPage.commonTextAreaEnter(key, value)) {
//                foundItem = true;
//                System.out.println("Entering \"" + value + "\" into \"" + key + "\" text area");
//            }
//            Assert.assertTrue(foundItem, "Could not find " + key + " field, drop down, or text area!");
//            editedValues.put(key, value);
//        });
//    }
//
//    @And("Verify the error {string} displays")
//    public void verifyTheErrorDisplays(String error) {
//        commonPage.pageLoaded();
//        boolean errorDisplays = driver.findElement(By.xpath("//*[normalize-space()=\"" + error + "\"]")).isDisplayed();
//
//        Assert.assertTrue(errorDisplays, "Error not displayed");
//
//        System.out.println("Correct error displays");
//    }
//
//    @When("I use the Back button")
//    public void iUseTheBackButton() {
//        driver.navigate().back();
//        pageLoaded();
//    }
//
//    @And("I Verified the Work order back ground {string} color")
//    public void iVerifiedTheWorkOrderBackGroundColor(String scenarioName) throws Exception {
//        pcPage.verifyBackGroundColor(scenarioName);
//    }
//

//
//    @When ("I filter for work order {string}")
//    public void sandbox(String searchString){
//        commonGrid.gridHeaderField("WO #", searchString);
//        try {
//            wait.until(ExpectedConditions.stalenessOf(commonGrid.gridEntry("row 1", "WO #")));
//        } catch (TimeoutException ignored) {
//        }
//    }
//
//    @Then("There are no results")
//    public void thereAreNoResults() {
//        Assert.assertEquals(commonGrid.gridRowCount("Open Policies"), 1); //There is one row with the empty message
//        Assert.assertEquals(commonGrid.gridRecordNumber("Open Policies"), 0);
//    }
//
//    @Then("There are results")
//    public void thereAreResults() {
//        Assert.assertNotEquals(commonGrid.gridRowCount("Open Policies"), 1);
//        Assert.assertNotEquals(commonGrid.gridRecordNumber("Open Policies"), 0);
//    }
//
//    @Then("The stop button {string} displayed")
//    public void stopButtonDisplayed(String displayed) {
//        if(displayed.equalsIgnoreCase("is not")){
//            Assert.assertFalse(pcPage.stopButtonDisplayed());
//        }
//        else{
//            Assert.assertTrue(pcPage.stopButtonDisplayed());
//        }
//    }
//
//    @Then("The Time Tracking modal {string} displayed")
//    public void timeTrackingModalDisplayed(String displayed) {
//        if(displayed.equalsIgnoreCase("is not")){
//            Assert.assertFalse(pcPage.timeTrackingModalDisplayed(false));
//        }
//        else{
//            Assert.assertTrue(pcPage.timeTrackingModalDisplayed(true));
//        }
//    }
//
//    @When("I click the {string} button in the time tracking modal")
//    public void iClickTheButtonInTheTimeTrackingModal(String button) {
//        pcPage.clickTimeTrackingButton(button);
//    }
//
//    @Then("the timer is for {string}")
//    public void theTimerIsFor(String company) {
//        Assert.assertTrue(pcPage.getTimerCompany().contains(company));
//    }
//
//    // Is there a way to combine this function with the previous one?
//    @Then("the timer is for {string} or {string}")
//    public void theTimerIsFor(String company, String otherPossibleCompany) {
//        Assert.assertTrue(pcPage.getTimerCompany().contains(company) || pcPage.getTimerCompany().contains(otherPossibleCompany));
//    }
//
//    @And("The {string} has a red outline")
//    public void theHasARedOutline(String fieldDropDown) {
//        if(commonForm.commonField(fieldDropDown) != null){
//            Assert.assertTrue(pcPage.elementHasError(commonForm.commonField(fieldDropDown)));
//        }
//        else if(commonForm.commonField(fieldDropDown) != null){
//            Assert.assertTrue(pcPage.elementHasError(commonForm.commonField(fieldDropDown)));
//        }
//        else{
//            System.out.println("Could not find field or dropdown.");
//            Assert.assertTrue(false);
//        }
//    }
//
//    @And("the open timer for the current user is {string}")
//    public void theOpenTimerForTheCurrentUserIs(String task) {
//        // Get the current user
//        String currentUser = pcPage.currentUserOpenTimer();
//
//        //Find employee in table
//        //Check task for employee row matches submit
//        Assert.assertEquals(pcPage.userOpenTask(currentUser), task);
//    }
//
//    @And("I enter the work order into the work order field")
//    public void iEnterTheWorkOrderIntoTheWorkOrderField() {
//        pcPage.setTimerWO(valueStore.get("workOrder"));
//    }
//
//    @And("The {string} link {string} displayed")
//    public void theLinkDisplayed(String linkText, String expectation) {
//        System.out.println("Checking if "+ linkText + " link exists.");
//        if(expectation.equalsIgnoreCase("is not")){
//            Assert.assertFalse(pcPage.linkDisplayed(linkText));
//        }
//        else{
//            Assert.assertTrue(pcPage.linkDisplayed(linkText));
//        }
//    }
//
//    @And("I set the {string} date in the date picker to {string}")
//    public void iSetThatDateInTheDatePickerTo(String datePicker,String date) {
//        System.out.println("Setting the calendar fields.");
//        pcPage.setDateField(datePicker, date);
//    }
//
//    @And("The timer error is displayed")
//    public void theTimerErrorIsDisplayed() {
//        System.out.println("Checking contents of the timer error message.");
//        Assert.assertTrue(pcPage.timerError().contains("Please enter a valid Work Order # for the selected Company"));
//    }
//
//    @Then("The reports page is displayed")
//    public void theReportsPageIsDisplayed() {
//        System.out.println("Checking that I am on the Reports page.");
//        Assert.assertTrue(pcPage.verifyReportsPage());
//    }
//
//    @Then("I see an error warning for the {string} field")
//    public void iSeeAnErrorWarningForTheField(String fieldName) {
//        Assert.assertTrue(pcPage.errorWarningDisplayed(fieldName));
//    }
//
//    @Then("The the {string} field is set to {string}")
//    public void theTheFieldIsSetTo(String fieldName, String day) throws ParseException {
//
//        DateFormat dateOnly = new SimpleDateFormat("MM/dd/yyyy");
//
//        Date actualDate = dateOnly.parse(commonForm.commonFieldRead(fieldName));
//        Date expectedDate;
//
//        if (day.equals("tomorrow")) {
//            long milliSecondsDate = System.currentTimeMillis();
//            long oneDayMilliseconds = 86400000;
//            expectedDate = new Date(milliSecondsDate + oneDayMilliseconds);
//        } else {
//            expectedDate = new Date();
//        }
//        expectedDate = dateOnly.parse(dateOnly.format(expectedDate));// Remove the time component, setting it to midnight.
//
//        Assert.assertEquals(expectedDate, actualDate);
//    }
//
//    @Then("The work order will be displayed in the grid")
//    public void theWorkOrderWillBeDisplayedInTheGrid() {
//        System.out.println("Checking that the most recent work order is displayed in the grid.");
//        Assert.assertEquals(commonGrid.gridEntry("row 1", "WO #").getText(), valueStore.get("workOrder"));
//    }
//
//    @And("I click Ok button on discard alert pop-up for PC")
//    public void iClickOkButtonOnDiscardAlertPopUpForPC() {
//        System.out.println("Clicking the OK button to dismiss the alert modal");
//        pcPage.dismissDiscardModal();
//    }
//
//    @And("I click the Policy Checking Reset button")
//    public void iClickThePolicyCheckingResetButton() {
//        System.out.println("Clicking the Reset button to clear all filters");
//        pcPage.clickResetButton();
//    }
}