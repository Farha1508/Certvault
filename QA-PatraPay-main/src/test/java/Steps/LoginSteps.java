package Steps;

import Base.BaseUtil;
import Pages.PaymentPortalPage;
import Pages.QBISPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LoginSteps extends BaseUtil {

    public LoginSteps() {
        paymentPortal_Page = new PaymentPortalPage(driver, js);
        qbisPage = new QBISPage(driver, js);
    }

    @When("I enter an email without proper formatting")
    public void iEnterAnEmailWithoutProperFormatting() {
        driver.findElement(By.id("signIn-Email")).sendKeys("test.com");
      driver.findElement(By.id("signIn-SubmitLogin")).click();
    }

    @Then("A proper browser message displays showing the correct formatting")
    public void aProperBrowserMessageDisplaysShowingTheCorrectFormatting() {
        boolean result = false;
        String validation = "You have entered an invalid email";
        try {
            result = new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text() = 'You have entered an invalid email']"))).isDisplayed();
        } catch (
                TimeoutException ignored) {
        }
        Assert.assertTrue(result, "Expected validation message \"" + validation + "\" did not display!");
        System.out.println("Email validation message: \n" + validation);

    }

    @When("I enter an email with no password")
    public void iEnterAnEmailWithNoPassword() {
        driver.findElement(By.id("signIn-Email")).sendKeys("admin+test_finance_agent@qbisins.com");
        driver.findElement(By.id("signIn-SubmitLogin")).click();
    }

    @Then("A browser message displays about the blank password field")
    public void aBrowserMessageDisplaysAboutTheBlankPasswordField() {
        boolean result = false;
        String validation = "Please enter your password";
        try {
            result = new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text() = 'Please enter your password']"))).isDisplayed();
        } catch (
                TimeoutException ignored) {
        }
        Assert.assertTrue(result, "Expected validation message \"" + validation + "\" did not display!");
        System.out.println("Password validation message: \n" + validation);

    }

    @When("I enter a password with no Email")
    public void iEnterAPasswordWithNoEmail() {
        driver.findElement(By.id("signIn-Password")).sendKeys("Password");
        driver.findElement(By.id("signIn-SubmitLogin")).click();
    }

    @Then("A browser message displays about the blank Email field")
    public void aBrowserMessageDisplaysAboutTheBlankEmailField() {
        boolean result = false;
        String validation = "Please enter your email address";
        try {
            result = new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text() = 'Please enter your email address']"))).isDisplayed();
        } catch (
                TimeoutException ignored) {
        }
        Assert.assertTrue(result, "Expected validation message \"" + validation + "\" did not display!");
        System.out.println("Email validation message when left blank: \n" + validation);

    }

    @When("I enter an Email with a random password which is not created")
    public void iEnterAnEmailWithARandomPasswordWhichIsNotCreated() {
        driver.findElement(By.id("signIn-Email")).sendKeys("roopali.sharma+inactiveaccount@jellyfishtechnologies.com");
        driver.findElement(By.id("signIn-Password")).sendKeys("Password");
        commonPage.commonButton("SIGN IN");
    }

    @Then("A proper error message displays")
    public void aProperErrorMessageDisplays()  {
    boolean result = false;
    String validation = "Oops! You entered an invalid username or password.";
        try {
        result = new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text() = 'Oops!  You entered an invalid username or password.']"))).isDisplayed();
    } catch (
    TimeoutException ignored) {
    }
        Assert.assertTrue(result, "Expected validation message \"" + validation + "\" did not display!");
        System.out.println("In-active account validation message: \n" + validation);
    }


        @And("I enter email id {string} in the {string} field")
    public void iEnterEmailIdInTheField(String email, String field) {
        driver.findElement(By.xpath("//input[@id='resetPassword-email']")).sendKeys(email);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("")));
    }

    @Then("I go to my gmail mail inbox")
    public void iGoToMyGmailInbox(List<Map<String, String>> dataTable) {
        Map<String, String> login = dataTable.get(0);
        paymentPortal_Page.enterMailId(login);
        paymentPortal_Page.enterPassword(login);
        paymentPortal_Page.clickSignUPButton();
    }

    @And("I enter valid gmail credentials to login")
    public void iEnterValidGmailCredentialsToLogin(List<Map<String, String>> entry) {
        Map<String, String> dataTable = entry.get(0);
        paymentPortal_Page.enterMailId(dataTable);
        paymentPortal_Page.enterPassword(dataTable);
        paymentPortal_Page.clickSignUPButton();
    }

    @And("I open the reset password mail in gmail")
    public void iOpenTheResetPasswordMailInGmail() {
        List<WebElement> a = driver.findElements(By.xpath("//*[@class='yW']/span"));
        System.out.println(a.size());
        for (WebElement webElement : a) {
            System.out.println(webElement.getText());
            if (webElement.getText().equals("PatraCloud Password.")) {  // if u want to click on the specific mail then here u can pass it
                webElement.click();
            }
        }
    }

    @Then("I click the reset password link in the email")
    public void iClickTheResetPasswordLinkInTheEmail() {
        int tabs = driver.getWindowHandles().size();
        driver.findElement(By.linkText("Reset Password")).click();
        wait.until(ExpectedConditions.numberOfWindowsToBe(tabs + 1));
        String Tab1 = driver.getWindowHandle();
        ArrayList<String> availableWindows = new ArrayList<String>(driver.getWindowHandles());
        if (!availableWindows.isEmpty()) {
            driver.switchTo().window(availableWindows.get(1));
        }
    }

    @And("Enter a(n) {string} email")
    public void enterAnEmail(String email) {
        login.enterEmail(email);
    }

    @And("Enter a(n) {string} password")
    public void enterAPassword(String password) {
        login.enterPassword(password);
    }

    @Then("User should taken to the QBIS home page successfully")
    public void userShouldTakenToTheQBISHomePageSuccessfully() {
        login.confirmLoginSuccessful();
    }
}
