package Pages;


import io.cucumber.datatable.DataTable;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class PaymentPortalPage {

    private final WebDriver driver;
    private final JavascriptExecutor js;
    private final WebDriverWait wait;

    public PaymentPortalPage(WebDriver driver, JavascriptExecutor js) {
        this.driver = driver;
        this.js = js;
        this.wait = new WebDriverWait(this.driver, 10);
        PageFactory.initElements(driver, this);
    }

    public @FindBy(id = "identifierId")
    WebElement Gmail_id;
    public @FindBy(xpath = "//div[@class='VfPpkd-RLmnJb']")
    WebElement Button_Next;
    public @FindBy(name = "password")
    WebElement Gmail_Password;
    public @FindBy(xpath = "/html[1]/body[1]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/button[1]/div[2]")
    WebElement Gmail_Sign_UP;
    public @FindBy(xpath = "//*[@class='yW']/span")
    List<WebElement> Inbox_List;
    public @FindBy(xpath = "//a[contains(text(),'CLICK HERE TO PROCEED')]")
    WebElement Click_Here_To_Proceed_Button;
    public @FindBy(xpath = "/html[1]/body[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[2]/div[1]/label[1]/span[1]/input[1]")
    WebElement Select_CheckBox;
    public @FindBy(xpath = "//button[@class='ant-btn style_policiesListViewButton__FhFru ant-btn-link ant-btn-background-ghost']")
    WebElement View_Button;
    public @FindBy(xpath = "//body/div/div[@class='ant-modal-root']/div[@class='ant-modal-wrap ant-modal-centered']/div[@class='ant-modal']/div[@class='ant-modal-content']/div[2]")
    WebElement Fetching_Policy_Details;
    public @FindBy(xpath = "//span[@class='ant-modal-close-x']")
    WebElement modal_close;
    public @FindBy(xpath = "//button[@class='ant-btn style_continueButton__38Vro ant-btn-primary']")
    WebElement continue_button;
    public @FindBy(css = ".ant-col:nth-child(2) > div > .style_total__BCIwz")
    WebElement Payment_Option;
    public @FindBy(xpath = "//div[@class='style_quoteDetail__urcRS']")
    WebElement Payment_Details;
    public @FindBy(xpath = "//button[@class='ant-btn ant-btn-primary']")
    WebElement button_continue;
    public @FindBy(xpath = "/html[1]/body[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[2]/div[1]/button[1]")
    WebElement Pfa_Link;
    public @FindBy(xpath = "//span[contains(.,'OK')]")
    WebElement OK_Button;
    //public @FindBy(xpath = "//span[@class='m-sign-modal--menu--item--label']")
    public @FindBy(xpath = "//li[@class='signature-modal-tab-bar__list-item']")
    List<WebElement> Draw_Name;
    // public @FindBy(xpath = "//button[@class='src-hellospa-foundation-control-button-button__button--3xui_ src-hellospa-foundation-control-button-button__button--3xui_ whitelabel-primary-button src-hellospa-foundation-control-button-button__primary--3Guxx src-hellospa-foundation-control-button-button__disabled--d7Qcw']")
    public @FindBy(xpath = "//button[@data-btn='foundation']")
    List<WebElement> InsertEverywhere_Button;
    public @FindBy(xpath = "//span[@class='l-nowrap']")
    WebElement Next;
    public @FindBy(xpath = "//span[@class='l-nowrap']")
    WebElement I_Agree;
    public @FindBy(xpath = "//button[@class='ant-btn']")
    WebElement PDF_Download;
    public @FindBy(xpath = "//button[@class='ant-btn ant-btn-primary']")
    WebElement Continue;
    public @FindBy(xpath = "/html[1]/body[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[2]")
    WebElement ACH_Text;
    public @FindBy(xpath = "//input[@placeholder='Account Number']")
    WebElement Account_Number;
    public @FindBy(xpath = "//input[@placeholder='Routing Number']")
    WebElement Routing_Number;
    public @FindBy(xpath = "//div[@class='ant-select-selection__placeholder']")
    WebElement Drop_Down_Click;
    public @FindBy(xpath = "//li[contains(text(),'Personal Savings')]")
    WebElement Drop_Down_Select;
    public @FindBy(xpath = "/html[1]/body[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/form[1]/div[4]/button[1]")
    WebElement Pay_And_Continue_Button;
    public @FindBy(xpath = "/html[1]/body[1]/div[7]/div[3]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]")
    WebElement Back_To_Inbox;
    public @FindBy(xpath = "//*[@class='yW']/span")
    List<WebElement> inboxlist;
    public @FindBy(xpath = "//div[@id=':3']")
    WebElement Payment_Receipt;
    public @FindBy(xpath = "/html[1]/body[1]/div[7]/div[3]/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[2]/div[1]/table[1]/tr[1]/td[1]/div[2]/div[2]/div[1]/div[3]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[3]/div[5]/div[4]/span[1]/div[1]/div[1]/div[1]")
    WebElement PFA_Download_In_Receipt;


    public void navigate_To_Gmail() {
        driver.get("https://mail.google.com/mail/");
        wait.until(ExpectedConditions.visibilityOf(Gmail_id));
    }

    public void enterMailId(Map<String, String> dataTable) {

        sendKeysToWebElement(Gmail_id, dataTable.get("Id"));

        //List<Map<String,String>> data=dataTable.asMaps(String.class,String.class);
        // List<List<String>> data = dataTable.raw();
        //  sendKeysToWebElement(Gmail_id, data.get(row).get(column));
        wait.until(ExpectedConditions.visibilityOf(Button_Next));
        waitAndClickElement(Button_Next);
    }

    public void enterPassword(Map<String, String> dataTable) {
        //  List<List<String>> data = dataTable.raw();
        sendKeysToWebElement(Gmail_Password, dataTable.get("Password"));

        //  List<Map<String,String>> data=dataTable.asMaps(String.class,String.class);
        //sendKeysToWebElement(Gmail_Password, data.get(row).get(column));

    }

    public void clickSignUPButton() {
        waitAndClickElement(Gmail_Sign_UP);
       // wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\":5l\"]/div/div")));
        driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
    }

    public void clickInsurancePaymentLink() {
        List<WebElement> a = Inbox_List;
        System.out.println(a.size());
        for (int i = 0; i < a.size(); i++) {
            System.out.println(a.get(i).getText());
            if (a.get(i).getText().equals("Test Finance Agent")) {  // if u want to click on the specific mail then here u can pass it
                a.get(i).click();
            }
        }

    }

    public void clickHereToProceed() {
        int tabs = driver.getWindowHandles().size();
        Actions action = new Actions(driver);
        action.contextClick(Click_Here_To_Proceed_Button).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).build().perform();
        wait.until(ExpectedConditions.numberOfWindowsToBe(tabs + 1));
        String Tab1 = driver.getWindowHandle();
        ArrayList<String> availableWindows = new ArrayList<String>(driver.getWindowHandles());
        if (!availableWindows.isEmpty()) {
            driver.switchTo().window(availableWindows.get(1));
        }
    }

    public void selectCheckBox() throws InterruptedException {
        js.executeScript("arguments[0].click()", Select_CheckBox);
        Thread.sleep(3000);
    }

    public void clickViewButton() throws InterruptedException {
        View_Button.click();
        String text = Fetching_Policy_Details.getText();
        System.out.println(text);
        Thread.sleep(3000);
        waitAndClickElement(modal_close);
        Thread.sleep(7000);
    }

    public void clickOnContinue() {
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        waitAndClickElement(continue_button);
    }

    public void verifyTheSelectPaymentPage() throws InterruptedException {
        String actual_title = driver.getTitle();
        System.out.println(actual_title);
        Assert.assertEquals(actual_title, "Patra Pay");
        Thread.sleep(3000);
    }

    public void selectPaymentOption() {
        js.executeScript("arguments[0].click()", Payment_Option);
        // Payment_Option.click();
        String text = Payment_Option.getText();
        System.out.println("Quarterly Payment Details:- " + "\n" + text);
    }

    public void clickContinue() throws InterruptedException {
        js.executeScript("arguments[0].click()", button_continue);
        Thread.sleep(3000);
    }

    public void clickPDFLink() throws InterruptedException {
        waitAndClickElement(Pfa_Link);
        Thread.sleep(20000);
        driver.switchTo().frame(0);
        OK_Button.click();
        driver.switchTo().defaultContent();
        Thread.sleep(3000);
    }

    public void signTheDocument() throws InterruptedException {
        Thread.sleep(3000);
        Actions act = new Actions(driver);
        act.sendKeys(Keys.TAB).build().perform();
        Thread.sleep(3000);
        act.sendKeys(Keys.ENTER).build().perform();
        int size = driver.findElements(By.tagName("iframe")).size();
        System.out.println("Total Frames --" + size);
        // prints the total number of frames
        driver.switchTo().frame(0); // Switching the Outer Frame
        Draw_Name.get(0).click();
        js.executeScript("arguments[0].click()", InsertEverywhere_Button.get(3));
        // InsertEverywhere_Button.click();
        Thread.sleep(3000);
        Next.click();
        Thread.sleep(3000);
        I_Agree.click();
        Thread.sleep(3000);
        driver.switchTo().defaultContent();
        Thread.sleep(3000);
    }

    public void downloadPDF() throws InterruptedException {
        PDF_Download.click();
        Thread.sleep(12000);
        PDF_Download.click();
        Thread.sleep(12000);
    }

    public void click_Continue() throws InterruptedException {
        Continue.click();
        Thread.sleep(3000);
    }

    public void transactionDetails() {
        String ACH = ACH_Text.getText();
        System.out.println(ACH);
    }

    public void transactionACH(String Acc, String Route) throws Exception {
        sendKeysToWebElement(Account_Number, Acc);
        sendKeysToWebElement(Routing_Number, Route);
        waitAndClickElement(Drop_Down_Click);
        waitAndClickElement(Drop_Down_Select);
    }

    public void clickOnPayAndContinue() throws InterruptedException {
        Pay_And_Continue_Button.click();

        String validation = new WebDriverWait(driver, 20).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Invalid routing number: 9')]"))).getText();
        System.out.println(validation);
        org.testng.Assert.assertEquals(validation, "Invalid routing number: 9", "Incorrect validation message displayed!");
        System.out.println("Routing number validation message: \n"+validation);
        Thread.sleep(10000);

        Routing_Number.sendKeys(Keys.chord(Keys.CONTROL, "a"),"091000019");

        Pay_And_Continue_Button.click();
        Thread.sleep(10000);
    }

    public void switchBackToMailInbox() throws InterruptedException {
        String currentTab = driver.getWindowHandle();

        for (String tab : driver.getWindowHandles()) {

            if (!tab.equals(currentTab)) {

                driver.switchTo().window(tab);

            }
        }
        Thread.sleep(30000);
        Back_To_Inbox.click();
        Thread.sleep(30000);

    }

    public void openThePaymentReceipt() throws InterruptedException {
        List<WebElement> a = inboxlist;
        System.out.println(a.size());
        for (int i = 0; i < a.size(); i++) {
            System.out.println(a.get(i).getText());
            if (a.get(i).getText().equals("notifications+TestF.")) {  // if u want to click on the specific mail then here u can pass it
                a.get(i).click();
                String receipt = Payment_Receipt.getText();
                System.out.println(receipt);
                js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
                Thread.sleep(3000);
                PFA_Download_In_Receipt.click();
            }
        }
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