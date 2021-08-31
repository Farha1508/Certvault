package Steps;


import Base.BaseUtil;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;


public class Basic_Information_Page_Validation extends BaseUtil {


    public Basic_Information_Page_Validation() {

    }
    @When("I enter multiple entries on basic information page to validate t field")
    public void i_enter_multiple_entries_on_basic_information_page_to_validate_t_field() {

driver.findElement(By.xpath("//input[@id='name']")).sendKeys("t");
        String validation = driver.findElement(By.xpath("//div[contains(text(),'Business name is required and must be between 3 an')]")).getText();
        Assert.assertEquals(validation, "Business name is required and must be between 3 and 120 characters", "Incorrect validation message displayed!");
        System.out.println("Business Name field having less than 3 characters validation message: \n"+validation);
    }




    @When("I enter multiple entries on basic information page to validate The quick, brown fox jumps over a lazy dog. DJs flock by when MTV ax quiz prog. Junk MTV quiz graced by fox whelps. Bawds field")
    public void i_enter_multiple_entries_on_basic_information_page_to_validate_The_quick_brown_fox_jumps_over_a_lazy_dog_DJs_flock_by_when_MTV_ax_quiz_prog_Junk_MTV_quiz_graced_by_fox_whelps_Bawds_field() {
        driver.findElement(By.xpath("//input[@id='name']")).sendKeys("The quick, brown fox jumps over a lazy dog. DJs flock by when MTV ax quiz prog.  Junk MTV quiz graced by fox whelps. Bawds");
        String validation = driver.findElement(By.xpath("//div[contains(text(),'Business name is required and must be between 3 an')]")).getText();
        Assert.assertEquals(validation, "Business name is required and must be between 3 and 120 characters", "Incorrect validation message displayed!");
        System.out.println("Business Name field having more than 120 characters validation message: \n"+validation);
    }

    @When("I enter multiple entries on basic information page to validate %,^,,\\,\\{} field")
    public void i_enter_multiple_entries_on_basic_information_page_to_validate_field() throws InterruptedException {
        driver.findElement(By.xpath("//input[@id='name']")).sendKeys("%,^,,\\,{}");

        //String validation = driver.findElement(By.xpath("//span[contains(text(),'Failed to search insured')]")).getText();
        //Assert.assertEquals(validation, "Failed to search insured", "Incorrect validation message displayed!");
        //System.out.println("Business Name field having some special characters validation message: \n"+validation);

        String validation = new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(),'Failed to search insured')]"))).getText();
        System.out.println(validation);
        Assert.assertEquals(validation, "Failed to search insured", "Incorrect validation message displayed!");
        System.out.println("Business Name field having some special characters validation message: \n"+validation);
        Thread.sleep(20000);

    }
    @When("I enter invalid data into ey&&{int} field")
    public void i_enter_invalid_data_into_ey_field(Integer int1) {
        driver.findElement(By.id("zipCode")).sendKeys("47&&yu");
    }

    @Then("A validation error message displays on this field")
    public void a_validation_error_message_displays_on_this_field() throws InterruptedException {
        String validation = driver.findElement(By.xpath("//div[contains(text(),'Zip code is invalid')]")).getText();
        Assert.assertEquals(validation, "Zip code is invalid", "Incorrect validation message displayed!");
        System.out.println("Zip code field validation message when enter invalid data: \n"+validation);

    }

    @When("I enter invalid data into  field")
    public void i_enter_invalid_data_into_field() throws InterruptedException {
        driver.findElement(By.id("zipCode")).sendKeys(" ");

       /*String validation = driver.findElement(By.xpath("//div[contains(text(),'Zip code is required')]")).getText();
       System.out.println(validation);
     Assert.assertEquals(validation, "Zip code is required", "Incorrect validation message displayed!");
        System.out.println("Zip code field validation message when whitespace is entered: \n"+validation);*/
    }

    @When("I enter more than {int} characters into The quick, brown fox jumps over a lazy dog. DJs flock by when MTV ax quiz prog. Junk MTV quiz graced by fox whelps. Bawds field")
    public void i_enter_more_than_characters_into_The_quick_brown_fox_jumps_over_a_lazy_dog_DJs_flock_by_when_MTV_ax_quiz_prog_Junk_MTV_quiz_graced_by_fox_whelps_Bawds_field(Integer int1) {

        driver.findElement(By.id("firstName")).sendKeys("The quick, brown fox jumps over a lazy dog. DJs flock by when MTV ax quiz prog.  Junk MTV quiz graced by fox whelps. Bawds");
    }

    @Then("A validation message appears on this field")
    public void a_validation_message_appears_on_this_field() {

        String validation = driver.findElement(By.xpath("//div[contains(text(),'First name is required and can not exceed 100 characters')]")).getText();
        System.out.println(validation);
        Assert.assertEquals(validation, "First name is required and can not exceed 100 characters", "Incorrect validation message displayed!");
        System.out.println("First Name field validation message when enter more than 100 characters: \n"+validation);
    }

    @When("I enter more than {int} characters in the The quick, brown fox jumps over a lazy dog. DJs flock by when MTV ax quiz prog. Junk MTV quiz graced by fox whelps. Bawds field")
    public void i_enter_more_than_characters_in_the_The_quick_brown_fox_jumps_over_a_lazy_dog_DJs_flock_by_when_MTV_ax_quiz_prog_Junk_MTV_quiz_graced_by_fox_whelps_Bawds_field(Integer int1) {
        driver.findElement(By.id("lastName")).sendKeys("The quick, brown fox jumps over a lazy dog. DJs flock by when MTV ax quiz prog.  Junk MTV quiz graced by fox whelps. Bawds");
    }

    @Then("A validation message appeared on this field")
    public void a_validation_message_appeared_on_this_field() {
        String validation = driver.findElement(By.xpath("//div[contains(text(),'Last name is required and can not exceed 100 characters')]")).getText();
        System.out.println(validation);
        Assert.assertEquals(validation, "Last name is required and can not exceed 100 characters", "Incorrect validation message displayed!");
        System.out.println("Last Name field validation message when enter more than 100 characters: \n"+validation);
    }



    @When("i enter multiple invalid entried to validate error message on trt{int}@ field")
    public void i_enter_multiple_invalid_entried_to_validate_error_message_on_trt_field(Integer int1) {
        driver.findElement(By.id("email")).sendKeys("R77@");
        String validation = driver.findElement(By.xpath("//div[contains(text(),'Email is invalid')]")).getText();
        System.out.println(validation);
        Assert.assertEquals(validation, "Email is invalid", "Incorrect validation message displayed!");
        System.out.println("Email field validation error message: \n"+validation);
    }

    @When("i enter multiple invalid entried to validate error message on {int}yy&& field")
    public void i_enter_multiple_invalid_entried_to_validate_error_message_on_yy_field(Integer int1) {

        driver.findElement(By.id("mobilePhone")).sendKeys("767hhh##");
        String validation = driver.findElement(By.xpath("//div[contains(text(),'Phone number is invalid')]")).getText();
        System.out.println(validation);
        Assert.assertEquals(validation, "Phone number is invalid", "Incorrect validation message displayed!");
        System.out.println("Phone Number field validation error message: \n"+validation);

    }

}
