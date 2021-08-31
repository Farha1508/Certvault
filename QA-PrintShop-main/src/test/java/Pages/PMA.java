package Pages;

import Base.KpiClass;
import Base.NodeApp;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.HashMap;
import java.util.List;

public class PMA extends NodeApp {
    
    private HashMap<String, String> gridMap = new HashMap<>();
    private HashMap<String, String> checkMap = new HashMap<>();

    public PMA(WebDriver driver, JavascriptExecutor js) {
        super(driver, js);
        initializeMaps();
    }

    @FindBy(how = How.XPATH, using = "//*[@id=\"index-root\"]/span")
    private WebElement pageName;
    @FindBy(how = How.ID, using = "index1")
    private WebElement pageName2;

    @Override
    public boolean onCorrectPage() {
        if(pageName.isDisplayed() && pageName2.getAttribute("class").contains("active")) {
            return true;
        }
        return false;
    }


/*
    @Override
    public boolean commonCheckBox(String checkBox) {
        String boxName = checkBox.toLowerCase().replaceAll("\\s+", "");
        try {
            super.getJs().executeScript("document.getElementById('"+checkMap.get(boxName)+"').click()");
//            super.getDriver().findElement(By.id(checkMap.get(boxName))).click();
            return true;
        } catch (Exception ignored) {

        }
        return false;


    }

 */


    // KPI stuff
    // My Open Opportunities
    @FindBy(how = How.ID, using = "kpi3Count")
    private WebElement kpi3Value;
    @FindBy(how = How.XPATH, using = "//*[@id=\"kpi3\"]/div[2]/div[2]")
    private WebElement kpi3Title;

    //My New Opportunities
    @FindBy(how = How.ID, using = "kpi1Count")
    private WebElement kpi1Value;
    @FindBy(how = How.XPATH, using = "//*[@id=\"kpi1\"]/div[2]/div[2]")
    private WebElement kpi1Title;

    // My bound Opportunities this month
    @FindBy(how = How.XPATH, using = "//*[@id=\"kpi2\"]/div[2]/div[1]")
    private WebElement kpi2Heading; // Header for KPI block
    @FindBy(how = How.ID, using = "kpi2Count")
    private WebElement kpi2Value; // ammount for commission
    @FindBy(how = How.XPATH, using = "//*[@id=\"kpi2Count\"]/span")
    private WebElement kpi2Title; // Commission title
    @FindBy(how = How.ID, using = "kpi5Count")
    private WebElement kpi5Value; // amount for count
    @FindBy(how = How.XPATH, using = "//*[@id=\"kpi5Count\"]/span")
    private WebElement kpi5Title; // count title

    // My bound opportunities this year
    @FindBy(how = How.XPATH, using = "//*[@id=\"kpi4\"]/div[2]/div[1]")
    private WebElement kpi4Heading;
    @FindBy(how = How.ID, using = "kpi4Count")
    private WebElement kpi4Value; // Commission amount
    @FindBy(how = How.XPATH, using = "//*[@id=\"kpi4Count\"]/span")
    private WebElement kpi4Title; // Commission text
    @FindBy(how = How.ID, using = "kpi6Count")
    private WebElement kpi6Value; // Count amount
    @FindBy(how = How.XPATH, using = "//*[@id=\"kpi6Count\"]/span")
    private WebElement kpi6Title; // Count title

    @Override
    public KpiClass addKpi(int number){
        String title;
        int intValue;
        float floatValue;
        KpiClass listEntry;
        String numExtraction;
        StringBuilder stringBuilder;

        switch(number){
            case 0:
                // My Open Opportunities
                title = kpi3Title.getText();
                intValue = Integer.parseInt(kpi3Value.getText());
                listEntry = new KpiClass(title, intValue);
                break;
            case 1:
                // My New Opportunities
                title = kpi1Title.getText();
                intValue = Integer.parseInt(kpi1Value.getText());
                listEntry = new KpiClass(title, intValue);
                break;
            case 2:
                // My Bound Opportunities this Month Commission
                title = kpi2Heading.getText() + " " + kpi2Title.getText();
                // Using .getText() on kpiValue2 gives 'Commission $0.00' (with 0.00 being whatever the amount is)
                // The amount needs to be extracted from the string
                numExtraction = kpi2Value.getText();
                stringBuilder = new StringBuilder();
                for(int i = 0; i < numExtraction.length(); i++) {
                    if(Character.isDigit(numExtraction.charAt(i)) || numExtraction.charAt(i) == '.'){
                        stringBuilder.append(numExtraction.charAt(i));
                    }
                }
                floatValue = Float.parseFloat(stringBuilder.toString());
                listEntry = new KpiClass(title, floatValue);
                break;
            case 3:
                // My Bound Opportunities this Month Count
                title = kpi2Heading.getText() + " " + kpi5Title.getText();
                // Using .getText() on kpiValue5 gives 'Count 0' (with 0 being whatever the amount is)
                // The amount needs to be extracted from the string
                numExtraction = (kpi5Value.getText());
                stringBuilder = new StringBuilder();
                for(int i = 0; i < numExtraction.length(); i++) {
                    if(Character.isDigit(numExtraction.charAt(i))) {
                        stringBuilder.append(numExtraction.charAt(i));
                    }
                }
                intValue = Integer.parseInt(stringBuilder.toString());
                listEntry = new KpiClass(title, intValue);
                break;
            case 4:
                // My Bound Opportunities This Year Commission
                title = kpi4Heading.getText() + " " + kpi4Title.getText();

                numExtraction = kpi4Value.getText();
                stringBuilder = new StringBuilder();
                for(int i = 0; i < numExtraction.length(); i++) {
                    if(Character.isDigit(numExtraction.charAt(i)) || numExtraction.charAt(i) == '.'){
                        stringBuilder.append(numExtraction.charAt(i));
                    }
                }
                floatValue = Float.parseFloat(stringBuilder.toString());
                listEntry = new KpiClass(title, floatValue);
                break;
            case 5:
                // My Bound Opportunities This Year Count
                title = kpi4Heading.getText() + " " + kpi6Title.getText();

                numExtraction = (kpi6Value.getText());
                stringBuilder = new StringBuilder();
                for(int i = 0; i < numExtraction.length(); i++) {
                    if(Character.isDigit(numExtraction.charAt(i))) {
                        stringBuilder.append(numExtraction.charAt(i));
                    }
                }
                intValue = Integer.parseInt(stringBuilder.toString());
                listEntry = new KpiClass(title, intValue);
                break;

            default:
                return null;
        }

        return listEntry;

    }

    @Override
    public boolean gridTab(String tabName) {
        String tabFix = tabName.toLowerCase().replaceAll("\\s+", "");
        WebElement tab = super.getDriver().findElement(By.id(gridMap.get(tabFix + "tab")));


        try {
            if (!tab.getAttribute("class").contains("active")) {
                while(!tab.getAttribute("class").contains("active")) {
                    try{
                        tab.click();
                    } catch (ElementClickInterceptedException e) {
                        clickErrorHandle(e.toString(), tab);
                    }
                }

            } else {
                System.out.println(tabName + " is already the current tab.");
            }
        } catch (NoSuchElementException e) {
            return false;
        }

        super.getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\""+gridMap.get(tabFix)+"\"]/thead//tr/th/div")));

        return true;

    }

    WebElement gridPageNum;
    WebElement totalPages;

    @Override
    public int gridPageNumber(String tabName, String result) {
        String tabNameFixed = tabName.replaceAll("\\s+", ""). toLowerCase();

        if(super.getDriver().findElement(By.id(gridMap.get(tabNameFixed) + "_paginate")).getAttribute("style").equals("display: none;")) {
            return 0;
        }
        try{
            gridPageNum = super.getDriver().findElement(By.cssSelector("#" + gridMap.get(tabNameFixed) + "_paginate .pagination-panel-input"));
            totalPages = super.getDriver().findElement(By.cssSelector("#" + gridMap.get(tabNameFixed) + "_paginate .pagination-panel-total"));
        } catch (NoSuchElementException e) {
            return -1;
        }

        String pageNum = gridPageNum.getAttribute("value");
        if(result.equals("current")) {
            return Integer.parseInt(pageNum);
        } else if(result.equals("total")) {
            return Integer.parseInt(totalPages.getText());
        }

        return -1;
    }

    @Override
    public int gridRandomPage(String tabName) {
        int totalPages = gridPageNumber(tabName, "total");
        String tabNameFixed = tabName.replaceAll("\\s+", ""). toLowerCase();
        WebElement pageNum = super.getDriver().findElement(By.cssSelector("#" + gridMap.get(tabNameFixed) + "_paginate .pagination-panel-input"));
        System.out.println("Total number of available pages is: " + totalPages);
        if(totalPages == 1){
            return totalPages;
        } else {
            int randomPage = (int) (Math.random() * (totalPages - 1)) + 1;
            pageNum.click();
            pageNum.sendKeys(Keys.chord(Keys.LEFT_CONTROL, "a"));
            pageNum.sendKeys(Integer.toString(randomPage));
            pageNum.sendKeys(Keys.ENTER);
            return gridPageNumber(tabName, "current");
        }

    }

    @Override
    public boolean gridNextPage(String tabName) {
        WebElement nextBtn = super.getDriver().findElement(By.cssSelector("#" + gridMap.get(tabName.replaceAll("\\s+", ""). toLowerCase()) + "_paginate .next"));
        if(nextBtn.getAttribute("class").contains("disabled")) {
            return false;
        } else {
            nextBtn.click();
            return true;
        }

    }

    @Override
    public boolean gridPrevPage(String tabName) {
        WebElement prevBtn = super.getDriver().findElement(By.cssSelector("#" + gridMap.get(tabName.replaceAll("\\s+", ""). toLowerCase()) + "_paginate .prev"));
        if(prevBtn.getAttribute("class").contains("disabled")) {
            return false;
        } else {
            prevBtn.click();
            return true;
        }
    }

    @Override
    public void gridViewSelection(String tabName, String option) {
        System.out.println("Selecting " + option + " from Viewing dropdown");
        WebElement dropDown = super.getDriver().findElement(By.xpath("//*[@id=\""+gridMap.get(tabName.toLowerCase().replaceAll("\\s+", ""))+"_length\"]/label/select"));
        Select rows = new Select(dropDown);
        dropDown.click();
        rows.selectByVisibleText(option);
    }

    @Override
    public int gridRecordNumber(String tabName) {
        String currentTab = gridMap.get(tabName.toLowerCase().replaceAll("\\s+", ""));
        int recordCount;

        String records = super.getDriver().findElement(By.id(currentTab + "_info")).getText();
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 0; i < records.length(); i++) {
            if(Character.isDigit(records.charAt(i))) {
                stringBuilder.append(records.charAt(i));
            }
        }
        if(stringBuilder.length() == 0) {
            return 0;
        }

        recordCount = Integer.parseInt(stringBuilder.toString());

        return recordCount;
    }

    @Override
    public int gridRowCount(String tabName) {
        String currentTab = gridMap.get(tabName.toLowerCase().replaceAll("\\s+", ""));

        List<WebElement> rows = super.getDriver().findElements(By.xpath("//*[@id=\""+currentTab+"\"]/tbody/tr"));
        return rows.size();

    }

    private void initializeMaps() {
        gridMap.put("opportunitytab", "tabbtnopportunity");
        gridMap.put("opportunity", "dtopportunity");
        gridMap.put("businesstab", "tabbtnbusiness");
        gridMap.put("business", "dtbusiness");
        gridMap.put("importedleadstab", "tabbtnimportedleads");
        gridMap.put("importedleads", "dtimportedleads");
        gridMap.put("activitiestab", "tabbtnactivities");
        gridMap.put("activities", "dtactivities");
        gridMap.put("notes", "Notes_sects");
        gridMap.put("followupstab", "tabbtnfollowups");
        gridMap.put("followups", "dtfollowups");

        checkMap.put("markasselectedquote", "quote_mark_selec");
        checkMap.put("carrierdeclined", "quote_carr_decl");
        checkMap.put("submitted", "quote_submitted");
        checkMap.put("activitycompleted", "aa-Actv_Completed");
        checkMap.put("activityperformed", "aa-actvity_perfm");
        checkMap.put("reviewdesired", "aa-Review_desired");
        checkMap.put("followuprequired", "aa-follow_up_req");


    }


}
