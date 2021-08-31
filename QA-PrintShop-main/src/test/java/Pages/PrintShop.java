package Pages;

import Base.KpiClass;
import Base.BaseUtil;
import Base.NodeApp;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PrintShop extends NodeApp {

    private HashMap<String, String> gridMap = new HashMap<>();

    public PrintShop(WebDriver driver, JavascriptExecutor js) {
        super(driver, js);
    }

    // Functions
    public void typeInSearchField(String entry){
        // Manually clear the search field, because the .clear() function does not work (because it's a "value" and not "text")
        // Could send "Ctrl+A" instead, but might not work on MacOS.

        elementFound(topSearchBar, "Top Search Bar");
        clearField(topSearchBar);

        long startTime = System.currentTimeMillis();
        while(topSearchBar.getAttribute("value").equals("") && ((System.currentTimeMillis() - startTime) < 5000)){ // even with the updated pageLoaded, sometimes the search field was not being filled. Just run this line until it is.
            topSearchBar.sendKeys(entry);
            waitForMiliseconds(500);
        }

        getWait().until(ExpectedConditions.visibilityOfAllElements(allSearchDropdown));
    }

    public int numberOfSearchResults(){
        elementFound(allSearchDropdown, "Search Dropdown");
        return allSearchDropdown.size();
    }

    public void searchResultsContainSearch(String expected){
        elementFound(allSearchDropdown, "Search Dropdown");
        for (WebElement dropDownResult : allSearchDropdown) {
            Assert.assertTrue(dropDownResult.getText().contains(expected), "Search string was not in this result.");
        }
    }

    public void searchResultsDescending(){
        elementFound(allSearchDropdown, "Search Dropdown");
        int[] workOrderNumbers = new int[allSearchDropdown.size()];
        // initialize the first value so we can do the rest in just one FOR loop without IF statements
        workOrderNumbers[0] = Integer.parseInt(topSearchDropdown.getText().split(" /| ")[0]);

        // compare each number to its neighbours
        for(int i = 1; i < allSearchDropdown.size(); i++){
            workOrderNumbers[i] = Integer.parseInt(allSearchDropdown.get(i).getText().split(" /| ")[0]); // To remove the " | <company name>" part of the string
            Assert.assertTrue(workOrderNumbers[i] < workOrderNumbers[i-1]);
        }
    }

    public String clickTopSearchResult(){
        elementFound(topSearchDropdown, "Top Search Result");
        String toStore = topSearchDropdown.getText().split(" /| ")[0]; // To remove the " | <company name>" part of the string
        topSearchDropdown.click();
        pageLoaded();
        return toStore; // so the value can be put in valueStore.
    }

    public String getWorkOrderNumber(){
        elementFound(workOrderNumber, "Work Order Number");
        return workOrderNumber.getText();
    }

    public void waitForCmpErrMsg(){ // wait for the error message to be displayed (after clicking Submit button)
        getWait().until(ExpectedConditions.visibilityOf(companyErrMsg));
        elementFound(companyErrMsg, "Company Error Message");
    }
    public void waitForIndiaFolderErrMsg(){ // wait for the error message to be displayed (after clicking Submit button)
        getWait().until(ExpectedConditions.visibilityOf(folderIndiaErrMsg));
        elementFound(folderIndiaErrMsg, "Folder Error Message");
    }

    public String companyErrorDisplayed(){
        if(companyErrMsg.isDisplayed()){
            return companyErrMsg.getText();
        }
        else{
            return "COMPANY ERROR MESSAGE NOT DISPLAYED";
        }
    }

    public String folderErrorDisplayed(){
        if(folderErrMsg.isDisplayed()){
            return folderErrMsg.getText();
        }
        else{
            return "COMPANY ERROR MESSAGE NOT DISPLAYED";
        }
    }

    public String folderIndiaErrorDisplayed(){
        if(folderIndiaErrMsg.isDisplayed()){
            return folderIndiaErrMsg.getText();
        }
        else{
            return "INDIA FOLDER ERROR MESSAGE NOT DISPLAYED";
        }
    }

    public void waitForDivErrMsg(){ // wait for the error message to be displayed (after clicking Submit button)
        getWait().until(ExpectedConditions.visibilityOf(divisionErrMsg));
        elementFound(divisionErrMsg, "Division Error Message");
    }

    public String divisionErrorDisplayed(){
        if(divisionErrMsg.isDisplayed()){
            return divisionErrMsg.getText();
        }
        else{
            return "DIVISION ERROR MESSAGE NOT DISPLAYED";
        }
    }

    public String branchErrorDisplayed(){
        if(branchErrMsg.isDisplayed()){
            return branchErrMsg.getText();
        }
        else{
            return "BRANCH ERROR MESSAGE NOT DISPLAYED";
        }
    }

    public String emailNotificationReminderText(){
        if(emailNotificationReminderText.isDisplayed()){
            return emailNotificationReminderText.getText(); //should be "(Multiple mail separated by ; )"
        }
        else{
            return "EMAIL REMINDER TEXT NOT DISPLAYED";
        }
    }

    public boolean addRecordModalDisplayed(){
        elementFound(addRecordModal, "Add Record Modal");
        return addRecordModal.isDisplayed();
    }

    public boolean waitForAddRecordClosed(){
        waitForElementGone(addRecordModal);
        if(elementFound(addRecordModal, "Record Detail Form")){
            return addRecordModal.isDisplayed();
        }
        return false;
    }

    public boolean waitForAddRecordConfirmModal(){
        getWait().until(ExpectedConditions.visibilityOf(addRecordConfirm));
        return addRecordConfirm.isDisplayed();
    }

    public String checkAddRecordConfirmModal(){
        elementFound(addRecordConfirm, "Add Record Confirmation Modal");
        if(addRecordConfirm.isDisplayed()){
            return addRecordConfirm.getText();
        }
        else{
            return "CONFIRM MODAL NOT DISPLAYED";
        }
    }

    public String getConfirmRecordNum(){
        elementFound(addRecordConfirm, "Add Record Confirmation Modal");
        return addRecordConfirm.getText().split("\\W+")[3]; // pulled from https://stackoverflow.com/questions/10563202/java-split-a-string-by-space-new-line-tab-punctuation
    }

    public void clickConfirmOKBtn(){
        elementFound(addRecordConfirmOKBtn, "Add Record Confirmation Modal OK Button");
        addRecordConfirmOKBtn.click();
        waitForElementGone(addRecordConfirmOKBtn);
    }

    public boolean confirmModalNotDisplayed(){
        return waitForElementGone(addRecordConfirm);
    }

    public boolean waitForEditRecordConfirmModal(){
        getWait().until(ExpectedConditions.visibilityOf(editRecordConfirm));
        return editRecordConfirm.isDisplayed();
    }

    public String checkEditRecordConfirmModal(){
        elementFound(editRecordConfirm, "Add Record Confirmation Modal");
        if(editRecordConfirm.isDisplayed()){
            return editRecordConfirm.getText();
        }
        else{
            return "CONFIRM MODAL NOT DISPLAYED";
        }
    }

    public void clickEditConfirmOKBtn(){
        elementFound(editRecordConfirmOKBtn, "Add Record Confirmation Modal OK Button");
        editRecordConfirmOKBtn.click();
        waitForElementGone(editRecordConfirmOKBtn);
    }

    public boolean confirmEditModalNotDisplayed(){
        return waitForElementGone(editRecordConfirm);
    }

    public boolean elementFound(WebElement targetElement, String elementName){
        try {
            if(targetElement.isDisplayed()){
                return true;
            }
            else{
                System.out.println("Element " + elementName + " was not displayed.");
                return false;
            }
        }catch(NoSuchElementException | ElementNotInteractableException e){
            System.out.println("Element " + elementName + " does not exist, or cannot be interacted with.");
            return false;
        }
    }

    public boolean elementFound(List<WebElement> targetElements, String elementName){
        try {
            for (WebElement targetElement : targetElements) {
                if (!targetElement.isDisplayed()) {
                    System.out.println(elementName + " was not displayed.");
                    return false;
                }
            }
        }catch(NoSuchElementException | ElementNotInteractableException e){
            System.out.println(elementName + " does not exist, or cannot be interacted with.");
            return false;
        }
        return true;
    }

    public boolean waitForElementGone(WebElement gallowsElement){
        long startTime = System.currentTimeMillis();
        while((System.currentTimeMillis() - startTime) < 5000){ // lifted from NodeApp
            try{
                if(gallowsElement.isDisplayed()) { // element is displayed: wait longer
                    waitForMiliseconds(500);
                }
                else{ // element is hidden: we're done
                    return true;
                }
            }
            catch(NoSuchElementException | ElementNotInteractableException e){ // element doesn't exist: we're done
                return true;
            }
        }
        return false;
    }

    public void waitForMiliseconds(int miliseconds){
        try {
            Thread.sleep(miliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String verifyMeterNameFilled(){
        return commonDropDownRead("Meter Name");
    }

    public void cannotMakeDuplicateRecordsSetup(){
        System.out.println("Making a new record to confirm ID is not reused.");
        Assert.assertTrue(commonButton("Add Record"), "Add Record button could not be found on page");
        pageLoaded();
        commonDropDownSelect("Company", "Company 019");
        Assert.assertTrue(commonButton("Submit"), "Add Record button could not be found on page");
    }

    public boolean cmFieldDisplayed(){
        return elementFound(certifiedMailField, "CM # Field");
    }

    public boolean cmrrFieldDisplayed(){
        return elementFound(certifiedMailReturnReceiptField, "CMRR # Field");
    }

    public boolean clearField(WebElement field){
        long startTime = System.currentTimeMillis();
        while(!field.getAttribute("value").equals("") && ((System.currentTimeMillis() - startTime) < 5000)){ // lifted from NodeApp
            field.sendKeys(Keys.BACK_SPACE);
        }
        return field.getAttribute("value").equals("");
    }

    public boolean requiredAsterixDisplayed(String field){
        return switch (field) {
            case "Folder" -> folderAsterisk.isDisplayed();
            case "Company" -> companyAsterisk.isDisplayed();
            case "Division" -> divisionAsterisk.isDisplayed();
            case "Branch" -> branchAsterisk.isDisplayed();
            default -> false;
        };
        // todo: maybe also confirm element colour is red.
    }

    public String checkPDFPage(){
        // could also use the sendKeys method to send CTRL+T to switch tabs.

        String mainTab = getDriver().getWindowHandle();
        // String newTab = getDriver().getWindowHandles()[1];
        ArrayList<String> newTab = new ArrayList<>(getDriver().getWindowHandles());
        newTab.remove(mainTab); // lifted from https://stackoverflow.com/questions/12729265/switch-tabs-using-selenium-webdriver-with-java
        getDriver().switchTo().window(newTab.get(0));
        String url = getURL();
        getDriver().close();
        getDriver().switchTo().window(mainTab);
        return url;
    }

    public String getURL(){
        return getDriver().getCurrentUrl();
    }

    public void prepareConditionalTest(){
        if(certifiedMailChBox.isSelected()){
            commonCheckBox("Certified Mail");
        }
        if(certifiedMailChBox.isSelected()){
            commonCheckBox("Certified Mail with Return Receipt");
        }
        waitForElementGone(certifiedMailField);
        waitForElementGone(certifiedMailReturnReceiptField);
    }

    public void clickPrintShopResetButton() {
        // todo: moved this to common grid. Depricate this version.
        printShopGridResetBtn.click();
    }

    public String getIndicatorMouseoverText(String colour) {
        WebElement colourBox = getDriver().findElement(By.className(colour.toLowerCase()+"-row"));
//        Actions action = new Actions(getDriver());
//        action.moveToElement(colourBox).perform();
        // todo: figure out how to mouse over the element, and verify the text is displayed.
        // May be a browser thing, because text does not appear in the DOM.
        return colourBox.getAttribute("title");
    }

    // Selectors
    @FindBy(how = How.ID, using = "typeaheadValue")
    private WebElement topSearchBar;

    @FindBy(how = How.XPATH, using = "//*[@id=\"the-basics-new\"]/span/div/div/p")
    private WebElement topSearchDropdown;

    @FindBy(how = How.XPATH, using = "//*[@id=\"the-basics-new\"]/span/div/div/p")
    private List<WebElement> allSearchDropdown;

    @FindBy(how = How.ID, using = "ps_WoNumber1")
    private WebElement workOrderNumber;

    @FindBy(how = How.ID, using = "Company_err")
    private WebElement companyErrMsg;

    @FindBy(how = How.ID, using = "Folder_err")
    private WebElement folderErrMsg;

    @FindBy(how = How.ID, using = "Folder_err1")
    private WebElement folderIndiaErrMsg;

    @FindBy(how = How.ID, using = "Division_err")
    private WebElement divisionErrMsg;

    @FindBy(how = How.ID, using = "Branch_err")
    private WebElement branchErrMsg;

    @FindBy(how = How.XPATH, using = "//*[@id=\"emailnotification\"]/div/div/div/div[1]/label/span")
    private WebElement emailNotificationReminderText;

    @FindBy(how = How.ID, using = "printshopDetailForm")
    private WebElement addRecordModal; //printshopDetailForm //addPrintshop

    @FindBy(how = How.XPATH, using = "/html/body/div[5]/div[2]/div/div/div/div/div/div/div")
    private WebElement addRecordConfirm;

    @FindBy(how = How.XPATH, using = "/html/body/div[5]/div[2]/div/div/div/div/div/div/div/div[4]/button")
    private WebElement addRecordConfirmOKBtn;

    @FindBy(how = How.XPATH, using = "/html/body/div[3]/div[2]/div/div/div/div/div/div/div")
    private WebElement editRecordConfirm;

    @FindBy(how = How.XPATH, using = "/html/body/div[3]/div[2]/div/div/div/div/div/div/div/div[4]/button")
    private WebElement editRecordConfirmOKBtn;

    @FindBy(how = How.ID, using = "IsCertifiedMail")
    private WebElement certifiedMailChBox;

    @FindBy(how = How.ID, using = "IsCertifiedMailWithReturnReceipt")
    private WebElement certifiedMailReturnReceiptChBox;

    @FindBy(how = How.ID, using = "CertifiedMailNumber")
    private WebElement certifiedMailField;

    @FindBy(how = How.ID, using = "CertifiedMailWithReturnReceiptNumber")
    private WebElement certifiedMailReturnReceiptField;

    @FindBy(how = How.XPATH, using = "//*[@id=\"Details\"]/div/div/div/div/div[1]/div[1]/label/span[1]")
    private WebElement folderAsterisk;

    @FindBy(how = How.XPATH, using = "//*[@id=\"printshopDetailForm\"]/div[1]/div[3]/label/span[1]")
    private WebElement companyAsterisk;

    @FindBy(how = How.ID, using = "Division_compl")
    private WebElement divisionAsterisk;

    @FindBy(how = How.ID, using = "Branch_compl")
    private WebElement branchAsterisk;

    @FindBy(how = How.XPATH, using = "//*[@id=\"dtprocessing_wrapper\"]/div[1]/div[2]/div/div[3]")
    //*[@id="dtprocessing_wrapper"]/div[1]/div[2]/div/div[3]
    private WebElement printShopGridResetBtn;

    @Override
    public boolean onCorrectPage(){
        return getDriver().getCurrentUrl().contains("printshop");}

    // Override functions for NodeApp class
    @Override
    public KpiClass addKpi(int number){return null;}
    @Override
    public boolean gridTab(String tabName){return false;}
    @Override
    public int gridPageNumber(String tabName, String result){return 0;}
    @Override
    public int gridRandomPage(String tabName){return 0;}
    @Override
    public boolean gridNextPage(String tabName){return false;}
    @Override
    public boolean gridPrevPage(String tabName){return false;}
    @Override
    public void gridViewSelection(String tabName, String option){}
    @Override
    public int gridRecordNumber(String tabName){return 0;}
    @Override
    public int gridRowCount(String tabName){return 0;}
}
