package Steps;

import Base.BaseUtil;
import Pages.CommonForm;
import Pages.TimerModal;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.testng.Assert;

public class TimerSteps extends BaseUtil {

    public static TimerModal timerModal;

    public TimerSteps() {
        commonForm = new CommonForm(driver, js);
        timerModal = new TimerModal(driver, js);
    }

    @When("I click the {string} button in the time tracking modal")
    public void iClickTheButtonInTheTimeTrackingModal(String button) {
        // This method exists because searching for the "Start" button (CommonForm) will not find this button.
        timerModal.clickTimeTrackingButton(button);
    }

    @And("I enter the work order into the work order field")
    public void iEnterTheWorkOrderIntoTheWorkOrderField() {
        timerModal.setTimerWO(valueStore.get("workOrder"));
    }

    @Then("The stop button {string} displayed")
    public void theStopButtonDisplayed(String displayExpectation) {
        pageLoaded();
        boolean displayStatus = true;
        if(displayExpectation.equalsIgnoreCase("is not")){
            displayStatus = false;
        }

        if(commonForm.commonButtonGet("Stop") != null){
            Assert.assertEquals(displayStatus, commonForm.commonButtonGet("Stop").isDisplayed());
        }
        else{
            // If the button cannot be found, and that's expected, then this test case will pass.
            Assert.assertFalse(displayStatus);
        }
    }

    @Then("the timer is for {string}")
    public void theTimerIsFor(String company) {
        Assert.assertTrue(timerModal.getTimerCompany().contains(company));
    }

    // Is there a way to combine this function with the previous one?
    @Then("the timer is for {string} or {string}")
    public void theTimerIsFor(String company, String otherPossibleCompany) {
        Assert.assertTrue(timerModal.getTimerCompany().contains(company) || timerModal.getTimerCompany().contains(otherPossibleCompany));
    }

    @And("If the {string} modal is displayed, dismiss it")
    public void ifTheModalIsDisplayedDismissIt(String modalTitle) {
        System.out.println("Checking if the " + modalTitle + " modal is displayed.");
        try {
            driver.findElement(By.xpath("//span[@class=\"jconfirm-title\" and contains(text(), \"" + modalTitle + "\")]"));
            System.out.println("Dismissing the " + modalTitle + " modal.");
            driver.findElement(By.xpath("//button[contains(text(), \"NO\")]")).click();
            // Todo: verify modal dismissed
        } catch (NoSuchElementException e) {
            System.out.println("No " + modalTitle + " alert was displayed.");
        }

    }
}