package Steps;

import Base.BaseUtil;

import Pages.PaymentPortalPage;
import Pages.QBISPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;


public class InsuredEmailValidation extends BaseUtil {
    QBISPage qbisPage;
    public InsuredEmailValidation() {
        qbisPage = new QBISPage(driver, js);
    }



    @Then("Open the application by  hamburger menu")
    public void open_the_application_by_hamburger_menu() throws InterruptedException {
        driver.findElement(By.cssSelector(".touchRipple path")).click();
       WebElement element1= driver.findElement(By.xpath("//div[contains(text(),'Applications')]"));
        js.executeScript("arguments[0].click()", element1);
        new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/div[5]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[2]/table[1]/tbody[1]/tr[1]/td[1]"))).click();

       // driver.findElement(By.xpath("//tbody/tr[1]/td[2]/span[1]")).click();

        WebDriverWait wait = new WebDriverWait(driver, 60);
       WebElement element= wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(),'RESUME APPLICATION')]")));
        ((JavascriptExecutor)driver).executeScript("arguments[0].click();", element);

        //new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(),'RESUME APPLICATION')]"))).click();


     //   driver.findElement(By.xpath("//span[contains(text(),'RESUME APPLICATION')]")).click();
        Thread.sleep(6000);
    }

    @Then("Go to send payment link pop-up")
    public void go_to_send_payment_link_pop_up() throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,10000)");
        driver.findElement(By.xpath("//body/div[@id='root']/div[1]/div[1]/div[6]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[3]/div[1]/div[1]/div[1]/div[1]/div[1]/div[3]/div[1]/div[3]/div[2]/div[1]/input[1]")).click();
driver.findElement(By.xpath("//span[contains(text(),'Send Link')]")).click();

     //qbisPage.clickOnSendLink();
     //Thread.sleep(5000);
    }

    @When("Removing insured email id from the email box")
    public void removing_insured_email_id_from_the_email_box() throws InterruptedException {
driver.findElement(By.xpath("//div[2]/div/div/input")).sendKeys(Keys.CONTROL, Keys.chord("a",Keys.BACK_SPACE));
//qbisPage.switchTOSendPaymentLinkPopUp();
        driver.findElement(By.xpath("//span[contains(text(),'Send Payment Link')]")).click();
    }

    @Then("A proper validation error message should be displayed")
    public void a_proper_validation_error_message_should_be_displayed() {
        String validation = driver.findElement(By.xpath("//div[contains(text(),'Email is invalid')]")).getText();
        System.out.println(validation);
        Assert.assertEquals(validation, "Email is invalid", "Incorrect validation message displayed!");
        System.out.println("Insured Email field validation error message when remove the email: \n"+validation);
    }

    @Then("Enter a invalid email id")
    public void enter_a_invalid_email_id() throws InterruptedException {
        driver.findElement(By.xpath("//div[2]/div/div/input")).sendKeys("ywdwyhh");
       // qbisPage.clickOnSendLink();
       // WebElement element=     driver.findElement(By.xpath("//span[contains(text(),'Send Link')]"));
      //  js.executeScript("arguments[0].click()", element);

    }

    @Then("A error message should be displayed")
    public void a_error_message_should_be_displayed() {
        String validation = driver.findElement(By.xpath("//div[contains(text(),'Email is invalid')]")).getText();
        System.out.println(validation);
        Assert.assertEquals(validation, "Email is invalid", "Incorrect validation message displayed!");
        System.out.println("Insured Email field validation error message when email is not in the proper format: \n"+validation);
    }

    @When("click on cancel button")
    public void click_on_cancel_button() {
        driver.findElement(By.xpath("//span[contains(text(),'Cancel')]")).click();

    }


    @Then("Verified that the application has been reassigned successfully")
    public void verifiedThatTheApplicationHasBeenReassignedSuccessfully() {
        qbisPage.reassign_button_validation();

    }
}
