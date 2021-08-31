package Steps;

import Base.BaseUtil;
import Base.KpiClass;
import Pages.Login;
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
import java.util.*;

public class StepDefs extends BaseUtil {


    public StepDefs() {
        login = new Login(driver);
        wait = new WebDriverWait(driver, 10);
    }

    @And("I open the URL {string}")
    public void iOpenTheURL(String url) {
        driver.get(url);
        commonPage.pageLoaded();
    }

    @Then("I log out")
    public void iLogOut() {
        System.out.println("Logging out " + currentLogin.toUpperCase());

        if (currentApp.equalsIgnoreCase("setup")) {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".dropdown-toggle"))).click();
        } else {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".glyphicon"))).click();
        }

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Log Out')]"))).click();
        commonPage.pageLoaded();
    }

    @When("I click on the {string} tile")
    public void iClickOnTheTile(String tileName) throws Exception {
        currentApp = tileName;
        System.out.println("Clicking on " + tileName + " tile");
        login.ClickOnTile(tileName);
        pageMap(currentApp);
        commonPage.pageLoaded();
    }

    @Then("I will be taken to the homepage for that app")
    public void iWillBeTakenToTheHomepageForThatApp() throws Exception {
        commonPage.pageLoaded();
        Assert.assertTrue(commonPage.onCorrectPage(), "Homepage for " + currentApp + " not displayed");
        System.out.println("On homepage for " + currentApp);
    }

    @And("I wait for {string} seconds")
    public void iWaitForSeconds(String seconds) throws InterruptedException {
        int timeToWait = Integer.parseInt(seconds) * 1000;
        Thread.sleep(timeToWait);
    }

    @And("Return to the apps page")
    public void returnToTheAppsPage() {
        driver.findElement(By.id("index")).click();
    }

    @And("I take a screenshot")
    public void iTakeAScreenshot() {
        String dateString = dateFormat.format(new Date());
        String timeString = timeFormat.format((new Date()));
        System.out.println("attempting to add to file: " + filePath + "\\screenshots\\" + BaseUtil.scenarioName + " " + dateString + " " + timeString + ".png");

        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(srcFile, new File(filePath + "\\screenshots\\" + BaseUtil.scenarioName + dateString + timeString + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @And("I get the {string} info from valueStore")
    public void iGetTheInfoFromValueStore(String storeItem) throws InterruptedException {
        js.executeScript("alert('" + storeItem + " in valueStore: " + valueStore.get(storeItem) + "')");
        Thread.sleep(5000);
        driver.switchTo().alert().accept();
        System.out.println(storeItem + " in valueStore: " + valueStore.get(storeItem));
    }

    @And("I clear the valueStore")
    public void iClearTheValueStore() {
        valueStore.clear();
        editedValues.clear();
    }

    @And("I find the newly created business")
    public void iFindTheNewlyCreatedBusiness() {
        String check = valueStore.get("Business/Policy Holder Name");
        WebElement result = commonPage.gridEntry("row 1", "Business Name");
        commonPage.gridHeaderEnter("Business Name", check);
        wait.until(ExpectedConditions.refreshed(ExpectedConditions.stalenessOf(result)));
        driver.findElement(By.linkText(check)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"opportunities_datatable_ajax\"]/tbody/tr/td[3]")));

    }

    @And("I click on the {string} link")
    public void iClickOnTheLink(String linkText) {
        System.out.println("Clicking on the "+linkText+" link");
        try {
            wait.until(ExpectedConditions.elementToBeClickable(By.linkText(linkText))).click();
        } catch (ElementClickInterceptedException e) {
            commonPage.clickErrorHandle(e.toString(), driver.findElement(By.linkText(linkText)));
        }
        commonPage.pageLoaded();

    }

    @And("I add {string} key and {string} value to valueMap")
    public void iAddKeyAndValueToValueMap(String key, String value) {
        valueStore.put(key, value);
    }

    @And("I print out the valueStore")
    public void iPrintOutTheValueStore() {
        valueStore.forEach((key, value) -> System.out.println(key + " - " + value));
    }

    @Then("The new activity will be displayed in the Activities tab")
    public void theNewActivityWillBeDisplayedInTheActivitiesTab() {
        commonPage.pageLoaded();
//        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("addActivity")));
        try {
            driver.findElement(By.linkText("Activities")).click();
        } catch (ElementClickInterceptedException e) {
            commonPage.clickErrorHandle(e.toString(), driver.findElement(By.linkText("Activities")));
        }

        commonPage.pageLoaded();
        Assert.assertTrue(driver.findElement(By.linkText(valueStore.get("Activity Type"))).isDisplayed(),
                "New activity " + valueStore.get("Activity Type") + " not found in Activities grid");

        System.out.println("New activity " + valueStore.get("Activity Type") + " is displayed in the Activities grid");
    }

    @When("I open the newly created activity")
    public void iOpenTheNewlyCreatedActivity() {
        driver.findElement(By.linkText("Activities")).click();
        commonPage.pageLoaded();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(valueStore.get("Activity Type")))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.id("add_follow")));
    }

    @Then("The new follow up will be displayed in the grid")
    public void theNewFollowUpWillBeDisplayedInTheGrid() {
        driver.findElement(By.id("followUp_submit")).click();
        WebElement activity = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[@id=\"followup_dets\"]/tbody/tr/td[./text()=\"" + valueStore.get("Follow Up Type") + "\"]")));
        Assert.assertEquals(activity.getText(), valueStore.get("Follow Up Type"),
                "Expected follow up " + valueStore.get("Follow Up Type") + " does not display in the Follow Ups grid");
        /*
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"followup_dets\"]/tbody/tr/td[1]")));
        driver.findElement(By.xpath("//*[@id=\"followup_dets\"]/tbody/tr/td[./text()=\""+valueStore.get("Follow Up Type")+"\"]"));
        Assert.assertTrue(driver.findElement(By.xpath("//*[@id=\"followup_dets\"]/tbody/tr/td[./text()=\""+valueStore.get("Follow Up Type")+"\"]")).isDisplayed(),
                "Expected follow up " + valueStore.get("Follow Up Type") + " does not display in the Follow Ups grid");

         */

//        if(commonPage.gridEntry("row 1", "Follow Up Type").getText().equals(valueStore.get("Follow Up Type"))) {
//            System.out.println(valueStore.get("Follow Up Type") + " follow up is displayed in Follow Ups grid");
//        } else {
//            System.err.println("Expected follow up " + valueStore.get("Follow Up Type") + " does not display in the Follow Ups grid");
//        }

    }

    @When("I upload an attachment")
    public void iUploadAnAttachment() {
        commonPage.commonButton("Add Attachments");
        driver.findElement(By.id("filename")).sendKeys(attachLocation);

    }

    @Then("The file will be displayed in the Attachments grid")
    public void theFileWillBeDisplayedInTheAttachmentsGrid() {
        try {
            driver.findElement(By.linkText("Attachments")).click();
        } catch (ElementClickInterceptedException e) {
            commonPage.clickErrorHandle(e.toString(), driver.findElement(By.linkText("Attachments")));
        }

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText(attachName))).click();


        System.out.println("File " + attachName + " found in Attachment grid");

        Set<String> handles = driver.getWindowHandles();
        String parentWindow = driver.getWindowHandle();

        for (String windowHandle : handles) {
            if (!windowHandle.equals(parentWindow)) {
                driver.switchTo().window(windowHandle);
            }
        }

        String download = driver.getCurrentUrl();

        if (download.contains(attachName)) {
            System.out.println("Attachment URL verified as " + download);
        }

        String dateString = dateFormat.format(new Date());
        String timeString = timeFormat.format((new Date()));
        System.out.println("attempting to add to file: " + filePath + "\\screenshots\\" + "PMA opened attachment" + " " + dateString + " " + timeString + ".png");

        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(srcFile, new File(filePath + "\\screenshots\\" + "PMA opened attachment" + dateString + timeString + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        driver.switchTo().window(parentWindow);
    }

    @And("I open the opportunity")
    public void iOpenTheOpportunity() {
        commonPage.pageLoaded();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(valueStore.get("Coverage Type")))).click();
    }

    @Then("The update success message will be displayed")
    public void theUpdateSuccessMessageWillBeDisplayed() {
        wait.until(ExpectedConditions.attributeContains(By.id("oppor_succ_msg"), "style", "block"));
    }

    @And("I click {string} checkbox")
    public void iClickCheckbox(String checkBox) {
        System.out.println("Clicking " + checkBox + " check box");
        Assert.assertTrue(commonPage.commonCheckBox(checkBox), checkBox + " checkbox could not be found!");
    }

    @And("I get all the KPI elements")
    public void iGetAllTheKPIElements() {
        ArrayList<KpiClass> testKpi = new ArrayList<>();
        List<WebElement> kpi = driver.findElements(By.xpath("//a[contains(translate(@id, 'KPI', 'kpi'), 'kpi')]"));
        KpiClass listEntry;
        String numExtraction;
        StringBuilder stringBuilder;
        String title;
        int intValue;
        float floatValue;
        for (WebElement element : kpi) {
            title = element.findElement(By.className("desc")).getText();
            List<WebElement> values = element.findElements(By.xpath(".//descendant::div[@class=\"number\"]/span"));
            for (WebElement ele2 : values) {
                String cTitle = title.replaceAll("\\n", " ");
                StringBuilder vName;
                numExtraction = ele2.getText().replaceAll("\\s+", "");
                if (numExtraction.matches(".*\\d\\.\\d.*")) {
                    stringBuilder = new StringBuilder();
                    vName = new StringBuilder();
                    for (int i = 0; i < numExtraction.length(); i++) {
                        if (Character.isDigit(numExtraction.charAt(i)) || numExtraction.charAt(i) == '.') {
                            stringBuilder.append(numExtraction.charAt(i));
                        } else if (Character.isLetter(numExtraction.charAt(i))) {
                            vName.append(numExtraction.charAt(i));
                        }
                    }
                    if (vName.length() > 0) {
                        cTitle += " " + vName.toString();
                    }
                    floatValue = Float.parseFloat(stringBuilder.toString());
                    listEntry = new KpiClass(cTitle, floatValue);
                } else {
                    stringBuilder = new StringBuilder();
                    vName = new StringBuilder();
                    for (int i = 0; i < numExtraction.length(); i++) {
                        if (Character.isDigit(numExtraction.charAt(i))) {
                            stringBuilder.append(numExtraction.charAt(i));
                        } else if (Character.isLetter(numExtraction.charAt(i))) {
                            vName.append(numExtraction.charAt(i));
                        }
                    }
                    if (vName.length() > 0) {
                        cTitle += " " + vName.toString();
                    }
                    intValue = Integer.parseInt(stringBuilder.toString());
                    listEntry = new KpiClass(cTitle, intValue);
                }
                testKpi.add(listEntry);
            }

        }
        System.out.println("Printing testKpi array!");
        for (KpiClass kpiClass : testKpi) {
            System.out.println("KPI Title: " + kpiClass.getKpiTitle());
            if (kpiClass.getKpiValueInt() < 0) {
                System.out.println("KPI Value: " + kpiClass.getKpiValueFloat());
            } else {
                System.out.println("KPI Value: " + kpiClass.getKpiValueInt());
            }

        }
    }

    @And("I choose {string} from the list")
    public void iChooseFromTheList(String choice) {
        driver.findElement(By.xpath("//ul[@class=\"dropdown-menu inner selectpicker\"]/descendant::span[text()=\"" + choice + "\"]")).click();
    }

    @And("I get the text from the {string} field")
    public void iGetTheTextFromTheField(String field) {
        String fieldText = commonPage.commonFieldRead(field);
        System.out.println("Text currently visible in " + field + " field: " + fieldText);
        valueStore.put(field, fieldText);
    }

    @And("I return to the Apps homepage")
    public void iReturnToTheAppsHomepage() {
        driver.get("https://dev.patracorp.net/home");
        commonPage.pageLoaded();
    }

    @And("I open DBR for Company 002")
    public void iOpenDBRForCompany() {
        commonPage.pageLoaded();
        WebElement selectedList = driver.findElement(By.id("companyStartId"));
        Select list = new Select(selectedList);
        list.selectByVisibleText("Company 002");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(" Company 002 - DBR"))).click();
        commonPage.pageLoaded();
    }

    @And("I open {string} for Company 002")
    public void iOpenForCompany(String service) {
        commonPage.pageLoaded();
        WebElement selectedList = driver.findElement(By.id("companyStartId"));
        Select list = new Select(selectedList);
        list.selectByVisibleText("Company 002");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Company 002 - " + service + ""))).click();
        commonPage.pageLoaded();
    }

    @Then("The log out version of the sign in page will be displayed")
    public void theLogOutVersionOfTheSignInPageWillBeDisplayed() {
        commonPage.pageLoaded();
        Assert.assertTrue(driver.getCurrentUrl().contains("auth/logout"), "Not redirected to log out page!");
        Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[1]")).getText().equals("You are now logged out"),
                "You are now logged out message not displayed!");
        System.out.println("Log out screen displayed");
    }

    @And("I fail the test")
    public void iFailTheTest() {
        Assert.fail("Failing test for testing purpose");
    }

    @And("I add the following items to the valueMap")
    public void iAddTheFollowingItemsToTheValueMap(Map<String, String> table) {
        table.forEach((key, value) -> valueStore.put(key, value));
    }

    @When("I edit the following items")
    public void iEditTheFollowingItems(Map<String, String> table) {
        String dateString = dateFormat.format(new Date());
        String timeString = timeFormat.format(new Date());
        table.forEach((key, value) -> {
            boolean foundItem = false;
            if (value.contains("<current date>")) {
                foundItem = true;
                value = value.replaceAll("<current date>", dateString + " " + timeString);
            }
            if (commonPage.commonFieldEnter(key, value)) {
                foundItem = true;
                System.out.println("Entering \"" + value + "\" into \"" + key + "\" field");
            } else if (commonPage.commonDropDownSelect(key, value)) {
                foundItem = true;
                System.out.println("Selecting \"" + value + "\" from \"" + key + "\" drop down");
            } else if (commonPage.commonTextAreaEnter(key, value)) {
                foundItem = true;
                System.out.println("Entering \"" + value + "\" into \"" + key + "\" text area");
            }
            Assert.assertTrue(foundItem, "Could not find " + key + " field, drop down, or text area!");
            editedValues.put(key, value);
        });
    }

    @And("Verify the error {string} displays")
    public void verifyTheErrorDisplays(String error) {
        commonPage.pageLoaded();
        boolean errorDisplays = driver.findElement(By.xpath("//*[normalize-space()=\"" + error + "\"]")).isDisplayed();

        Assert.assertTrue(errorDisplays, "Error not displayed");

        System.out.println("Correct error displays");
    }

    @Then("I can see the search field")
    public void verifyTheSearchFieldDisplays(String error) {

    }

    @And("I refresh the page")
    public void iRefreshThePage() {
        System.out.println("Refreshing the page.");
        driver.navigate().refresh();
        pageLoaded();
    }

    @When("I Click on user icon")
    public void iClickOnUserIcon() {
        System.out.println("Clicking User Icon");
        login.ClickUserIcon();
    }

    @And("I click Logout button")
    public void iClickLogoutButton() {
        System.out.println("Clicking Logout button");
        login.ClickLogOut();
    }
}
