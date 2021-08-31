package Pages;

import io.cucumber.datatable.DataTable;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;
import static org.openqa.selenium.support.ui.ExpectedConditions.titleContains;

public class QBISPage {

    private final WebDriver driver;
    private final JavascriptExecutor js;
    private final WebDriverWait wait;

    public QBISPage(WebDriver driver, JavascriptExecutor js) {
        this.driver = driver;
        this.js = js;
        this.wait = new WebDriverWait(this.driver, 10);
        PageFactory.initElements(driver, this);
    }

    public @FindBy(id="signIn-Email") WebElement Email_id;
    public @FindBy(id="signIn-Password") WebElement Password;
    public @FindBy(xpath = "//span[contains(text(),'SIGN IN')]") WebElement Sign_UP;
    public @FindBy(css=".touchRipple path") WebElement menu;
    public @FindBy(css = "span:nth-child(2)") WebElement new_application;
    public @FindBy(xpath="//input[@id='name']") WebElement textfield_BusinessName;
    public @FindBy(xpath="//input[@id='zipCode']") WebElement ZipCode;
    public @FindBy(id="firstName") WebElement textfield_firstName;
    public @FindBy(id="lastName") WebElement textfield_lastName;
    public @FindBy(id="email") WebElement textfield_email;
    public @FindBy(id="mobilePhone") WebElement Phone_Number;
    public @FindBy(xpath = "//body/div[@id='root']/div[1]/div[1]/div[6]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[3]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/form[1]/div[2]/div[1]/button[1]") WebElement button_Continue;
    public @FindBy(xpath = "//div[contains(text(),'Add')]") WebElement button_Add;
    public @FindBy(id="policy_type_of_coverage0") WebElement Policy_Coverage;
    public @FindBy(id="date__effective_date_of_policy0__picker") WebElement Effective_Date;
    public @FindBy(css="div:nth-child(3) div:nth-child(1) div:nth-child(1) div:nth-child(3) > button:nth-child(3)") WebElement Select_Date;
    public @FindBy(id="policy_insurance_carrier0") WebElement Insurance_Carrier;
    public @FindBy(id="financing_general_agent0") WebElement General_Agent;
    public @FindBy(id="policy_pure_premium0") WebElement Total_Premium;
    public @FindBy(css="#flag__policy_fund_commission0 div:nth-child(2) > input") WebElement Commission_Field;
    public @FindBy(id="policy_minimum_earned_premium0") WebElement Minimum_Earned_Percentage;
    public @FindBy(xpath="//span[contains(text(),'Add')]") WebElement Button_AddPolicy;
    public @FindBy(xpath="//span[contains(text(),'Continue')]") WebElement Click_Continue;
    public @FindBy(id="company_street_address") WebElement Business_Street_Address;
    public @FindBy(xpath="//div[@class='qbisForm']//div[2]//input[1]") WebElement button_No;
    public @FindBy(id="mailing_street_address") WebElement Mailing_Street_Address ;
    public @FindBy(id="mailing_city") WebElement Mailing_City;
    public @FindBy(id="mailing_state") WebElement Mailing_State;
    public @FindBy(xpath="/html[1]/body[1]/div[15]/div[1]/div[1]/div[1]/div[4]/span[1]/div[1]/div[1]/div[1]") WebElement Select_State;
    public @FindBy(id="mailing_zip") WebElement Mailing_Zip;
    public @FindBy(xpath="//span[contains(text(),'Continue')]") WebElement button__Continue;
    public @FindBy(xpath="//b[contains(text(),'view the application details')]") WebElement view_the_application_details;
    public @FindBy(xpath="//span[contains(text(),'Close')]") WebElement button_close;
    public @FindBy(xpath="//body/div[@id='root']/div[1]/div[1]/div[6]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[3]/div[1]/div[1]/div[1]/div[1]/div[1]/div[3]/div[1]/div[3]/div[2]/div[1]/input[1]") WebElement select_checkbox;
    public @FindBy(xpath="//div[@class='btn']//button") WebElement button__Continue_to_proceed;
    public @FindBy(xpath="//span[contains(text(),'Send Link')]") WebElement button__send_link;
    public @FindBy(css= ".right:nth-child(1) .touchRipple > div > span") WebElement send_payment_link;
    public @FindBy(xpath = "//div[@id='root']/div/div/div[6]/div/div/div/div/div/div/div/div/div/div[3]") WebElement Step_1st;
    public @FindBy(xpath = "//div[@id='root']/div/div/div[6]/div/div/div/div/div/div/div/div/div/div[3]") WebElement Step_3rd;
    public @FindBy(xpath = "//div[@id='root']/div/div/div[6]/div/div/div/div/div/div/div/div/div/div[3]") WebElement Step_6th;
    public @FindBy(xpath = "/html[1]/body[1]/div[1]/div[1]/div[1]/div[6]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[3]") WebElement Step_7th;
    public @FindBy(xpath = "/html[1]/body[1]/div[1]/div[1]/div[1]/div[6]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[3]") WebElement Step_8th;
    public @FindBy(xpath= "//span[contains(text(),'Application has been sent')]") WebElement Reassign_validate;
    public @FindBy(xpath = "//body/div[@id='root']/div[1]/div[1]/div[6]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[3]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/form[1]/div[1]/div[1]/div[2]/div[1]/div[1]/button[1]/div[1]") WebElement Policy_Display_Name;


    public void clickHamburgerMenu(){

        wait.until(ExpectedConditions.visibilityOf(menu)).click();
        wait.until(ExpectedConditions.visibilityOf(new_application)).click();
        driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
        System.out.println("Reached Step-1st:-" +Step_1st.getText());

        wait.until(ExpectedConditions.visibilityOf(textfield_BusinessName));

    }


public void verifying_Step_3rd(){
    System.out.println("Reached Step-3rd:-" +Step_3rd.getText());
}
    public void selectPolicyCoverageType(DataTable dataTable)  {
        for(Map<Object, Object> data:dataTable.asMaps(String.class,String.class)){
            sendKeysToWebElement(Policy_Coverage, (String) data.get("Policy Type"));
            Policy_Coverage.sendKeys(Keys.ENTER);
            driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
        }

    }
    public void effectiveDate() {
        waitAndClickElement(Effective_Date);
        driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
        wait.until(elementToBeClickable(Select_Date)).click();
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
    }
    public void insuranceCarrier(DataTable dataTable)  {

        for(Map<Object, Object> data:dataTable.asMaps(String.class,String.class)){
            sendKeysToWebElement(Insurance_Carrier, (String) data.get("Insurance Carrier"));
            Insurance_Carrier.sendKeys(Keys.ENTER);
            driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
        }
    }

    public void generalAgent(DataTable dataTable)  {
        for(Map<Object, Object> data:dataTable.asMaps(String.class,String.class)){
            sendKeysToWebElement(General_Agent, (String) data.get("General Agent"));
            Insurance_Carrier.sendKeys(Keys.ENTER);
            driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
        }
    }
    public void purePremium(DataTable dataTable) {
        for(Map<Object, Object> data:dataTable.asMaps(String.class,String.class)){
            sendKeysToWebElement(Total_Premium, (String) data.get("Pure Premium"));
            driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
            js.executeScript("window.scrollBy(0,10000)");
            js.executeScript("arguments[0].click()", Commission_Field);
            driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
        }
    }

    public  void verifyingPolicyBlockDisplayNameAndValue(){
        boolean result = false;
        String PolicyBlockDisplayName = Policy_Display_Name.getText();
        try {
            result = new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOf(Policy_Display_Name)).isDisplayed();
        } catch (
                TimeoutException ignored) {
        }
        org.testng.Assert.assertTrue(result, "Expected Policy Block Display Key and Value \"" + PolicyBlockDisplayName + "\" did not display!");
        System.out.println("Added Policy Block Display Key and Value: \n" + PolicyBlockDisplayName);
    }

public void enterMinimumEarnerPercent(DataTable dataTable)  {
    for(Map<Object, Object> data:dataTable.asMaps(String.class,String.class)){
        sendKeysToWebElement(Minimum_Earned_Percentage, (String) data.get("Minimum Earned Percentage"));
        driver.manage().timeouts().implicitlyWait(50000,TimeUnit.SECONDS);
    }
}
    public void clickADDButtonToAddPolicy() {
        js.executeScript("arguments[0].click()", Button_AddPolicy);
        driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
        waitAndClickElement(Button_AddPolicy);
        driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
    }

    public void clickOnContinueButton() {
     waitAndClickElement(Click_Continue);
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
    }


    public void verifying_Step_6th(){
        System.out.println("Reached Step-6th:-" +Step_6th.getText());
    }

public void  select_Isthemailingaddressthesameasthebusinessaddress()  {
    js.executeScript("arguments[0].click()", button_No);
    driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
    js.executeScript("window.scrollBy(0,2000)");
}


public void selectMailingState()  {
    Mailing_State.click();
    System.out.println(Mailing_State.getText());
    Select_State.click();
    driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
}

    public  void verifying_Step_7th(){
        System.out.println("Reached Step-7th:-" +Step_7th.getText());
    }

    public void viewTheApplicationDetails() {
        js.executeScript("arguments[0].click()", view_the_application_details);
        Assert.assertTrue(titleContains("PatraCloud").apply(driver));
        System.out.println(view_the_application_details.getText());
        driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
        js.executeScript("window.scrollBy(0,10000)");
    }

    public void clickOnCloseButton () {
        waitAndClickElement(button_close);
    }




    public  void verifying_Step_8th(){
        System.out.println("Reached Step-8th:-" +Step_8th.getText());
    }

    public void selectTheCheckBox() {
        js.executeScript("window.scrollBy(0,10000)");
        js.executeScript("arguments[0].click()", select_checkbox);
        Assert.assertTrue(titleContains("PatraCloud").apply(driver));
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
    }

    public void clickOnSendLink(){
        String title=driver.getTitle();
        System.out.println(title);
       js.executeScript("window.scrollBy(0,10000)");
        js.executeScript("arguments[0].click()", button__send_link);
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
    }

    public void reassign_button_validation(){
String validation= Reassign_validate.getText();
        System.out.println(validation);
        org.testng.Assert.assertEquals(validation, "Application has been sent", "Incorrect validation message displayed!");
        System.out.println("Application has been reassigned successfully: \n"+validation);
    }


    public void sendKeysToWebElement(WebElement element, String textToSend) {
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
            element.clear();
            element.sendKeys(textToSend);
            System.out.println("Successfully Sent the following keys: '" + textToSend + "' to element: " + "<" + element.toString() + ">");
        } catch (Exception e) {
            System.out.println("Unable to locate WebElement: " + "<" + element.toString() + "> and send the following keys: " + textToSend);
            Assert.fail("Unable to send keys to WebElement, Exception: " + e.getMessage());
        }
    }

    public void waitAndClickElement(WebElement element) {
        boolean clicked = false;
        long startTime = System.currentTimeMillis();
        while (!clicked && (System.currentTimeMillis() - startTime) < 5000) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException ignored) {
            }
            try {
                wait.until(ExpectedConditions.elementToBeClickable(element)).click();
                System.out.println("Successfully clicked on the WebElement: " + "<" + element.toString() + ">");
                clicked = true;
            } catch (Exception e) {
                System.out.println("Unable to wait and click on WebElement, Exception: " + e.getMessage());
                Assert.fail("Unable to wait and click on the WebElement, using locator: " + "<" + element.toString() + ">");
            }
        }
    }

}
