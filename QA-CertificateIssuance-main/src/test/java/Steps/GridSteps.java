package Steps;


import Base.BaseUtil;
import Pages.CommonGrid;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class GridSteps extends BaseUtil {

    private CommonGrid commonGrid;

    public GridSteps() {
        this.commonGrid = new CommonGrid(driver, js);
    }

    @And("I get the current grid page number")
    public void iGetTheCurrentGridPageNumber() throws Exception {
        BaseUtil.pageLoaded();
        int result = commonGrid.gridPageNumber(currentTab, "current");
        if (result == 0) {
            System.out.println("Page number not displayed as there are no entries in the grid");
        } else if (result == -1) {
            throw new Exception("Unable to get page number");
        } else {
            System.out.println("Currently on page " + result + " of grid");
        }
    }

    @And("I move to the next page in the grid")
    public void iMoveToTheNextPageInTheGrid() {
        BaseUtil.pageLoaded();
        System.out.println("Clicking next page button in " + currentTab + " grid");
        int currentPage = commonGrid.gridPageNumber(currentTab, "current");
        if (currentPage == -1) {
            if (commonGrid.gridNextPage(currentTab)) {
                System.out.println("Moved to next page in grid");
            } else {
                System.out.println("No next page to move to in grid");
            }
        } else if (currentPage == 0) {
            System.out.println("Cannot go to next page as the grid has no entries");
        } else {
            int newPage;
            if (commonGrid.gridNextPage(currentTab)) {
                BaseUtil.pageLoaded();
                newPage = commonGrid.gridPageNumber(currentTab, "current");
                Assert.assertEquals(newPage, currentPage + 1,
                        "Did not move to previous page after clicking previous page button! Previous page: " + currentPage + " current page: " + newPage);
                System.out.println("Successfully moved to next page " + newPage + " from original page " + currentPage);
            } else {
                System.out.println("Could not go to next page of grid as page " + currentPage + " is the last page");
            }
        }
    }

    @And("I move to the previous page in the grid")
    public void iMoveToThePreviousPageInTheGrid() {
        BaseUtil.pageLoaded();
        System.out.println("Clicking previous page button in " + currentTab + " grid");
        int currentPage = commonGrid.gridPageNumber(currentTab, "current");
        if (currentPage == -1) {
            if (commonGrid.gridPrevPage(currentTab)) {
                System.out.println("Moved to previous page in grid");
            } else {
                System.out.println("No previous page to move to in grid");
            }
        } else if (currentPage == 0) {
            System.out.println("Cannot go to previous page as the grid has no entries");
        } else {
            int newPage;
            if (commonGrid.gridPrevPage(currentTab)) {
                BaseUtil.pageLoaded();
                newPage = commonGrid.gridPageNumber(currentTab, "current");
                Assert.assertEquals(newPage, currentPage - 1,
                        "Did not move to previous page after clicking previous page button! Previous page: " + currentPage + " current page: " + newPage);
                System.out.println("Successfully moved to previous page " + newPage + " from original page " + currentPage);
            } else {
                System.out.println("Could not go to previous page of grid as page " + currentPage + " is the first page");
            }
        }
    }

    @And("I change pages by entering a number")
    public void iEnterANumber() throws Exception {
        BaseUtil.pageLoaded();
        int currentPage = commonGrid.gridPageNumber(currentTab, "current");
        if (currentPage == 0) {
            System.out.println("Cannot change pages as there are no entries in the grid");
        } else {
            int randPage = commonGrid.gridRandomPage(currentTab);
            if (randPage == 1) {
                System.out.println("Only one page, grid displays all available records");

            } else if (randPage == -1) {
                throw new Exception("Unable to find page number");
            } else {
                System.out.println("Moved from page " + currentPage + " to " + randPage);
            }
        }
    }

    @When("I navigate to the {string} tab")
    public void iNavigateToTheTab(String tabName) {
        currentTab = tabName;
        Assert.assertTrue(commonGrid.gridTab(currentTab), "Unable to navigate to " + tabName + " tab!");
        System.out.println("On " + tabName + " tab");
        BaseUtil.pageLoaded();
    }

    @When("I select the {string} option from the the Viewing drop down")
    public void iSelectTheOptionFromTheTheViewingDropDown(String rowOption) {
        BaseUtil.pageLoaded();
        int currentPage = commonGrid.gridPageNumber(currentTab, "current");
        if (currentPage == 0) {
            System.out.println("Cannot change grid size as there are no entries in the grid");
        } else {
            rowCount = Integer.parseInt(rowOption);
            commonGrid.gridViewSelection(currentTab, rowOption);
        }
        BaseUtil.pageLoaded();
    }

    @Then("The number of rows displayed will be less than or equal to the number selected")
    public void theNumberOfRowsDisplayedWillBeLessThanOrEqualToTheNumberSelected() {
        BaseUtil.pageLoaded();
        int currentPage = commonGrid.gridPageNumber(currentTab, "current");
        if (currentPage == 0) {
            System.out.println("No rows to count as there are no entries in the grid");
        } else {
            int recordCount = commonGrid.gridRecordNumber(currentTab);
            int visibleRows = commonGrid.gridRowCount(currentTab);
            Assert.assertTrue(visibleRows == rowCount || visibleRows == recordCount,
                    "Rows displayed (" + visibleRows + ") does note equal selection from Viewing dropdown (" + rowCount + "), or record count (" + recordCount + ")");
            System.out.println("Number of records currently displayed is " + visibleRows + " of a possible " + recordCount);
        }
    }

    @And("I enter {string} into the {string} grid header")
    public void iEnterIntoTheGridHeader(String textEntry, String header) {
        System.out.println("Searching for " + textEntry + " in " + header + " column");
        String currentRow1 = commonGrid.gridEntry("row 1", "WO #").getText();
        headerChoice = header;
        headerInfo = textEntry;
        commonGrid.gridHeaderField(header, textEntry);
        commonGrid.waitForFilter(currentRow1, "WO #");
        BaseUtil.pageLoaded();
    }

    @Then("I expect {string} in the {string} grid header")
    public void iReadTheGridHeader(String textEntry, String header) {
        System.out.println("Reading the " + header + " column filter");
        Assert.assertEquals(commonGrid.gridHeaderField(header, null), textEntry);
        BaseUtil.pageLoaded();
    }

    @And("I get the {string} for {string} in the grid")
    public void iGetTheForInTheGrid(String columnName, String rowName) {
        System.out.println(commonGrid.gridEntry(rowName, columnName));
    }

    @And("I open the {string} grid item")
    public void iOpenTheGridItem(String gridItem) {
        System.out.println(commonGrid.gridOpenItem(currentTab, gridItem));
    }

    @And("I select {string} from the {string} header in the grid")
    public void iSelectFromTheHeaderInTheGrid(String selection, String header) {
        System.out.println("Selecting " + selection + " from the " + header + " header");
        headerChoice = header;
        headerInfo = selection;
        Assert.assertTrue(commonGrid.gridHeaderSelectorSelect(header, selection),
                "Could not find " + selection + " option in " + header + " grid header");
        BaseUtil.pageLoaded();
    }

    @When("I get the number of records in the {string} tab")
    public void iGetTheNumberOfRecordsInTheTab(String tabName) {
        BaseUtil.pageLoaded();
        commonGrid.gridTab(tabName);
        gridRecords = commonGrid.gridRecordNumber(tabName);
        System.out.println("Number of records in " + tabName + " grid: " + gridRecords);
    }

    @And("I check if {string} is selected in the {string} header")
    public void iCheckIfIsSelectedInTheHeader(String selection, String headerName) {
        ArrayList<String> entries = commonGrid.gridHeaderSelectorRead(headerName);
        Assert.assertNotNull(entries, "No active selections in " + headerName + " list!");
        Assert.assertTrue(entries.contains(selection), selection + " not selected in " + headerName + " list!");
        System.out.println(selection + " is currently selected in " + headerName + " list");

    }

    @And("I check if the following items are selected in the {string} header")
    public void iCheckIfTheFollowingItemsAreSelectedInTheHeader(String headerName, List<List<String>> table) {
        List<String> items = table.get(0);
        ArrayList<String> entries = commonGrid.gridHeaderSelectorRead(headerName);
        Assert.assertNotNull(entries, "No active selections in " + headerName + " list!");
        for (String entry : items) {
            Assert.assertTrue(entries.contains(entry), entry + " not selected in " + headerName + " list!");
            System.out.println(entry + " is currently selected in " + headerName + " list");
        }
    }

    String rowInfo;

    @And("I enter that wo number into the grid")
    public void iEnterThatWoNumberIntoTheGrid() {
        commonGrid.gridHeaderField("WO #", rowInfo);
    }

    String headerChoice;
    String headerInfo;

    @And("I get the {string} for {string} of the grid")
    public void iGetTheForRowOfTheGrid(String columnName, String rowNumber) {
        System.out.println("Getting the data for " + rowNumber + " of the  " + columnName + " column.");
        // columnName is the header of the grid (eg. "Branch Office")
        // rowNumber is the index (starting at 1) of the row you want to view (eg. "row 3")
        headerChoice = columnName;
        headerInfo = commonGrid.gridEntry(rowNumber, columnName).getText();
        if(headerInfo.isBlank()){
            System.out.println("Either " + rowNumber + " does not exist, or the contents were blank.");
        }
        System.out.println("The info in " + rowNumber + " for " + columnName + " was " + headerInfo);
        valueStore.put("headerChoice", headerChoice);
        valueStore.put("headerInfo", headerInfo);
    }

    @And("I store that information for later")
    public void iStoreThatInformation() {
        valueStore.put("headerChoiceStore", valueStore.get("headerChoice"));
        valueStore.put("headerInfoStore", valueStore.get("headerInfo"));
    }

    @And("I enter that information into the grid header")
    public void iEnterThatInformationIntoTheGridHeader() {
        System.out.println("Entering " + headerInfo + " into " + headerChoice + " header");
        String currentRow1 = commonGrid.gridEntry("row 1", "WO #").getText();
        if(!headerInfo.isBlank()){
            commonGrid.gridHeaderFields(headerChoice, headerInfo);
        }
        else{
            System.out.println("The grid contents were empty.");
        }

        try {
            wait.until(ExpectedConditions.stalenessOf(commonGrid.gridEntry("row 1", headerChoice)));
            commonGrid.waitForFilter(currentRow1, "WO #");
        } catch (TimeoutException ignored) {
        }
        BaseUtil.pageLoaded();
    }

    @Then("The information in the first row of the grid will match what was entered")
    public void theInformationInTheFirstRowOfTheGridWillMatchWhatWasEntered() {
        if (commonGrid.gridRecordNumber(currentTab) == 0) {
            System.out.println("No entries in grid match " + headerInfo + " in " + headerChoice + " column");
        } else {
            String result = commonGrid.gridEntry("row 1", headerChoice).getText();
            System.out.println("result is: " + result);
            Assert.assertTrue(commonGrid.gridEntry("row 1", headerChoice).getText().toLowerCase().contains(headerInfo.toLowerCase()),
                    "Grid data does not match. Expected '" + headerInfo + "', found '" + commonGrid.gridEntry("row 1", headerChoice).getText() + "'");
            System.out.println("Entry for " + headerChoice + " header in first row of grid matches " + headerInfo);
        }
    }

    @And("I select that information from the grid header")
    public void iSelectThatInformationFromTheGridHeader() {
        System.out.println("Entering " + headerInfo + " into " + headerChoice + " header");
        String currentRow1 = commonGrid.gridEntry("row 1", "WO #").getText();
        commonGrid.gridHeaderSelectorSelect(headerChoice, headerInfo);
        try {
            wait.until(ExpectedConditions.stalenessOf(commonGrid.gridEntry("row 1", headerChoice)));
            commonGrid.waitForFilter(currentRow1, "WO #");
        } catch (TimeoutException ignored) {
        }
        BaseUtil.pageLoaded();
    }

    @And("The following headers are present")
    public void verifyTheFollowingHeadersArePresent(List<String> table) {
        BaseUtil.pageLoaded();
        for (String header : table) {
            Assert.assertTrue(commonGrid.gridHeaderFind(currentTab, header),
                    header + " header not visible in " + currentTab + " grid!");
            System.out.println(header + " header verified on grid");
        }
    }

    @And("I set the {string} field in the {string} header to {string}-{string}-{string}")
    public void iSetTheFieldInTheHeaderTo(String fromTo, String headerName, String day, String month, String year) {
        commonGrid.gridHeaderDateSelect(headerName, fromTo, day, month, year);
    }

    @And("I set the date in the {string} header to the following")
    public void iSetTheDateInTheHeaderToTheFollowing(String header, List<Map<String, String>> table) {
        Map<String, String> selections = table.get(0);
        if (selections.containsKey("Time")) {
            System.out.println("Entering " + selections.get("Month") + "/" + selections.get("Day") + "/" + selections.get("Year") + " " + selections.get("Time") + selections.get("AM / PM") + " into " + selections.get("From / To") + " field of " + header + " header.");
            commonGrid.gridHeaderDateSelect(header, selections);
        } else {
            System.out.println("Entering " + selections.get("Month") + "/" + selections.get("Day") + "/" + selections.get("Year") + " into " + selections.get("From / To") + " field of " + header + " header.");
            commonGrid.gridHeaderDateSelect(header, selections.get("From / To"), selections.get("Day"), selections.get("Month"), selections.get("Year"));
        }
    }

    @And("I set that date in the header")
    public void iSetThatDateInTheHeader() {
        System.out.println("Entering " + headerInfo + " into " + headerChoice + " header");
        String[] date;
        Map<String, String> dateMap = new HashMap<>();
        if (headerInfo.contains("/")) {
            date = headerInfo.split("/");
        } else {
            date = headerInfo.split("-");
        }
        dateMap.put("Month", new DateFormatSymbols().getMonths()[Integer.parseInt(date[0].replaceAll("^0", "")) - 1]);
        dateMap.put("Day", date[1].replaceAll("^0", ""));
        dateMap.put("Year", date[2].replaceAll(" .+", ""));

        if (driver.getCurrentUrl().contains("policychecking")) {
            String[] time = date[2].split(" ");
            dateMap.put("From / To", "From");
            dateMap.put("Time", time[1]);
            dateMap.put("AM / PM", time[2]);
            commonGrid.gridHeaderDateSelect(headerChoice, dateMap);
            dateMap.put("From / To", "To");
            // If the From and To times are identical, then no rows will be displayed. Add this janky mess to increment the minute by 1 and display rows at the desired time
            // FOR SOME COLUMNS ONLY. Eg. "Discarded Date" but not "Due Date"
            // Should probably use date methods to roll one minute forward and catch everything that might roll over (date).
            String[] hourMin = time[1].split(":");
            int hour = Integer.parseInt(hourMin[0]);
            int minute = Integer.parseInt(hourMin[1]);
            minute = (minute + 1) % 60;
            if (minute == 0) {
                hour++;
                if (hour >= 13) {
                    hour = 1;
                    if (time[2].equals("AM")) {
                        dateMap.put("AM / PM", "PM");
                    } else {
                        dateMap.put("AM / PM", "AM");
                    }
                }
            }
            dateMap.put("Time", hour + ":" + minute);

            commonGrid.gridHeaderDateSelect(headerChoice, dateMap);

        } else {

            commonGrid.gridHeaderDateSelect(headerChoice, "From", dateMap.get("Day"), dateMap.get("Month"), dateMap.get("Year"));
            commonGrid.gridHeaderDateSelect(headerChoice, "To", dateMap.get("Day"), dateMap.get("Month"), dateMap.get("Year"));
        }


        BaseUtil.pageLoaded();
    }

    @And("set the tab to {string}")
    public void setTheTabTo(String tabName) {
        currentTab = tabName;
    }

    @And("I sort the {string} grid column by {string}")
    public void iSortTheGridColumnBy(String headerName, String sort) {
        System.out.println("Sorting the " + headerName + " by " + sort);
        // Depending on the table, we will watch a different column to confirm that the grid has resorted
        // We don't necessarily look to the headerName column because sometimes those values do not change
        String waitColumn = "";
        long waitLength = 1;
        if (commonGrid.gridHeaderFind(currentTab, "WO #")) { // Used on the main page grid
            waitColumn = "WO #";
            waitLength = 10; // Extra wait time for the massive grid to sort
        } else if (commonGrid.gridHeaderFind(currentTab, "Employee")) { // Used on the Time Records / Time Rec Open / Admin pages
            waitColumn = "Employee";
        } else if (commonGrid.gridHeaderFind(currentTab, "Start Time")) { // Used in the Work Order Time Records grid
            waitColumn = "Start Time";
        } else if (commonGrid.gridHeaderFind(currentTab, "Date Created")) { // Used in the Work Order Attachments grid
            waitColumn = "Date Created";
        }

        String currentRow1 = commonGrid.gridEntry("row 1", waitColumn).getText();
        if (commonGrid.gridHeaderSort(headerName, sort)) {
            commonGrid.waitForFilter(currentRow1, waitColumn, waitLength);
        } else {
            System.out.println("Did not find the " + headerName + " header, or it was not sorted.");
        }
    }

    @Then("The {string} grid column is sorted by {string}")
    public void checkGridColumSort(String headerName, String sort) {
        System.out.println("Checking that the " + headerName + " coloumn is sorted by " + sort);
        String rowOne = commonGrid.gridEntry("row 1", headerName).getText();
        String rowTwo = commonGrid.gridEntry("row 2", headerName).getText();
        try { // If the column is only numerical values (eg. "# of Holder Certs"), we need to convert to floats for a proper check.
            float row1 = Float.parseFloat(rowOne);
            float row2 = Float.parseFloat(rowTwo);
            if (sort.equalsIgnoreCase("ascending")) {
                Assert.assertTrue(row1 <= row2, "The " + headerName + " was not sorted (ascending) correctly. Expected " + row2 + " before " + row1);
            } else if (sort.equalsIgnoreCase("descending")) {
                Assert.assertTrue(row1 >= row2, "The " + headerName + " was not sorted (descending) correctly. Expected " + row2 + " before " + row1);
            } else {
                Assert.fail("Sort provided by step was neither \"ascending\" nor \"descending\". It must be one of those two.");
            }

        } catch (NumberFormatException e) {
            if (sort.equalsIgnoreCase("ascending")) {
                Assert.assertTrue(rowOne.compareToIgnoreCase(rowTwo) <= 0, "The " + headerName + " was not sorted (ascending) correctly. Expected " + rowTwo + " before " + rowOne);
            } else if (sort.equalsIgnoreCase("descending")) {
                Assert.assertTrue(rowOne.compareToIgnoreCase(rowTwo) >= 0, "The " + headerName + " was not sorted (descending) correctly. Expected " + rowTwo + " before " + rowOne);
            } else {
                Assert.fail("Sort provided by step was neither \"ascending\" nor \"descending\". It must be one of those two.");
            }
        }
        System.out.println(headerName + " " + sort + " sort check passed.");
    }

    @Then("The {string} grid header {string} displayed")
    public void gridHeaderDisplayed(String headerName, String expectation) {
        // expectation should be "is" or "is not"
        if (expectation.equalsIgnoreCase("is not")) {
            Assert.assertFalse(commonGrid.gridHeaderFind(currentTab, headerName),
                    "The " + headerName + " was displayed, but should not have been.");
        } else {
            Assert.assertTrue(commonGrid.gridHeaderFind(currentTab, headerName),
                    "The " + headerName + " was not displayed, but should have been.");
        }

    }

    @And("I search the stored work order number")
    public void iSearchTheStoredWorkOrderNumber() {
        System.out.println("Searching for work order: " + valueStore.get("workOrder"));
        String currentRow1 = commonGrid.gridEntry("row 1", "WO #").getText();
        commonGrid.gridHeaderField("WO #", valueStore.get("workOrder"));
        commonGrid.waitForFilter(currentRow1, "WO #");
        BaseUtil.pageLoaded();
    }

    @And("I search the stored work order number and wait up to {int} seconds")
    public void iSearchTheStoredWorkOrderNumberCustomWait(int maxSeconds) {
        System.out.println("Searching for work order: " + valueStore.get("workOrder"));
        String currentRow1 = commonGrid.gridEntry("row 1", "WO #").getText();
        commonGrid.gridHeaderField("WO #", valueStore.get("workOrder"));
        commonGrid.waitForFilter(currentRow1, "WO #", maxSeconds);
        BaseUtil.pageLoaded();
    }

    @Then("There is no data in the grid")
    public void gridIsEmpty() {
        Assert.assertEquals(commonGrid.topRowText(), "No data available in table");
    }

    @Then("The {string} grid header is {string}")
    public void theGridHeaderIs(String header, String expectation) {
        System.out.println("Checking the " + header + "header contents.");
        if(expectation.contains("<headerInfo>")){ //todo: maybe use a better flag.
            expectation = expectation.replaceAll("<headerInfo>", headerInfo);
        }
        BaseUtil.pageLoaded();
        String actual = commonGrid.gridHeaderField(header, null);
        Assert.assertEquals(actual, expectation, "Header text was not corret. Expected \"" + expectation + "\", but was \"" + actual + "\".");
    }

    @And("The top row background colour is {string}")
    public void iVerifiedTheWorkOrderBackGroundColor(String colour) {
        commonGrid.verifyBackGroundColor(colour);
    }

    @Then("The {string} indicator mouseover text is {string}")
    public void theIndicatorMouseoverTextIs(String colour, String expectedText) {
        System.out.println("Checking the mouseover text of the "+colour+" box.");
        Assert.assertEquals(commonGrid.getIndicatorMouseoverText(colour, currentTab),expectedText);
    }

    @And("I click the Reset button in the grid header")
    public void iClickTheResetButton() {
        System.out.println("Clicking the Reset button to clear all filters");
        String currentRow1 = commonGrid.gridEntry("row 1", "WO #").getText();
        commonGrid.clickResetButton();
        try {
            commonGrid.waitForFilter(currentRow1, "WO #");
        } catch (TimeoutException ignored) {
        }
    }

    @And("I wait for the grid to be filtered")
    public void iWaitForTheGridToBeFiltered() {
        System.out.println("Waiting for the grid to be resorted");
        // Todo: make this more robust by looking at different different columns depending on which table is being filtered.
        String currentRow1 = commonGrid.gridEntry("row 1", "WO #").getText();
        commonGrid.gridHeaderField(headerChoice, headerInfo);
        try {
            wait.until(ExpectedConditions.stalenessOf(commonGrid.gridEntry("row 1", headerChoice)));
            commonGrid.waitForFilter(currentRow1, "WO #");
        } catch (TimeoutException ignored) {
        }
        BaseUtil.pageLoaded();
    }

    @And("I wait for the grid to be filled")
    public void iWaitForTheGridToBeFilled() {
        System.out.println("Waiting for the grid to be resorted");
        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("dataTables_empty")));
        } catch (TimeoutException ignored) {
        }
        BaseUtil.pageLoaded();
    }

    @Then("The {string} for {string} in the grid is {string}")
    public void confirmGridCellContents(String columnName, String rowName, String expectation){
        if(expectation.equals("<current date>")){
            SimpleDateFormat formDateFormat = new SimpleDateFormat("MM-dd-yyyy"); //fascinating how this date format is different from form date fields
            expectation = formDateFormat.format(new Date());
        }
        String actual = commonGrid.gridEntry(rowName, columnName).getText();
        Assert.assertTrue(actual.contains(expectation), "The actual("+actual+") did not match the expected("+expectation+").");
    }
}
