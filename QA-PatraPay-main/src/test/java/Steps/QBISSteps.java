package Steps;

import Base.BaseUtil;
import Pages.QBISPage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class QBISSteps extends BaseUtil {

    QBISPage qbisPage;
    public QBISSteps() {
        qbisPage= new QBISPage(driver, js);
    }

    @Given("I am on the Basic Information page")
    public void iAmOnTheBasicInformationPage(){
        qbisPage.clickHamburgerMenu();
    }

    @Then("^Enter all the valid information to add the policy\\(skip optional fields\\)$")
    public void enter_all_the_valid_information_to_add_the_policy_skip_optional_fields(DataTable dataTable)  {
        qbisPage.selectPolicyCoverageType(dataTable);
        qbisPage.effectiveDate();
        qbisPage.insuranceCarrier(dataTable);
        qbisPage.generalAgent(dataTable);
        qbisPage.purePremium(dataTable);
        qbisPage.enterMinimumEarnerPercent(dataTable);
    }

    @Then("^Click on the ADD button$")
    public void click_on_the_ADD_button() {
        qbisPage.clickADDButtonToAddPolicy();
    }

    @Then("^Click on the Continue button to go to the step-(\\d+)th$")
    public void click_on_the_Continue_button_to_go_to_the_step_th(int arg1)  {
        qbisPage.clickOnContinueButton();
    }

    @Given("^On the \"([^\"]*)\" page click on the \"([^\"]*)\"$")
    public void on_the_page_click_on_the(String arg1, String arg2)  {
        qbisPage.viewTheApplicationDetails();
    }

    @Then("^Click on the Close button$")
    public void click_on_the_Close_button(){
        qbisPage.clickOnCloseButton();
    }

    @Then("^Select the check-box$")
    public void select_the_check_box() {

    }

    @Then("^At the payment page ,click on the send link$")
    public void at_the_payment_page_click_on_the_send_link()  {
        qbisPage.selectTheCheckBox();
        qbisPage.clickOnSendLink();
    }

    @Then("Verified that the final details page is displayed")
    public void verifiedThatTheFinalDetailsPageIsDisplayed() {
        qbisPage.verifying_Step_6th();
    }

    @And("Click on {string} button")
    public void clickOnButton(String arg0) {
        qbisPage.select_Isthemailingaddressthesameasthebusinessaddress();
    }

    @And("Select the Mailing state")
    public void selectTheMailingState() {
        qbisPage.selectMailingState();
    }

    @And("Verified that the Policy details page is displayed")
    public void verifiedThatThePolicyDetailsPageIsDisplayed() {
qbisPage.verifying_Step_3rd();

    }

    @Then("Verified that the Application Review page is displayed")
    public void verifiedThatTheApplicationReviewPageIsDisplayed() {
qbisPage.verifying_Step_7th();
    }

    @Then("Verified that the Accept and Send page is displayed")
    public void verifiedThatTheAcceptAndSendPageIsDisplayed() {
        qbisPage.verifying_Step_8th();
    }

    @And("Verify the policy block display name and value")
    public void verifyThePolicyBlockDisplayNameAndValue() {
        qbisPage.verifyingPolicyBlockDisplayNameAndValue();
    }
}
