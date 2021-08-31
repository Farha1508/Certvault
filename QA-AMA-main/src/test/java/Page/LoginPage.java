package Page;

import BaseUtil.BaseUtil;
import com.fasterxml.jackson.core.io.NumberInput;
import com.google.gson.annotations.Until;
import cucumber.api.java.eo.Se;
import cucumber.api.java.gl.E;
//import jdk.nashorn.internal.runtime.arrays.NumericElements;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.w3c.dom.Document;
//import sun.rmi.runtime.Log;

import javax.swing.*;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
//import java.util.concurrent.TimeUnit;

public class LoginPage {
    WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    //user signing into the AMA application
    @FindBy(how = How.ID, using = "email")
    public WebElement user_name;

    @FindBy(how = How.ID, using = "password")
    public WebElement pass_word;

    @FindBy(how = How.ID, using = "submit")
    public WebElement siginbutton;

    @FindBy(how = How.PARTIAL_LINK_TEXT, using = "https://nodedevpma.patracorp.net/accountmanagement")
    public WebElement clickingonama;

    public void loginintoama(String username, String password) {
        try {
            System.out.println("User entering credentials");
            user_name.sendKeys(username);
            Thread.sleep(2000);
            pass_word.sendKeys(password);
            Thread.sleep(2000);

            System.out.println("User clicking on sigin button");
            siginbutton.click();
            Thread.sleep(2000);

            clickingonama.click();
            Thread.sleep(10000);
        } catch (Exception e) {
        }
    }


    //User adding policy in AMA

    @FindBy(how = How.XPATH, using = "//*[@id=\"Policy-addPolicy\"]")
    public WebElement addpolicybutton;

    @FindBy(how = How.XPATH, using = "//*[@id=\"policy-acc-manager\"]")
    public WebElement accountmanagerdropdown;

    @FindBy(how = How.XPATH, using = "//*[@id=\"policy-company\"]")
    public WebElement companydropdown;

    @FindBy(how = How.XPATH, using = "//*[@id=\"policy-status\"]")
    public WebElement statusdropdown;

    @FindBy(how = How.XPATH, using = "//*[@id=\"business-type-form\"]")
    public WebElement businesstypedropdown;

    @FindBy(how = How.XPATH, using = "//*[@id=\"agency-mgmt-code\"]")
    public WebElement agency_managementcode;

    @FindBy(how = How.XPATH, using = "//*[@id=\"addPolicy\"]/div/div/div/div[2]/div/div[3]/div[3]/div[1]/div/span/span")
    public WebElement datepicker1;

    @FindBy(how = How.XPATH, using = "/html/body/div[4]/div[1]/table/tbody/tr[4]/td[4]")
    public WebElement selectingthedate;

    @FindBy(how = How.XPATH, using = "//*[@id=\"policy-insured\"]")
    public WebElement insured1;

    @FindBy(how = How.XPATH, using = "//*[@id=\"policy-business-class\"]")
    public WebElement businessclassdropdown;

    @FindBy(how = How.XPATH, using = "//*[@id=\"policy-coverage-type\"]")
    public WebElement coveragetypedropdown;

    @FindBy(how = How.XPATH, using = "//*[@id=\"policy-producer\"]")
    public WebElement producer_office;

    @FindBy(how = How.XPATH, using = "//*[@id=\"policy-prior-carrier\"]")
    public WebElement prior_carrier;

    @FindBy(how = How.XPATH, using = "//*[@id=\"policy-prior-carrier-type\"]")
    public WebElement priorcarriertype_dropdown;

    @FindBy(how = How.XPATH, using = "//*[@id=\"policy-expiring-premium\"]")
    public WebElement expiring_premium;

    @FindBy(how = How.XPATH, using = "//*[@id=\"policy-commission-perc\"]")
    public WebElement expiring_commissionage;

    @FindBy(how = How.XPATH, using = "//*[@id=\"policy-commission\"]")
    public WebElement expiring_commission;

    @FindBy(how = How.XPATH, using = "//*[@id=\"policy-renewal-carrier\"]")
    public WebElement renewalnewcarrier_dropdown;

    @FindBy(how = How.XPATH, using = "//*[@id=\"policy-renewal-carrier-type\"]")
    public WebElement renewalnewcarriertype_dropdown;

    @FindBy(how = How.XPATH, using = "//*[@id=\"policy-renewal-premium\"]")
    public WebElement renewalnew_premium;

    @FindBy(how = How.XPATH, using = "//*[@id=\"policy-renewal-comm-percent\"]")
    public WebElement renewalnew_commissionage;

    @FindBy(how = How.XPATH, using = "//*[@id=\"policy-renewal-commission\"]")
    public WebElement renewalnew_commission;

    @FindBy(how = How.XPATH, using = "//*[@id=\"isNew\"]")
    public WebElement newexisting_dropdown;

    @FindBy(how = How.XPATH, using = "//*[@id=\"brand-list-drop-down\"]")
    public WebElement brand_dropdown;

    @FindBy(how = How.XPATH, using = "//*[@id=\"Policy-Details-Submit\"]")
    public WebElement addpolicysubmitbtn;

    private static String newlycreated_amarecord = "";

    public void addingpolicy(String agencymanagementcode, String insured, String produceroffice, String expiringpremium, String expiringcommissionage, String expiringcommission, String renewalnewpremium, String renewalnewcommissionage, String renewalnewcommission) {
        try {
            System.out.println("User clicking on add policy button for adding policy");
            System.out.println("user entering details for adding policy in AMA");
            addpolicybutton.click();
            Thread.sleep(3000);

            System.out.println("user selecting account manager under from account manager drop down");
            accountmanagerdropdown.click();
            Thread.sleep(1000);
            Select account_managerdropdown = new Select(accountmanagerdropdown);
            account_managerdropdown.selectByVisibleText("Kiran Chakrapani");
            Thread.sleep(2000);

            System.out.println("user selecting company from company drop down");
            companydropdown.click();
            Thread.sleep(1000);
            Select company_dropdown = new Select(companydropdown);
            company_dropdown.selectByVisibleText("Atlas Insurance - SAMS");
            Thread.sleep(2000);

            System.out.println("User selecting status from status drop down");
            statusdropdown.click();
            Thread.sleep(1000);
            Select status_dropdown = new Select(statusdropdown);
            status_dropdown.selectByValue("1");
            Thread.sleep(2000);

            System.out.println("User selecting business type from business type drop down");
            businesstypedropdown.click();
            Thread.sleep(1000);
            Select businesstype = new Select(businesstypedropdown);
            businesstype.selectByValue("1");
            Thread.sleep(2000);

            System.out.println("User entering value into agency management code");
            agency_managementcode.sendKeys(agencymanagementcode);
            Thread.sleep(1000);

            System.out.println("Use selecting date from the calendar");
            datepicker1.click();
            Thread.sleep(1000);

            selectingthedate.click();
            Thread.sleep(1000);

            System.out.println("User entering insured name into insured field");
            insured1.sendKeys(insured);
            Thread.sleep(1000);

            System.out.println("User selecting business class from business class drop down");
            businessclassdropdown.click();
            Thread.sleep(1000);
            Select business_classdropdown = new Select(businessclassdropdown);
            business_classdropdown.selectByVisibleText("Auto Services");
            Thread.sleep(2000);

            System.out.println("User selecting coverage type from coverage type drop down");
            coveragetypedropdown.click();
            Thread.sleep(1000);
            Select coverage_typedropdown = new Select(coveragetypedropdown);
            coverage_typedropdown.selectByVisibleText("Bond");
            Thread.sleep(1000);

            System.out.println("User entering value into producer/office field");
            producer_office.sendKeys(produceroffice);
            Thread.sleep(1000);

            System.out.println("User selecting prior carrier from prior carrier drop down");
            prior_carrier.click();
            Thread.sleep(1000);
            Select priorcarrierdropdown = new Select(prior_carrier);
            priorcarrierdropdown.selectByVisibleText("ABA Insurance Service");
            Thread.sleep(1000);

            System.out.println("User selecting prior carrier type from prior carrier type drop down");
            priorcarriertype_dropdown.click();
            Thread.sleep(1000);
            Select priorcarriertypedropdown = new Select(priorcarriertype_dropdown);
            priorcarriertypedropdown.selectByVisibleText("Standard (S)");
            Thread.sleep(1000);

            System.out.println("User entering expiring premium");
            expiring_premium.sendKeys(expiringpremium);
            Thread.sleep(1000);

            System.out.println("User entering expiring commission%");
            expiring_commissionage.sendKeys(expiringcommissionage);
            Thread.sleep(1000);

            System.out.println("User entering expiring commission");
            expiring_commission.sendKeys(expiringcommission);
            Thread.sleep(1000);

            System.out.println("User selecting renewal new carrier");
            renewalnewcarrier_dropdown.click();
            Thread.sleep(1000);
            Select renewal_newcarrier = new Select(renewalnewcarrier_dropdown);
            renewal_newcarrier.selectByVisibleText("Acceptance Ins Co");
            Thread.sleep(1000);

            System.out.println("User selecting renewal new carrier type");
            renewalnewcarriertype_dropdown.click();
            Thread.sleep(1000);
            Select renewal_newcarriertype = new Select(renewalnewcarriertype_dropdown);
            renewal_newcarriertype.selectByVisibleText("Standard Consolidations");
            Thread.sleep(1000);

            System.out.println("User entering renewal/new premium");
            renewalnew_premium.sendKeys(renewalnewpremium);
            Thread.sleep(1000);

            System.out.println("User entering renewal/new commission%");
            renewalnew_commissionage.sendKeys(renewalnewcommissionage);
            Thread.sleep(1000);

            System.out.println("User entering renewal/new commission");
            renewalnew_commission.sendKeys(renewalnewcommission);
            Thread.sleep(1000);

            System.out.println("User selecting new/existing");
            newexisting_dropdown.click();
            Thread.sleep(1000);
            Select new_existingdropdown = new Select(newexisting_dropdown);
            new_existingdropdown.selectByVisibleText("Existing");
            Thread.sleep(1000);

            System.out.println("User selecting brand from brand drop down");
            brand_dropdown.click();
            Thread.sleep(1000);
            Select brand_drop_down = new Select(brand_dropdown);
            brand_drop_down.selectByVisibleText("Heff Direct");
            Thread.sleep(1000);

            System.out.println("User clicking on submit button");
            addpolicysubmitbtn.click();
            Thread.sleep(5000);

            column_ID_sorting.click();
            Thread.sleep(3000);
            column_ID_sorting.click();
            Thread.sleep(3000);

            String amapolicyrecordnumber = newlycreatedpolicynumber.getText();
            System.out.println("Newly added policy number:" + amapolicyrecordnumber);

            LoginPage.newlycreated_amarecord = amapolicyrecordnumber;

        } catch (Exception e) {
        }
    }


    @FindBy(how = How.XPATH, using = "//*[@id=\"dtPolicies\"]/tbody/tr[1]/td[1]/a")
    public WebElement newlycreatedpolicynumber;


    //Opening the created record and entering details in the questions tab

    @FindBy(how = How.XPATH, using = "//*[@id=\"kpi1\"]")
    public WebElement myexpirationkpi;

    @FindBy(how = How.XPATH, using = "//*[@id=\"policy_sects\"]")
    public WebElement clickingonpoliciestab;

    @FindBy(how = How.XPATH, using = "//*[@id=\"PreRenewalLetterAttached\"]")
    public WebElement prerenewalletterdropdown;

    @FindBy(how = How.XPATH, using = "//*[@id=\"AutomaticRenewal\"]")
    public WebElement automaticrenewaldropdown;

    @FindBy(how = How.XPATH, using = "//*[@id=\"Applications\"]")
    public WebElement applicationsdropdown;

    @FindBy(how = How.XPATH, using = "//*[@id=\"Quote\"]")
    public WebElement quotedropdown;

    @FindBy(how = How.XPATH, using = "//*[@id=\"Binder\"]")
    public WebElement binderdropdown;

    @FindBy(how = How.XPATH, using = "//*[@id=\"PolicyRenewed\"]")
    public WebElement policyrenewalinamsdropdown;

    @FindBy(how = How.XPATH, using = "//*[@id=\"BillType\"]")
    public WebElement billtypedropdown;

    @FindBy(how = How.XPATH, using = "//*[@id=\"PolicyBilled\"]")
    public WebElement policbeenbilleddropdown;

    @FindBy(how = How.XPATH, using = "//*[@id=\"Financed\"]")
    public WebElement financeddropdown;

    @FindBy(how = How.XPATH, using = "//*[@id=\"PolicyAttached\"]")
    public WebElement ispolicyattachedinamsdmsdropdown;

    @FindBy(how = How.XPATH, using = "//*[@id=\"PolicyChecked\"]")
    public WebElement policybeendeliveredchecked;

    @FindBy(how = How.XPATH, using = "//*[@id=\"PolicyChecked\"]")
    public WebElement deliverytoinsureddropdown;

    @FindBy(how = How.XPATH, using = "//*[@id=\"ChecklistAttached\"]")
    public WebElement checklistattachedamsdms;

    @FindBy(how = How.XPATH, using = "//*[@id=\"Certificates\"]")
    public WebElement certificatesdropdown;

    @FindBy(how = How.XPATH, using = "//*[@id=\"State\"]")
    public WebElement statedropdown;

    @FindBy(how = How.XPATH, using = "//*[@id=\"NAICSCode\"]")
    public WebElement na_ics;

    @FindBy(how = How.XPATH, using = "//*[@id=\"ZipCode\"]")
    public WebElement zip_codes;

    @FindBy(how = How.XPATH, using = "//*[@id=\"NumberOfEmployees\"]")
    public WebElement numberofemployees;

    @FindBy(how = How.XPATH, using = "//*[@id=\"AnnualGrossRevenue\"]")
    public WebElement annualgross_revenue;

    @FindBy(how = How.XPATH, using = "//*[@id=\"policy_entry_submit_close\"]")
    public WebElement policy_entry_submit_close;

    @FindBy(how = How.XPATH, using = "//*[@id=\"kpi2\"]")
    public WebElement My_Renewals_KPI;

    @FindBy(how = How.XPATH, using = "//*[@id=\"PolicyDelivered\"]")
    public WebElement Has_Policybeen_delivered_to_Insured;

    @FindBy(how = How.XPATH, using = "//*[@id=\"ChecklistAttached\"]")
    public WebElement IsDelivery_to_Insured_Attached_AMSDMS;

    @FindBy(how = How.XPATH, using = "/html/body/div[1]/div[1]/div/div[1]/div/ul/li[1]/button")
    public WebElement Add_Note_In_Record;

    @FindBy(how = How.XPATH, using = "//*[@id=\"note_title\"]")
    public WebElement note_title;

    @FindBy(how = How.XPATH, using = "//*[@id=\"note_desc\"]")
    public WebElement note_desc;

    @FindBy(how = How.XPATH, using = "//*[@id=\"notes_submit\"]")
    public WebElement Note_Submit_Btn;

    @FindBy(how = How.XPATH, using = "//*[@id=\"notes_sects\"]")
    public WebElement notes_sects_Tab;

    @FindBy(how = How.XPATH, using = "//*[@id=\"dtnotes_wrapper\"]/tbody/tr/td[5]/center/span")
    public WebElement Viewing_Note;

    @FindBy(how = How.XPATH, using = "//*[@id=\"myModal\"]/div/div/div[3]/button")
    public WebElement Viewing_Note_Closebtn_Click;

    @FindBy(how = How.XPATH, using = "//*[@id=\"history_sects\"]")
    public WebElement History_Tab;

    @FindBy(how = How.XPATH, using = "//*[@id=\"ID\"]")
    public WebElement column_id_search;

    /*This is selecting the record and entering the values in the record form and submitting the changed values*/

    public void checkingwithkpis(String naics, String zipcode, String noofemployees, String annualgrossrevenue) {
        try {
            String newlyaddedamarecordno = LoginPage.newlycreated_amarecord;

            System.out.println("User entering newly created record number into column search");
            column_id_search.sendKeys(newlyaddedamarecordno);
            Thread.sleep(1000);
            clickingonpoliciestab.click();
            Thread.sleep(4000);

            System.out.println("User opening the record");
            newlycreatedpolicynumber.click();
            Thread.sleep(4000);

            System.out.println("user entering detail in the questions tab and changing the KPI to My Renewals");
            System.out.println("user selecting Is Pre-Renewal letter attached in AMS/DMS?");
            Select Pre_Renewal = new Select(prerenewalletterdropdown);
            prerenewalletterdropdown.click();
            Thread.sleep(1000);
            Pre_Renewal.selectByVisibleText("Yes");
            Thread.sleep(1000);

            System.out.println("user selecting Automatic Renewal or Submission Required?");
            Select automatic_renewal = new Select(automaticrenewaldropdown);
            automaticrenewaldropdown.click();
            Thread.sleep(1000);
            automatic_renewal.selectByVisibleText("Submission Required");
            Thread.sleep(1000);

            System.out.println("user selecting Applications drop down");
            Select app_lications = new Select(applicationsdropdown);
            applicationsdropdown.click();
            Thread.sleep(1000);
            app_lications.selectByVisibleText("From Insured");
            Thread.sleep(1000);

            System.out.println("user selecting Quote drop down");
            Select quo_te = new Select(quotedropdown);
            quotedropdown.click();
            Thread.sleep(1000);
            quo_te.selectByVisibleText("N/A");
            Thread.sleep(1000);

            System.out.println("user selecting binder drop down");
            Select bi_nder = new Select(binderdropdown);
            binderdropdown.click();
            Thread.sleep(1000);
            bi_nder.selectByValue("17");
            Thread.sleep(1000);

            System.out.println("user selecting Is Policy Renewed in AMS? drop down");
            Select policy_renewal_AMS = new Select(policyrenewalinamsdropdown);
            policyrenewalinamsdropdown.click();
            Thread.sleep(1000);
            policy_renewal_AMS.selectByVisibleText("No");
            Thread.sleep(1000);

            System.out.println("user selecting billtype drop down");
            Select bill_type = new Select(billtypedropdown);
            billtypedropdown.click();
            Thread.sleep(1000);
            bill_type.selectByVisibleText("AB");
            Thread.sleep(1000);

            System.out.println("user selecting Has Policy been Billed drop down");
            Select policy_been_billed = new Select(policbeenbilleddropdown);
            policbeenbilleddropdown.click();
            Thread.sleep(1000);
            policy_been_billed.selectByVisibleText("Yes");
            Thread.sleep(1000);

            System.out.println("user selecting Financed drop down");
            Select fina_nced = new Select(financeddropdown);
            financeddropdown.click();
            Thread.sleep(1000);
            fina_nced.selectByVisibleText("No");
            Thread.sleep(1000);

            System.out.println("user selecting Is Policy attached in AMS/DMS drop down");
            Select policy_attached_AMS_DMS = new Select(ispolicyattachedinamsdmsdropdown);
            ispolicyattachedinamsdmsdropdown.click();
            Thread.sleep(1000);
            policy_attached_AMS_DMS.selectByVisibleText("Yes");
            Thread.sleep(1000);

            System.out.println("user selecting Has Policy been checked? drop down");
            Select policy_been_checked_delivered = new Select(policybeendeliveredchecked);
            policybeendeliveredchecked.click();
            Thread.sleep(1000);
            policy_been_checked_delivered.selectByVisibleText("N/A");
            Thread.sleep(1000);

            System.out.println("user selecting Is Delivery to insured attached in AMS/DMS? drop down");
            Select Is_Delivery_To_Insured_Attached_In_AMSDMS = new Select(IsDelivery_to_Insured_Attached_AMSDMS);
            IsDelivery_to_Insured_Attached_AMSDMS.click();
            Thread.sleep(1000);
            Is_Delivery_To_Insured_Attached_In_AMSDMS.selectByVisibleText("Yes");
            Thread.sleep(1000);

            System.out.println("user selecting Is checklist attached in AMS/DMS? drop down");
            Select delivery_to_insured_attachedin_AMS_DMS = new Select(deliverytoinsureddropdown);
            deliverytoinsureddropdown.click();
            Thread.sleep(1000);
            delivery_to_insured_attachedin_AMS_DMS.selectByVisibleText("No");

            System.out.println("user selecting Certificates drop down");
            Select checklist_attached_AMS_DMS = new Select(checklistattachedamsdms);
            checklistattachedamsdms.click();
            Thread.sleep(1000);
            checklist_attached_AMS_DMS.selectByVisibleText("Yes");
            Thread.sleep(1000);

            System.out.println("user selecting State drop down");
            Select cert_ficates = new Select(certificatesdropdown);
            certificatesdropdown.click();
            Thread.sleep(1000);
            cert_ficates.selectByVisibleText("Master Updated");
            Thread.sleep(1000);

            System.out.println("user selecting from state drop down");
            Select state_dropdown = new Select(statedropdown);
            statedropdown.click();
            Thread.sleep(1000);
            state_dropdown.selectByVisibleText("Indiana (IN)");
            Thread.sleep(1000);

            System.out.println("user selecting Has Policy been delivered to the insured? drop down");
            Select delivered_to_insured = new Select(Has_Policybeen_delivered_to_Insured);
            Has_Policybeen_delivered_to_Insured.click();
            Thread.sleep(1000);
            delivered_to_insured.selectByVisibleText("Yes");
            Thread.sleep(1000);

            System.out.println("user entering NAICS");
            na_ics.sendKeys(naics);
            Thread.sleep(1000);

            System.out.println("user entering Zip Code");
            zip_codes.sendKeys(zipcode);
            Thread.sleep(1000);

            System.out.println("user entering Number Of Employees");
            numberofemployees.sendKeys(noofemployees);
            Thread.sleep(1000);

            System.out.println("user entering Annual Gross Revenue");
            annualgross_revenue.sendKeys(annualgrossrevenue);
            Thread.sleep(1000);

            System.out.println("user clicking on submit button");
            policy_entry_submit.click();
            Thread.sleep(5000);

            System.out.println("user adding note into record detail page");
            System.out.println("user clicking on add note button");
            Add_Note_In_Record.click();
            Thread.sleep(2000);

            System.out.println("user entering title for new note");
            note_title.sendKeys("Standard Business Solutions");
            Thread.sleep(1000);

            System.out.println("user entering description for new note");
            note_desc.sendKeys("Branch of Kirans bsiness code2");
            Thread.sleep(1000);

            System.out.println("user clicking on submit button of add note pop up");
            Note_Submit_Btn.click();
            Thread.sleep(5000);

            System.out.println("user navigating to notes tab in the detail page");
            System.out.println("user clicking on note tab");
            notes_sects_Tab.click();
            Thread.sleep(2000);

            System.out.println("user clicking view note button");
            Viewing_Note.click();
            Thread.sleep(2000);

            System.out.println("user clicking on close button of note popup");
            Viewing_Note_Closebtn_Click.click();
            Thread.sleep(3000);

            System.out.println("user navigating to history tab");
            System.out.println("user clicking on history tab");
            History_Tab.click();
            Thread.sleep(3000);

            System.out.println("user clicking on record detail close button");
            policy_entry_close.click();
            Thread.sleep(6000);

        } catch (Exception e) {
        }
    }

    @FindBy(how = How.XPATH, using = "//*[@id=\"policy_entry_submit\"]")
    public WebElement policy_entry_submit;

    @FindBy(how = How.XPATH, using = "//*[@id=\"policy_entry_close\"]")
    public WebElement policy_entry_close;

    public void expirationtorenewal() {
        try {
            System.out.println("User moving the created new policy to different KPIs");
            System.out.println("User clicking on my expirations KPI");
            myexpirationkpi.click();
            Thread.sleep(6000);

            String newlyaddedamarecordno = LoginPage.newlycreated_amarecord;

            System.out.println("user searching for record in my expiration KPI");
            column_id_search.sendKeys(newlyaddedamarecordno);
            Thread.sleep(1000);
            clickingonpoliciestab.click();
            Thread.sleep(3000);

            System.out.println("user opening the record");
            newlycreatedpolicynumber.click();
            Thread.sleep(3000);

            System.out.println("user selecting the business type drop down as renewal");
            businesstypedropdown.click();
            Thread.sleep(1000);
            Select business_type = new Select(businesstypedropdown);
            business_type.selectByVisibleText("Renewal");
            Thread.sleep(1000);

            System.out.println("user clicking on submit  and close button");
            policy_entry_submit_close.click();
            Thread.sleep(5000);

        } catch (Exception e) {
        }
    }

    public void myrenewalkpitomyewbusinesskpi() {
        try {
            System.out.println("user moving the record from My Renewals KPI to My New Business KPI");
            System.out.println("user clicking on my renewal KPI");
            My_Renewals_KPI.click();
            Thread.sleep(6000);
            String newlyaddedamarecordno = LoginPage.newlycreated_amarecord;

            System.out.println("user searching for record in my renewal KPI");
            column_id_search.sendKeys(newlyaddedamarecordno);
            Thread.sleep(1000);
            clickingonpoliciestab.click();
            Thread.sleep(3000);

            System.out.println("user opening the record");
            newlycreatedpolicynumber.click();
            Thread.sleep(3000);

            System.out.println("user selecting the business type drop down as renewal");
            businesstypedropdown.click();
            Thread.sleep(1000);
            Select business_type = new Select(businesstypedropdown);
            business_type.selectByVisibleText("New New");
            Thread.sleep(4000);

            System.out.println("user clicking on submit and close button");
            policy_entry_submit_close.click();
            Thread.sleep(5000);

        } catch (Exception e) {
        }
    }

    @FindBy(how = How.XPATH, using = "//*[@id=\"kpi3\"]")
    public WebElement My_New_Business_KPI;

    @FindBy(how = How.XPATH, using = "//*[@id=\"policy_reason_lost\"]")
    public WebElement Reason_Lost;

    @FindBy(how = How.XPATH, using = "//*[@id=\"policy-add-date-lost\"]")
    public WebElement Date_Lost;

    @FindBy(how = How.XPATH, using = "/html/body/div[3]/div[1]/table/tbody/tr[2]/td[6]")
    public WebElement Selecting_Date_Lost;


    public void mynewvbusinesskpitomylostbusinesskpi() {
        try {
            System.out.println("User moving the record from My New Business KPI to My Lost Business KPI");
            My_New_Business_KPI.click();
            Thread.sleep(6000);

            String newlyaddedamarecordno = LoginPage.newlycreated_amarecord;

            System.out.println("user searching for record in my new business KPI");
            column_id_search.sendKeys(newlyaddedamarecordno);
            Thread.sleep(1000);
            clickingonpoliciestab.click();
            Thread.sleep(3000);

            System.out.println("user opening the record");
            newlycreatedpolicynumber.click();
            Thread.sleep(3000);

            System.out.println("user selecting business type as midterm lost");
            Select business_type = new Select(businesstypedropdown);
            businesstypedropdown.click();
            Thread.sleep(1000);
            business_type.selectByVisibleText("Midterm Lost");
            Thread.sleep(2000);

            System.out.println("user selecting reason lost");
            Select Reason_Lost_dropdown = new Select(Reason_Lost);
            Reason_Lost_dropdown.selectByVisibleText("No Response");
            Thread.sleep(2000);

            System.out.println("user selecting date lost");
            Date_Lost.click();
            Thread.sleep(1000);
            Selecting_Date_Lost.click();
            Thread.sleep(1000);

            System.out.println("user selecting effective date");
            MidtermLost_EffectiveDate.click();
            Thread.sleep(1000);
            MidtermLost_EffectiveDate_Selection.click();
            Thread.sleep(2000);

            System.out.println("user clicking on submit and close button");
            policy_entry_submit_close.click();
            Thread.sleep(5000);

        } catch (Exception e) {
        }
    }

    @FindBy(how = How.XPATH, using = "//*[@id=\"midtermlostdiv\"]/div[1]/div/span/span")
    public WebElement MidtermLost_EffectiveDate;

    @FindBy(how = How.XPATH, using = "/html/body/div[3]/div[1]/table/tbody/tr[2]/td[3]")
    public WebElement MidtermLost_EffectiveDate_Selection;

    @FindBy(how = How.XPATH, using = "//*[@id=\"kpi4\"]")
    public WebElement My_Lost_Business_KPI;

    public void mylostbusinesskpitomyworkinprogresskpi() {
        try {

            System.out.println("User moving the record from My Lost Business KPI to My Work In Progress KPI");
            My_Lost_Business_KPI.click();
            Thread.sleep(6000);

            String newlyaddedamarecordno = LoginPage.newlycreated_amarecord;

            System.out.println("user searching for record in my new lost business KPI");
            column_id_search.sendKeys(newlyaddedamarecordno);
            Thread.sleep(1000);
            clickingonpoliciestab.click();
            Thread.sleep(3000);

            System.out.println("user opening the record");
            newlycreatedpolicynumber.click();
            Thread.sleep(3000);

            System.out.println("user selecting business type as work in progress");
            Select business_type = new Select(businesstypedropdown);
            businesstypedropdown.click();
            Thread.sleep(1000);
            business_type.selectByVisibleText("Work In Progress");
            Thread.sleep(2000);

            System.out.println("user clicking on submit and close button");
            policy_entry_submit_close.click();
            Thread.sleep(5000);

        } catch (Exception e) {
        }
    }

    @FindBy(how = How.XPATH, using = "//*[@id=\"kpi6\"]")
    public WebElement My_YTD_Revenue;

    @FindBy(how = How.XPATH, using = "//*[@id=\"kpi5\"]")
    public WebElement My_Work_In_Progress;

    public void myworkinprogresskpitomyytdpolicycountkpi() {
        try {
            System.out.println("user moving the reccord from My Work in Progress KPI to My YTD Policy Count KPI");
            My_Work_In_Progress.click();
            Thread.sleep(6000);

            String newlyaddedamarecordno = LoginPage.newlycreated_amarecord;
            System.out.println("user searching for record in my work in progress KPI");
            column_id_search.sendKeys(newlyaddedamarecordno);
            Thread.sleep(1000);
            clickingonpoliciestab.click();
            Thread.sleep(3000);

            System.out.println("user opening the record");
            newlycreatedpolicynumber.click();
            Thread.sleep(3000);

            businesstypedropdown.click();
            Thread.sleep(1000);
            Select business_type = new Select(businesstypedropdown);
            business_type.selectByVisibleText("Expiration");
            Thread.sleep(4000);

            System.out.println("user clicking on submit and close button");
            policy_entry_submit_close.click();
            Thread.sleep(5000);

        } catch (Exception e) {
        }
    }

    public void myytdpolicycountkpi() {
        try {

            System.out.println("user checking for the record in My YTD Policy KPI which is moves from My Work in Progress KPI");
            My_YTD_Revenue.click();
            Thread.sleep(6000);

            String newlyaddedamarecordno = LoginPage.newlycreated_amarecord;
            System.out.println("user searching for record in My YTD Policy KPI");
            column_id_search.sendKeys(newlyaddedamarecordno);
            Thread.sleep(1000);
            clickingonpoliciestab.click();
            Thread.sleep(3000);

        } catch (Exception e) {
        }

    }

    @FindBy(how = How.XPATH, using = "//*[@id=\"typeaheadValue\"]")
    public WebElement Global_Searching_Record;

    @FindBy(how = How.XPATH, using = "//*[@id=\"the-basics-new\"]/span/div/div/p/a")
    public WebElement Global_Search_Selecting_Record;

    @FindBy(how = How.XPATH, using = "//*[@id=\"dtPolicies_wrapper\"]/div[1]/div[2]/div/div[3]")
    public WebElement Reset_btn;

    public void globalsearchs() {
        try {

            System.out.println("user checking for the new record with global search");
            String newlyaddedamarecordno = LoginPage.newlycreated_amarecord;
            Global_Searching_Record.sendKeys(newlyaddedamarecordno);
            Thread.sleep(4000);

            System.out.println("user selecting the record from global search drop down");
            Global_Search_Selecting_Record.click();
            Thread.sleep(4000);

            System.out.println("user closing the work order");
            policy_entry_close.click();
            Thread.sleep(4000);

        } catch (Exception e) {
        }
    }

    @FindBy(how = How.XPATH, using = "//*[@id=\"view-Yearly-Summary\"]")
    public WebElement View_Yearly_Summary;

    @FindBy(how = How.XPATH, using = "//*[@id=\"viewSummary\"]/div/div/div/div[1]/div/div[2]/button")
    public WebElement View_Yearly_Summary_Close_Btn;

    public void viewyearlysummaryimportpolicies() {
        try {
            System.out.println("User clicking on reset button");
            Reset_btn.click();
            Thread.sleep(4000);

            System.out.println("User clicking on View Yearly Summary button");
            View_Yearly_Summary.click();
            Thread.sleep(2000);
            View_Yearly_Summary_Close_Btn.click();
            Thread.sleep(2000);

        } catch (Exception e) {
        }
    }

    @FindBy(how = How.XPATH, using = "//*[@id=\"importing-Policies\"]")
    public WebElement Import_Policies;

    @FindBy(how = How.CSS, using = "#dtPolicies_info")
    public WebElement getting_the_count_beforeafter_import;

    public void importingpolicies() {
        try {
            System.out.println("User importing policies into AMA thru excel sheet");
            clickingonpoliciestab.click();
            Thread.sleep(5000);

            String No_Of_Records = getting_the_count_beforeafter_import.getText();
            System.out.println("No.of record after import" + No_Of_Records);

            System.out.println("user clicking on import policies button");
            Import_Policies.click();
            Thread.sleep(4000);

        } catch (Exception e) {
        }
    }

    @FindBy(how = How.XPATH, using = "//*[@id=\"th-ManagedPolicyIDAccounts\"]/div")
    public WebElement column_ID_sorting;

    @FindBy(how = How.XPATH, using = "//*[@id=\"th-SAMSAgencyAccounts\"]/div")
    public WebElement column_SAMS_Agency;

    public void policiestabgridfiltersandsorting() {
        try {
            System.out.println("user checking for sorting and grid filter option of polcies tab");
            System.out.println("user checking for sorting functinality for column - ID");
            column_ID_sorting.click();
            Thread.sleep(3000);
            column_ID_sorting.click();
            Thread.sleep(3000);

            System.out.println("user checking for grid search option for column - ID");
            String newlyaddedamarecordno = LoginPage.newlycreated_amarecord;
            column_id_search.sendKeys(newlyaddedamarecordno);
            Thread.sleep(1000);
            clickingonpoliciestab.click();
            Thread.sleep(4000);

            System.out.println("User clicking on reset button");
            Reset_btn.click();
            Thread.sleep(4000);

            System.out.println("user checking for sorting functinality for column - SAMS Agency");
            column_SAMS_Agency.click();
            Thread.sleep(3000);
            column_SAMS_Agency.click();
            Thread.sleep(3000);

            System.out.println("user checking grid filter for column - SAMS Agency");
            column_SAMS_Agency_filter.click();
            Thread.sleep(2000);
            column_SAMS_Agency_filter_internalsearch.sendKeys("Catto & Catto - SAMS");
            Thread.sleep(2000);
            selecting_company_inthefilter.click();
            Thread.sleep(2000);
            clickingonpoliciestab.click();
            Thread.sleep(4000);

            System.out.println("User clicking on reset button");
            Reset_btn.click();
            Thread.sleep(4000);

            System.out.println("user checking for sorting functionality for column - Business Type");
            column_BusinessType_sorting.click();
            Thread.sleep(3000);
            column_BusinessType_sorting.click();
            Thread.sleep(3000);

            System.out.println("user checking grid filter for column - Business Type");
            BusinessTypeColumn_GridFilter.click();
            Thread.sleep(2000);
            BusinessTypeColumn_GridFilter_InternalSearch.sendKeys("Renewal");
            Thread.sleep(2000);
            BusinessTypeColumn_GridFilter_InternalSearch_Selection.click();
            Thread.sleep(2000);
            clickingonpoliciestab.click();
            Thread.sleep(4000);

            System.out.println("User clicking on reset button");
            Reset_btn.click();
            Thread.sleep(4000);

            System.out.println("user checking for sorting functionality for column - Status");
            StatusColumn_SortingFunctionality.click();
            Thread.sleep(3000);
            StatusColumn_SortingFunctionality.click();
            Thread.sleep(3000);

            System.out.println("user checking for grid filter for column - Status");
            StatusColumn_GridFilter.click();
            Thread.sleep(3000);
            StatusColumn_GridFilter_InternalSearch.sendKeys("In Progress");
            Thread.sleep(3000);
            StatusColumn_GridFilter_InternalSearch_Selection.click();
            Thread.sleep(2000);
            clickingonpoliciestab.click();
            Thread.sleep(4000);

            System.out.println("User clicking on reset button");
            Reset_btn.click();
            Thread.sleep(4000);

            System.out.println("user checking for sorting functionality for column - Date");
            DateColumn_Sorting.click();
            Thread.sleep(3000);
            DateColumn_Sorting.click();
            Thread.sleep(3000);

            System.out.println("user checking for date filter for column - Date");
            System.out.println("user selecting from date");
            DateColumn_FromDateFilter_Selection.sendKeys("5/9/2019");
            Thread.sleep(3000);

            System.out.println("user selecting to date");
            DateColumn_ToDateFilter_Selection.sendKeys("5/22/2019");
            Thread.sleep(3000);
            clickingonpoliciestab.click();
            Thread.sleep(4000);

            System.out.println("User clicking on reset button");
            Reset_btn.click();
            Thread.sleep(4000);

            System.out.println("User checking for sorting functionality of Insured column");
            InsuredColumn_SortingFunctionality.click();
            Thread.sleep(3000);
            InsuredColumn_SortingFunctionality.click();
            Thread.sleep(3000);

            System.out.println("User checking grid search option for column - Insured");
            InsuredColumn_SearchOption.sendKeys("test");
            Thread.sleep(2000);
            clickingonpoliciestab.click();
            Thread.sleep(4000);

            System.out.println("User clicking on reset button");
            Reset_btn.click();
            Thread.sleep(4000);

            System.out.println("User checking for sorting functionality of Coverage Type column");
            CoverageTypeColumn_SortingFunctionality.click();
            Thread.sleep(3000);
            CoverageTypeColumn_SortingFunctionality.click();
            Thread.sleep(3000);

            System.out.println("User checking for grid filter functionality of column - Coverage Type");
            CoverageTypeColumn_GridFilter.click();
            Thread.sleep(2000);

            System.out.println("User entering value into internal search");
            CoverageTypeColumn_GridFilter_InternalSearch.sendKeys("bond");
            Thread.sleep(2000);
            CoverageTypeColumn_GridFilter_InternalSearch_Select.click();
            Thread.sleep(2000);
            clickingonpoliciestab.click();
            Thread.sleep(4000);

            System.out.println("User clicking on reset button");
            Reset_btn.click();
            Thread.sleep(4000);

            System.out.println("User checking for sorting functionality of Expiring Premium column");
            ExpiringPremium_Sorting_Functionality.click();
            Thread.sleep(3000);
            ExpiringPremium_Sorting_Functionality.click();
            Thread.sleep(3000);

            System.out.println("User checking grid search functionality for column - Expiring Premium");
            ExpiringPremium_GridSearch_Option.sendKeys("1000");
            Thread.sleep(2000);
            clickingonpoliciestab.click();
            Thread.sleep(4000);

            System.out.println("User clicking on reset button");
            Reset_btn.click();
            Thread.sleep(4000);

            System.out.println("User checking for sorting functinality of Expiring Commission percentage column");
            Expiring_Commission_Percentage_Column_Sorting.click();
            Thread.sleep(3000);
            Expiring_Commission_Percentage_Column_Sorting.click();
            Thread.sleep(3000);

            System.out.println("User checking for sorting functionality of Expiring Commission column");
            Expiring_Commission_Column_Sorting.click();
            Thread.sleep(3000);
            Expiring_Commission_Column_Sorting.click();
            Thread.sleep(3000);

            System.out.println("User checking search functionality of Expiring Commission column");
            Expiring_Commission_Column_Search.sendKeys("10");
            Thread.sleep(2000);
            clickingonpoliciestab.click();
            Thread.sleep(4000);

            System.out.println("User clicking on reset button");
            Reset_btn.click();
            Thread.sleep(4000);

            System.out.println("User checking for sorting functionality of Prior Carrier");
            PriorCarrier_Column_Sorting.click();
            Thread.sleep(3000);
            PriorCarrier_Column_Sorting.click();
            Thread.sleep(3000);

            System.out.println("User checking grid filter option for column - Prior Carrier");
            PriorCarrier_Column_GirdFilter.click();
            Thread.sleep(3000);
            PriorCarrier_Column_InternalSearch.sendKeys("Standard");
            Thread.sleep(3000);
            PriorCarrier_Column_InternalSearch_Selection.click();
            Thread.sleep(2000);
            clickingonpoliciestab.click();
            Thread.sleep(4000);

            System.out.println("User clicking on reset button");
            Reset_btn.click();
            Thread.sleep(4000);

            System.out.println("User checking sorting functionality for column Automatic Renewal");
            AutomaticRenewal_Column_Sorting.click();
            Thread.sleep(3000);
            AutomaticRenewal_Column_Sorting.click();
            Thread.sleep(3000);

            System.out.println("User checking grid filter for the column - Automatic Renewal");
            AutomaticRenewal_GridFilter.click();
            Thread.sleep(2000);
            AutomaticRenewal_GridFilter_InternalSearch.sendKeys("required");
            Thread.sleep(2000);
            AutomaticRenewal_GridFilter_InternalSearch_Selection.click();
            Thread.sleep(3000);
            clickingonpoliciestab.click();
            Thread.sleep(4000);

            System.out.println("User clicking on reset button");
            Reset_btn.click();
            Thread.sleep(4000);

            System.out.println("User checking sorting functionality for column Last Update");
            LastUpdate_Column_Sorting.click();
            Thread.sleep(3000);
            LastUpdate_Column_Sorting.click();
            Thread.sleep(3000);

            System.out.println("User checking date filter functinality for column Last Update");
            System.out.println("User selecting from date");
            LastUpdate_Column_FromDateFilter.click();
            Thread.sleep(2000);
            LastUpdate_Column_FromDateFilter_Select.click();
            Thread.sleep(3000);

            System.out.println("User selecting to date");
            LastUpdate_Column_ToDateFilter.click();
            Thread.sleep(1000);
            LastUpdate_Column_ToDateFilter_Select.click();
            Thread.sleep(2000);
            clickingonpoliciestab.click();
            Thread.sleep(3000);

            System.out.println("User clicking on reset button");
            Reset_btn.click();
            Thread.sleep(4000);

        } catch (Exception e) {
        }
    }

    @FindBy(how = How.XPATH, using = "/html/body/div[3]/div[1]/table/tbody/tr[4]/td[5]")
    public WebElement LastUpdate_Column_ToDateFilter_Select;

    @FindBy(how = How.XPATH, using = "//*[@id=\"to-LastUpdateAccounts\"]")
    public WebElement LastUpdate_Column_ToDateFilter;

    @FindBy(how = How.XPATH, using = "/html/body/div[3]/div[1]/table/tbody/tr[3]/td[4]")
    public WebElement LastUpdate_Column_FromDateFilter_Select;

    @FindBy(how = How.XPATH, using = "//*[@id=\"from-LastUpdateAccounts\"]")
    public WebElement LastUpdate_Column_FromDateFilter;

    @FindBy(how = How.XPATH, using = "//*[@id=\"th-LastUpdateAccounts\"]/div[1]")
    public WebElement LastUpdate_Column_Sorting;

    @FindBy(how = How.XPATH, using = "//*[@id=\"th-AutomaticRenewalAccounts\"]/span/div/ul/li[4]/a/label")
    public WebElement AutomaticRenewal_GridFilter_InternalSearch_Selection;

    @FindBy(how = How.XPATH, using = "//*[@id=\"th-AutomaticRenewalAccounts\"]/span/div/ul/li[1]/div/input")
    public WebElement AutomaticRenewal_GridFilter_InternalSearch;

    @FindBy(how = How.XPATH, using = "//*[@id=\"th-AutomaticRenewalAccounts\"]/span/div/button/span")
    public WebElement AutomaticRenewal_GridFilter;

    @FindBy(how = How.XPATH, using = "//*[@id=\"th-AutomaticRenewalAccounts\"]/div")
    public WebElement AutomaticRenewal_Column_Sorting;

    @FindBy(how = How.XPATH, using = "//*[@id=\"th-PriorCarrierTypeAccounts\"]/span/div/ul/li[3]/a/label")
    public WebElement PriorCarrier_Column_InternalSearch_Selection;

    @FindBy(how = How.XPATH, using = "//*[@id=\"th-PriorCarrierTypeAccounts\"]/span/div/ul/li[1]/div/input")
    public WebElement PriorCarrier_Column_InternalSearch;

    @FindBy(how = How.XPATH, using = "//*[@id=\"th-PriorCarrierAccounts\"]/span/div/button")
    public WebElement PriorCarrier_Column_GirdFilter;

    @FindBy(how = How.XPATH, using = "//*[@id=\"th-PriorCarrierAccounts\"]/div")
    public WebElement PriorCarrier_Column_Sorting;

    @FindBy(how = How.XPATH, using = "//*[@id=\"Exp Comm\"]")
    public WebElement Expiring_Commission_Column_Search;

    @FindBy(how = How.XPATH, using = "//*[@id=\"th-ExpiringCommissionAccounts\"]/div")
    public WebElement Expiring_Commission_Column_Sorting;

    @FindBy(how = How.XPATH, using = "//*[@id=\"th-ExpiringCommissionPercentAccounts\"]/div")
    public WebElement Expiring_Commission_Percentage_Column_Sorting;

    @FindBy(how = How.XPATH, using = "//*[@id=\"Exp Prem\"]")
    public WebElement ExpiringPremium_GridSearch_Option;

    @FindBy(how = How.XPATH, using = "//*[@id=\"th-ExpiringPremiumAccounts\"]/div")
    public WebElement ExpiringPremium_Sorting_Functionality;

    @FindBy(how = How.XPATH, using = "//*[@id=\"th-CoverageTypeAccounts\"]/span/div/ul/li[8]/a/label")
    public WebElement CoverageTypeColumn_GridFilter_InternalSearch_Select;

    @FindBy(how = How.XPATH, using = "//*[@id=\"th-CoverageTypeAccounts\"]/span/div/ul/li[1]/div/input")
    public WebElement CoverageTypeColumn_GridFilter_InternalSearch;

    @FindBy(how = How.XPATH, using = "//*[@id=\"th-CoverageTypeAccounts\"]/span/div/button/span")
    public WebElement CoverageTypeColumn_GridFilter;

    @FindBy(how = How.XPATH, using = "//*[@id=\"th-CoverageTypeAccounts\"]/div")
    public WebElement CoverageTypeColumn_SortingFunctionality;

    @FindBy(how = How.XPATH, using = "//*[@id=\"Insured\"]")
    public WebElement InsuredColumn_SearchOption;

    @FindBy(how = How.XPATH, using = "//*[@id=\"th-InsuredAccounts\"]/div")
    public WebElement InsuredColumn_SortingFunctionality;

    @FindBy(how = How.XPATH, using = "//*[@id=\"to-DateAccounts\"]")
    public WebElement DateColumn_ToDateFilter_Selection;

    @FindBy(how = How.XPATH, using = "//*[@id=\"from-DateAccounts\"]")
    public WebElement DateColumn_FromDateFilter_Selection;

    @FindBy(how = How.XPATH, using = "//*[@id=\"th-DateAccounts\"]/div[1]")
    public WebElement DateColumn_Sorting;

    @FindBy(how = How.XPATH, using = "//*[@id=\"th-ManagedPolicyStatusAccounts\"]/span/div/ul/li[3]/a/label")
    public WebElement StatusColumn_GridFilter_InternalSearch_Selection;

    @FindBy(how = How.XPATH, using = "//*[@id=\"th-ManagedPolicyStatusAccounts\"]/span/div/ul/li[1]/div/input")
    public WebElement StatusColumn_GridFilter_InternalSearch;

    @FindBy(how = How.XPATH, using = "//*[@id=\"th-ManagedPolicyStatusAccounts\"]/span/div/button/span")
    public WebElement StatusColumn_GridFilter;

    @FindBy(how = How.XPATH, using = "//*[@id=\"th-ManagedPolicyStatusAccounts\"]/div")
    public WebElement StatusColumn_SortingFunctionality;

    @FindBy(how = How.XPATH, using = "//*[@id=\"th-BusinessTypeAccounts\"]/span/div/ul/li[4]/a/label")
    public WebElement BusinessTypeColumn_GridFilter_InternalSearch_Selection;

    @FindBy(how = How.XPATH, using = "//*[@id=\"th-BusinessTypeAccounts\"]/span/div/ul/li[1]/div/input")
    public WebElement BusinessTypeColumn_GridFilter_InternalSearch;

    @FindBy(how = How.XPATH, using = "//*[@id=\"th-BusinessTypeAccounts\"]/span/div/button/span")
    public WebElement BusinessTypeColumn_GridFilter;

    @FindBy(how = How.XPATH, using = "//*[@id=\"th-BusinessTypeAccounts\"]/div")
    public WebElement column_BusinessType_sorting;

    @FindBy(how = How.XPATH, using = "//*[@id=\"th-SAMSAgencyAccounts\"]/span/div/ul/li[9]/a/label")
    public WebElement selecting_company_inthefilter;

    @FindBy(how = How.XPATH, using = "//*[@id=\"th-SAMSAgencyAccounts\"]/span/div/ul/li[1]/div/input")
    public WebElement column_SAMS_Agency_filter_internalsearch;

    @FindBy(how = How.XPATH, using = "//*[@id=\"th-SAMSAgencyAccounts\"]/span/div/button/span")
    public WebElement column_SAMS_Agency_filter;

    public void PreviousAndNextButtonsOfPages() {
        try {
            System.out.println("User checking for next button of the page");
            PageNextButton.click();
            Thread.sleep(3000);
            PageNextButton.click();
            Thread.sleep(3000);

            System.out.println("User checking for previous button of the page");
            PagePreviousButton.click();
            Thread.sleep(3000);
            PagePreviousButton.click();
            Thread.sleep(3000);

        } catch (Exception e) {
        }
    }

    @FindBy(how = How.XPATH, using = "//*[@id=\"dtPolicies_paginate\"]/div/a[1]")
    public WebElement PagePreviousButton;

    @FindBy(how = How.XPATH, using = "//*[@id=\"dtPolicies_paginate\"]/div/a[2]")
    public WebElement PageNextButton;

    public void Pagination102050100() {
        try {
            System.out.println("User checking for pagination");
            System.out.println("User selecting pagination 20");

            Select paginationbyvalue = new Select(Pagination_DropDown);
            Pagination_DropDown.click();
            Thread.sleep(1000);
            paginationbyvalue.selectByValue("20");
            Thread.sleep(1000);
            clickingonpoliciestab.click();
            Thread.sleep(3000);

            System.out.println("User selecting pagination 50");
            Pagination_DropDown.click();
            Thread.sleep(1000);
            paginationbyvalue.selectByValue("50");
            Thread.sleep(1000);
            clickingonpoliciestab.click();
            Thread.sleep(3000);

            System.out.println("User selecting pagination 100");
            Pagination_DropDown.click();
            Thread.sleep(1000);
            paginationbyvalue.selectByValue("100");
            Thread.sleep(1000);
            clickingonpoliciestab.click();
            Thread.sleep(3000);

        } catch (Exception e) {
        }
    }

    @FindBy(how = How.XPATH, using = "//*[@id=\"dtPolicies_length\"]/label/select")
    public WebElement Pagination_DropDown;

    public void LogoutOfApplication() {
        try {
            System.out.println("User logging out of AMA application");
            UserLogout.click();
            Thread.sleep(3000);
            logout.click();
            Thread.sleep(3000);

            ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
            driver.switchTo().window(tabs.get(1));
            driver.close();
            Thread.sleep(2000);

            driver.switchTo().window(tabs.get(0));
            driver.close();

        } catch (Exception e) {
        }
    }

    @FindBy(how = How.XPATH, using = "/html/body/header/nav/div/div/div[3]/div[1]/div/ul/li[16]")
    public WebElement logout;

    @FindBy(how = How.XPATH, using = "/html/body/header/nav/div/div/div[3]/div[1]/div/button/i")
    public WebElement UserLogout;

    public void mandatoryfieldsinaddpolicy(String insuredfield) {
        try {
            System.out.println("user clicking add policy button");
            System.out.println("user checking for manadatory fields in add policy pop up");
            addpolicybutton.click();
            Thread.sleep(3000);

            System.out.println("user clicking on submit button without selecting account manager");
            System.out.println("If account manager drop down field is mandatory then an error message should be displayed on the field it self");
            addpolicysubmitbtn.click();
            Thread.sleep(2000);

            String Account_Manager_Mandatory_Messages = Account_Manager_Mandatory_Message.getText();
            System.out.println("Account manager dropdown manadatory message: " + Account_Manager_Mandatory_Messages);
            Thread.sleep(2000);

            System.out.println("user selecting account manager under from account manager drop down");
            accountmanagerdropdown.click();
            Thread.sleep(1000);
            Select account_managerdropdown = new Select(accountmanagerdropdown);
            account_managerdropdown.selectByVisibleText("Kiran Chakrapani");
            Thread.sleep(2000);

            System.out.println("user clicking on submit button without selecting Company from company drop down");
            System.out.println("If company drop down field is mandatory then an error message should be displayed on the field it self");
            addpolicysubmitbtn.click();
            Thread.sleep(2000);

            String Company_Drop_Down = Company_Mandatory_Message.getText();
            System.out.println("Company drop down mandatory message: " + Company_Drop_Down);
            Thread.sleep(2000);

            System.out.println("user selecting company from company drop down");
            companydropdown.click();
            Thread.sleep(1000);
            Select company_dropdown = new Select(companydropdown);
            company_dropdown.selectByVisibleText("Atlas Insurance - SAMS");
            Thread.sleep(2000);

            System.out.println("user clicking on submit button without selecting Status from status drop down");
            System.out.println("If status drop down field is mandatory then an error message should be displayed on the field it self");
            addpolicysubmitbtn.click();
            Thread.sleep(2000);

            String Status_Drop_Down = Status_Mandatory_Message.getText();
            System.out.println("Status drop down mandatory message: " + Status_Drop_Down);
            Thread.sleep(2000);

            System.out.println("user selecting status from status drop down");
            statusdropdown.click();
            Thread.sleep(1000);
            Select status_dropdown = new Select(statusdropdown);
            status_dropdown.selectByValue("1");
            Thread.sleep(2000);

            System.out.println("user clicking on submit button without selecting Business Type from business type drop down");
            System.out.println("If business type drop down field is mandatory then an error message should be displayed on the field it self");
            addpolicysubmitbtn.click();
            Thread.sleep(2000);

            String Business_Type_Drop_Down = BusinessType_Mandatory_Message.getText();
            System.out.println("Business type drop down mandatory message: " + Business_Type_Drop_Down);
            Thread.sleep(2000);

            System.out.println("User selecting business type from business type drop down");
            businesstypedropdown.click();
            Thread.sleep(1000);
            Select businesstype = new Select(businesstypedropdown);
            businesstype.selectByValue("1");
            Thread.sleep(2000);

            System.out.println("user clicking on submit button without selecting Date from date picker");
            System.out.println("If date field is mandatory then an error message should be displayed on the field it self");
            addpolicysubmitbtn.click();
            Thread.sleep(2000);

            String Date_Picker = Date_Mandatory_Message.getText();
            System.out.println("Date field mandatory message: " + Date_Picker);
            Thread.sleep(2000);

            System.out.println("Use selecting date from the calendar");
            datepicker1.click();
            Thread.sleep(1000);

            selectingthedate.click();
            Thread.sleep(2000);

            System.out.println("user clicking on submit button without entering insured");
            System.out.println("If insured field is mandatory then an error message should be displayed on the field it self");
            addpolicysubmitbtn.click();
            Thread.sleep(2000);

            String Insured_Field = Insured_Mandatory_Message.getText();
            System.out.println("Insured field manadatory message: " + Insured_Field);

            System.out.println("User entering insured name into insured field");
            insured1.sendKeys(insuredfield);
            Thread.sleep(1000);

            System.out.println("user clicking on submit button after all mandatory fields in add policy popup");
            addpolicysubmitbtn.click();
            Thread.sleep(6000);

        } catch (Exception e) {
        }
    }

    @FindBy(how = How.XPATH, using = "//*[@id=\"err_insured\"]")
    public WebElement Insured_Mandatory_Message;

    @FindBy(how = How.XPATH, using = "//*[@id=\"err_add_Date\"]")
    public WebElement Date_Mandatory_Message;

    @FindBy(how = How.XPATH, using = "//*[@id=\"err_businessType\"]")
    public WebElement BusinessType_Mandatory_Message;

    @FindBy(how = How.XPATH, using = "//*[@id=\"err_status\"]")
    public WebElement Status_Mandatory_Message;

    @FindBy(how = How.XPATH, using = "//*[@id=\"err_company\"]")
    public WebElement Company_Mandatory_Message;

    @FindBy(how = How.XPATH, using = "//*[@id=\"err_acc_manager\"]")
    public WebElement Account_Manager_Mandatory_Message;

    public void numericfieldsinaddpolicy(String insuredfield, String expiringpremium, String expcommissionpercentage, String renewalnewpremium, String renewalnewcommissionpercentage) {
        try {
            System.out.println("user clicking add policy button");
            System.out.println("user checking for numeric fields in add policy pop up");
            addpolicybutton.click();
            Thread.sleep(3000);

            System.out.println("user selecting account manager under from account manager drop down");
            accountmanagerdropdown.click();
            Thread.sleep(1000);
            Select account_managerdropdown = new Select(accountmanagerdropdown);
            account_managerdropdown.selectByVisibleText("Kiran Chakrapani");
            Thread.sleep(2000);

            System.out.println("user selecting company from company drop down");
            companydropdown.click();
            Thread.sleep(1000);
            Select company_dropdown = new Select(companydropdown);
            company_dropdown.selectByVisibleText("Atlas Insurance - SAMS");
            Thread.sleep(2000);

            System.out.println("user selecting status from status drop down");
            statusdropdown.click();
            Thread.sleep(1000);
            Select status_dropdown = new Select(statusdropdown);
            status_dropdown.selectByValue("1");
            Thread.sleep(2000);

            System.out.println("User selecting business type from business type drop down");
            businesstypedropdown.click();
            Thread.sleep(1000);
            Select businesstype = new Select(businesstypedropdown);
            businesstype.selectByValue("1");
            Thread.sleep(2000);

            System.out.println("Use selecting date from the calendar");
            datepicker1.click();
            Thread.sleep(1000);

            selectingthedate.click();
            Thread.sleep(2000);

            System.out.println("User entering insured name into insured field");
            insured1.sendKeys(insuredfield);
            Thread.sleep(1000);

            //Expiring premium numeric value testing
            System.out.println("User entering characters into expiring premium");
            expiring_premium.sendKeys("test");
            Thread.sleep(4000);

            if (expiring_premium.getAttribute("value").isEmpty()) {
                System.out.println("Characters are not allowed in expiring premium");
                Thread.sleep(2000);
            } else {
                System.out.println("Expiring premium is allowing characters");
                Thread.sleep(2000);
            }

            System.out.println("User entering numerics value into expiring premium");
            expiring_premium.sendKeys(expiringpremium);

            Double.parseDouble(expiringpremium);
            if (isNumeric(expiringpremium)) {
                System.out.println("Expiring premium is allowing numeric values only");
                Thread.sleep(2000);
            } else {
                System.out.println("Expiring premium is not allowing numeric values");
            }

            //Expiring commission percentage numeric value testing
            System.out.println("User entering characters into expiring commission");
            expiring_commissionage.sendKeys("test");
            Thread.sleep(4000);

            if (expiring_commissionage.getAttribute("value").isEmpty()) {
                System.out.println("Characters are not allowed in expiring commission");
                Thread.sleep(2000);
            } else {
                System.out.println("Expiring commission is allowing characters");
                Thread.sleep(2000);
            }

            System.out.println("User entering numeric value into expiring commission%");
            expiring_commissionage.sendKeys(expcommissionpercentage);
            Thread.sleep(2000);

            Double.parseDouble(expcommissionpercentage);
            if (isNumeric(expcommissionpercentage)) {
                System.out.println("Expiring commission is allowing numeric values only");
                Thread.sleep(2000);
            } else {
                System.out.println("Expiring commission is not allowing numeric values");
                Thread.sleep(2000);
            }

            //Renewal/New premium numeric value testing
            System.out.println("User entering characters into renewal/new premium");
            renewalnew_premium.sendKeys("test");
            Thread.sleep(2000);

            if (renewalnew_premium.getAttribute("value").isEmpty()) {
                System.out.println("Characters are not allowed in renewal/new premium");
                Thread.sleep(2000);
            } else {
                System.out.println("Renewal/New premium is allowing characters");
                Thread.sleep(2000);
            }

            System.out.println("User entering numeric value into renewal/new premium");
            renewalnew_premium.sendKeys(renewalnewpremium);
            Thread.sleep(2000);

            Double.parseDouble(renewalnewpremium);
            if (isNumeric(renewalnewpremium)) {
                System.out.println("Renewal/New premium is allowing numeric values only");
                Thread.sleep(2000);
            } else {
                System.out.println("Renewal/New premium is not allowing numeric values");
                Thread.sleep(2000);
            }

            //Renewal/New commission percentage numeric value testing
            System.out.println("User entering characters into renewal/new commission%");
            renewalnew_commissionage.sendKeys("test");
            Thread.sleep(3000);

            if (renewalnew_commissionage.getAttribute("value").isEmpty()) {
                System.out.println("Characters are not allowed in renewal/new commission%");
                Thread.sleep(2000);
            } else {
                System.out.println("Renewal/New commission% is allowing characters");
                Thread.sleep(2000);
            }

            System.out.println("User entering numeric value into renewal/new commission%");
            renewalnew_commissionage.sendKeys(renewalnewcommissionpercentage);
            Thread.sleep(2000);

            Double.parseDouble(renewalnewcommissionpercentage);
            if (isNumeric(renewalnewcommissionpercentage)) {
                System.out.println("Renewal/New commission percentage is allowing numeric values only");
                Thread.sleep(2000);
            } else {
                System.out.println("Renewal/New commission percentage is not allowing numeric values");
                Thread.sleep(2000);
            }

            System.out.println("User clicking on add policy submit button");
            addpolicysubmitbtn.click();
            Thread.sleep(3000);

        } catch (Exception e) {
        }
    }

    public static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public void notestabmandatoryfields() {
        try {
            System.out.println("User checking for mandatory fields in add note popup in detail page");
            System.out.println("User opening a work order from policies tab");
            newlycreatedpolicynumber.click();
            Thread.sleep(5000);

            System.out.println("User clicking on add note button in detail page");
            Add_Note_In_Record.click();

            System.out.println("user clicking on submit button of add note pop up without entering Title");
            Note_Submit_Btn.click();
            Thread.sleep(2000);

            String AddNote_Title_Error = AddNote_Title_Mandatory_Error.getText();
            System.out.println("Add note title is a mandatory field " + AddNote_Title_Error);

            System.out.println("User entering title in add note popup");
            note_title.sendKeys("Standard Business Solutions");
            Thread.sleep(1000);

            System.out.println("user clicking on submit button of add note pop up without entering Description");
            Note_Submit_Btn.click();
            Thread.sleep(2000);

            String AddNote_Description_Error = AddNote_Description_Mandatory_Error.getText();
            System.out.println("Add note description is a mandatory field " + AddNote_Description_Error);

            System.out.println("User entering description in add note popup");
            note_desc.sendKeys("Branch of Kirans bsiness code2");
            Thread.sleep(1000);

            System.out.println("user clicking on submit button of add note pop up");
            Note_Submit_Btn.click();
            Thread.sleep(4000);

        } catch (Exception e) {
        }
    }

    @FindBy(how = How.XPATH, using = "//*[@id=\"note_desc_err\"]")
    public WebElement AddNote_Description_Mandatory_Error;

    @FindBy(how = How.XPATH, using = "//*[@id=\"note_title_err\"]")
    public WebElement AddNote_Title_Mandatory_Error;

    public void importingpolcies2() {
        try {
            System.out.println("user checking for importing functionality by entering incorrect data");

            String Total_NumberOf_Records_BeforeImport = TotalNumberOfRecordsImport.getText();
            System.out.println("Total No.of records before import " + Total_NumberOf_Records_BeforeImport);

            System.out.println("user clicking on import policies button");
            Import_Policies.click();
            Thread.sleep(3000);

            System.out.println("user browsing for excel file");
            WebElement uploadElements = driver.findElement(By.id("excelfile"));
            uploadElements.sendKeys("D:\\kiranchakrapani\\Desktop\\AMA Import Sample_updated.xls");
            Thread.sleep(4000);

            System.out.println("user clicking on submit button of import policies");
            ImportSubmitButton.click();
            Thread.sleep(2000);

            System.out.println("user clicking on upload file button of import policies");
            ImportUploadButton.click();
            Thread.sleep(5000);

            String Total_NumberOf_Records_AfterImport = TotalNumberOfRecordsImport.getText();
            System.out.println("Total No.of records after import " + Total_NumberOf_Records_AfterImport);

            if (Total_NumberOf_Records_BeforeImport.contentEquals(Total_NumberOf_Records_AfterImport)) {
                System.out.println("Please correct the content in the excel sheet");
            } else {
                System.out.println("Import functionality is importing incorrect values");
            }

            System.out.println("user clicking on reset button of import policies popup");
            ImportPoliciesPopUpResetButton.click();
            Thread.sleep(2000);

            System.out.println("user clicking close button of import policies popup");
            ImportPoliciesPopUpCloseButton.click();
            Thread.sleep(3000);

        } catch (Exception e) {
        }
    }

    @FindBy(how = How.XPATH, using = "//*[@id=\"Import_Policy_Cancel\"]")
    public WebElement ImportPoliciesPopUpCloseButton;

    @FindBy(how = How.XPATH, using = "//*[@id=\"resets\"]")
    public WebElement ImportPoliciesPopUpResetButton;

    @FindBy(how = How.XPATH, using = "//*[@id=\"dtPolicies_info\"]")
    public WebElement TotalNumberOfRecordsImport;

    @FindBy(how = How.ID, using = "uploadfile")
    public WebElement ImportUploadButton;

    @FindBy(how = How.ID, using = "viewfile")
    public WebElement ImportSubmitButton;


    public void DeletingRecord() {
        try {
            System.out.println("user checking for deleting a record from policies tab");
            clickingonpoliciestab.click();
            Thread.sleep(5000);

            String SelectedRecordNumberForDeletion = Record_NumberFor_Deletion.getText();
            Thread.sleep(2000);

            System.out.println("user entering seleted record number in column ID search option for deleting");

            System.out.println("user opening the selected record for deletion");
            Record_NumberFor_Deletion.click();
            Thread.sleep(4000);

            System.out.println("user clicking on delete button in detail page");
            EditRecordDeleteButton.click();
            Thread.sleep(3000);

            System.out.println("user clicking on yes on the confirm popup");
            ConfirmPopUpYesClick.click();
            Thread.sleep(6000);

            System.out.println("user checking for the deleted record under policies tab");
            column_id_search.sendKeys(SelectedRecordNumberForDeletion);
            Thread.sleep(1000);

            clickingonpoliciestab.click();
            Thread.sleep(4000);

            Reset_btn.click();
            Thread.sleep(5000);

        } catch (Exception e) {
        }
    }

    @FindBy(how = How.XPATH, using = "/html/body/div[3]/div[2]/div/div/div/div/div/div/div/div[4]/button[1]")
    public WebElement ConfirmPopUpYesClick;

    @FindBy(how = How.XPATH, using = "//*[@id=\"policy_delete\"]")
    public WebElement EditRecordDeleteButton;

    @FindBy(how = How.XPATH, using = "//*[@id=\"dtPolicies\"]/tbody/tr[1]/td[1]/a")
    public WebElement Record_NumberFor_Deletion;

    public void AddPolicySubmitOpenButton() {
        try {
            System.out.println("user clicking add policy button");
            System.out.println("user checking for manadatory fields in add policy pop up");
            addpolicybutton.click();
            Thread.sleep(3000);

            System.out.println("user clicking on submit&Open button without selecting account manager");
            System.out.println("If account manager drop down field is mandatory then an error message should be displayed on the field it self");
            AddPolicySubmitAndOpenButton.click();
            Thread.sleep(2000);

            String Account_Manager_Mandatory_Messages = Account_Manager_Mandatory_Message.getText();
            System.out.println("Account manager dropdown manadatory message: " + Account_Manager_Mandatory_Messages);
            Thread.sleep(2000);

            System.out.println("user selecting account manager under from account manager drop down");
            accountmanagerdropdown.click();
            Thread.sleep(1000);
            Select account_managerdropdown = new Select(accountmanagerdropdown);
            account_managerdropdown.selectByVisibleText("Kiran Chakrapani");
            Thread.sleep(2000);

            System.out.println("user clicking on submit&Open button without selecting Company from company drop down");
            System.out.println("If company drop down field is mandatory then an error message should be displayed on the field it self");
            AddPolicySubmitAndOpenButton.click();
            Thread.sleep(2000);

            String Company_Drop_Down = Company_Mandatory_Message.getText();
            System.out.println("Company drop down mandatory message: " + Company_Drop_Down);
            Thread.sleep(2000);

            System.out.println("user selecting company from company drop down");
            companydropdown.click();
            Thread.sleep(1000);
            Select company_dropdown = new Select(companydropdown);
            company_dropdown.selectByVisibleText("Atlas Insurance - SAMS");
            Thread.sleep(2000);

            System.out.println("user clicking on submit&Open button without selecting Status from status drop down");
            System.out.println("If status drop down field is mandatory then an error message should be displayed on the field it self");
            AddPolicySubmitAndOpenButton.click();
            Thread.sleep(2000);

            String Status_Drop_Down = Status_Mandatory_Message.getText();
            System.out.println("Status drop down mandatory message: " + Status_Drop_Down);
            Thread.sleep(2000);

            System.out.println("user selecting status from status drop down");
            statusdropdown.click();
            Thread.sleep(1000);
            Select status_dropdown = new Select(statusdropdown);
            status_dropdown.selectByValue("1");
            Thread.sleep(2000);

            System.out.println("user clicking on submit&Open button without selecting Business Type from business type drop down");
            System.out.println("If business type drop down field is mandatory then an error message should be displayed on the field it self");
            AddPolicySubmitAndOpenButton.click();
            Thread.sleep(2000);

            String Business_Type_Drop_Down = BusinessType_Mandatory_Message.getText();
            System.out.println("Business type drop down mandatory message: " + Business_Type_Drop_Down);
            Thread.sleep(2000);

            System.out.println("User selecting business type from business type drop down");
            businesstypedropdown.click();
            Thread.sleep(1000);
            Select businesstype = new Select(businesstypedropdown);
            businesstype.selectByValue("1");
            Thread.sleep(2000);

            System.out.println("user clicking on submit&Open button without selecting Date from date picker");
            System.out.println("If date field is mandatory then an error message should be displayed on the field it self");
            AddPolicySubmitAndOpenButton.click();
            Thread.sleep(2000);

            String Date_Picker = Date_Mandatory_Message.getText();
            System.out.println("Date field mandatory message: " + Date_Picker);
            Thread.sleep(2000);

            System.out.println("Use selecting date from the calendar");
            datepicker1.click();
            Thread.sleep(1000);

            selectingthedate.click();
            Thread.sleep(2000);

            System.out.println("user clicking on submit&Open button without entering insured");
            System.out.println("If insured field is mandatory then an error message should be displayed on the field it self");
            AddPolicySubmitAndOpenButton.click();
            Thread.sleep(2000);

            String Insured_Field = Insured_Mandatory_Message.getText();
            System.out.println("Insured field manadatory message: " + Insured_Field);

            System.out.println("User entering insured name into insured field");
            insured1.sendKeys("Testing Bussiness Code007");
            Thread.sleep(1000);

            System.out.println("user clicking on submit&Open button after all mandatory fields in add policy popup");
            AddPolicySubmitAndOpenButton.click();
            Thread.sleep(6000);

            System.out.println("user clicking on close button of work order detail page");
            policy_entry_close.click();
            Thread.sleep(6000);
        } catch (Exception e) {
        }
    }


    @FindBy(how = How.XPATH, using = "//*[@id=\"Policy_submit_open\"]")
    public WebElement AddPolicySubmitAndOpenButton;

    public void AddPolicyCancelButton() {
        try {
            System.out.println("user checking for cancel button on add policy popup");
            System.out.println("user clicking add policy button");
            addpolicybutton.click();
            Thread.sleep(3000);

            System.out.println("user clicking on cancel button");
            AddPolicyCancelButtton.click();

            System.out.println("user refreshing the current page after checking for cancel button in add policy popup");
            driver.navigate().refresh();
            Thread.sleep(5000);

        } catch (Exception e) {
        }
    }

    @FindBy(how = How.XPATH, using = "//*[@id=\"Policy-Details-Cancel\"]")
    public WebElement AddPolicyCancelButtton;

    public void AddPolicyDefaultValues() {
        try {
            System.out.println("user checking for add policy popup whether values are defauting to blank and select after adding a policy");
            System.out.println("user clicking on add policy button");
            addpolicybutton.click();
            Thread.sleep(4000);

            Select accountmanager_dropdown = new Select(accountmanagerdropdown);
            String AccountManagerDropDownDefaults = accountmanager_dropdown.getFirstSelectedOption().getText();
            Thread.sleep(1000);
            System.out.println("account manager drop down is defaulting to " + AccountManagerDropDownDefaults);

            Select company_dropdown = new Select(companydropdown);
            String CompanyDropDownDefaults = company_dropdown.getFirstSelectedOption().getText();
            Thread.sleep(1000);
            System.out.println("company drop down is defaulting to " + CompanyDropDownDefaults);


            Select status_dropdown = new Select(statusdropdown);
            String StatusDropDownDefaults = status_dropdown.getFirstSelectedOption().getText();
            Thread.sleep(1000);
            System.out.println("status drop down is defaulting to " + StatusDropDownDefaults);

            Select businesstype_dropdown = new Select(businesstypedropdown);
            String BusinessTypeDropDowns = businesstype_dropdown.getFirstSelectedOption().getText();
            Thread.sleep(1000);
            System.out.println("business type drop down is defaulting to " + BusinessTypeDropDowns);

            String Date_Picker = datepicker1.getText();
            if (Date_Picker.isEmpty()) {
                System.out.println("date picker field is blank");
            } else {
                System.out.println("date picker field is not blank");
            }

            String InsuredTextField = insured1.getAttribute("value");
            if (InsuredTextField.isEmpty()) {
                System.out.println("Insured field is blank");
            } else {
                System.out.println("Insured field is not blank");
            }

            Select BusinessClass_DropDown = new Select(BusinessClassDropDown);
            String BusinessClassDropDowns = BusinessClass_DropDown.getFirstSelectedOption().getText();
            Thread.sleep(1000);
            System.out.println("business class drop down is defaulting to " + BusinessClassDropDowns);

            Select CoverageType_DropDown = new Select(CoverageTypeDropDown);
            String CoverageType_DropDowns = CoverageType_DropDown.getFirstSelectedOption().getText();
            Thread.sleep(1000);
            System.out.println("coverage type drop down is defaulting to " + CoverageType_DropDowns);

            String ProducerOrOfficeTextField = ProducerOrOffice.getAttribute("value");
            if (ProducerOrOfficeTextField.isEmpty()) {
                System.out.println("Producer or Office field is blank");
            } else {
                System.out.println("Producer or Office field is not blank");
            }

            Select PriorCarrier_DropDown = new Select(PriorCarrierDropDown);
            String PriorCarrierDropDowns = PriorCarrier_DropDown.getFirstSelectedOption().getText();
            Thread.sleep(1000);
            System.out.println("prior carrier drop down is defaulting to " + PriorCarrierDropDowns);

            Select PriorCarrierType_DropDown = new Select(PriorCarrierTypeDropDown);
            String policy_prior_carrier_type = PriorCarrierType_DropDown.getFirstSelectedOption().getText();
            Thread.sleep(1000);
            System.out.println("prior carrier type drop down is defaulting to " + policy_prior_carrier_type);

            String ExpiringPremium = expiring_premium.getAttribute("value");
            if (ExpiringPremium.isEmpty()) {
                System.out.println("expiring premium field is blank");
            } else {
                System.out.println("expiring premium field is not blank");
            }

        } catch (Exception e) {
        }
    }

    @FindBy(how = How.XPATH, using = "//*[@id=\"policy-prior-carrier-type\"]")
    public WebElement PriorCarrierTypeDropDown;

    @FindBy(how = How.XPATH, using = "//*[@id=\"policy-prior-carrier\"]")
    public WebElement PriorCarrierDropDown;

    @FindBy(how = How.XPATH, using = "//*[@id=\"policy-producer\"]")
    public WebElement ProducerOrOffice;

    @FindBy(how = How.XPATH, using = "//*[@id=\"policy-coverage-type\"]")
    public WebElement CoverageTypeDropDown;

    @FindBy(how = How.XPATH, using = "//*[@id=\"policy-business-class\"]")
    public WebElement BusinessClassDropDown;

    public void editrecordcancelchanges() {
        try {
            System.out.println("user checking for cancel changes button in detail page");
            System.out.println("user opening a record under policies tab");
            Record_NumberFor_Deletion.click();
            Thread.sleep(4000);

            Select DetailPage_AccountManager = new Select(DetailPageAccountManager);
            String DetailPageAccountManagers = DetailPage_AccountManager.getFirstSelectedOption().getText();
            Thread.sleep(1000);
            System.out.println("Account manager name selected before changing is " + DetailPageAccountManagers);
            Thread.sleep(1000);
            DetailPageAccountManager.click();
            Thread.sleep(1000);
            DetailPage_AccountManager.selectByVisibleText("Administrator Base");
            Thread.sleep(1000);

            System.out.println("user clicking cancel changes button");
            policy_entry_cancel.click();
            Thread.sleep(2000);

            String DetailPageAccountManagerss = DetailPage_AccountManager.getFirstSelectedOption().getText();
            Thread.sleep(1000);
            System.out.println("Account manager name selected after clicking cancel changes " + DetailPageAccountManagerss);

            if (DetailPageAccountManagerss.contentEquals(DetailPageAccountManagers)) {
                System.out.println("After clicking cancel changes button the old data is being retrieved");
            } else {
                System.out.println("Cancel changes button is not working as per requirement");
            }
        } catch (Exception e) {
        }
    }

    @FindBy(how = How.XPATH, using = "//*[@id=\"policy_entry_cancel\"]")
    public WebElement policy_entry_cancel;

    @FindBy(how = How.XPATH, using = "//*[@id=\"policy-acc-manager\"]")
    public WebElement DetailPageAccountManager;

    public void MandatoryfieldsInDetailPage(String detailpageinsured) {
        try {
            System.out.println("user checking for mandatory fields in detail page");
            System.out.println("user opening a record under policies tab");
            Record_NumberFor_Deletion.click();
            Thread.sleep(4000);

            System.out.println("user checking for insured mandatory field in the detail page");
            DetailPagePolicyInsured.clear();
            Thread.sleep(2000);

            System.out.println("user clicking submit button without entering insured field");
            DetailPageSubmitButton.click();
            Thread.sleep(2000);

            String InsuredMandatoryMessage = MandatoryMessageOnInsuredField.getText();
            System.out.println("Detail page insured field mandatory message " + InsuredMandatoryMessage);
            Thread.sleep(2000);

            System.out.println("user entering detail into insured field");
            DetailPagePolicyInsured.sendKeys(detailpageinsured);
            Thread.sleep(2000);
            System.out.println("user clicking on submit button");
            DetailPageSubmitButton.click();
            Thread.sleep(3000);

            System.out.println("user closing the work order");
            policy_entry_close.click();
            Thread.sleep(4000);

        } catch (Exception e) {
        }
    }

    @FindBy(how = How.XPATH, using = "//*[@id=\"err_insured\"]")
    public WebElement MandatoryMessageOnInsuredField;

    @FindBy(how = How.XPATH, using = "//*[@id=\"policy_entry_submit\"]")
    public WebElement DetailPageSubmitButton;

    @FindBy(how = How.XPATH, using = "//*[@id=\"policy-insured\"]")
    public WebElement DetailPagePolicyInsured;

    public void DetailPageSubmitNextButton() throws Throwable {
        System.out.println("user checking for submit next button in record detail page");
        Thread.sleep(5000);

        String WO_Number = Record_NumberFor_Deletion.getText();
        System.out.println("user selected work order number: " + WO_Number);

        System.out.println("user opening a record under policies tab");
        Record_NumberFor_Deletion.click();
        Thread.sleep(4000);

        System.out.println("user selecting date");
        DetailPageDateField.click();
        Thread.sleep(1000);
        SelectingDateInDetailPage.click();
        Thread.sleep(2000);

        System.out.println("user clicking submit next button in the detail");
        DetailPageSubmitNextButton.click();
        Thread.sleep(10000);

        String nOrder = NextWoNumber.getAttribute("value");
        System.out.println("nextworkorder -- " + nOrder);


        if (WO_Number.contentEquals(nOrder)) {
            System.out.println("submit next button is not working");
        } else {
            System.out.println("submit next button is working correctly");
        }

        System.out.println("user closing the work order");
        submitNextCloseButton.click();
        Thread.sleep(4000);
    }

    @FindBy(how = How.XPATH, using = "//*[@id=\"policy_entry_close\"]")
    public WebElement submitNextCloseButton;

    @FindBy(how = How.XPATH, using = "//*[@id=\"policy_record_id\"]")
    public WebElement NextWoNumber;

    @FindBy(how = How.XPATH, using = "//*[@id=\"policy_entry_submit_next\"]")
    public WebElement DetailPageSubmitNextButton;

    @FindBy(how = How.XPATH, using = "//*[@id=\"policy-add-date\"]")
    public WebElement DetailPageDateField;

    @FindBy(how = How.XPATH, using = "/html/body/div[3]/div[1]/table/tbody/tr[5]/td[2]")
    public WebElement SelectingDateInDetailPage;

    public void DetailPageSubmitAndCloseButtonMandate() throws Throwable {
        System.out.println("user checking for mandatory functionality of submit button in detail page");
        System.out.println("user opening a record under policies tab");
        Record_NumberFor_Deletion.click();
        Thread.sleep(5000);

        DetailPagePolicyInsured.clear();
        Thread.sleep(1000);
        DetailPagePolicyInsured.sendKeys("Test");
        Thread.sleep(2000);

        System.out.println("user clicking submit and close button without entering date field");
        policy_entry_submit_close.click();

        String Datemandatorymessage = DetailPage_Date_MandatoryMessage.getText();
        System.out.println("" + Datemandatorymessage);

        if (Datemandatorymessage.contentEquals("Please Select Date")) {
            System.out.println("Submit and close button is also working fine for mandatory message");
        } else {
            System.out.println("Submit and close button is not working for mandatory message");
        }

        System.out.println("user closing the work order");
        submitNextCloseButton.click();
        Thread.sleep(4000);
    }

    @FindBy(how = How.XPATH, using = "//*[@id=\"err_add_Date\"]")
    public WebElement DetailPage_Date_MandatoryMessage;

    public void AddNotePopUpCancelButton() throws Throwable{
        System.out.println("user checking for cancel button in add note popup of detail page");
        System.out.println("user opening an existing record under policies tab");
        Record_NumberFor_Deletion.click();
        Thread.sleep(4000);

        System.out.println("user navigating to notes tab in detail page");
        notes_sects_Tab.click();
        Thread.sleep(2000);

        String notestabrecordscountbefore = NoOfRecordsUnderNotesTab.getText();
        System.out.println("No.of records under notes tab: "+notestabrecordscountbefore);

        System.out.println("user clicking on add note button in detail page");
        Add_Note_In_Record.click();
        Thread.sleep(2000);

        System.out.println("user clicking on cancel button on add note popup");
        AddNoteCancelButton.click();
        Thread.sleep(2000);

        String notestabrecordscountAfter = NoOfRecordsUnderNotesTab.getText();
        System.out.println("No.of records under notes tab: "+notestabrecordscountAfter);

        if(notestabrecordscountbefore.contentEquals(notestabrecordscountAfter)){
            System.out.println("Cancel button for add note popup is working fine");
        }else{
            System.out.println("Cancel button for add note popup is not working correctly");
        }

        System.out.println("user closing the work order");
        submitNextCloseButton.click();
        Thread.sleep(4000);

    }

    @FindBy(how = How.XPATH, using = "//*[@id=\"dtnotes_wrapper_info\"]")
    public WebElement NoOfRecordsUnderNotesTab;

    @FindBy(how = How.XPATH, using = "//*[@id=\"notes_close\"]")
    public WebElement AddNoteCancelButton;
}