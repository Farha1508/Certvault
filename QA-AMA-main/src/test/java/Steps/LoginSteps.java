package Steps;

import BaseUtil.BaseUtil;
import Page.LoginPage;
import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
//import sun.rmi.runtime.Log;
//import sun.rmi.runtime.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class LoginSteps extends BaseUtil {

    private BaseUtil base;

    public LoginSteps(BaseUtil base) {
        this.base = base;
    }

    @Given("^User navigates to login page$")
    public void userNavigatesToLoginPage() throws Throwable {
        System.out.println("User navigating to login page");
        base.driver.navigate().to("https://dev.patracorp.net");
        Thread.sleep(5000);
        base.driver.manage().window().maximize();
        Thread.sleep(3000);
    }

    @And("^user enters credentials$")
    public void userEntersCredentials(DataTable table) throws Throwable {
        List<SigIn> loginids = new ArrayList<SigIn>();
        loginids = table.asList(SigIn.class);
        LoginPage page = new LoginPage(base.driver);
        for (SigIn loginid : loginids) {
            page.loginintoama(loginid.username, loginid.password);
        }
        Thread.sleep(2000);
        String parentWindow = base.driver.getWindowHandle();
        Set<String> handles = base.driver.getWindowHandles();
        for (String Windowhandle : handles) {
            if (!Windowhandle.equals(parentWindow)) {
                base.driver.switchTo().window(Windowhandle);
            }
        }
    }

    public class SigIn {
        public String username;
        public String password;

        public SigIn(String Username, String Password) {
            username = Username;
            password = Password;
        }
    }

    @And("^user checking for record in My YTD Policy Count KPI$")
    public void userCheckingForRecordInMyYTDPolicyCountKPI() {
        LoginPage page = new LoginPage(base.driver);
        page.myytdpolicycountkpi();
    }


    @And("^user adding the policy into the ama$")
    public void userAddingThePolicyIntoTheAma(DataTable table) throws Throwable {
        List<PoLicy> details = new ArrayList<PoLicy>();
        details = table.asList(PoLicy.class);
        LoginPage page = new LoginPage(base.driver);
        for (PoLicy detail : details) {
            page.addingpolicy(detail.agencymanagementcode, detail.insured, detail.produceroffice, detail.expiringpremium, detail.expiringcommissionage, detail.expiringcommission, detail.renewalnewpremium, detail.renewalnewcommissionage, detail.renewalnewcommission);
        }
    }

    public class PoLicy {
        public String agencymanagementcode;
        public String insured;
        public String produceroffice;
        public String expiringpremium;
        public String expiringcommissionage;
        public String expiringcommission;
        public String renewalnewpremium;
        public String renewalnewcommissionage;
        public String renewalnewcommission;

        public PoLicy(String Agencymanagementcode, String Insured, String Produceroffice, String Expiringpremium, String Expiringcommissionage, String Expiringcommission, String
                Renewalnewpremium, String Renewalnewcommissionage, String Renewalnewcommission) {
            agencymanagementcode = Agencymanagementcode;
            insured = Insured;
            produceroffice = Produceroffice;
            expiringpremium = Expiringpremium;
            expiringcommissionage = Expiringcommissionage;
            expiringcommission = Expiringcommission;
            renewalnewpremium = Renewalnewpremium;
            renewalnewcommissionage = Renewalnewcommissionage;
            renewalnewcommission = Renewalnewcommission;
        }
    }

    @And("^user edit the record$")
    public void userChangesTheRecordToDifferentKPIs(DataTable table) throws Throwable {
        System.out.println("user changes the created record to different KPIs");
        List<QuestionTab> entries = new ArrayList<QuestionTab>();
        entries = table.asList(QuestionTab.class);
        LoginPage page = new LoginPage(base.driver);
        for (QuestionTab entrie : entries) {
            page.checkingwithkpis(entrie.naics, entrie.zipcode, entrie.noofemployees, entrie.annualgrossrevenue);
        }
    }

    public class QuestionTab {
        public String naics;
        public String zipcode;
        public String noofemployees;
        public String annualgrossrevenue;

        public QuestionTab(String Naics, String Zipcode, String Noofemployees, String Annualgrossrevenue) {
            naics = Naics;
            zipcode = Zipcode;
            noofemployees = Noofemployees;
            annualgrossrevenue = Annualgrossrevenue;
        }
    }

    @And("^user moves the record from my expiration KPI to my renewals KPI$")
    public void userMovesTheRecordFromMyExpirationKPIMyRenewalsKPI() {
        LoginPage page = new LoginPage(base.driver);
        page.expirationtorenewal();
    }

    @And("^user moves the record from My Renewals KPI to My New Business KPI$")
    public void userMovesTheRecordFromMyRenewalsKPIToMyNewBusinessKPI() {
        LoginPage page = new LoginPage(base.driver);
        page.myrenewalkpitomyewbusinesskpi();
    }

    @And("^user moves the record from My New Business KPI to My Lost Business KPI$")
    public void userMovesTheRecordFromMyNewBusinessKPIToMyLostBusinessKPI() {
        LoginPage page = new LoginPage(base.driver);
        page.mynewvbusinesskpitomylostbusinesskpi();
    }

    @And("^user moves the record from My Lost Business KPI to My Work in Progress KPI$")
    public void userMovesTheRecordFromMyLostBusinessKPIToMyWorkInProgressKPI() {
        LoginPage page = new LoginPage(base.driver);
        page.mylostbusinesskpitomyworkinprogresskpi();
    }

    @And("^user moves the record from My Work in Progress to My YTD Policy Count KPI$")
    public void userMovesTheRecordFromMyWorkInProgressToMyYTDPoluctCountKPI() {
        LoginPage page = new LoginPage(base.driver);
        page.myworkinprogresskpitomyytdpolicycountkpi();
    }

    @And("^user searching for record using global search$")
    public void userSearchingForRecordUsingGlobalSearch() throws Throwable {
        LoginPage page = new LoginPage(base.driver);
        page.globalsearchs();

    }

    @And("^user checking for view yearly summary$")
    public void userCheckingForViewYearlySummaryAndImportPolicies() throws Throwable {
        System.out.println("user checking for view yearly summary and importing policies to the grid");
        LoginPage page = new LoginPage(base.driver);
        page.viewyearlysummaryimportpolicies();
    }

    @And("^user importing policies using import policies functionality$")
    public void userImportingPoliciesUsingImportPoliciesFunctionality() throws Throwable {
        LoginPage page = new LoginPage(base.driver);
        page.importingpolicies();
        Thread.sleep(2000);

        WebElement uploadElement = base.driver.findElement(By.id("excelfile"));
        uploadElement.sendKeys("D:\\kiranchakrapani\\Desktop\\AMA Import Sample_updated.xls");
        Thread.sleep(4000);
        base.driver.findElement(By.id("viewfile")).click();
        Thread.sleep(2000);
        base.driver.findElement(By.id("uploadfile")).click();
        Thread.sleep(6000);
        base.driver.findElement(By.cssSelector("body > div.jconfirm.jconfirm-light.jconfirm-open > div.jconfirm-scrollpane > div > div > div > div > div > div > div > div.jconfirm-buttons > button")).click();
        Thread.sleep(2000);

        String getting_No_Of_Records = base.driver.findElement(By.cssSelector("#dtPolicies_info")).getText();
        System.out.println(",No.of record before import" + getting_No_Of_Records);
    }

    @And("^user checking for polcies tab grid filters and sorting functionality$")
    public void userCheckingForPolciesTabGridFiltersAndSortingFunctionality() throws Throwable {
        LoginPage page = new LoginPage(base.driver);
        page.policiestabgridfiltersandsorting();
    }

    @And("^User checking for pages next and previous buttons$")
    public void userCheckingForPagesNextAndPreviousButtons() throws Throwable {
        LoginPage page = new LoginPage(base.driver);
        page.PreviousAndNextButtonsOfPages();
    }

    @And("^User checking for pagination of the policies tab$")
    public void userCheckingForPaginationOfThePoliciesTab() throws Throwable {
        LoginPage page = new LoginPage(base.driver);
        page.Pagination102050100();
    }

    @And("^User logs out of the application$")
    public void userLogsOutOfTheApplication() throws Throwable {
        LoginPage page = new LoginPage(base.driver);
        page.LogoutOfApplication();
    }

    @And("^user checking for mandatory fields in add policy pop up$")
    public void userCheckingForMandatoryFieldsInAddPolicyPopUp(DataTable table) throws Throwable {
        List<MandatoryFields> manfields = new ArrayList<MandatoryFields>();
        manfields = table.asList(MandatoryFields.class);
        LoginPage page = new LoginPage(base.driver);
        for (MandatoryFields manfield : manfields) {
            page.mandatoryfieldsinaddpolicy(manfield.insuredfield);
        }
    }

    public class MandatoryFields {
        public String insuredfield;

        public MandatoryFields(String Insuredfield) {
            insuredfield = Insuredfield;
        }
    }

    @And("^user checking for numeric fields in add policy pop up$")
    public void userCheckingForNumericFieldsInAddPolicyPopUp(DataTable table) throws Throwable {
        List<NumericFields> numfields = new ArrayList<NumericFields>();
        numfields = table.asList(NumericFields.class);
        LoginPage page = new LoginPage(base.driver);
        for (NumericFields numfield : numfields) {
            page.numericfieldsinaddpolicy(numfield.insuredfield, numfield.expiringpremium, numfield.expcommissionpercentage, numfield.renewalnewpremium, numfield.renewalnewcommissionpercentage);
        }
    }

    public class NumericFields {
        public String insuredfield;
        public String expiringpremium;
        public String expcommissionpercentage;
        public String renewalnewpremium;
        public String renewalnewcommissionpercentage;

        public NumericFields(String Insuredfield, String Expiringpremium, String Expcommissionpercentage, String Renewalnewpremium, String Renewalnewcommissionpercentage) {
            insuredfield = Insuredfield;
            expiringpremium = Expiringpremium;
            expcommissionpercentage = Expcommissionpercentage;
            renewalnewpremium = Renewalnewpremium;
            renewalnewcommissionpercentage = Renewalnewcommissionpercentage;
        }
    }

    @And("^user checking mandatory fields in add note popup for detail page$")
    public void userCheckingMandatoryFieldsInAddNotePopupForDetailPage() {
        LoginPage page = new LoginPage(base.driver);
        page.notestabmandatoryfields();
    }

    @And("^user importing excel sheet with incorrect information$")
    public void userImportingExcelSheetWithIncorrectInformation() throws Throwable {
        LoginPage page = new LoginPage(base.driver);
        page.importingpolcies2();
        Thread.sleep(2000);
    }

    @And("^user checking for deleting a record from the existing records$")
    public void userCheckingForDeletingARecordFromTheExistingRecords() throws Throwable {
        LoginPage page = new LoginPage(base.driver);
        page.DeletingRecord();
        Thread.sleep(1000);
    }

    @And("^user adding policy for checking mandatory fields with submit and open button$")
    public void userAddingPolicyForCheckingMandatoryFieldsWithSubmitAndOpenButton() throws Throwable {
        LoginPage page = new LoginPage(base.driver);
        page.AddPolicySubmitOpenButton();
        Thread.sleep(1000);

    }

    @And("^user checking for cancel button in add policy popup$")
    public void userCheckingForCancelButtonInAddPolicyPopup() throws Throwable {
        LoginPage page = new LoginPage(base.driver);
        page.AddPolicyCancelButton();
        Thread.sleep(1000);
    }

    @And("^user checking for fields are defaulting to blank and select after adding a record$")
    public void userCheckingForFieldsAreDefaultingToBlankAndSelectAfterAddingARecord() throws Throwable {
        LoginPage page = new LoginPage(base.driver);
        page.AddPolicyDefaultValues();
        Thread.sleep(1000);
    }

    @And("^user checking for cancel changes button in detail page$")
    public void userCheckingForCancelChangesButtonInDetailPage() throws Throwable {
        LoginPage page = new LoginPage(base.driver);
        page.editrecordcancelchanges();
        Thread.sleep(1000);
    }

    @And("^user checking for mandatory fields in detail or edit page$")
    public void userCheckingForMandatoryFieldsInDetailOrEditPage(DataTable table) throws Throwable {
        List<DetailpageMandatory> insureds = new ArrayList<DetailpageMandatory>();
        insureds = table.asList(DetailpageMandatory.class);
        LoginPage page = new LoginPage(base.driver);
        for (DetailpageMandatory insured : insureds) {
            page.MandatoryfieldsInDetailPage(insured.detailpageinsured);
        }
    }

    public class DetailpageMandatory {
        public String detailpageinsured;

        public DetailpageMandatory(String Detailpageinsured) {
            detailpageinsured = Detailpageinsured;
        }
    }

    @And("^user checking for submit next button in detail page$")
    public void userCheckingForSubmitNextButtonInDetailPage() throws Throwable{
        LoginPage page = new LoginPage(base.driver);
        page.DetailPageSubmitNextButton();
        Thread.sleep(1000);
    }

    @And("^user checking for mandatory field with submit and close button in detail page$")
    public void userCheckingForMandatoryFieldWithSubmitButtonInDetailPage() throws Throwable{
        LoginPage page = new LoginPage(base.driver);
        page.DetailPageSubmitAndCloseButtonMandate();
        Thread.sleep(1000);
    }

    @And("^user checking for cancel button in add note popup$")
    public void userCheckingForCancelButtonInAddNotePopup() throws Throwable{
        LoginPage page = new LoginPage(base.driver);
        page.AddNotePopUpCancelButton();
        Thread.sleep(1000);
    }
}