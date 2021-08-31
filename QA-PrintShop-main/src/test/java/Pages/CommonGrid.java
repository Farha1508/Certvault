package Pages;

import Base.BaseUtil;
import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommonGrid {
    private final WebDriver driver;
    private final JavascriptExecutor js;
    private final WebDriverWait wait;
    private final HashMap<String, String> gridMap = new HashMap<>();

    public CommonGrid(WebDriver driver, JavascriptExecutor js) {
        this.driver = driver;
        this.js = js;
        this.wait = new WebDriverWait(this.driver, 10);
        PageFactory.initElements(driver, this);
        initializeMaps();
    }
    public boolean gridTab(String tabName) {
        String tabFix = tabName.toLowerCase().replaceAll("\\s+", "");
        WebElement tab = driver.findElement(By.id(gridMap.get(tabFix + "tab")));
        BaseUtil.pageLoaded();
        try{
            tab.click();
        } catch (ElementClickInterceptedException e) {
            clickErrorHandle(e.toString(), tab);
        }
        BaseUtil.pageLoaded();
        return true;

    }

    WebElement gridPageNum;
    WebElement totalPages;

// used to get either the current page number, or the total number of pages.
    public int gridPageNumber(String tabName, String result) {
        // result should be "current" or "total"
        String tabNameFixed = tabName.replaceAll("\\s+", ""). toLowerCase();

        if(driver.findElement(By.id(gridMap.get(tabNameFixed) + "_paginate")).getAttribute("style").equals("display: none;")) {
            return 0;
        }
        try{
            gridPageNum = driver.findElement(By.cssSelector("#" + gridMap.get(tabNameFixed) + "_paginate .pagination-panel-input"));
            totalPages = driver.findElement(By.cssSelector("#" + gridMap.get(tabNameFixed) + "_paginate .pagination-panel-total"));
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

        public int gridRandomPage(String tabName) {
        int totalPages = gridPageNumber(tabName, "total");
        String tabNameFixed = tabName.replaceAll("\\s+", ""). toLowerCase();
        WebElement pageNum = driver.findElement(By.cssSelector("#" + gridMap.get(tabNameFixed) + "_paginate .pagination-panel-input"));
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


    public boolean gridNextPage(String tabName) {
        WebElement nextBtn;
        if(gridPageNumber(tabName, "current") < 0) {
            nextBtn = driver.findElement(By.cssSelector("#" + gridMap.get(tabName.replaceAll("\\s+", ""). toLowerCase()) + "_next"));
            if(nextBtn.getAttribute("class").contains("disabled")) {
                return false;
            } else {
                nextBtn.findElement(By.tagName("a")).click();
                return true;
            }
        } else {
            nextBtn = driver.findElement(By.cssSelector("#" + gridMap.get(tabName.replaceAll("\\s+", ""). toLowerCase()) + "_paginate .next"));
            if(nextBtn.getAttribute("class").contains("disabled")) {
                return false;
            } else {
                nextBtn.click();
                return true;
            }
        }

    }

    public boolean gridPrevPage(String tabName) {
        WebElement prevBtn;
        if(gridPageNumber(tabName, "current") < 0) {
            prevBtn = driver.findElement(By.cssSelector("#" + gridMap.get(tabName.replaceAll("\\s+", ""). toLowerCase()) + "_previous"));
            if(prevBtn.getAttribute("class").contains("disabled")) {
                return false;
            } else {
                prevBtn.findElement(By.tagName("a")).click();
                return true;
            }
        } else {
            prevBtn = driver.findElement(By.cssSelector("#" + gridMap.get(tabName.replaceAll("\\s+", ""). toLowerCase()) + "_paginate .prev"));
            if(prevBtn.getAttribute("class").contains("disabled")) {
                return false;
            } else {
                prevBtn.click();
                return true;
            }
        }

    }

    public String gridGoToLastPage(String tabName) {
        String lastPage = String.valueOf(gridPageNumber(tabName, "total"));
        List<WebElement> currentPages = driver.findElements(By.className("pagination-panel-input"));
        // class="pagination-panel-input form-control input-sm input-inline input-mini"
        for (WebElement pageSelector : currentPages) {
            if(pageSelector.isDisplayed()){
                pageSelector.click();
                pageSelector.sendKeys(Keys.chord(Keys.LEFT_CONTROL, "a"));
                pageSelector.sendKeys(lastPage);
                pageSelector.sendKeys(Keys.ENTER);
                //pageSelector.submit();
                return lastPage;
            }
        }
        return null;
    }

//    public String gridTotalPages() {
//        List<WebElement> pageTotals = driver.findElements(By.className("pagination-panel-total"));
//        for (WebElement pageTotal : pageTotals) {
//            if(pageTotal.isDisplayed()){
//                return pageTotal.getText();
//            }
//        }
//        return null;
//    }

    public void gridViewSelection(String tabName, String option) {
        System.out.println("Selecting " + option + " from Viewing dropdown");
        WebElement dropDown = driver.findElement(By.xpath("//*[@id='"+gridMap.get(tabName.toLowerCase().replaceAll("\\s+", ""))+"_length']/label/select"));
        Select rows = new Select(dropDown);
        dropDown.click();
        rows.selectByVisibleText(option);
    }


    public int gridRecordNumber(String tabName) {
        String currentTab = gridMap.get(tabName.toLowerCase().replaceAll("\\s+", ""));
        int recordCount;

        String records = driver.findElement(By.id(currentTab + "_info")).getText();
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


    public int gridRowCount(String tabName) {
        String currentTab = gridMap.get(tabName.toLowerCase().replaceAll("\\s+", ""));
        List<WebElement> rows = driver.findElements(By.xpath("//*[@id='"+currentTab+"']/tbody/tr"));
        return rows.size();

    }

    /**
     * Will search the page for the specified grid header
     * @param tab
     * @param header
     * @return Returns <code>true</code> if header is found in grid, otherwise returns <code>false</code>
     */
    public boolean gridHeaderFind(String tab, String header) {
        String tabName = tab.replaceAll("\\s", "").toLowerCase();
        try {
            return driver.findElement(By.xpath("//table[@id='"+gridMap.get(tabName)+"']/descendant::th[contains(@aria-label, '"+header+"')]")).isDisplayed();
//            return driver.findElement(By.xpath("//table[@id='"+gridMap.get(tabName)+"']/descendant::th[normalize-space(@aria-label)='"+header+"']")).isDisplayed();
        } catch (Exception e) {
            return false;
        }

    }

    /**
     * Sort the column on the grid
     * @param headerName <code>String</code> Name of the grid header to be sorted
     * @param sort <code>String</code> Choose whether to sort by Ascending or Descending
     * @return <code>boolean</code> Returns true if sorting was successful, otherwise returns false
     */
    public boolean gridHeaderSort(String headerName, String sort) {
        String option = sort.equalsIgnoreCase("ascending") ? "filterHeader sorting_asc" : "filterHeader sorting_desc";
        WebElement selectedHeader = null;
        List<WebElement> headers = driver.findElements(By.xpath(
                "//*[contains(@class, 'filterHeader sorting')][normalize-space()='" + headerName + "']"));

        if (headers.size() > 0) {
            long startTime = System.currentTimeMillis();
            while (selectedHeader == null && (System.currentTimeMillis() - startTime) < 5000) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ignored) {

                }
                for (WebElement hdr : headers) {
                    if (hdr.isDisplayed()) {
                        selectedHeader = hdr;
                    }
                }
            }
        }

        if(selectedHeader == null) {
            return false;
        }
        String classInfo;
        selectedHeader.click();
        BaseUtil.pageLoaded();
        classInfo = selectedHeader.getAttribute("class");
        if(!classInfo.equals(option)) {
            selectedHeader.click();
            BaseUtil.pageLoaded();
            classInfo = selectedHeader.getAttribute("class");
            if(!classInfo.equals(option)) {
                return false;
            }
        }
        return true;
    }

    public boolean gridHeaderSorted(String headerName, String sort) {
        // sort should be "ascending" or "descending".
        String option = sort.equalsIgnoreCase("ascending") ? "filterHeader sorting_asc" : "filterHeader sorting_desc";
        WebElement selectedHeader = null;
        List<WebElement> headers = driver.findElements(By.xpath("//div[contains(@class, 'filterHeader') and normalize-space()='"+headerName+"']/../input"));

        if (headers.size() > 0) {
            long startTime = System.currentTimeMillis();
            while (selectedHeader == null && (System.currentTimeMillis() - startTime) < 5000) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ignored) {

                }
                for (WebElement hdr : headers) {
                    if (hdr.isDisplayed()) {
                        selectedHeader = hdr;
                    }
                }
            }
        }

        if(selectedHeader == null) {
            System.out.println("Could not find "+headerName+" column.");
            return false;
        }

        String classInfo = selectedHeader.getAttribute("class");
        return classInfo.equals(option);

    }

    private void initializeMaps() {
        // Purchase Orders tabs
        gridMap.put("requeststab", "tabbtnRequests");
        gridMap.put("requests", "dtRequests");
        gridMap.put("purchaseorderstab", "tabbtnPurchaseOrders");
        gridMap.put("purchaseorders", "dtPurchaseOrder");
        gridMap.put("pendingapprovaltab", "tabbtnApproval");
        gridMap.put("pendingapproval", "dtApproval");
        // Policy Checking tabs
        gridMap.put("openpoliciestab", "tabbtnopenpolicies");
        gridMap.put("openpolicies", "dtopenpolicies");
        gridMap.put("completedpoliciestab", "tabbtncompletedpolicies");
        gridMap.put("completedpolicies", "dtcompletedpolicies");
        gridMap.put("discardedpoliciestab", "tabbtndiscardedpolicies");
        gridMap.put("discardedpolicies", "dtdiscardedpolicies");
        gridMap.put("timerecopen", "dtTimeRecord");
        gridMap.put("timerecord", "dtTimeRecord");
        // Policy Checking Work Order tabs
        gridMap.put("attachmentstab", "attachments_sects");
        gridMap.put("attachments", "dtattachments");
        gridMap.put("emailtab", "Email_sects");
        gridMap.put("emails", "dtemails");
        gridMap.put("historytab", "history_sects");
        gridMap.put("history", "dthistory");
        gridMap.put("timerecords", "dttimerecords");

        gridMap.put("open", "dtprocessing");
        gridMap.put("opentab", "tabbtnprocessing");
        // Certificate Issuance tabs
        gridMap.put("inprocess", "dtprocessing");
        gridMap.put("inprocesstab", "tabbtnprocessing");
        gridMap.put("completed", "dtcompleted");
        gridMap.put("completedtab", "tabbtncompleted");
        gridMap.put("discarded", "dtdiscarded");
        gridMap.put("discardedtab", "tabbtndiscarded");
        gridMap.put("incomingresponsestab", "tabbtn-incoming-responses");
        gridMap.put("incomingresponses", "dt-incoming-responses");
        gridMap.put("importantinstructiontab", "tabbtn-important-instruction");
        gridMap.put("importantinstruction", "dt-important-instruction");
        gridMap.put("tobediscardedtab", "tabbtn-tobeDiscarded");
        gridMap.put("tobediscarded", "dt-tobeDiscarded");

        gridMap.put("exceptionstab", "tabbtnException");
        gridMap.put("exceptions", "dtException");
        // PMA Tabs
        gridMap.put("opportunitytab", "tabbtnopportunity");
        gridMap.put("opportunity", "dtopportunity");
        gridMap.put("businesstab", "tabbtnbusiness");
        gridMap.put("business", "dtbusiness");
        gridMap.put("importedleadstab", "tabbtnimportedleads");
        gridMap.put("importedleads", "dtimportedleads");

        // Implementations tabs
        gridMap.put("implementations", "dt-implementations");
        gridMap.put("implementationstab", "tab-btn-implementations");

        // Print Shop Tabs
        gridMap.put("processingtab", "tabbtnprocessing");
        gridMap.put("processing", "dtprocessing");
        gridMap.put("printedtab", "tabbtnprinted");
        gridMap.put("printed", "dtprinted");
    }

    public String gridHeaderField(String headerName, String input) {
        // This method is used to both search in a header field, and read from it.
        // If the "input" argument is not null, then it will be written to the header.
        // If the "input" argument IS null, then this method will return the value in the field.

        WebElement selectedHeader = null;
        List<WebElement> headers = driver.findElements(By.xpath(
                "//div[contains(@class, 'filterHeader') and normalize-space()='"+headerName+"']/../input"));
        if (headers.size() > 0) {
            long startTime = System.currentTimeMillis();
            while (selectedHeader == null && (System.currentTimeMillis() - startTime) < 5000) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ignored) {

                }
                for (WebElement hdr : headers) {
                    if (hdr.isDisplayed()) {
                        selectedHeader = hdr;
                    }
                }
            }
        }

        if (input == null) {
            assert selectedHeader != null;
            if (selectedHeader.getAttribute("value") == null) {
                return selectedHeader.getText();
            }
            return selectedHeader.getAttribute("value");

        }

        assert selectedHeader != null;
        try {
            selectedHeader.click();

        } catch (ElementClickInterceptedException e) {
            clickErrorHandle(e.toString(), selectedHeader);
        }

        selectedHeader.sendKeys(Keys.chord(Keys.LEFT_CONTROL, "a"));
        selectedHeader.sendKeys(input);
        selectedHeader.click();
        return null;
    }

    public boolean clearHeader(String headerName){
        WebElement selectedHeader = null;
        List<WebElement> headers = driver.findElements(By.xpath(
                "//div[contains(@class, 'filterHeader') and normalize-space()='"+headerName+"']/../input"));
        if (headers.size() > 0) {
            long startTime = System.currentTimeMillis();
            while (selectedHeader == null && (System.currentTimeMillis() - startTime) < 5000) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ignored) {

                }
                for (WebElement hdr : headers) {
                    if (hdr.isDisplayed()) {
                        selectedHeader = hdr;
                    }
                }
            }
        }

        selectedHeader.sendKeys(Keys.chord(Keys.LEFT_CONTROL, "a"));
        selectedHeader.sendKeys(Keys.DELETE);
        return selectedHeader.getAttribute("value").equals("");
    }

    public WebElement gridHeaderSelector(String headerName) {
        WebElement selectedHeader = null;
        List<WebElement> headers = driver.findElements(By.xpath(
                "//div[contains(@class, 'filterHeader') and normalize-space()='"+headerName+"']/..//button"));
        if (headers.size() > 0) {
            long startTime = System.currentTimeMillis();
            while (selectedHeader == null && (System.currentTimeMillis() - startTime) < 5000) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ignored) {

                }
                for (WebElement hdr : headers) {
                    if (hdr.isDisplayed()) {
                        selectedHeader = hdr;
                    }
                }
            }
        } else {
            return null;
        }
        return selectedHeader;
    }

    public boolean gridHeaderSelectorSelect(String headerName, String selection) {
        WebElement selectedHeader = gridHeaderSelector(headerName);

        if (selectedHeader == null) {
            return false;
        }

        try {
            selectedHeader.click();
        } catch (ElementClickInterceptedException e) {
            clickErrorHandle(e.toString(), selectedHeader);
        }

        // The select all option is special and has a different value in the DOM.
        if(selection.toLowerCase().equals("select all")){
            selection = "multiselect-all";
        }

        try {
            selectedHeader.findElement(By.xpath("following-sibling::ul/li/descendant::input[@value='"+selection+"'] ")).click();
        } catch (NoSuchElementException e) {
            return false;
        }

        selectedHeader.click();

        return true;

    }

    public ArrayList<String> gridHeaderSelectorRead(String headerName) {
        ArrayList<String> selections = new ArrayList<>();
        WebElement selectedHeader = gridHeaderSelector(headerName);
        if (selectedHeader == null) {
            return null;
        }
        selectedHeader.click();
        wait.until(ExpectedConditions.visibilityOf(selectedHeader.findElement(By.xpath("following-sibling::ul"))));
        List<WebElement> active = selectedHeader.findElements(By.xpath("following::li[@class='active']"));
        if (active.size() > 0) {
            for (WebElement element : active) {
                selections.add(element.getText().trim());
            }
        } else {
            selectedHeader.click();
            return null;
        }

        selectedHeader.click();
        return selections;
    }

    public WebElement gridHeaderDate(String header, String fromTo) {
        WebElement selectedHeader = null;
      List<WebElement> headers = driver.findElements(By.xpath(
                "//th[contains(@aria-label, '"+header+"')]/descendant::input[contains(@placeholder, '"+fromTo+"')]"));

        if (headers.size() > 0) {
            long startTime = System.currentTimeMillis();
            while (selectedHeader == null && (System.currentTimeMillis() - startTime) < 5000) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ignored) {

                }
                for (WebElement hdr : headers) {
                    if (hdr.isDisplayed()) {
                        selectedHeader = hdr;
                    }
                }
            }
        } else {
            return null;
        }
        return selectedHeader;
    }

    public boolean gridHeaderDateSelect(String header, String fromTo, String day, String month, String year) {
        WebElement selectedHeader = gridHeaderDate(header, fromTo);

        if (selectedHeader == null) {
            return false;
        }

        try {
            wait.until(ExpectedConditions.visibilityOf(selectedHeader)).click();
        } catch (ElementClickInterceptedException e) {
            clickErrorHandle(e.toString(), selectedHeader);
        }

        BaseUtil.pageLoaded();

        WebElement pickerMonthYear = driver.findElement(By.xpath("//div[@class='datepicker-days']/descendant::th[@class='datepicker-switch']"));
        WebElement pickerYear = driver.findElement(By.xpath("//div[@class='datepicker-months']/descendant::th[@class='datepicker-switch']"));
        WebElement pickerNext = driver.findElement(By.xpath("//div[@class='datepicker-months']/descendant::th[@class='next']"));
        WebElement pickerPrev = driver.findElement(By.xpath("//div[@class='datepicker-months']/descendant::th[@class='prev']"));

        String curMY = pickerMonthYear.getText().replaceAll("\\s+", "");
        String curMonth = curMY.replaceAll("\\d", "");
        String curYear = curMY.replaceAll("\\D", "");

        if (!month.equalsIgnoreCase(curMonth) || !year.equalsIgnoreCase(curYear)) {
            pickerMonthYear.click();
            wait.until(ExpectedConditions.visibilityOf(pickerYear));
            if (!pickerYear.getText().equalsIgnoreCase(year)) {
                int myYr = Integer.parseInt(year);
                long startTime = System.currentTimeMillis();
                while (!pickerYear.getText().equalsIgnoreCase(year) && (System.currentTimeMillis() - startTime) < 30000) {
                    int pickYr = Integer.parseInt(pickerYear.getText());
                    if (pickYr < myYr) {
                        pickerNext.click();
                    } else {
                        pickerPrev.click();
                    }
                }

            }
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                    "//div[@class='datepicker-months']/descendant::span[contains(@class, 'month') " +
                            "and text()='" + month.substring(0, 3) + "']"))).click();


        }
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='datepicker-days']" +
                "/descendant::td[contains(@class, 'day') and not(contains(@class, 'new')) and " +
                "not(contains(@class, 'old'))][text()='" + day + "']"))).click();

        return true;

    }

    public boolean gridHeaderDateSelect(String header, Map<String, String> selections) {
        WebElement selectedHeader = gridHeaderDate(header, selections.get("From / To"));

        if (selectedHeader == null) {
            return false;
        }

        try {
            wait.until(ExpectedConditions.visibilityOf(selectedHeader)).click();
        } catch (ElementClickInterceptedException e) {
            clickErrorHandle(e.toString(), selectedHeader);
        }

        BaseUtil.pageLoaded();

        WebElement pickerMonthYear = null;
        List<WebElement> pMY = driver.findElements(By.xpath("//div[@class='datetimepicker-days']/descendant::th[@class='switch']"));
        for(WebElement ele : pMY) {
            if(ele.isDisplayed()) {
                pickerMonthYear = ele;
            }
        }
        String hour = selections.get("Time").replaceAll("(:\\d\\d)", "");
        WebElement pickerYear = null;
        WebElement pickerNext = null;
        WebElement pickerPrev = null;

        assert pickerMonthYear != null;
        String curMY = pickerMonthYear.getText().replaceAll("\\s+", "");
        String curMonth = curMY.replaceAll("\\d", "");
        String curYear = curMY.replaceAll("\\D", "");

        if (!selections.get("Month").equalsIgnoreCase(curMonth) || !selections.get("Year").equalsIgnoreCase(curYear)) {
            pickerMonthYear.click();
            List<WebElement> pY = driver.findElements(By.xpath("//div[@class='datetimepicker-months']/descendant::th[@class='switch']"));
            for (WebElement ele : pY) {
                if(ele.isDisplayed()) {
                    pickerYear = ele;
                }
            }
            List<WebElement> pN = driver.findElements(By.xpath("//div[@class='datetimepicker-months']/descendant::th[@class='next']"));
            for (WebElement ele : pN) {
                if(ele.isDisplayed()) {
                    pickerNext = ele;
                }
            }
            List<WebElement> pP = driver.findElements(By.xpath("//div[@class='datetimepicker-months']/descendant::th[@class='prev']"));
            for (WebElement ele : pP) {
                if(ele.isDisplayed()) {
                    pickerPrev = ele;
                }
            }
            assert pickerYear != null;
            assert pickerPrev != null;
            assert pickerNext != null;
            if (!pickerYear.getText().equalsIgnoreCase(selections.get("Year"))) {
                int myYr = Integer.parseInt(selections.get("Year"));
                long startTime = System.currentTimeMillis();
                while (!pickerYear.getText().equalsIgnoreCase(selections.get("Year")) && (System.currentTimeMillis() - startTime) < 30000) {
                    int pickYr = Integer.parseInt(pickerYear.getText());
                    if (pickYr < myYr) {
                        pickerNext.click();
                    } else {
                        pickerPrev.click();
                    }
                }

            }
            List<WebElement> pM = driver.findElements(By.xpath(
                    "//div[@class='datetimepicker-months']/descendant::span[contains(@class, 'month') " +
                            "and text()='" + selections.get("Month").substring(0, 3) + "']"));
            for (WebElement ele : pM) {
                if(ele.isDisplayed()) {
                    ele.click();
                    break;
                }
            }

        }
        List<WebElement> pD = driver.findElements(By.xpath("//div[@class='datetimepicker-days']" +
                "/descendant::td[contains(@class, 'day') and not(contains(@class, 'new')) and " +
                "not(contains(@class, 'old'))][text()='" + selections.get("Day") + "']"));
        for (WebElement ele : pD) {
            if(ele.isDisplayed()) {
                ele.click();
            }
        }
        List<WebElement> pH = driver.findElements(By.xpath(
                "//div[@class='datetimepicker-hours']/descendant::legend[normalize-space()='"+selections.get("AM / PM")+"']/following-sibling::span[normalize-space()='"+hour+"']"));
        for (WebElement ele : pH) {
            if(ele.isDisplayed()) {
                ele.click();
                break;
            }
        }
        List<WebElement> pMi = driver.findElements(By.xpath(
                "//div[@class='datetimepicker-minutes']/descendant::legend[normalize-space()='"+selections.get("AM / PM")+"']/following-sibling::span[normalize-space()='"+selections.get("Time")+"']"));
        for (WebElement ele : pMi) {
            if(ele.isDisplayed()) {
                ele.click();
                break;
            }
        }

        return true;

    }

    /**
     * Finds and returns the text in the grid cell that matches the requested column and row.
     *
     * @param tableRow    This is the row of the grid being requested. Set this to either the text in the first cell of the row
     *                    being requested (such as the work order number in purchase orders)
     *                    or the number of the row (such as "row 3")
     * @param tableColumn This is the column of the grid being requested. Set this to the header name for the column
     * @return Returns the text in the matching column and row cell of the grid.
     */
    public WebElement gridEntry(String tableRow, String tableColumn) {
        WebElement column = null;
        WebElement row = null;
        String rowPath;
        String colPath;
        String title = null;

        List<WebElement> titles = driver.findElements(By.xpath("//table[@id]"));
        for (WebElement ttlEle : titles) {
            if (ttlEle.isDisplayed() && ttlEle.getAttribute("id").length() > 0) {
                title = ttlEle.getAttribute("id");
                break;
            }
        }

        List<WebElement> setCol = driver.findElements(By.xpath(
                "//th/div[text()='" + tableColumn + "']"));
        for (WebElement colElement : setCol) {
            if (colElement.isDisplayed()) {
                column = colElement;
                break;
            }
        }

        assert column != null;
        colPath = generateXPATH(column, "");
        Pattern colP = Pattern.compile("(?<=th\\[)(\\d+)(?=\\])");
        assert colPath != null;
        Matcher colM = colP.matcher(colPath);
        while (colM.find()) {
            colPath = colM.group();
        }

        if (tableRow.toLowerCase().contains("row")) {
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < tableRow.length(); i++) {
                if (Character.isDigit(tableRow.charAt(i))) {
                    stringBuilder.append(tableRow.charAt(i));
                }

            }
            rowPath = stringBuilder.toString();
        } else {
            List<WebElement> setRow = driver.findElements(By.xpath("" +
                    "//td[normalize-space()='" + tableRow + "']"));
            for (WebElement rowElement : setRow) {
                if (rowElement.isDisplayed()) {
                    row = rowElement;
                    break;
                }
            }

            assert row != null;
            rowPath = generateXPATH(row, "");
            Pattern rowP = Pattern.compile("(?<=tr\\[)(\\d+)(?=\\])");
            assert rowPath != null;
            Matcher rowM = rowP.matcher(rowPath);
            while (rowM.find()) {
                rowPath = rowM.group();
            }
        }

        List<WebElement> results = driver.findElements(By.xpath("" +
                "//*[@id='" + title + "']/tbody/tr[" + rowPath + "]/td[" + colPath + "]"));
        for (WebElement resElement : results) {
            if (resElement.isDisplayed()) {
                return resElement;
            }
        }

        return null;
    }

    public String gridOpenItem(String tabName, String column) {
        int rows = gridRowCount(tabName);
        return "rows in the grid called from gridOpenItem: " + rows;
    }

    public String generateXPATH(WebElement childElement, String current) {
        String childTag = childElement.getTagName();
        if (childTag.equals("html")) {
            return "/html[1]" + current;
        }
        WebElement parentElement = childElement.findElement(By.xpath(".."));
        List<WebElement> childrenElements = parentElement.findElements(By.xpath("*"));
        int count = 0;
        for (int i = 0; i < childrenElements.size(); i++) {
            WebElement childrenElement = childrenElements.get(i);
            String childrenElementTag = childrenElement.getTagName();
            if (childTag.equals(childrenElementTag)) {
                count++;
            }
            if (childElement.equals(childrenElement)) {
                return generateXPATH(parentElement, "/" + childTag + "[" + count + "]" + current);
            }
        }
        return null;
    }

    public void clickErrorHandle(String error, WebElement target) {
        String xPath = "";
        String selector = "";
        Pattern element = Pattern.compile("(?<=click: \\<)(.*?)(?=\\s*\\>)");
        Pattern type = Pattern.compile("^\\w+");
        Pattern tag = Pattern.compile("\\w+=+\"(.*?)\"");
        Matcher eleM = element.matcher(error);
        while (eleM.find()) {
            selector = eleM.group();
        }

        Matcher typeM = type.matcher(selector);
        while (typeM.find()) {
            xPath += "//" + typeM.group();
        }

        Matcher tagM = tag.matcher(selector);
        while (tagM.find()) {
            xPath += "[@" + tagM.group() + "]";
        }
        List<WebElement> blockers = driver.findElements(By.xpath(xPath));
        try {
            wait.until(ExpectedConditions.invisibilityOfAllElements(blockers));
//            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(xPath)));
        } catch (TimeoutException ignored) {

        }

        target.click();
    }

    public void clickTopRecord(String tabName) {
        String currentTab = gridMap.get(tabName.toLowerCase().replaceAll("\\s+", ""));
        try{
            WebElement topWOLink = driver.findElement(By.xpath("//*[@id='"+currentTab+"']/tbody/tr[1]/td[1]/a"));
            topWOLink.click();
            //*[@id="dtprinted"]/tbody/tr[1]/td[1]/a
        }
        catch (NoSuchElementException e) {
            WebElement topWOLink = driver.findElement(By.xpath("//*[@id='"+currentTab+"']/tbody/tr/td[1]/a"));
            topWOLink.click();
            //*[@id="dtprinted"]/tbody/tr/td[1]/a
        }
    }

    public String gridHeaderFieldRead(String headerName) {
        WebElement selectedHeader = null;
        List<WebElement> headers = driver.findElements(By.xpath(
                "//*[contains(@class, 'filterHeader') and normalize-space()='"+headerName+"']/following::input[1]"));

        if(headers.size()==0){
            System.out.println("Header not found.");
            return null;
        }
        return headers.get(0).getAttribute("value");
    }

    public void waitForFilter(String originalRow1RecordID) {
        /* This method waits for the top row of the grid to be different
         indicating that the table has been filtered.
        This will time out if filtering for content already in the top row.
        */
        System.out.println("Waiting for the Grid to be resorted.");
        //String originalTopRecordID = gridEntry("row 1", "Record ID").getText();
        long waitLength = 3000; // Wait up to 3 seconds, but maybe this value could be set somewhere else.
        long startTime = System.currentTimeMillis();
        try {
            while (gridEntry("row 1", "Record ID").getText().equals(originalRow1RecordID) && (System.currentTimeMillis() - startTime) < waitLength) {
                try {
                    Thread.sleep(500); //check table every half second to see if it has been filtered.
                } catch (InterruptedException ignored) {
                    System.out.println("Sleep was interrupted.");
                    //break;
                }
            }
        }catch(StaleElementReferenceException e){
            // This happens when no entries are displayed.
            System.out.println("Grid has no entries.");
            return;
        }

        // Could maybe use this format instead.
        /*try {
            wait.until(ExpectedConditions.stalenessOf(commonGrid.gridEntry("row 1", headerChoice)));
        } catch (TimeoutException ignored) {
        }*/

        if(gridEntry("row 1", "Record ID").getText().equals(originalRow1RecordID)){
            System.out.println("Grid filter wait timed out. Top record is the same after filtering.");
        }
        else {
            System.out.println("Grid has been sorted.");
        }
    }

    public void clickPrintShopResetButton(String currentTab) {
        // Copied waitForFilter() contents here because resetting can be very fast, and by the time the first row was evaluated, the reset had already occured.
        // The waitForFilter() was always waiting the full time.

        String originalTopRecordID = gridEntry("row 1", "Record ID").getText();
        driver.findElement(By.xpath("//*[@id='dt"+currentTab.toLowerCase()+"_wrapper']/div[1]/div[2]/div/div[3]")).click();
        long waitLength = 3000; // Wait up to 3 seconds, but maybe this value could be set somewhere else.
        long startTime = System.currentTimeMillis();
        while(gridEntry("row 1", "Record ID").getText().equals(originalTopRecordID) && (System.currentTimeMillis() - startTime) < waitLength){
            try {
                Thread.sleep(500); //check table every half second to see if it has been filtered.
            } catch (InterruptedException ignored) {
                System.out.println("Sleep was interrupted.");
                //break;
            }
        }
        if(gridEntry("row 1", "Record ID").getText().equals(originalTopRecordID)){
            System.out.println("Grid filter wait timed out. Top record is the same after filtering.");
        }
        else {
            System.out.println("Grid has been sorted.");
        }
    }

    public String verifyBackGroundColor(String tabName){
        String currentTab = gridMap.get(tabName.toLowerCase().replaceAll("\\s+", ""));
        try{
            WebElement gridFirstRow = driver.findElement(By.xpath("//*[@id='"+currentTab+"']/tbody/tr"));
            return gridFirstRow.getCssValue("background-color");}
        catch (java.util.NoSuchElementException e){
            System.out.println("Was unable to find the first row in the "+currentTab+" grid.");
            return null;
        }
    }

    public boolean hasDateAndTime(String columnEntry) {
        // We want to catch strings with a format like "04-21-2021 3:15 PM"
        SimpleDateFormat dateAndTimeFormat = new SimpleDateFormat("MM-dd-yyyy h:mm a", Locale.ENGLISH);
        String topEntry = gridEntry("row 1", columnEntry).getText();

        // Try to cast text to datetime object. If possible, then it's a date + time.
        try{
            Date date = dateAndTimeFormat.parse(topEntry);
        }catch(ParseException e){
            System.out.println(topEntry+" was not a date and time.");
            return false;
        }
        return true;
    }

    public String getTopEntryTime(String columnEntry) {
        // We want to catch strings with a format like "04-21-2021 3:15 PM"
        SimpleDateFormat dateAndTimeFormat = new SimpleDateFormat("MM-dd-yyyy h:mm a", Locale.ENGLISH);
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a", Locale.ENGLISH);
        String topEntry = gridEntry("row 1", columnEntry).getText();
        String time;

        // Try to cast text to datetime object. If possible, then it's a date + time.
        try{
            Date date = dateAndTimeFormat.parse(topEntry);
            time = timeFormat.format(date);
        }catch(ParseException e){
            System.out.println(topEntry+" was not a date and time.");
            return null;
        }
        return time;
    }

    public boolean tableIsEmpty() {
        try {
            return driver.findElement(By.className("dataTables_empty")).isDisplayed();
        }
        catch(NoSuchElementException e){
            System.out.println("Could not find 'No data available in table' element.");
            return false;
        }
    }
}
