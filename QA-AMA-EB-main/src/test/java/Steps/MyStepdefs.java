package Steps;

import Pages.BaseUtil;
import Pages.Loginpage;
import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class MyStepdefs extends BaseUtil {

    public MyStepdefs(BaseUtil base) {
        this.base = base;
    }

    private BaseUtil base;

    @Given("^I navigate to login page of EB app$")
    public void iNavigateToLoginPageOfEBApp() {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("Navigates to DEV Login page\n");
        base.driver.get("https://dev.patracorp.net");
        base.driver.manage().window().maximize();
    }

    @And("^I enter login credentials$")
    public void iEnterLoginCredentials(DataTable table) {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("Entering email address and password\n");
        //create arraylst
        List<SignIn> users = new ArrayList<SignIn>();
        users = table.asList(SignIn.class);
        Loginpage page = new Loginpage(base.driver);
        for (SignIn user : users) {
            page.Login(user.email, user.password);
        }

    }

    public class SignIn {
        public String email;
        public String password;

        public SignIn(String Email, String Password) {
            email = Email;
            password = Password;
        }
    }

    @And("^I click on Sign In button$")
    public void iClickOnSignInButton() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("Clicks Sign In button\n");
        Loginpage page = new Loginpage(base.driver);
        page.SignInButton();
        Thread.sleep(10000);
        page.EBLink();
        Thread.sleep(10000);
        String parentWindow = base.driver.getWindowHandle();
        Set<String> handles = base.driver.getWindowHandles();
        for (String windowHandle : handles) {
            if (!windowHandle.equals(parentWindow)) {
                base.driver.switchTo().window(windowHandle);
            }
        }
        Thread.sleep(20000);
    }

    @And("^Pagination value")
    public void paginationValue(DataTable table) throws Throwable {
        // Write code here that turns the phrase above into concrete actions

        List<pagin> pages = new ArrayList<pagin>();
        pages = table.asList(pagin.class);
        Loginpage page = new Loginpage(base.driver);
        for (pagin page1 : pages) {
            page.pagin(page1.pagevalue);
        }
        Thread.sleep(5000);
        page.pagin1();

    }

    public class pagin {
        public String pagevalue;

        public pagin(String Pagevalue) {
            pagevalue = Pagevalue;
        }
    }

    @And("^Get the KPI Count$")
    public void getTheKpiCOunt() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        Thread.sleep(5000);
        Loginpage page = new Loginpage(base.driver);
        page.KPIValue();
    }

    @When("^Click on Add Policy$")
    public void clickOnAddPolicy() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("clicks Add Policy\n");
        Loginpage page = new Loginpage(base.driver);
        Thread.sleep(5000);
        page.clickAddPolicy();
        Thread.sleep(5000);
    }

    @Then("^Enter Add Policy Details$")
    public void enterAddPolicyDetails(DataTable table) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("Entering Add Policy Details\n");
        List<addPolicy> users = new ArrayList<addPolicy>();
        users = table.asList(addPolicy.class);
        Loginpage page = new Loginpage(base.driver);
        Thread.sleep(5000);
        for (addPolicy user : users) {
            page.addPolicyButton(user.accountManager, user.company, user.status, user.businessType, user.newExisting, user.date, user.employerGroup, user.coverageType, user.priorCarrier,user.priorPlanName,
                    user.expiringPremium, user.expiringCommission, user.renewalNewCarrier,user.renewalNewPlanName, user.renewalNewPremium, user.renewalNewCommission, user.leadSource);

        }
        Thread.sleep(15000);

    }

    public class addPolicy {

        public String accountManager, company, status, businessType, newExisting, date, employerGroup, coverageType, priorCarrier, priorPlanName, expiringPremium, expiringCommission, renewalNewCarrier, renewalNewPlanName,
                renewalNewPremium, renewalNewCommission, leadSource;


        public addPolicy(String AccountManager, String Company, String Status, String BusinessType, String NewExisting, String Date, String EmployerGroup, String CoverageType,
                         String PriorCarrier, String PriorPlanName, String ExpiringPremium, String ExpiringCommission, String RenewalNewCarrier, String RenewalNewPlanName,String RenewalNewPremium, String RenewalNewCommission, String LeadSource) {
            this.accountManager = AccountManager;
            this.company = Company;
            this.status = Status;
            this.businessType = BusinessType;
            this.newExisting = NewExisting;
            this.date = Date;
            this.employerGroup = EmployerGroup;
            this.coverageType = CoverageType;
            this.priorCarrier = PriorCarrier;
            this.priorPlanName = PriorPlanName;
            this.expiringPremium = ExpiringPremium;
            this.expiringCommission = ExpiringCommission;
            this.renewalNewCarrier = RenewalNewCarrier;
            this.renewalNewPlanName = RenewalNewPlanName;
            this.renewalNewPremium = RenewalNewPremium;
            this.renewalNewCommission = RenewalNewCommission;
            this.leadSource = LeadSource;
        }
    }

    @Then("^Navigate to Policy Details section$")
    public void navigateToPolicyDetailsSection(DataTable table) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("\nEntering Policy details section\n");
        List<policyDetail> users = new ArrayList<policyDetail>();
        users = table.asList(policyDetail.class);
        Loginpage page = new Loginpage(base.driver);
        Thread.sleep(5000);
        for (policyDetail user : users) {
            page.policyDetailsSection(user.accountManagerDetails, user.companyDetails, user.statusDetails, user.businessTypeDetails, user.newExistingDetails, user.dateDetails, user.employerGroupDetails, user.coverageTypeDetails, user.priorCarrierDetails,
                    user.expiringPremiumDetails, user.expiringCommissionDetails, user.renewalNewCarrierDetails, user.renewalNewPremiumDetails, user.renewalNewCommissionDetails, user.leadSourceDetails);

        }
        Thread.sleep(5000);

    }

    public class policyDetail {

        public String accountManagerDetails, companyDetails, statusDetails, businessTypeDetails, newExistingDetails, dateDetails, employerGroupDetails, coverageTypeDetails, priorCarrierDetails,
                expiringPremiumDetails, expiringCommissionDetails, renewalNewCarrierDetails, renewalNewPremiumDetails, renewalNewCommissionDetails, leadSourceDetails;


        public policyDetail(String AccountManagerDetails, String CompanyDetails, String StatusDetails, String BusinessTypeDetails, String NewExistingDetails, String DateDetails, String EmployerGroupDetails, String CoverageTypeDetails,
                            String PriorCarrierDetails, String ExpiringPremiumDetails, String ExpiringCommissionDetails, String RenewalNewCarrierDetails, String RenewalNewPremiumDetails, String RenewalNewCommissionDetails, String LeadSourceDetails) {
            this.accountManagerDetails = AccountManagerDetails;
            this.companyDetails = CompanyDetails;
            this.statusDetails = StatusDetails;
            this.businessTypeDetails = BusinessTypeDetails;
            this.newExistingDetails = NewExistingDetails;
            this.dateDetails = DateDetails;
            this.employerGroupDetails = EmployerGroupDetails;
            this.coverageTypeDetails = CoverageTypeDetails;
            this.priorCarrierDetails = PriorCarrierDetails;
            this.expiringPremiumDetails = ExpiringPremiumDetails;
            this.expiringCommissionDetails = ExpiringCommissionDetails;
            this.renewalNewCarrierDetails = RenewalNewCarrierDetails;
            this.renewalNewPremiumDetails = RenewalNewPremiumDetails;
            this.renewalNewCommissionDetails = RenewalNewCommissionDetails;
            this.leadSourceDetails = LeadSourceDetails;
        }
    }

    @Then("^Search with all the filters in the grid$")
    public void searchWithAllTheFiltersInTheGrid(DataTable table) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("\nSearching with all the filters in the grid\n");
        List<policyGrid> users = new ArrayList<policyGrid>();
        users = table.asList(policyGrid.class);
        Loginpage page = new Loginpage(base.driver);
        Thread.sleep(5000);
        for (policyGrid user : users) {
            page.policyGridSection(user.accountManagerDetails, user.companyDetails, user.statusDetails, user.businessTypeDetails, user.newExistingDetails, user.dateDetails, user.employerGroupDetails, user.coverageTypeDetails, user.priorCarrierDetails,
                    user.expiringPremiumDetails, user.expiringCommissionDetails, user.renewalNewCarrierDetails, user.renewalNewPremiumDetails, user.renewalNewCommissionDetails, user.leadSourceDetails);

        }
        Thread.sleep(5000);

    }

    public class policyGrid {

        public String accountManagerDetails, companyDetails, statusDetails, businessTypeDetails, newExistingDetails, dateDetails, employerGroupDetails, coverageTypeDetails, priorCarrierDetails,
                expiringPremiumDetails, expiringCommissionDetails, renewalNewCarrierDetails, renewalNewPremiumDetails, renewalNewCommissionDetails, leadSourceDetails;


        public policyGrid(String AccountManagerDetails, String CompanyDetails, String StatusDetails, String BusinessTypeDetails, String NewExistingDetails, String DateDetails, String EmployerGroupDetails, String CoverageTypeDetails,
                            String PriorCarrierDetails, String ExpiringPremiumDetails, String ExpiringCommissionDetails, String RenewalNewCarrierDetails, String RenewalNewPremiumDetails, String RenewalNewCommissionDetails, String LeadSourceDetails) {
            this.accountManagerDetails = AccountManagerDetails;
            this.companyDetails = CompanyDetails;
            this.statusDetails = StatusDetails;
            this.businessTypeDetails = BusinessTypeDetails;
            this.newExistingDetails = NewExistingDetails;
            this.dateDetails = DateDetails;
            this.employerGroupDetails = EmployerGroupDetails;
            this.coverageTypeDetails = CoverageTypeDetails;
            this.priorCarrierDetails = PriorCarrierDetails;
            this.expiringPremiumDetails = ExpiringPremiumDetails;
            this.expiringCommissionDetails = ExpiringCommissionDetails;
            this.renewalNewCarrierDetails = RenewalNewCarrierDetails;
            this.renewalNewPremiumDetails = RenewalNewPremiumDetails;
            this.renewalNewCommissionDetails = RenewalNewCommissionDetails;
            this.leadSourceDetails = LeadSourceDetails;
        }
    }

    @Then("^Click on Add Note Button$")
    public void clickOnAddNoteButton() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("clicks Add Note Button\n");
        Loginpage page = new Loginpage(base.driver);
        Thread.sleep(5000);
        page.clickAddNote();
        Thread.sleep(5000);
    }
    @Then("^Input values in the Note pop-up$")
    public void inputValuesInTheNotePopUp(DataTable table) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("Entering details in the Note Pop-up\n");
        List<noteDetails> users1 = new ArrayList<noteDetails>();
        users1 = table.asList(noteDetails.class);
        Loginpage page = new Loginpage(base.driver);
        Thread.sleep(5000);
        for (noteDetails user1 : users1) {
            page.noteDetails(user1.title, user1.description);
        }
        Thread.sleep(5000);
    }
    public class noteDetails {

        public String title,description;

        public noteDetails(String Title, String Description)
        {
            this.title = Title;
            this.description = Description;
        }
    }
    @Then("^Input values in the Questions tab$")
    public void inputValuesInTheQuestionsTab(DataTable table) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("Entering details in the Questions tab\n");
        List<questionDetails> users1 = new ArrayList<questionDetails>();
        users1 = table.asList(questionDetails.class);
        Loginpage page = new Loginpage(base.driver);
        Thread.sleep(5000);
        for (questionDetails user1 : users1) {
            page.questionDetails1(user1.question1, user1.question2,user1.question3,user1.question4);
        }
        Thread.sleep(5000);
    }
    public class questionDetails {

        public String question1,question2,question3,question4;

        public questionDetails(String Question1, String Question2, String Question3, String Question4)
        {
            this.question1 = Question1;
            this.question2 = Question2;
            this.question3 = Question3;
            this.question4 = Question4;
        }
    }
    @Then("^Click on Notes Tab$")
    public void clickOnNotesTab() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("clicks on Notes Tab\n");
        Loginpage page = new Loginpage(base.driver);
        Thread.sleep(5000);
        page.clickNotesTab();
        Thread.sleep(5000);
    }
    @And("^Click on History tab$")
    public void clickOnHistoryTab() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("clicks on History Tab\n");
        Loginpage page = new Loginpage(base.driver);
        Thread.sleep(5000);
        page.clickHistoryTab();
        Thread.sleep(5000);
    }
    @Then("^Click on Submit button$")
    public void clickOnSubmitButton() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("Click On Submit\n");
        Thread.sleep(7000);
        Loginpage page = new Loginpage(base.driver);
        page.addSubmit();
        Thread.sleep(7000);
    }
    @Then("Click on Submit and Close button$")
    public void clickOnSubmitAndCloseButton() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        Thread.sleep(5000);
        Loginpage page = new Loginpage(base.driver);
        page.submitCloseButton();
        Thread.sleep(5000);

    }
    @Then("Get the My Prospects KPI Count$")
    public void getTheMyProspectsKPICount() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        Thread.sleep(5000);
        Loginpage page = new Loginpage(base.driver);
        page.prospectsKPICount();
        Thread.sleep(5000);
    }
    @Then("Get the My Renewals KPI Count$")
    public void getTheMyRenewalsKPICount() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        Thread.sleep(5000);
        Loginpage page = new Loginpage(base.driver);
        page.renewalsKPICount();
        Thread.sleep(5000);
    }
    @Then("Get the My New Business KPI Count$")
    public void getTheMyNewBusinessKPICount() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        Thread.sleep(5000);
        Loginpage page = new Loginpage(base.driver);
        page.newBusinessKPICount();
        Thread.sleep(5000);
    }

    @Then("Get the My Lost Business KPI Count$")
    public void getTheMyLostBusinessKPICount() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        Thread.sleep(5000);
        Loginpage page = new Loginpage(base.driver);
        page.lostBusinessKPICount();
        Thread.sleep(5000);
    }

    @Then("Get the Work In Progress Business KPI Count$")
    public void getTheWorkInProgressKPICount() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        Thread.sleep(5000);
        Loginpage page = new Loginpage(base.driver);
        page.workInProgressKPICount();
        Thread.sleep(5000);
    }

    @Then("^I click on Home button$")
    public void iClickOnHomeButton() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        Thread.sleep(5000);
        Loginpage page = new Loginpage(base.driver);
        page.homeButton();
    }

    @And("^Click on reset button$")
    public void clickOnResetButton() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        Thread.sleep(5000);
        Loginpage page = new Loginpage(base.driver);
        page.resetButton();
    }

    @And("^I search a record with the Employer Group$")
    public void iSearchARecordWithTheEmployerGroup(DataTable table) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("\nSearches record with the Employer Group\n");
        List<RecordIDSearch> recordIDSearches = new ArrayList<RecordIDSearch>();
        recordIDSearches = table.asList(RecordIDSearch.class);
        Loginpage page = new Loginpage(base.driver);
        Thread.sleep(5000);
        for (RecordIDSearch recordIDSearch : recordIDSearches) {
            page.RecordSearch(recordIDSearch.employergroupsearch);
        }
        Thread.sleep(5000);
    }
    public class RecordIDSearch {
        public String employergroupsearch;

        public RecordIDSearch(String EmployerGroupSearch)
        {
            employergroupsearch = EmployerGroupSearch;
        }
    }
    @And("^I select a record under Policies tab$")
    public void iSelectARecordUnderPoliciesTab() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("Select a record under Policies tab\n");
        Loginpage page = new Loginpage(base.driver);
        page.SelRecord();
        Thread.sleep(2000);
    }

    @And("^I move the record to Renewal$")
    public void iMoveTheRecordToRenewal(DataTable table) throws Throwable
    {
        System.out.println("Moving Record to renewal\n");
        List<moveRecord> users2 = new ArrayList<moveRecord>();
        users2 = table.asList(moveRecord.class);
        Loginpage page = new Loginpage(base.driver);
        Thread.sleep(2000);
        for (moveRecord user2 : users2) {
            page.moveRecordToRenewal(user2.movRec);
        }
        Thread.sleep(2000);
    }
    public class moveRecord {

        public String movRec;

        public moveRecord(String MovRec)
        {
            this.movRec = MovRec;
        }
    }

    @And("^I move the record to New Business$")
    public void iMoveTheRecordToNewBusiness(DataTable table) throws Throwable
    {
        System.out.println("Moving Record to New Business\n");
        List<moveRecord1> users2 = new ArrayList<moveRecord1>();
        users2 = table.asList(moveRecord1.class);
        Loginpage page = new Loginpage(base.driver);
        Thread.sleep(2000);
        for (moveRecord1 user2 : users2) {
            page.moveRecordToNewBusiness(user2.movRec1);
        }
        Thread.sleep(2000);
    }
    public class moveRecord1 {

        public String movRec1;

        public moveRecord1(String MovRec1)
        {
            this.movRec1 = MovRec1;
        }
    }
    @And("^Click on My Prospects KPI$")
    public void clickOnMyProspectsKPI() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        Loginpage page = new Loginpage(base.driver);
        Thread.sleep(4000);
        page.prospectsKPI();
        Thread.sleep(4000);
    }

    @And("^Click on My Renewals KPI$")
    public void clickOnMyRenewalsKPI() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("Click on My Renewals KPI\n");
        Loginpage page = new Loginpage(base.driver);
        Thread.sleep(2000);
        page.myRenewalsKPI();
        Thread.sleep(2000);
    }
    @And("^Click On My New Business KPI$")
    public void clickOnMyNewBusinessKPI() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("Click On My new Business KPI\n");
        Loginpage page = new Loginpage(base.driver);
        Thread.sleep(2000);
        page.myNewBusinessKPI();
        Thread.sleep(2000);
    }
    @And("^Move the record to Lost Business$")
    public void moveTheRecordToLostBusiness(DataTable table) throws Throwable
    {
        System.out.println("Moving Record to Lost Business\n");
        List<moveRecord2> users2 = new ArrayList<moveRecord2>();
        users2 = table.asList(moveRecord2.class);
        Loginpage page = new Loginpage(base.driver);
        Thread.sleep(2000);
        for (moveRecord2 user2 : users2) {
            page.moveRecordToLostBusiness(user2.movRec2);
        }
        Thread.sleep(2000);
    }
    public class moveRecord2 {

        public String movRec2;

        public moveRecord2(String MovRec2)
        {
            this.movRec2 = MovRec2;
        }
    }
    @And("^Click On My Lost Business KPI$")
    public void clickOnMyLostBusinessKPI() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("Click On My Lost Business KPI\n");
        Loginpage page = new Loginpage(base.driver);
        Thread.sleep(2000);
        page.myLostBusinessKPI();
    }
    @And("^Move the record to Work in Progress$")
    public void moveTheRecordToWorkInProgress(DataTable table) throws Throwable
    {
        System.out.println("Moving Record to Work in Progress\n");
        List<moveRecord3> users2 = new ArrayList<moveRecord3>();
        users2 = table.asList(moveRecord3.class);
        Loginpage page = new Loginpage(base.driver);
        Thread.sleep(2000);
        for (moveRecord3 user2 : users2) {
            page.moveRecordToWorkInProgress(user2.movRec3);
        }
        Thread.sleep(2000);
    }
    public class moveRecord3
    {
        public String movRec3;
        public moveRecord3(String MovRec3)
        {
            this.movRec3 = MovRec3;
        }
    }
    @And("^Move the record to YTD$")
    public void moveTheRecordToYTD(DataTable table) throws Throwable
    {
        System.out.println("Moving Record to YTD\n");
        List<moveRecord4> users2 = new ArrayList<moveRecord4>();
        users2 = table.asList(moveRecord4.class);
        Loginpage page = new Loginpage(base.driver);
        Thread.sleep(2000);
        for (moveRecord4 user2 : users2) {
            page.moveRecordToYTD(user2.movRec4);
        }
        Thread.sleep(2000);
    }
    public class moveRecord4 {

        public String movRec4;

        public moveRecord4(String MovRec4)
        {
            this.movRec4 = MovRec4;
        }
    }
    @And("^Click on YTD KPI$")
    public void clickOnYTDKPI() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("Click on YTD KPI\n");
        Loginpage page = new Loginpage(base.driver);
        Thread.sleep(2000);
        page.ytdKPI();
        Thread.sleep(2000);
    }
    @And("^user searches with the following global value$")
    public void userSearchesWithTheFollowingGlobalValue(DataTable Table) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("Global Search: record id\n");
        List<GlobalSearch> keywords = new ArrayList<GlobalSearch>();
        keywords = Table.asList(GlobalSearch.class);
        Loginpage page = new Loginpage(base.driver);
        for (GlobalSearch keyword : keywords) {
            page.GlobalSearch(keyword.globalsearch);
        }
        Thread.sleep(4000);
    }
    public class GlobalSearch {
        public String globalsearch;

        public GlobalSearch(String Globalsearch) {
            globalsearch = Globalsearch;
        }
    }

    @And("^Click on Delete button$")
    public void clickOnDeleteButton() throws Throwable {
        System.out.println("Click on Delete Button\n");
        Loginpage page = new Loginpage(base.driver);
        Thread.sleep(2000);
        page.delete();
    }
    @When("^Click on Import Policies button$")
    public void clickOnImportPoliciesButton() throws Throwable {
        System.out.println("Click on import policies button\n");
        Loginpage page = new Loginpage(base.driver);
        page.Importpolicies();
        Thread.sleep(5000);

        WebElement uploadElement = base.driver.findElement(By.id("excelfile"));
        uploadElement.sendKeys("C:\\Renuka\\AMA Import Sample_new1.xls");
    }
    @And("^Click on Import Submit button$")
    public void clickOnImportSubmitButton() throws Throwable {
        System.out.println("Click Import submit button\n");
        Loginpage page = new Loginpage(base.driver);
        page.importsubmit();
        Thread.sleep(2000);
    }
    @And("^Click alert OK button$")
    public void clickAlertOKButton() throws Throwable {
        System.out.println("Click alert OK button\n");
        Loginpage page = new Loginpage(base.driver);
        page.uploadBtn();
        Thread.sleep(5000);
        page.alertok();
        Thread.sleep(5000);
    }
    @And("^Click on reports button$")
    public void clickOnReportsButton() throws Throwable {
        System.out.println("Click on Reports button\n");
        Loginpage page = new Loginpage(base.driver);
        Thread.sleep(3000);
        page.ReportsButton();
        Thread.sleep(6000);
        String currentWindow = base.driver.getWindowHandle();
        base.driver.switchTo().window(currentWindow);
    }
    @When("^Click on patra corp link$")
    public void clickOnPatraCorpLink() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("Clicks Patracorp logo\n");
        Loginpage page = new Loginpage(base.driver);
        Thread.sleep(4000);
        page.logo();
    }
    @Then("^Click on Logout icon$")
    public void clickOnLogoutIcon() {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("Clicks Logout icon\n");
        Loginpage page = new Loginpage(base.driver);
        page.clicklogouticon();
    }
    @And("^Click Logout button$")
    public void clickLogoutButton() throws Throwable {
        System.out.println("Clicks Logout button\n");
        Loginpage page = new Loginpage(base.driver);
        Thread.sleep(2000);
        page.logout();
    }
    @Then("Take Screenshot")
    public void takeScreenshot() throws Throwable
    {
        File scrFile = ((TakesScreenshot)base.driver).getScreenshotAs(OutputType.FILE);
        try
        {
            FileUtils.copyFile(scrFile, new File("C:/selenium/"+System.currentTimeMillis()+".png"));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}