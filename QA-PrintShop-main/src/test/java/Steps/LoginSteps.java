package Steps;

import Base.BaseUtil;
import Pages.Login;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class LoginSteps extends BaseUtil {

    public LoginSteps() {
        login = new Login(driver);
        wait = new WebDriverWait(driver, 10);
    }

    @Given("^I am on the login page$")
    public void iAmOnTheLoginPage() {
        System.out.println("Navigating to DEV login page\n");
        driver.get("https://dev.patracorp.net");
//        driver.get("http://login.dev.patracloud.net/auth");
        driver.manage().window().maximize();
    }

    @When("I enter the email and password for the \"([^\"]*)\"$")
    public void iEnterTheEmailAndPasswordForThe(String user) throws Exception {
        currentLogin = user;
        login.EnterCredentials(user);
    }

    @When("I enter a(n) {string} email address and {string} password")
    public void iEnterEmail(String userType, String passType) throws Exception {
        // should this switch statement instead be in the enterEmail() function?
       switch (userType) {
            case "empty" -> login.enterEmail("");
            case "invalid" -> login.enterEmail(login.getLogins().getProperty("invalidUser"));
            case "valid" -> login.enterEmail(login.getLogins().getProperty("validUser"));
            case "incorrect" -> login.enterEmail(login.getLogins().getProperty("incorrectUser")); //email missing the '@' symbol.
            default -> throw new Exception("No login information available for " + userType + " please check Login list.");
        }

        // should this switch statement instead be in the enterPass() function?
        switch (passType) {
            case "empty" -> login.enterPass("");
            case "invalid" -> login.enterPass(login.getLogins().getProperty("invalidPassword"));
            case "valid" -> login.enterPass(login.getLogins().getProperty("validPassword"));
            default -> throw new Exception("No login information available for " + userType + " please check Login list.");
        }
    }

    /**
     * Javadoc for a step.
     * */
    @Then("I see the Login Failed message")
    public void iSeeTheLoginFailedMessage() throws Exception {
        System.out.println("Checking the error message.");
        boolean check = login.confirmErrorMsg();
        Assert.assertTrue(login.confirmErrorMsg(), "The error message was incorrect or not found.");
        Assert.assertTrue(login.confirmOnLoginPage(), "Appear to not be on the Login page.");
    }

    @Then("I see the empty {string} field warning")
    public void thereIsNoErrorDisplayed(String field) throws Exception {
        System.out.println("There should be a warning on the empty field.");
        String warningMsg = "Please fill out this field.";
        //Note: these warnings are browser-specific. This code was written for Chrome.
        Assert.assertEquals(login.warningMsgDisplayed(field), warningMsg);
        Assert.assertFalse(login.errorMsgDisplayed(), "An error message was displayed, but was not expected to.");
        Assert.assertTrue(login.confirmOnLoginPage(), "Appear to not be on the Login page.");
    }

    @Then("I see the email error message")
    public void iSeeTheEmailErrorMessage() throws Exception {
        // full message looks something like "Please include an '@' in the email address. 'carlwu_patracorp.com' is missing an '@'."
        //Note: these warnings are browser-specific. This code was written for Chrome.
        Assert.assertTrue(login.warningMsgDisplayed("email").contains("Please include an '@' in the email address."), "The warning message was not found, or had incorrect text.");
        Assert.assertTrue(login.confirmOnLoginPage(), "Appear to not be on the Login page.");
    }

    @And("I click the Sign In button")
    public void iClickTheSignInButton() {
        System.out.println("Clicking Sign In button\n");
        login.ClickSignIn();
    }

    @Then("I am redirected to login page")
    public void iAmRedirectedToLoginPage() throws Exception {
        System.out.println("Checking the error message.");
        Assert.assertTrue(login.confirmSuccessMsg(), "The success message could not be found, or had incorrect text.");
        Assert.assertTrue(login.confirmOnLoginPage(), "Appear to not be on the Login page.");
    }

    @Then("I will be taken to the apps page")
    public void iWillBeTakenToTheAppsPage() throws Exception {
        login.onCorrectPage();
    }
}
