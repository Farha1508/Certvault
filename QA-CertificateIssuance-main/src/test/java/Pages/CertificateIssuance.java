package Pages;

import Base.KpiClass;
import Base.BaseUtil;
import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CertificateIssuance {
    private final WebDriver driver;
    private final JavascriptExecutor js;
    private final WebDriverWait wait;

    public CertificateIssuance(WebDriver driver, JavascriptExecutor js) {
        this.driver = driver;
        this.js = js;
        this.wait = new WebDriverWait(this.driver, 10);
        PageFactory.initElements(driver, this);
    }

//    public void modalDisplayed(){
//        commonForm.elementFound("string");
//    }

    //id="jconfirm-box48401" // when gone, is removed from DOM
    // /html/body/div[6]/div[2]/div/div/div/div/div/div/div/div[4]/button[1]


    //    private HashMap<String, String> gridMap = new HashMap<>();
//
//
//
//    static String workOrder;
//
//    // Functions
//    public void typeInSearchField(String entry){
//        // Manually clear the search field, because the .clear() function does not work (because it's a "value" and not "text")
//        // Could send "Ctrl+A" instead, but might not work on MacOS.
//
//        elementFound(topSearchBar, "Top Search Bar");
//        clearField(topSearchBar);
//
//        long startTime = System.currentTimeMillis();
//        while(topSearchBar.getAttribute("value").equals("") && ((System.currentTimeMillis() - startTime) < 5000)){ // even with the updated pageLoaded, sometimes the search field was not being filled. Just run this line until it is.
//            topSearchBar.sendKeys(entry);
//            waitForMiliseconds(500);
//        }
//
//        getWait().until(ExpectedConditions.visibilityOfAllElements(allSearchDropdown));
//    }
//
//    public int numberOfSearchResults(){
//        elementFound(allSearchDropdown, "Search Dropdown");
//        return allSearchDropdown.size();
//    }
//
//    public void searchResultsContainSearch(String expected){
//        elementFound(allSearchDropdown, "Search Dropdown");
//        for (WebElement dropDownResult : allSearchDropdown) {
//            Assert.assertTrue(dropDownResult.getText().contains(expected), "Search string was not in this result.");
//        }
//    }
//
//    public void searchResultsDescending(){
//        elementFound(allSearchDropdown, "Search Dropdown");
//        int[] workOrderNumbers = new int[allSearchDropdown.size()];
//        // initialize the first value so we can do the rest in just one FOR loop without IF statements
//        workOrderNumbers[0] = Integer.parseInt(topSearchDropdown.getText().split(" /| ")[0]);
//
//        // compare each number to its neighbours
//        for(int i = 1; i < allSearchDropdown.size(); i++){
//            workOrderNumbers[i] = Integer.parseInt(allSearchDropdown.get(i).getText().split(" /| ")[0]); // To remove the " | <company name>" part of the string
//            Assert.assertTrue(workOrderNumbers[i] < workOrderNumbers[i-1]);
//        }
//    }
//
//    public String clickTopSearchResult(){
//        elementFound(topSearchDropdown, "Top Search Result");
//        String toStore = topSearchDropdown.getText().split(" /| ")[0]; // To remove the " | <company name>" part of the string
//        topSearchDropdown.click();
//        BaseUtil.pageLoaded();
//        return toStore; // so the value can be put in valueStore.
//    }
//
//    public String getWorkOrderNumber(){
//        elementFound(workOrderNumber, "Work Order Number");
//        return workOrderNumber.getText();
//    }
//
//    public void waitForCmpErrMsg(){ // wait for the error message to be displayed (after clicking Submit button)
//        getWait().until(ExpectedConditions.visibilityOf(companyErrMsg));
//        elementFound(companyErrMsg, "Company Error Message");
//    }
//
//    public String companyErrorDisplayed(){
//        if(companyErrMsg.isDisplayed()){
//            return companyErrMsg.getText();
//        }
//        else{
//            return "COMPANY ERROR MESSAGE NOT DISPLAYED";
//        }
//    }
//
//    public String folderErrorDisplayed(){
//        if(folderErrMsg.isDisplayed()){
//            return folderErrMsg.getText();
//        }
//        else{
//            return "FOLDER ERROR MESSAGE NOT DISPLAYED";
//        }
//    }
//
//    public void waitForDivErrMsg(){ // wait for the error message to be displayed (after clicking Submit button)
//        getWait().until(ExpectedConditions.visibilityOf(divisionErrMsg));
//        elementFound(divisionErrMsg, "Division Error Message");
//    }
//
//    public String divisionErrorDisplayed(){
//        if(divisionErrMsg.isDisplayed()){
//            return divisionErrMsg.getText();
//        }
//        else{
//            return "DIVISION ERROR MESSAGE NOT DISPLAYED";
//        }
//    }
//
//    public String branchErrorDisplayed(){
//        if(branchErrMsg.isDisplayed()){
//            return branchErrMsg.getText();
//        }
//        else{
//            return "BRANCH ERROR MESSAGE NOT DISPLAYED";
//        }
//    }
//
//    public boolean addRecordModalDisplayed(){
//        elementFound(addRecordModal, "Add Record Modal");
//        return addRecordModal.isDisplayed();
//    }
//
//    public boolean waitForAddRecordClosed(){
//        waitForElementGone(addRecordModal);
//        if(elementFound(addRecordModal, "Record Detail Form")){
//            return addRecordModal.isDisplayed();
//        }
//        return false;
//    }
//
//    public boolean waitForAddRecordConfirmModal(){
//        getWait().until(ExpectedConditions.visibilityOf(addRecordConfirm));
//        return addRecordConfirm.isDisplayed();
//    }
//
//    public String checkAddRecordConfirmModal(){
//        elementFound(addRecordConfirm, "Add Record Confirmation Modal");
//        if(addRecordConfirm.isDisplayed()){
//            return addRecordConfirm.getText();
//        }
//        else{
//            return "CONFIRM MODAL NOT DISPLAYED";
//        }
//    }
//
//    public String getConfirmRecordNum(){
//        elementFound(addRecordConfirm, "Add Record Confirmation Modal");
//        return addRecordConfirm.getText().split("\\W+")[3]; // pulled from https://stackoverflow.com/questions/10563202/java-split-a-string-by-space-new-line-tab-punctuation
//    }
//
//    public void clickConfirmOKBtn(){
//        elementFound(addRecordConfirmOKBtn, "Add Record Confirmation Modal OK Button");
//        addRecordConfirmOKBtn.click();
//        waitForElementGone(addRecordConfirmOKBtn);
//    }
//
//    public boolean confirmModalNotDisplayed(){
//        return waitForElementGone(addRecordConfirm);
//    }
//
//    public boolean waitForEditRecordConfirmModal(){
//        getWait().until(ExpectedConditions.visibilityOf(editRecordConfirm));
//        return editRecordConfirm.isDisplayed();
//    }
//
//    public String checkEditRecordConfirmModal(){
//        elementFound(editRecordConfirm, "Add Record Confirmation Modal");
//        if(editRecordConfirm.isDisplayed()){
//            return editRecordConfirm.getText();
//        }
//        else{
//            return "CONFIRM MODAL NOT DISPLAYED";
//        }
//    }
//
//    public void clickEditConfirmOKBtn(){
//        elementFound(editRecordConfirmOKBtn, "Add Record Confirmation Modal OK Button");
//        editRecordConfirmOKBtn.click();
//        waitForElementGone(editRecordConfirmOKBtn);
//    }
//
//    public boolean confirmEditModalNotDisplayed(){
//        return waitForElementGone(editRecordConfirm);
//    }
//
//    public boolean elementFound(WebElement targetElement, String elementName){
//        try {
//            if(targetElement.isDisplayed()){
//                return true;
//            }
//            else{
//                System.out.println("Element " + elementName + " was not displayed.");
//                return false;
//            }
//        }catch(NoSuchElementException | ElementNotInteractableException e){
//            System.out.println("Element " + elementName + " does not exist, or cannot be interacted with.");
//            return false;
//        }
//    }
//
//    public boolean elementFound(List<WebElement> targetElements, String elementName){
//        try {
//            for (WebElement targetElement : targetElements) {
//                if (!targetElement.isDisplayed()) {
//                    System.out.println(elementName + " was not displayed.");
//                    return false;
//                }
//            }
//        }catch(NoSuchElementException | ElementNotInteractableException e){
//            System.out.println(elementName + " does not exist, or cannot be interacted with.");
//            return false;
//        }
//        return true;
//    }
//
//    public boolean waitForElementGone(WebElement gallowsElement){
//        long startTime = System.currentTimeMillis();
//        while((System.currentTimeMillis() - startTime) < 5000){ // lifted from NodeApp
//            try{
//                if(gallowsElement.isDisplayed()) { // element is displayed: wait longer
//                    waitForMiliseconds(500);
//                }
//                else{ // element is hidden: we're done
//                    return true;
//                }
//            }
//            catch(NoSuchElementException | ElementNotInteractableException e){ // element doesn't exist: we're done
//                return true;
//            }
//        }
//        return false;
//    }
//
//    public void waitForMiliseconds(int miliseconds){
//        try {
//            Thread.sleep(miliseconds);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public String verifyMeterNameFilled(){
//        return commonDropDownRead("Meter Name");
//    }
//
//    public void cannotMakeDuplicateRecordsSetup(){
//        System.out.println("Making a new record to confirm ID is not reused.");
//        Assert.assertTrue(commonButton("Add Record"), "Add Record button could not be found on page");
//        BaseUtil.pageLoaded();
//        commonDropDownSelect("Company", "Company 019");
//        Assert.assertTrue(commonButton("Submit"), "Add Record button could not be found on page");
//    }
//
//    public boolean cmFieldDisplayed(){
//        return elementFound(certifiedMailField, "CM # Field");
//    }
//
//    public boolean cmrrFieldDisplayed(){
//        return elementFound(certifiedMailReturnReceiptField, "CMRR # Field");
//    }
//
//    public boolean clearField(WebElement field){
//        long startTime = System.currentTimeMillis();
//        while(!field.getAttribute("value").equals("") && ((System.currentTimeMillis() - startTime) < 5000)){ // lifted from NodeApp
//            field.sendKeys(Keys.BACK_SPACE);
//        }
//        return field.getAttribute("value").equals("");
//    }
//
//    public boolean requiredAsterixDisplayed(String field){
//        return switch (field) {
//            case "Folder" -> folderAsterisk.isDisplayed();
//            case "Company" -> companyAsterisk.isDisplayed();
//            case "Division" -> divisionAsterisk.isDisplayed();
//            case "Branch" -> branchAsterisk.isDisplayed();
//            default -> false;
//        };
//        // todo: maybe also confirm element colour is red.
//    }
//
//    public String checkPDFPage(){
//        // could also use the sendKeys method to send CTRL+T to switch tabs.
//
//        String mainTab = getDriver().getWindowHandle();
//        // String newTab = getDriver().getWindowHandles()[1];
//        ArrayList<String> newTab = new ArrayList<>(getDriver().getWindowHandles());
//        newTab.remove(mainTab); // lifted from https://stackoverflow.com/questions/12729265/switch-tabs-using-selenium-webdriver-with-java
//        getDriver().switchTo().window(newTab.get(0));
//        String url = getURL();
//        getDriver().close();
//        getDriver().switchTo().window(mainTab);
//        return url;
//    }
//
//    public String getURL(){
//        return getDriver().getCurrentUrl();
//    }
//
//    public void prepareConditionalTest(){
//        if(certifiedMailChBox.isSelected()){
//            commonCheckBox("Certified Mail");
//        }
//        if(certifiedMailChBox.isSelected()){
//            commonCheckBox("Certified Mail with Return Receipt");
//        }
//        waitForElementGone(certifiedMailField);
//        waitForElementGone(certifiedMailReturnReceiptField);
//    }
//
//    public String getRushStatus(){
//        return rushStatus.getText();
//    }
//
//    public void verifyBackGroundColor(String colour) throws Exception {
//        // Todo: move expected colour RGB values to a properties file for better management.
//        String cssColour;
//        switch (colour) {
//            case "White":
//                cssColour = gridFirstRow.getCssValue("background-color");
//                System.out.println("Verifying Color in Grid row  " + cssColour);
//                Assert.assertEquals("rgba(249, 249, 249, 1)", cssColour);
//                break;
//            case "Rush - Yellow":
//                cssColour = gridFirstRow.getCssValue("background-color");
//                System.out.println("Verifying Color in Grid row for Rush  " + cssColour);
//                Assert.assertEquals("rgba(255, 255, 102, 1)", cssColour);
//                break;
//            case "AGS Insurance - Green":
//                cssColour = gridFirstRow.getCssValue("background-color");
//                System.out.println("Verifying Color in Grid row for AGS insurance  " + cssColour);
//                Assert.assertEquals("rgba(0, 100, 0, 1)", cssColour);
//                break;
//            case "donut Account -Light Green":
//                cssColour = gridFirstRow.getCssValue("background-color");
//                System.out.println("Verifying Color in Grid row for Donut Account " + cssColour);
//                Assert.assertEquals("rgba(153, 204, 51, 1)", cssColour);
//                break;
//            case "DueDateToday -Purple":
//                cssColour = gridFirstRow.getCssValue("background-color");
//                System.out.println("Verifying Color in Grid row for Donut Account " + cssColour);
//                Assert.assertEquals("rgba(206, 84, 200, 1)", cssColour);
//                break;
//            case "Cancel -Blue":
//                cssColour = gridFirstRow.getCssValue("background-color");
//                System.out.println("Verifying Color in Grid row for Cancel " + cssColour);
//                Assert.assertEquals("rgba(103, 200, 255, 1)", cssColour);
//                break;
//            case "SLA -Red":
//                cssColour = gridFirstRow.getCssValue("background-color");
//                System.out.println("Verifying Color in Grid row for Cancel " + cssColour);
//                Assert.assertEquals("rgba(238, 67, 67, 1)", cssColour);
//                break;
//            default:
//                Assert.assertTrue(false, "Colour: "+ colour+ " was not in the switch statement. ");
//        }
//    }
//
    public String getPopupWorkOrderNumber() {
        return confirmModalWorkOrder.getText();
    }

    public void closeNewWorkOrderPopup() {
        confirmModalOKBtn.click();
    }

//    public void searchPopupWorkOrderNumber() {
//        workOrder = newWorkOrderNumber.getText();
//        openPolicies_WOSearch.sendKeys(workOrder);
//    }
//
//    public boolean stopButtonDisplayed(){
//        return elementFound(timerCloseButton, "Stop button");
//    }
//
//    public boolean timeTrackingModalDisplayed(boolean expectation){
//        if(expectation){
//            getWait().until(ExpectedConditions.visibilityOf(timerModal));
//        }
//        else{
//            waitForElementGone(timerModal);
//        }
//        return elementFound(timerModal, "Time Tracking Modal");
//    }
//
//    public void clickTimeTrackingButton(String button) {
//        if(button.equalsIgnoreCase("start")){
//            elementFound(timerModalStartBtn, "Time Tracking Modal "+button+" button.");
//            timerModalStartBtn.click();
//        }
//    }
//
//    public void setTimerWO(String workOrderNumber){
//        timerModalWorkOrderField.sendKeys(workOrderNumber);
//        BaseUtil.pageLoaded();
//    }
//
//    public String getTimerCompany(){
//        return companyTimerText.getText();
//    }
//
//    public boolean elementHasError(WebElement element){
//        return element.getAttribute("class").contains("selectboxErr");
//    }
//
//    public String currentUserOpenTimer(){
//        WebDriverWait wait = new WebDriverWait(getDriver(), 20);
//        wait.until(ExpectedConditions.elementToBeClickable(userIcon));
//        long startTime = System.currentTimeMillis();
//        while(!userNameInUserDropdown.isDisplayed() && ((System.currentTimeMillis() - startTime) < 5000)){
//            userIcon.click();
//            waitForMiliseconds(500);
//        }
//        return userNameInUserDropdown.getText();
//    }
//
//    // Todo: use Common Grid methods when available
//    public String userOpenTask(String user) {
//        //Assumes user already on Time Rec Open Page
//        //Find employee in table
//        timeTableEmployeeHeader.sendKeys(user);
//        waitForMiliseconds(3000);; // using pageLoaded did not give enough time for table to sort itself
//        BaseUtil.pageLoaded();
//
//        return timeTableFirstRowTask.getText();
//    }
//
//    public boolean linkDisplayed(String linkText){
//        try {
//            if(getDriver().findElement(By.linkText(linkText)).isDisplayed()){
//                System.out.println("Link: "+ linkText + " was displayed.");
//                return true;
//            }
//            else{
//                System.out.println("Link: "+ linkText + " was not displayed.");
//                return false;
//            }
//        }catch(NoSuchElementException | ElementNotInteractableException e){
//            System.out.println("Link: "+ linkText + " does not exist.");
//            return false;
//        }
//    }
//
//    public void setDateField(String datePicker, String date) {
//        if(datePicker.equalsIgnoreCase("to")){
//            timeAdmin_ToDateCalanderField.click();
//            timeAdmin_ToDateCalanderField.clear();
//            timeAdmin_ToDateCalanderField.sendKeys(date);
//            //timeAdmin_ToDateCalanderField.submit();
//        }
//        else if(datePicker.equalsIgnoreCase("from")){
//            timeAdmin_FromDateCalanderField.click();
//            timeAdmin_FromDateCalanderField.clear();
//            timeAdmin_FromDateCalanderField.sendKeys(date);
//            timeAdmin_FromDateCalanderField.submit();
//        }
//        else{
//            System.out.println("Bad input. Please select 'to' or 'from'.");
//        }
//    }
//
//    public boolean errorWarningDisplayed(String fieldName) {
//        // todo: make this more robust than a switch statement
//        switch(fieldName){
//            case "To":
//                return timeAdmin_ToDateError.isDisplayed() && timeAdmin_ToDateError.getText().contains("Please Select Start Time");
//            case "From":
//                return timeAdmin_FromDateError.isDisplayed() && timeAdmin_FromDateError.getText().contains("Please Select Start Time");
//            default:
//                System.out.println("Could not find field.");
//                return false;
//        }
//    }
//
//    public String timerError() {
//        elementFound(timerErrorMsg, "Timer Error message");
//        return timerErrorMsg.getText();
//    }
//
//    public boolean verifyReportsPage() {
//        boolean correctPage = getDriver().getCurrentUrl().contains("Reports");
//        boolean siteName = reportsSiteTitle.getText().equals("Patra Corp Reporting Services");
//        boolean pageName = reportsPageTitle.getText().equals("Alpha Reporting");
//        return correctPage && siteName && pageName;
//    }
//
//    public void dismissDiscardModal() {
//        dismissAlertOkBtn.click();
//        waitForElementGone(dismissAlertOkBtn);
//    }
//
//    public void clickResetButton() {
//        policyCheckingGridResetBtn.click();
//    }
//
//    // Selectors
//    @FindBy(how = How.ID, using = "pc_IsRush1")
//    private WebElement rushStatus;
//
//    @FindBy(how = How.ID, using = "typeaheadValue")
//    private WebElement topSearchBar;
//
//    @FindBy(how = How.XPATH, using = "//*[@id=\"the-basics-new\"]/span/div/div/p")
//    private WebElement topSearchDropdown;
//
//    @FindBy(how = How.XPATH, using = "//*[@id=\"the-basics-new\"]/span/div/div/p")
//    private List<WebElement> allSearchDropdown;
//
//    @FindBy(how = How.ID, using = "pc_WoNumber1")
//    private WebElement workOrderNumber;
//
//    @FindBy(how = How.ID, using = "Company_err")
//    private WebElement companyErrMsg;
//
//    @FindBy(how = How.ID, using = "Folder_err")
//    private WebElement folderErrMsg;
//
//    @FindBy(how = How.ID, using = "Division_err")
//    private WebElement divisionErrMsg;
//
//    @FindBy(how = How.ID, using = "Branch_err")
//    private WebElement branchErrMsg;
//
//    @FindBy(how = How.ID, using = "printshopDetailForm")
//    private WebElement addRecordModal; //printshopDetailForm //addPrintshop
//
//    @FindBy(how = How.XPATH, using = "/html/body/div[5]/div[2]/div/div/div/div/div/div/div")
//    private WebElement addRecordConfirm;
//
//    @FindBy(how = How.XPATH, using = "/html/body/div[5]/div[2]/div/div/div/div/div/div/div/div[4]/button")
//    private WebElement addRecordConfirmOKBtn;
//
//    @FindBy(how = How.XPATH, using = "/html/body/div[3]/div[2]/div/div/div/div/div/div/div")
//    private WebElement editRecordConfirm;
//
//    @FindBy(how = How.XPATH, using = "/html/body/div[3]/div[2]/div/div/div/div/div/div/div/div[4]/button")
//    private WebElement editRecordConfirmOKBtn;
//
//    @FindBy(how = How.ID, using = "IsCertifiedMail")
//    private WebElement certifiedMailChBox;
//
//    @FindBy(how = How.ID, using = "IsCertifiedMailWithReturnReceipt")
//    private WebElement certifiedMailReturnReceiptChBox;
//
//    @FindBy(how = How.ID, using = "CertifiedMailNumber")
//    private WebElement certifiedMailField;
//
//    @FindBy(how = How.ID, using = "CertifiedMailWithReturnReceiptNumber")
//    private WebElement certifiedMailReturnReceiptField;
//
//    @FindBy(how = How.XPATH, using = "//*[@id=\"Details\"]/div/div/div/div/div[1]/div[1]/label/span[1]")
//    private WebElement folderAsterisk;
//
//    @FindBy(how = How.XPATH, using = "//*[@id=\"printshopDetailForm\"]/div[1]/div[3]/label/span[1]")
//    private WebElement companyAsterisk;
//
//    @FindBy(how = How.ID, using = "Division_compl")
//    private WebElement divisionAsterisk;
//
//    @FindBy(how = How.ID, using = "Branch_compl")
//    private WebElement branchAsterisk;
//
//    @FindBy(how = How.ID, using = "companyStartId")
//    private WebElement companySelector;
//
//    @FindBy(how = How.XPATH, using = "//button[@data-id=\"serviceStartId\"]")
//    private WebElement serviceSelector;
//
//    @FindBy(how = How.XPATH, using = "//*[@id=\"dtopenpolicies\"]/tbody/tr[1]")
//    public static WebElement gridFirstRow;
//
//    @FindBy(how = How.XPATH, using = "//*[starts-with(@id,'jconfirm-box')]/div/font/b")
//    public WebElement newWorkOrderNumber;
//

//
//    @FindBy(how = How.XPATH, using = "//*[@id='WorkOrderPolicyCheckingNumberProcess']")
//    public WebElement openPolicies_WOSearch;
//
//    @FindBy(how = How.XPATH, using = "//*[@id=\"timer-Details-close\"]")   //Stop timer in header
//    public WebElement timerCloseButton;
//
//    @FindBy(how = How.ID, using = "startTimerID")   //Timer modal
//    public WebElement timerModal;
//
//    @FindBy(how = How.ID, using = "timer-Details-Submit")   //Timer modal start button
//    public WebElement timerModalStartBtn;
//
//    @FindBy(how = How.ID, using = "timer_work_order_no")
//    public WebElement timerModalWorkOrderField;
//
//    @FindBy(how = How.ID, using = "timerHeaderID")   //Timer company text
//    public WebElement companyTimerText;
//
//    @FindBy(how = How.CLASS_NAME, using = "userName")   //Timer company text
//    public WebElement userNameInUserDropdown;
//
//    @FindBy(how = How.CSS, using = ".glyphicon-user")
//    private WebElement userIcon;
//
//    @FindBy(how = How.ID, using = "EmployeeUserNameTR")   //Timer company text
//    public WebElement timeTableEmployeeHeader;//*[@id="dtTimeRecord"]/tbody/tr/td[4]
//
//    @FindBy(how = How.XPATH, using = "//*[@id=\"dtTimeRecord\"]/tbody/tr/td[4]")   //Timer company text
//    public WebElement timeTableFirstRowTask;
//
//    @FindBy(how = How.ID, using = "timer-err")
//    private WebElement timerErrorMsg;
//
//    @FindBy(how = How.XPATH, using = "/html/body/div[16]/div[2]/div/div/div/div/div/div/div/div[4]/button")
//    private WebElement dismissAlertOkBtn;
//
//    @FindBy(how = How.XPATH, using = "//*[@id=\"dtopenpolicies_wrapper\"]/div[1]/div[2]/div/div[3]")
//    private WebElement policyCheckingGridResetBtn;
//
//    // Time Records Admin Page
//    @FindBy(how = How.ID, using = "startTimeFrom")
//    public WebElement timeAdmin_FromDateCalanderField;
//    @FindBy(how = How.ID, using = "startTimeTo")
//    public WebElement timeAdmin_ToDateCalanderField;//
//    @FindBy(how = How.ID, using = "startTimeFrom_err")
//    public WebElement timeAdmin_FromDateError;
//    @FindBy(how = How.ID, using = "startTimeTo_err")
//    public WebElement timeAdmin_ToDateError;
//
//    // Records page
//    @FindBy(how = How.CLASS_NAME, using = "msrs-site_title")
//    private WebElement reportsSiteTitle;
//
//    @FindBy(how = How.CLASS_NAME, using = "msrs-page_title")
//    private WebElement reportsPageTitle;
//

    public void clickTimeRecordOpenResetButton() {
        timeRecordOpenGridResetBtn.click();
    }

    public void waitForHdoErrMsg() { // wait for the error message to be displayed (after clicking Submit button)
        wait.until(ExpectedConditions.visibilityOf(hdoErrMsg));
    }

    public String hdoErrorDisplayed() {
        if (hdoErrMsg.isDisplayed()) {
            return hdoErrMsg.getText();
        } else {
            return "COMPANY ERROR MESSAGE NOT DISPLAYED";
        }
    }

    public String hupErrorDisplayed() {
        if (hupErrMsg.isDisplayed()) {
            return hupErrMsg.getText();
        } else {
            return "FOLDER ERROR MESSAGE NOT DISPLAYED";
        }
    }

    public boolean addWorkOrderModalDisplayed() {
        try {
            return addWorkOrderModal.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void clickCertIssResetButton() {
        certIssGridResetBtn.click();
    }
    // todo: make clicking on Reset buttons not require different methods for each page.

    public String checkEditRecordConfirmModal() {
        wait.until(ExpectedConditions.visibilityOf(editRecordConfirm));
        if (editRecordConfirm.isDisplayed()) {
            return editRecordConfirm.getText();
        } else {
            return "CONFIRM MODAL NOT DISPLAYED";
        }
    }

    public boolean emailPopUpFields() {
        try {
            return emailToField.isDisplayed() && emailCCField.isDisplayed() && emailSubjectField.isDisplayed() && emailMessageField.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public String emailSuccessMsg() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email_succ_msg")));
        try {
            if (emailSuccessMsg.isDisplayed()) {
                return emailSuccessMsg.getText();
            }
            return null;
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    public boolean emailToErrorMsg() {
        try {
            return emailErrorMsg.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void emailEnterField(String value, String field) {
        switch (field.toLowerCase()) {
            case "to":
                emailToField.sendKeys(value);
                break;
            case "cc":
                emailCCField.sendKeys(value);
                break;
            case "subject":
                emailSubjectField.sendKeys(value);
                break;
            case "message":
                emailMessageField.sendKeys(value);
                break;
        }
    }

    public String emailReadField(String field) {
        switch (field.toLowerCase()) {
            case "to":
                return emailToField.getText();
            case "cc":
                return emailCCField.getText();
            case "subject":
                return emailSubjectField.getText();
            case "message":
                return emailMessageField.getText();
            default:
                return null;
        }
    }

    public boolean originalEmailFieldDisplayed() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("cert_description")));
        try {
            return originalEmailField.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public String originalEmailFieldContents() {
        return originalEmailField.getText();
    }

    public void originalEmailFieldEnter(String typingInput) {
        originalEmailField.sendKeys(typingInput);
    }

    public boolean timeTrackingModalDisplayed() {
        try {
            return timerModal.isDisplayed();
        } catch (java.util.NoSuchElementException e) {
            return false;
        }
    }

    public boolean elementHasError(WebElement element) {
        return element.getAttribute("class").contains("selectboxErr");
    }

    public String currentUserOpenTimer() {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.elementToBeClickable(userIcon));
        long startTime = System.currentTimeMillis();
        while (!userNameInUserDropdown.isDisplayed() && ((System.currentTimeMillis() - startTime) < 5000)) {
            userIcon.click();
            try {
                Thread.sleep(500);
            } catch (InterruptedException ignored) {
            }
        }
        return userNameInUserDropdown.getText();
    }

    public String userOpenTask(String user) {
        //Assumes user already on Time Rec Open Page
        //Find employee in table

        //String currentRow1 = commonGrid.gridEntry("row 1", "WO #").getText();
        timeTableEmployeeHeader.sendKeys(user);
        wait.until(ExpectedConditions.stalenessOf(timeTableFirstRowTask));

//        try {
//            Thread.sleep(3000);
//        } catch (InterruptedException ignored) {
//        }
        BaseUtil.pageLoaded();

        return timeTableFirstRowTask.getText();
    }

    public void topTimerRowEditIcon() {
        System.out.println("Clicking on the Edit icon for the first row.");
        driver.findElement(By.xpath("//*[@id='dtTimeRecordOpen']/tbody/tr[1]/td[1]/center/span")).click();
        //timeTableFirstRowEdit.click();
        BaseUtil.pageLoaded();
    }

    public void timeEnterField(String value, String field) {
        switch (field.toLowerCase()) {
            case "task":
                timeRecordModalTask.sendKeys(value);
                break;
            case "end time":
                timeRecordModalEndTime.sendKeys(value);
                break;
        }
    }

    @FindBy(how = How.ID, using = "companyStartId")
    private WebElement companySelector;

    @FindBy(how = How.XPATH, using = "//button[@data-id=\"serviceStartId\"]")
    private WebElement serviceSelector;

    @FindBy(how = How.XPATH, using = "//*[starts-with(@id,'jconfirm-box')]/div/font/b")
    public WebElement confirmModalWorkOrder;

    @FindBy(how = How.XPATH, using = "//div[@class='jconfirm-buttons']/button")
    public WebElement confirmModalOKBtn;

    @FindBy(how = How.XPATH, using = "//*[@id=\"dtTimeRecordOpen_wrapper\"]/div[1]/div[2]/div/div[3]")
    private WebElement timeRecordOpenGridResetBtn;

    @FindBy(how = How.XPATH, using = "//*[@id=\"dtprocessing_wrapper\"]/div[1]/div[2]/div/div[3]")
    private WebElement certIssGridResetBtn;

    @FindBy(how = How.ID, using = "holders_dated_off_error")
    private WebElement hdoErrMsg;

    @FindBy(how = How.ID, using = "holders_updated_error")
    private WebElement hupErrMsg;

    @FindBy(how = How.ID, using = "addWorkOrder")
    private WebElement addWorkOrderModal;

    @FindBy(how = How.XPATH, using = "//*[@id=\"dtprocessing\"]/tbody/tr[1]/td[1]/a")
    public WebElement topWorkOrderLink;

    @FindBy(how = How.XPATH, using = "//*[@id=\"dtprocessing\"]/tbody/tr/td[1]/a")
    public WebElement topWorkOrderLinkAlt;

    @FindBy(how = How.ID, using = "cert_header_succ_msg")
    private WebElement editRecordConfirm;

    @FindBy(how = How.ID, using = "to_email")
    private WebElement emailToField;

    @FindBy(how = How.ID, using = "cc_email")
    private WebElement emailCCField;

    @FindBy(how = How.ID, using = "subject_email")
    private WebElement emailSubjectField;

    @FindBy(how = How.XPATH, using = "//*[@id=\"sendEmail_form\"]/div[5]/div/div[2]/div")
    // cannot use "message_email" ID.
    private WebElement emailMessageField;

    @FindBy(how = How.ID, using = "email_address_err")
    private WebElement emailErrorMsg;

    @FindBy(how = How.XPATH, using = "//*[@id=\"email_succ_msg\"]/div/div")
    private WebElement emailSuccessMsg;

    @FindBy(how = How.ID, using = "cert_description")
    private WebElement originalEmailField;

    @FindBy(how = How.ID, using = "startTimerID")   //Timer modal
    public WebElement timerModal;

    @FindBy(how = How.CSS, using = ".glyphicon-user")
    private WebElement userIcon;

    @FindBy(how = How.CLASS_NAME, using = "userName")   //Timer company text
    public WebElement userNameInUserDropdown;

    @FindBy(how = How.ID, using = "EmployeeUserNameTR")   //Timer company text
    public WebElement timeTableEmployeeHeader;//*[@id="dtTimeRecord"]/tbody/tr/td[4]

    @FindBy(how = How.XPATH, using = "//*[@id=\"dtTimeRecord\"]/tbody/tr/td[4]")   //Timer company text
    public WebElement timeTableFirstRowTask;

    @FindBy(how = How.ID, using = "TimerecordDetails")
    public WebElement timeRecordDetailModal;

    @FindBy(how = How.XPATH, using = "//*[@id=\"dtTimeRecordOpen\"]/tbody/tr/td[1]/center/span")
    public WebElement timeTableFirstRowEdit;

    @FindBy(how = How.ID, using = "editTimerecord_task")
    public WebElement timeRecordModalTask;

    @FindBy(how = How.ID, using = "editTimerecord_end_time")
    public WebElement timeRecordModalEndTime;

}