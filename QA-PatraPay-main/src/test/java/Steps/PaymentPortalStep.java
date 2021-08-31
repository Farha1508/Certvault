package Steps;

//import com.cucumber.listener.Reporter;

import Base.BaseUtil;
import Pages.PaymentPortalPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import java.util.List;
import java.util.Map;

public class PaymentPortalStep extends BaseUtil {


    public PaymentPortalStep() {
        paymentPortal_Page = new PaymentPortalPage(driver, js);
    }

    @Given("^I am in the  inbox of respected mail id$")
    public void iAmInTheInboxOfRespectedMailId(List<Map<String, String>> entry) throws Exception {
        //   Reporter.addStepLog("Accessing PatraPay-Insurance Portal");
        Map dataTable = entry.get(0);
        paymentPortal_Page.navigate_To_Gmail();
        paymentPortal_Page.enterMailId(dataTable);
        paymentPortal_Page.enterPassword(dataTable);
        paymentPortal_Page.clickSignUPButton();
    }

    @Then("^Click on the insurance_payment_Link$")
    public void click_on_the_insurance_payment_Link() throws Throwable {
        paymentPortal_Page.clickInsurancePaymentLink();
    }

    @Then("^Click on the \"([^\"]*)\"$")
    public void click_on_the(String arg1) throws Throwable {
        paymentPortal_Page.clickHereToProceed();
    }

    @Then("^Select the checkbox on the Application_Review button$")
    public void select_the_checkbox_on_the_Application_Review_button() throws Throwable {
        paymentPortal_Page.selectCheckBox();
    }

    @Then("^Click on the view button to view the policy details$")
    public void click_on_the_view_button_to_view_the_policy_details() throws Throwable {
        paymentPortal_Page.clickViewButton();
    }

    @Then("^Click on the continue button$")
    public void click_on_the_continue_button() throws Throwable {
        paymentPortal_Page.clickOnContinue();
    }

    @Given("^I am on the payment option page$")
    public void i_am_on_the_payment_option_page() throws Throwable {
        paymentPortal_Page.verifyTheSelectPaymentPage();
    }

    @Then("^select any payment option$")
    public void select_any_payment_option() throws Throwable {
        paymentPortal_Page.selectPaymentOption();
    }

    @Then("^Click on the Continue button to reach the next step$")
    public void click_on_the_Continue_button_to_reach_the_next_step() throws Throwable {
        paymentPortal_Page.clickContinue();
    }

    @Then("^click on the view_and_sign_the_document link$")
    public void click_on_the_view_and_sign_the_document_link() throws Throwable {
        paymentPortal_Page.clickPDFLink();
    }

    @Then("^sign the document and click on the continue button$")
    public void sign_the_document_and_click_on_the_continue_button() throws Throwable {
        paymentPortal_Page.signTheDocument();
    }

    @Then("^downloaded the PDF$")
    public void downloaded_the_PDF() throws Throwable {
        paymentPortal_Page.downloadPDF();
    }

    @Then("^Click on the continue button to reach the (\\d+)th-step$")
    public void click_on_the_continue_button_to_reach_the_th_step(int arg1) throws Throwable {
        paymentPortal_Page.click_Continue();
    }

    @Then("^Select either ACH or credit card transaction option$")
    public void select_either_ACH_or_credit_card_transaction_option() throws Throwable {
        paymentPortal_Page.transactionDetails();
    }

    @Then("^Enter all the valid information$")
    public void enter_all_the_valid_information() throws Throwable {
        paymentPortal_Page.transactionACH("1234", "9");
    }

    @Then("^click on the pay and continue button$")
    public void click_on_the_pay_and_continue_button() throws Throwable {
        paymentPortal_Page.clickOnPayAndContinue();
    }

    @Then("^Go back to the mail inbox to check the payment receipt$")
    public void go_back_to_the_mail_inbox_to_check_the_payment_receipt() throws Throwable {
        paymentPortal_Page.switchBackToMailInbox();
    }

    @Then("^Now click on the payment receipt to view it$")
    public void now_click_on_the_payment_receipt_to_view_it() throws Throwable {
        paymentPortal_Page.openThePaymentReceipt();
    }


}
