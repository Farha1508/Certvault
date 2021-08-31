package Pages;


import Base.BaseUtil;
import org.junit.Assert;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Login {
    private final WebDriver driver;

    @FindBy(how = How.ID, using = "signIn-Email")
    private WebElement fieldEmail;
    @FindBy(how = How.ID, using = "signIn-Password")
    private WebElement fieldPassword;
    @FindBy(how = How.ID, using = "signIn-SubmitLogin")
    private WebElement btnSignIn;

    private final Properties logins;
    private final String logProp = Thread.currentThread().getContextClassLoader().getResource("").getPath()+"logins.properties";
    public Login(WebDriver driver) {
        this.driver = driver;
        this.logins = new Properties();
        try {
            this.logins.load(new FileInputStream(logProp));
        } catch (IOException e) {
            Assert.fail("Could not load logins.properties, please ensure file is available in src/test/resources folder");
        }

        PageFactory.initElements(driver, this);
    }

    public Properties getLogins() {
        return logins;
    }

    public void enterEmail(String userName) {

        String email;

        System.out.println("Entering login for " + userName.toUpperCase() + " role");
        switch (userName.toLowerCase().replaceAll("\\s+", "")) {
            case "carrier":
                email = logins.getProperty("carrierEmail");
                break;
            case "owner":
                email = logins.getProperty("ownerEmail");
                break;
            case "agency":
                email = logins.getProperty("agencyEmail");
                break;
            case "directpay_agency":
                email=logins.getProperty("DirectPayagencyEmail");
                break;
            default:
                email = "";
                System.out.println("No email information available for " + userName + " please check Login list.");

        }

        fieldEmail.sendKeys(email);


    }

    public void enterPassword(String userPass) {
        String pass;
        switch (userPass.toLowerCase().replaceAll("\\s+", "")) {
            case "carrier":
                pass = logins.getProperty("carrierPass");
                break;
            case "owner":
                pass = logins.getProperty("ownerPass");
                break;
            case "agency":
                pass = logins.getProperty("agencyPass");
                break;
            case "directpay_agency":
                pass = logins.getProperty("DirectPayagencyPass");
                break;
            default:
                pass = "";
                System.out.println("No password information available for " + userPass + " please check Login list.");

        }
        fieldPassword.sendKeys(pass);
    }

    public void ClickSignIn() {
        btnSignIn.click();
    }

    @FindBy(how = How.CSS, using = ".glyphicon-user")
    private WebElement userIcon;
    @FindBy(how = How.XPATH, using = "//a/h4[contains(text(),'Log Out')]")
    private WebElement logOutBtn;

    @FindBy(how = How.XPATH, using = "/html/body/title[contains(text(),'Patra Corp - Home')]")
    private WebElement pageName;

    public void onCorrectPage() throws Exception {
        try {
            pageName.isDisplayed();
        } catch (NoSuchElementException e) {
            throw new Exception("Login failed. Apps page not reached");
        }
        System.out.println("On apps homepage");
    }

    public void confirmLoginSuccessful() {
        String actual_title= driver.getTitle();
        System.out.println(actual_title);
        Assert.assertEquals(actual_title,"PatraCloud");
    }


}
