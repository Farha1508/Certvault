package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.List;


public class Loginpage  {

    WebDriver driver;
    public Loginpage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //Login Page
    @FindBy(how = How.ID, using = "email")
    public WebElement txtEmail;
    @FindBy(how = How.ID, using = "password")
    public WebElement txtPassword;
    @FindBy(how = How.ID, using = "submit")
    public WebElement btnSignIn;

    public void Login(String Email, String Password) {
        txtEmail.sendKeys(Email);
        txtPassword.sendKeys(Password);
        highLightElement(driver,txtEmail);
        highLightElement(driver,txtPassword);
    }

    //CLick on Sign-in Button
    public void SignInButton() {
        btnSignIn.click();
    }

    @FindBy(how = How.XPATH, using = "//*[contains(text(),'AMA-EB')]")
    public WebElement link;

    public void EBLink() {
        link.click();
    }

    /*   ------Size of table and pagination*/
    @FindBy(how = How.XPATH, using = "//*[@id=\"dtPolicies\"]")
    public WebElement pagination;
    @FindBy(how = How.XPATH, using = "//*[@id=\"dtPolicies_length\"]/label/select")
    public WebElement pValue;

    public void pagin(String Pagevalue) {
        pValue.sendKeys(Pagevalue);
    }

    public void pagin1() {
        List<WebElement> TotalRowsList = pagination.findElements(By.tagName("tr"));
        System.out.println("Total no of rows in table:" + (TotalRowsList.size() - 1));
    }

    /*   ------Get Values for KPI*/
    @FindBy(how = How.XPATH, using = "//*[@id=\"kpi1Count\"]")
    public WebElement MyProspectsKpiValue;
    @FindBy(how = How.XPATH, using = "//*[@id=\"kpi2Count\"]")
    public WebElement MyRenewalsKpiValue;
    @FindBy(how = How.XPATH, using = "//*[@id=\"kpi3Count\"]")
    public WebElement MyNewBusinessKpiValue;
    @FindBy(how = How.XPATH, using = "//*[@id=\"kpi4Count\"]")
    public WebElement MyLostBusinessKpiValue;
    @FindBy(how = How.XPATH, using = "//*[@id=\"kpi5Count\"]")
    public WebElement MyWorkInProgressKpiValue;
    @FindBy(how = How.XPATH, using = "//*[@id=\"kpi6Count\"]")
    public WebElement MyPremiumKpiValue;
    @FindBy(how = How.XPATH, using = "//*[@id=\"kpi7Count\"]")
    public WebElement MyRevenueKpiValue;
    @FindBy(how = How.XPATH, using = "//*[@id=\"kpi7Count\"]")
    public WebElement MyPolicyCountKpiValue;


    public void KPIValue() {
        String MyProspectsCount = MyProspectsKpiValue.getText();
        System.out.println("My Prospects KPI Count is :" + MyProspectsCount);
        String MyRenewalsCount = MyRenewalsKpiValue.getText();
        System.out.println("My Renewals KPI Count is :" + MyRenewalsCount);
        String MyNewBusinessCount = MyNewBusinessKpiValue.getText();
        System.out.println("My New Business KPI Count is :" + MyNewBusinessCount);
        String MyLostBusinessCount = MyLostBusinessKpiValue.getText();
        System.out.println("My Lost Business KPI Count is :" + MyLostBusinessCount);
        String MyWorkInProgressCount = MyWorkInProgressKpiValue.getText();
        System.out.println("My Work In Progress KPI Count is :" + MyWorkInProgressCount);
        String MyPremiumCount = MyPremiumKpiValue.getText();
        System.out.println("My Premium KPI Total Count is :" + MyPremiumCount);
        String MyRevenueCount = MyRevenueKpiValue.getText();
        System.out.println("My Revenue KPI Total Count is :\n" + MyRevenueCount);
        String MyPolicyCount = MyPolicyCountKpiValue.getText();
        System.out.println("My Policy KPI Total Count is :\n" + MyPolicyCount);
    }

    //Click on Add Policy Button---------------------
    @FindBy(how = How.ID, using = "Policy-addPolicy")   //Add policy
    public WebElement clickAddPolicyBtn;

    public void clickAddPolicy() {
        clickAddPolicyBtn.click();
        highLightElement(driver,clickAddPolicyBtn);
    }

    @FindBy(how = How.XPATH, using = "//*[@id=\"typeaheadValue\"]")
    public WebElement txtGlobalSearch;
    @FindBy(how = How.XPATH, using = " //*[@id=\"the-basics-new\"]/span/div/div/p/a")
    public WebElement txtGlobalSearch1;

    public void GlobalSearch(String GlobalSearch) throws Throwable {
          txtGlobalSearch.sendKeys(GlobalSearch);
          Thread.sleep(5000);
          txtGlobalSearch1.click();
    }

    //Initializing elements in Add Policy--------------------------------------------

    @FindBy(how = How.XPATH, using = "//*[@id=\"policy-acc-manager\"]")
    public WebElement addAccountManager;
    @FindBy(how = How.XPATH, using = "//*[@id=\"policy-company\"]")
    public WebElement addCompany;
    @FindBy(how = How.XPATH, using = "//*[@id=\"policy-status\"]")
    public WebElement addStatus;
    @FindBy(how = How.XPATH, using = "//*[@id=\"business-type-form\"]")
    public WebElement addBusinessType;
    @FindBy(how = How.XPATH, using = "//*[@id=\"isNew\"]")
    public WebElement addNewExisting;
    @FindBy(how = How.XPATH, using = "//*[@id=\"policy-add-date\"]") //*[@id="policy-add-date"]
    public WebElement addDate;
    @FindBy(how = How.XPATH, using = "//*[@id=\"policy-insured\"]")
    public WebElement addEmployerGroup;
    @FindBy(how = How.XPATH, using = "//*[@id=\"policy-coverage-type\"]")
    public WebElement addCoverageType;
    @FindBy(how = How.XPATH, using = "//*[@id=\"policy-prior-carrier\"]")
    public WebElement addPriorCarrier;
    @FindBy(how = How.XPATH, using = "//*[@id=\"PriorPlanName\"]")
    public WebElement addPriorPlanName;
    @FindBy(how = How.XPATH, using = "//*[@id=\"policy-expiring-premium\"]")
    public WebElement addExpiringPremium;
    @FindBy(how = How.XPATH, using = "//*[@id=\"policy-commission\"]")
    public WebElement addExpiringCommission;
    @FindBy(how = How.XPATH, using = "//*[@id=\"policy-renewal-carrier\"]")
    public WebElement addRenewalCarrier;
    @FindBy(how = How.XPATH, using = "//*[@id=\"RenewalNewPlanName\"]")
    public WebElement addRenewalNewPlanName;
    @FindBy(how = How.XPATH, using = "//*[@id=\"policy-renewal-premium\"]")
    public WebElement addRenewalPremium;
    @FindBy(how = How.XPATH, using = "//*[@id=\"policy-renewal-commission\"]")
    public WebElement addRenewalCommission;
    @FindBy(how = How.XPATH, using = "//*[@id=\"policy_lead_source\"]")
    public WebElement addLeadSource;
    @FindBy(how = How.XPATH, using = "//*[@id=\"policy-lead-source\"]")
    public WebElement addLeadSource1;

    public void addPolicyButton(String accountManager, String company, String status, String businessType, String newExisting, String date, String employerGroup, String coverageType, String priorCarrier, String priorPlanName,
                                String expiringPremium, String expiringCommission, String renewalNewCarrier,String renewalNewPlanName, String renewalNewPremium, String renewalNewCommission, String leadSource) {
            addAccountManager.sendKeys(accountManager);
            addCompany.sendKeys(company);
            addStatus.sendKeys(status);
            addBusinessType.sendKeys(businessType);
            addNewExisting.sendKeys(newExisting);
            addDate.sendKeys(date);
            addEmployerGroup.sendKeys(employerGroup);
            addCoverageType.sendKeys(coverageType);
            addPriorCarrier.sendKeys(priorCarrier);
            addPriorPlanName.sendKeys(priorPlanName);
            addExpiringPremium.sendKeys(expiringPremium);
            addExpiringCommission.sendKeys(expiringCommission);
            addRenewalCarrier.sendKeys(renewalNewCarrier);
            addRenewalNewPlanName.sendKeys(renewalNewPlanName);
            addRenewalPremium.sendKeys(renewalNewPremium);
            addRenewalCommission.sendKeys(renewalNewCommission);
            addLeadSource.sendKeys(leadSource);

    }

    public void policyDetailsSection(String accountManagerDetails, String companyDetails, String statusDetails, String businessTypeDetails, String newExistingDetails, String dateDetails, String employerGroupDetails, String coverageTypeDetails, String priorCarrierDetails,
                                     String expiringPremiumDetails, String expiringCommissionDetails, String renewalNewCarrierDetails, String renewalNewPremiumDetails, String renewalNewCommissionDetails, String leadSourceDetails) {
            addAccountManager.sendKeys(accountManagerDetails);
            addCompany.sendKeys(companyDetails);
            addStatus.sendKeys(statusDetails);
            addBusinessType.sendKeys(businessTypeDetails);
            addNewExisting.sendKeys(newExistingDetails);
            //addDate.sendKeys(dateDetails);
            addEmployerGroup.sendKeys(employerGroupDetails);
            addCoverageType.sendKeys(coverageTypeDetails);
            addPriorCarrier.sendKeys(priorCarrierDetails);
            addExpiringPremium.sendKeys(expiringPremiumDetails);
            addExpiringCommission.sendKeys(expiringCommissionDetails);
            addRenewalCarrier.sendKeys(renewalNewCarrierDetails);
            addRenewalPremium.sendKeys(renewalNewPremiumDetails);
            addRenewalCommission.sendKeys(renewalNewCommissionDetails);
            addLeadSource1.sendKeys(leadSourceDetails);
    }

    //Grid Filters

    @FindBy(how = How.XPATH, using = "//*[@id=\"th-AccountManagerAccounts\"]/span/div/button/span")
    public WebElement gridAM;
    @FindBy(how = How.XPATH, using = "//*[@id=\"th-AccountManagerAccounts\"]/span/div/ul/li[1]/div/span[2]/button")
    public WebElement gridAMClear;
    @FindBy(how = How.XPATH, using = "//*[@id=\"th-AccountManagerAccounts\"]/span/div/ul/li[1]/div/input\n")
    public WebElement gridAMInput;
    @FindBy(how = How.XPATH, using = "//*[@id=\"th-AccountManagerAccounts\"]/span/div/ul/li[6]/a/label")
    public WebElement gridAMClick;


    @FindBy(how = How.XPATH, using = "//*[@id=\"th-EB AgencyAccounts\"]/span/div/button/span")
    public WebElement gridEB;
    @FindBy(how = How.XPATH, using = "//*[@id=\"th-EB AgencyAccounts\"]/span/div/ul/li[1]/div/input")
    public WebElement gridEBInput;
    @FindBy(how = How.XPATH, using = "//*[@id=\"th-EB AgencyAccounts\"]/span/div/ul/li[3]/a/label")
    public WebElement gridEBClick;


    @FindBy(how = How.XPATH, using = "//*[@id=\"th-ManagedPolicyBusinessTypeAccounts\"]/span/div/button/span")
    public WebElement gridBusinessType;
    @FindBy(how = How.XPATH, using = "//*[@id=\"th-ManagedPolicyBusinessTypeAccounts\"]/span/div/ul/li[1]/div/input")
    public WebElement gridBusinessTypeInput;
    @FindBy(how = How.XPATH, using = "//*[@id=\"th-ManagedPolicyBusinessTypeAccounts\"]/span/div/ul/li[4]/a/label")
    public WebElement gridBusinessTypeClick;

    @FindBy(how = How.XPATH, using = "//*[@id=\"th-ManagedPolicyEBStatusAccounts\"]/span/div/button/span")
    public WebElement gridStatus;
    @FindBy(how = How.XPATH, using = "//*[@id=\"th-ManagedPolicyEBStatusAccounts\"]/span/div/ul/li[1]/div/input")
    public WebElement gridStatusInput;
    @FindBy(how = How.XPATH, using = "//*[@id=\"th-ManagedPolicyEBStatusAccounts\"]/span/div/ul/li[4]/a/label")
    public WebElement gridStatusClick;

    @FindBy(how = How.XPATH, using = "//*[@id=\"Employer Group\"]")
    public WebElement gridEmployerGroup;

    @FindBy(how = How.XPATH, using = "//*[@id=\"th-CoverageTypeAccounts\"]/span/div/button/span")
    public WebElement gridCoverageType;
    @FindBy(how = How.XPATH, using = "//*[@id=\"th-CoverageTypeAccounts\"]/span/div/ul/li[1]/div/input")
    public WebElement gridCoverageTypeInput;
    @FindBy(how = How.XPATH, using = "//*[@id=\"th-CoverageTypeAccounts\"]/span/div/ul/li[5]/a/label")
    public WebElement gridCoverageTypeClick;

    @FindBy(how = How.XPATH, using = "//*[@id=\"Exp Prem\"]")
    public WebElement gridExpPrem;

    @FindBy(how = How.XPATH, using = "//*[@id=\"Exp Comm\"]")
    public WebElement gridExpComm;

    @FindBy(how = How.XPATH, using = "//*[@id=\"th-PriorCarrierAccounts\"]/span/div/button/span")
    public WebElement gridCarrier;
    @FindBy(how = How.XPATH, using = "//*[@id=\"th-PriorCarrierAccounts\"]/span/div/ul/li[1]/div/input")
    public WebElement gridCarrierInput;
    @FindBy(how = How.XPATH, using = "//*[@id=\"th-PriorCarrierAccounts\"]/span/div/ul/li[3]/a/label")
    public WebElement gridCarrierClick;
    @FindBy(how = How.XPATH, using = "//*[@id=\"dtPolicies_wrapper\"]/div[1]/div[1]")
    public WebElement grid;


    public void policyGridSection(String accountManagerDetails, String companyDetails, String statusDetails, String businessTypeDetails, String newExistingDetails, String dateDetails, String employerGroupDetails, String coverageTypeDetails, String priorCarrierDetails,
                                     String expiringPremiumDetails, String expiringCommissionDetails, String renewalNewCarrierDetails, String renewalNewPremiumDetails, String renewalNewCommissionDetails, String leadSourceDetails) {
            gridAM.click();
            gridAMClear.click();
            gridAMInput.sendKeys(accountManagerDetails);
            gridAMClick.click();
            gridEB.click();
            gridEBInput.sendKeys(companyDetails);
            gridEBClick.click();
            gridStatus.click();
            gridStatusInput.sendKeys(statusDetails);
            gridStatusClick.click();
            gridBusinessType.click();
            gridBusinessTypeInput.sendKeys(businessTypeDetails);
            gridBusinessTypeClick.click();
            gridEmployerGroup.sendKeys(employerGroupDetails);
            gridCoverageType.click();
            gridCoverageTypeInput.sendKeys(coverageTypeDetails);
            gridCoverageTypeClick.click();
            gridExpPrem.sendKeys(expiringPremiumDetails);
            gridExpComm.sendKeys(expiringCommissionDetails);
            gridCarrier.click();
            gridCarrierInput.sendKeys(priorCarrierDetails);
            gridCarrierClick.click();
            grid.click();
    }

    //Entering details into WO Entry section

    @FindBy(how = How.XPATH, using = "//*[@id=\"business-type-form\"]")
    public WebElement moveBusinessType;

    @FindBy(how = How.XPATH, using = "//*[@id=\"note_title\"]")
    public WebElement noteTitle;
    @FindBy(how = How.XPATH, using = "//*[@id=\"note_desc\"]")
    public WebElement noteDescription;
    @FindBy(how = How.XPATH, using = "/html/body/div[1]/div[1]/div/div[1]/div/ul/li[1]/button")
    public WebElement addNotes;
    @FindBy(how = How.XPATH, using = "//*[@id=\"notes_submit\"]")
    public WebElement noteSubmit;

    public void clickAddNote() {
        addNotes.click();
        highLightElement(driver,addNotes);
    }

    public void noteDetails(String title, String description) {
        // Notes section tab
        noteTitle.sendKeys(title);
        noteDescription.sendKeys(description);
        noteSubmit.click();
    }

    // Entering values in the Question tab

    @FindBy(how = How.XPATH, using = "//*[@id=\"questionID1\"]")
    public WebElement quest1;
    @FindBy(how = How.XPATH, using = "//*[@id=\"questionID2\"]")
    public WebElement quest2;
    @FindBy(how = How.XPATH, using = "//*[@id=\"questionID3\"]")
    public WebElement quest3;
    @FindBy(how = How.XPATH, using = "//*[@id=\"questionID4\"]")
    public WebElement quest4;
    @FindBy(how = How.XPATH, using = "//*[@id=\"question_submit\"]")
    public WebElement saveBtn;

    public void questionDetails1(String question1, String question2,String question3, String question4) {
        quest1.sendKeys(question1);
        quest2.sendKeys(question2);
        quest3.sendKeys(question3);
        quest4.sendKeys(question4);
        saveBtn.click();
    }

    @FindBy(how = How.XPATH, using = "//*[@id=\"notes_sects\"]")
    public WebElement notesTab;
    @FindBy(how = How.XPATH, using = "//*[@id=\"dtnotes_wrapper\"]/tbody/tr/td[5]/center/span")
    public WebElement notesIcon;

    public void clickNotesTab() {
        notesTab.click();
        notesIcon.click();
    }
    @FindBy(how = How.XPATH, using = "//*[@id=\"history_sects\"]")
    public WebElement historyTab;

    public void clickHistoryTab() {
        historyTab.click(); 
    }

    //Intitializing elements in Policies tab
    @FindBy(how = How.XPATH, using = "//*[@id=\"Employer Group\"]")   //Client code search box
    public WebElement employerGroupSearch;

    public void RecordSearch(String employergroupsearch)  {
        employerGroupSearch.sendKeys(employergroupsearch);
    }

    // Clicking on Add Record Submit Button
    @FindBy(how = How.XPATH, using = "//*[@id=\"addPolicy\"]/div/div/div/div[1]/div/div[2]")
    public WebElement addSubmitButton;

    // Clicking on OK Button
    @FindBy(how = How.XPATH, using = "/html/body")
    public WebElement okButton;

    public void addSubmit() {
        okButton.click();
        addSubmitButton.click();
        highLightElement(driver,addSubmitButton);
    }

    @FindBy(how = How.XPATH, using = "//*[@id=\"policy_entry_submit_close\"]")
    public WebElement submitCloseBtn;
    public void submitCloseButton() throws Throwable {
            submitCloseBtn.click();
            highLightElement(driver,submitCloseBtn);
            Thread.sleep(5000);
    }

    @FindBy(how = How.XPATH, using = "//*[@id=\"index1\"]")
    public WebElement homeBtn;

    public void homeButton() {
        homeBtn.click();
    }

    //Selecting the record from the Policies Tab

    @FindBy(how = How.XPATH, using = "//*[@id=\"dtPolicies\"]/tbody/tr[1]/td[1]/a")
    public WebElement recordNum;
    public void SelRecord() {
        recordNum.click();
    }

    //Clicking on reset button

    @FindBy(how = How.XPATH, using = "//*[@id=\"dtPolicies_wrapper\"]/div[1]/div[2]/div/div[3]")
    public WebElement resetBtn;

    public void resetButton() throws Throwable {
        resetBtn.click();
        Thread.sleep(5000);
    }

    public void moveRecordToRenewal(String moveRec) throws Throwable {
        highLightElement(driver,moveBusinessType);
        Select se = new Select(moveBusinessType);
        se.selectByVisibleText(moveRec);
        Thread.sleep(10000);
    }

    public void moveRecordToNewBusiness(String moveRec1) throws Throwable {
        highLightElement(driver,moveBusinessType);
        Select se = new Select(moveBusinessType);
        se.selectByVisibleText(moveRec1);
        Thread.sleep(10000);
    }

    // Clicking on My Prospects KPI

    @FindBy(how = How.XPATH, using = "//*[@id=\"kpi1\"]/div[2]/div[2]")
    public WebElement prospectsKPI;
    public void prospectsKPI() {
        highLightElement(driver,prospectsKPI);
        prospectsKPI.click();
    }

    // My Renewals KPI

    @FindBy(how = How.XPATH, using = "//*[@id=\"kpi2\"]/div[2]/div[1]")
    public WebElement myRenewalsKPI;

    public void myRenewalsKPI() {
        highLightElement(driver,myRenewalsKPI);
        myRenewalsKPI.click();
    }

    // Clicking on My New Business KPI

    @FindBy(how = How.XPATH, using = "//*[@id=\"kpi3\"]")
    public WebElement myNewBusinessKPI;

    public void myNewBusinessKPI() {
        highLightElement(driver,myNewBusinessKPI);
        myNewBusinessKPI.click();
    }

    //Moving record to Lost Business

    public void moveRecordToLostBusiness(String moveRec2)throws Throwable {
        highLightElement(driver,moveBusinessType);
        Select se = new Select(moveBusinessType);
        se.selectByVisibleText(moveRec2);
        Thread.sleep(10000);
    }
    // Clicking on My Lost Business KPI

    @FindBy(how = How.XPATH, using = "//*[@id=\"kpi4\"]")
    public WebElement myLostBusinessKPI;

    public void myLostBusinessKPI() {
        myLostBusinessKPI.click();
    }

    public void moveRecordToWorkInProgress(String moveRec3)throws Throwable {
        highLightElement(driver,moveBusinessType);
        Select se = new Select(moveBusinessType);
        se.selectByVisibleText(moveRec3);
        Thread.sleep(10000);
    }

    public void moveRecordToYTD(String moveRec4)throws Throwable {
        highLightElement(driver,moveBusinessType);
        Select se = new Select(moveBusinessType);
        se.selectByVisibleText(moveRec4);
        Thread.sleep(3000);
        //addRenewalPremium.sendKeys("1");

    }

    // Clicking on YTD KPI

    @FindBy(how = How.XPATH, using = "//*[@id=\"kpi6\"]/div[2]/div")
    public WebElement ytd;

    public void ytdKPI() {
        highLightElement(driver,ytd);
        ytd.click();
    }

    public void prospectsKPICount()  {
        String MyProspectsCount = MyProspectsKpiValue.getText();
        System.out.println("My Prospects KPI Count is :" + MyProspectsCount);
    }
    public void renewalsKPICount()  {
        String MyRenewalsCount = MyRenewalsKpiValue.getText();
        System.out.println("My Renewals KPI Count is :" + MyRenewalsCount);
    }
    public void newBusinessKPICount() {
        String MyNewBusinessCount = MyNewBusinessKpiValue.getText();
        System.out.println("My New Business KPI Count is :" + MyNewBusinessCount);
    }
    public void lostBusinessKPICount() {
        String MyLostBusinessCount = MyLostBusinessKpiValue.getText();
        System.out.println("My Lost Business KPI Count is :" + MyLostBusinessCount);
    }
    public void workInProgressKPICount() {
        String MyWorkInProgressCount = MyWorkInProgressKpiValue.getText();
        System.out.println("My Work In Progress KPI Count is :" + MyWorkInProgressCount);
    }

    @FindBy(how = How.CSS, using = "#index > img")   //Patracorp logo
    public WebElement logo;
    public void logo() {
        logo.click();
    }

    @FindBy(how = How.CSS, using = "body > header > nav > div > div > div:nth-child(3) > div.topbar-actions > div > button > i")   // Logout icon
    public WebElement btnLogoutIcon;

    @FindBy (how = How.CSS, using = "body > header > nav > div > div > div.col-md-3.col-sm-3.col-xs-12.patraheaderp3 > div.topbar-actions > div > button > i")  //Logout
    public WebElement clickicon;
    public void clicklogouticon() {
        clickicon.click();
    }

    // Clicking on Logout
    @FindBy(how = How.CSS, using = "body > header > nav > div > div > div.col-md-3.col-sm-3.col-xs-12.patraheaderp3 > div.topbar-actions > div > ul > li.btn.btn-block.userMenu > a > h4")  // logout button
    public WebElement btnLogout;

    public void logout() {
        btnLogout.click();
    }

    //Delete Policy

    @FindBy(how = How.ID, using = "policy_delete")
    public WebElement delete;

    @FindBy(how = How.XPATH, using = "/html/body/div[3]/div[2]/div/div/div/div/div/div/div/div[4]/button[1]")
    public WebElement deleteConfirm;

    public void delete() throws Throwable{
        delete.click();
        Thread.sleep(3000);
        deleteConfirm.click();
    }
    @FindBy(how = How.XPATH, using = "//*[@id=\"importing-Policies\"]")  //Business Details link
    public WebElement btnImportPolicies;

    @FindBy(how = How.ID, using = "uploadfile")  //Business Details link
    public WebElement btnfile;
    public void Importpolicies() throws Throwable{
        Thread.sleep(5000);
        btnImportPolicies.click();
        Thread.sleep(5000);
    }
    @FindBy(how = How.XPATH, using = "//*[@id=\"viewfile\"]")  //Business Details link
    public WebElement btnsubmitpolicies;

    public void importsubmit() {
        btnsubmitpolicies.click();
    }
    public void uploadBtn() {
        btnfile.click();
    }

    @FindBy(how=How.XPATH, using = "/html/body/div[3]/div[2]/div/div/div/div/div/div/div/div[4]/button")  //Alert pop up ok btn
    public WebElement alertOkBtn;

    public void alertok() {
        alertOkBtn.click();
    }

    @FindBy(how = How.LINK_TEXT, using = "Reports")         // Reports tab
    public WebElement btnReports;

    public void ReportsButton() {
        btnReports.click();
    }

    public static void highLightElement(WebDriver driver, WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].setAttribute('style','background: yellow; border: 2px solid red;');", element);
    }
}

