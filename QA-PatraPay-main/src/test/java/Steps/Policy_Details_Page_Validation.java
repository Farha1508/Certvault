package Steps;

import Base.BaseUtil;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import java.util.List;

public class Policy_Details_Page_Validation extends BaseUtil {

    @Then("A proper validation error message should be displayed for the blank field")
    public void a_proper_validation_error_message_should_be_displayed_for_the_blank_field() {
       driver.findElement(By.xpath("//span[contains(text(),'Add')]")).click();

        boolean result = false;
        String validation = "Please fill all fields with valid value and try again";
        try {
            result = new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text() = 'Please fill all fields with valid value and try again']"))).isDisplayed();
        } catch (
                TimeoutException ignored) {
        }
        Assert.assertTrue(result, "Expected validation message \"" + validation + "\" did not display!");
        System.out.println("Policy Details page field validation error message on the top ,when keep blank: \n" + validation);

        String validation1 = "Field is required";
        try {
            result = driver.findElement(By.xpath("//div[text()='Field is required']")).isDisplayed();
        } catch (
                TimeoutException ignored) {
        }
        Assert.assertTrue(result, "Expected validation message \"" + validation1 + "\" did not display!");
        System.out.println("Policy Details page field validation error message on the field when keep blank: \n" + validation1);

    }

    @Then("A proper validation error message should be display")
    public void a_proper_validation_error_message_should_be_display() {
     boolean result = false;
        String validation = "Invalid answer";
        try {
            result = driver.findElement(By.xpath("//div[text()='Invalid answer']")).isDisplayed();
        } catch (
                TimeoutException ignored) {
        }
        Assert.assertTrue(result, "Expected validation message \"" + validation + "\" did not display!");
        System.out.println("Policy Details Page field validation error message for invalid data: \n" + validation);


    }

    @Then("Company name in this city should be displayed")
    public void companyNameInThisCityShouldBeDisplayed() {


        List<WebElement> allOptions = driver.findElements(By.xpath("//div[@role='menu']"));
        for(WebElement list:allOptions)
        {
            System.out.println("Insurance Carrier Company Name started by the name of city:-\n"+ list.getText());
        }
        
    }

    @Then("It should give the insurance company name in the drop down which is started by the entered alphabet.")
    public void itShouldGiveTheInsuranceCompanyNameInTheDropDownWhichIsStartedByTheEnteredAlphabet() {
        List<WebElement> allOptions = driver.findElements(By.xpath("//div[@role='menu']"));
        for(WebElement list:allOptions)
        {
        System.out.println("Insurance Carrier Company Name started by the entered alphabet:-\n"+ list.getText());
    }}
}
