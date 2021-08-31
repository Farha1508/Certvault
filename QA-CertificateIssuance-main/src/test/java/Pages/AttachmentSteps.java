package Pages;

import Base.BaseUtil;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.File;
import java.util.Set;

public class AttachmentSteps extends BaseUtil {

    public AttachmentSteps() {
        login = new Login(driver);
        wait = new WebDriverWait(driver, 10);
    }

    @When("I upload an attachment")
    public void iUploadAnAttachment() {
        if (!commonForm.commonButton("Add Attachments") && !commonForm.commonButton("Add Attachment")) {
            driver.findElement(By.linkText("Add Attachments")).click();
        }
        pageLoaded();
        Set<String> handles = driver.getWindowHandles();
        if (handles.size() > 1) {
            String parentWindow = driver.getWindowHandle();

            for (String windowHandle : handles) {
                if (!windowHandle.equals(parentWindow)) {
                    driver.switchTo().window(windowHandle);
                }
            }
            WebElement element = driver.findElement(By.id("UploadFile1"));
            Actions builder = new Actions(driver);
            builder.moveToElement(element).perform();
            element.sendKeys(attachLocation);
            driver.findElement(By.id("btnSave")).click();


            driver.switchTo().window(parentWindow);
            wait.until(ExpectedConditions.numberOfWindowsToBe(1));
        } else if (driver.findElements(By.id("addAttachment")).size() >= 1) {
            driver.findElement(By.id("attach_file")).sendKeys(attachLocation);
            driver.findElement(By.id("attachment_submit")).click();
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("addAttachment")));
        } else {
            driver.findElement(By.id("filename")).sendKeys(attachLocation);
        }
        pageLoaded();
    }

    @When("I cancel an attachment")
    public void iCancelAnAttachment() {
        if (!commonForm.commonButton("Add Attachments") && !commonForm.commonButton("Add Attachment")) {
            driver.findElement(By.linkText("Add Attachments")).click();
        }
        pageLoaded();
        Set<String> handles = driver.getWindowHandles();
        if (handles.size() > 1) {
            String parentWindow = driver.getWindowHandle();

            for (String windowHandle : handles) {
                if (!windowHandle.equals(parentWindow)) {
                    driver.switchTo().window(windowHandle);
                }
            }
            WebElement element = driver.findElement(By.id("UploadFile1"));
            Actions builder = new Actions(driver);
            builder.moveToElement(element).perform();
            element.sendKeys(attachLocation);
            driver.findElement(By.xpath("//input[@value='Cancel']")).click();


            driver.switchTo().window(parentWindow);
            wait.until(ExpectedConditions.numberOfWindowsToBe(1));
        } else if (driver.findElements(By.id("addAttachment")).size() >= 1) {
            driver.findElement(By.id("attach_file")).sendKeys(attachLocation);
            driver.findElement(By.id("attachment_close")).click();
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("addAttachment")));
        } else {
            driver.findElement(By.id("filename")).sendKeys(attachLocation);
        }
        pageLoaded();
    }

    @Then("The file will be displayed in the Attachments grid")
    public void theFileWillBeDisplayedInTheAttachmentsGrid() {
        File downloads = new File(dLFolder);
        int fileNum = downloads.listFiles().length;
        try {
            driver.findElement(By.linkText("Attachments")).click();
        } catch (ElementClickInterceptedException e) {
            commonForm.clickErrorHandle(e.toString(), driver.findElement(By.linkText("Attachments")));
        }
        pageLoaded();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText(attachName))).click();

        System.out.println("File " + attachName + " found in Attachment grid");

        Set<String> handles = driver.getWindowHandles();
        if (handles.size() > 1) {
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

            driver.close();
            driver.switchTo().window(parentWindow);
        } else {
            long startTime = System.currentTimeMillis();
            while (downloads.listFiles().length == fileNum && (System.currentTimeMillis() - startTime) < 30000) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ignored) {

                }
            }
            if (downloads.listFiles().length > fileNum) {
                for (File file : downloads.listFiles()) {
                    System.out.println("Filename: " + file.getName());
                    if (file.getName().contains(attachName)) {
                        System.out.println(attachName + " downloaded");
                        file.delete();
                    }
                }
            }
        }
    }

    @Then("The file will not be displayed in the Attachments grid")
    public void theFileWillNotBeDisplayedInTheAttachmentsGrid() {
        try {
            driver.findElement(By.linkText("Attachments")).click();
        } catch (ElementClickInterceptedException e) {
            commonForm.clickErrorHandle(e.toString(), driver.findElement(By.linkText("Attachments")));
        }

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[text()='No data available in table']")));
    }

    @When("I click the Attachments page Save button")
    public void clickAttachmentSaveBtn(){
        driver.findElement(By.id("btnSave")).click();
    }

    @Then("The no attachment error warning is displayed")
    public void theNoAttachmentErrorWarningIsDisplayed() {
        System.out.println("Checking for attachment error messages.");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("valSummary")));
        String errorText = driver.findElement(By.id("valSummary")).getText();
        Assert.assertTrue(errorText.contains("Errors:"));
        Assert.assertTrue(errorText.contains("You must select a file to upload"));

    }

    @And("I cannot delete the attachment")
    public void iCannotDeleteTheAttachment() {
        System.out.println("Checking that the delete action is unavailable.");
        Assert.assertTrue(driver.findElements(By.id("attachment_delete")).isEmpty());
    }

    @When("I delete the attachment")
    public void iDeleteTheAttachment() throws InterruptedException {
        System.out.println("Checking that the delete action is available.");
        int numberOfAttachments = driver.findElements(By.id("attachment_delete")).size();
        while(numberOfAttachments>0){
            System.out.println("Deleting file.");
            wait.until(ExpectedConditions.elementToBeClickable(By.id("attachment_delete")));
            driver.findElement(By.id("attachment_delete")).click();
            BaseUtil.pageLoaded();
            commonForm.commonButton("confirm");
            BaseUtil.pageLoaded();
            // After confirming a delete, the user is redirected to the Details tab. Wait for a Details tab element to be visible before continuing.
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("pc_Comments")));//Nothing special about this element. Just unique to Detail tab.
            driver.findElement(By.linkText("Attachments")).click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("th-LinkWOAttachments")));
            numberOfAttachments=driver.findElements(By.id("attachment_delete")).size();
        }
        Assert.assertTrue(driver.findElements(By.id("attachment_delete")).isEmpty());
    }
}