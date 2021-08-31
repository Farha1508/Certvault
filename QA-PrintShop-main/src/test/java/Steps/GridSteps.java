package Steps;


import Base.BaseUtil;
import Pages.CommonGrid;
import Pages.PrintShop;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.*;

public class GridSteps extends BaseUtil {

    private CommonGrid commonGrid;
    private PrintShop printShop = new PrintShop(driver,js);
    public GridSteps() {
        this.commonGrid = new CommonGrid(driver, js);
    }

    @And("I get the {string} for {string}")
    public void iGetTheFor(String colName, String rowName) {
        BaseUtil.pageLoaded();
        System.out.println(colName + " for " + rowName + " is: " + commonGrid.gridEntry(rowName, colName));
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
            valueStore.put("CurrentPageNumber",String.valueOf(result));
        }
    }

//    @Then("The grid page number is {int}")
//    public void theGridPageNumberIs(int expectedPageNumber) throws Exception {
//        System.out.println("Checking that the page number is " + expectedPageNumber + ".");
//        int actualPageNumber = commonGrid.gridPageNumber(currentTab, "current");
//        if (actualPageNumber == 0) {
//            Assert.fail("Page number not displayed as there are no entries in the grid");
//        } else if (actualPageNumber == -1) {
//            Assert.fail("Unable to get page number");
//        } else {
//            System.out.println("Currently on page " + actualPageNumber + " of grid");
//            Assert.assertEquals(actualPageNumber,expectedPageNumber, "The actual page number ("+actualPageNumber+") did not match the expected page number("+expectedPageNumber+").");
//        }
//    }

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

    @And("I move to the last page in the grid")
    public void iMoveToTheLastPageInTheGrid() {
        BaseUtil.pageLoaded();
        System.out.println("Moving to the last page in the " + currentTab + " grid");
        String lastPage = commonGrid.gridGoToLastPage(currentTab);
        valueStore.put("CurrentPageNumber",lastPage);
    }

    @Then("The page number is {string}")
    public void thePageNumberIs(String expectation){
        pageLoaded();
        int oldNumber = Integer.parseInt(valueStore.get("CurrentPageNumber"));
        int currentNumber = commonGrid.gridPageNumber(currentTab, "current");
        Assert.assertFalse(currentNumber < 1, "Could not find current page number.");

        switch (expectation.toLowerCase()) {
            case "decremented" -> Assert.assertEquals(currentNumber, oldNumber - 1, "Not on the expected page.");
            case "incremented" -> Assert.assertEquals(currentNumber, oldNumber + 1, "Not on the expected page.");
            case "the same" -> Assert.assertEquals(currentNumber, oldNumber, "Not on the expected page.");
            default -> Assert.fail("No viable case in switch statement.");
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
            viewSelection = Integer.parseInt(rowOption);
        }

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
            // This check was passing for the broken grid count of the PrintShop History Grid in print-shop-262. Changed to use viewSelection, which is set when selecting a value
            Assert.assertTrue(visibleRows<=viewSelection, "There are more rows displayed than the selector.");

            //Assert.assertTrue(visibleRows == rowCount || visibleRows == recordCount,
            //        "Rows displayed (" + visibleRows + ") does note equal selection from Viewing dropdown (" + rowCount + "), or record count (" + recordCount + ")");
            System.out.println("Number of records currently displayed is " + visibleRows + " of a possible " + recordCount);
        }
    }

    @And("I enter {string} into the {string} grid header")
    public void iEnterIntoTheGridHeader(String textEntry, String header) {
        String currentRow1 = commonGrid.gridEntry("row 1", "Record ID").getText();
        System.out.println("Searching for " + textEntry + " in " + header + " column");
        headerChoice = header;
        headerInfo = textEntry;
        commonGrid.gridHeaderField(header, textEntry);
        commonGrid.waitForFilter(currentRow1);
        BaseUtil.pageLoaded();
    }

    @When("I clear the {string} header")
    public void iClearTheHeaderField(String header) {
        // Only works on fields (not selections)
        System.out.println("Clearing the "+header+" field.");
        Assert.assertTrue(commonGrid.clearHeader(header));
    }

    @And("I get the {string} for {string} in the grid")
    public void iGetTheForInTheGrid(String columnName, String rowName) {
        System.out.println(commonGrid.gridEntry(rowName, columnName));
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

    @And("I check if the following items are not selected in the {string} header")
    public void iCheckIfTheFollowingItemsAreNotSelectedInTheHeader(String headerName, List<List<String>> table) {
        List<String> items = table.get(0);
        ArrayList<String> entries = commonGrid.gridHeaderSelectorRead(headerName);
        if(entries == null){
            System.out.println("No selections have been selected, as expected.");
            return;
        }
        for (String entry : items) {
            Assert.assertFalse(entries.contains(entry), entry + " is selected in " + headerName + " list!");
            System.out.println(entry + " is currently not selected in " + headerName + " list");
        }
    }

    String rowInfo;

    @And("I get the {string} for the {string} row of the grid")
    public void iGetTheForTheRowOfTheGrid(String column, String row) {
        headerInfo = commonGrid.gridEntry(row, column).getText();
        headerChoice = column;
        System.out.println(commonGrid.gridEntry(row, column).getText());
    }

    @And("I enter that wo number into the grid")
    public void iEnterThatWoNumberIntoTheGrid() {
        commonGrid.gridHeaderField("WO #", rowInfo);
    }

    String headerChoice;
    String headerInfo;

    @And("I get the {string} for {string} row of the grid")
    public void iGetTheForRowOfTheGrid(String column, String row) {
        headerChoice = column;
        headerInfo = commonGrid.gridEntry(row, column).getText();

    }

    @And("I enter that information into the grid header")
    public void iEnterThatInformationIntoTheGridHeader() {
        System.out.println("Entering " + headerInfo + " into " + headerChoice + " header");
        commonGrid.gridHeaderField(headerChoice, headerInfo);
        try {
            wait.until(ExpectedConditions.stalenessOf(commonGrid.gridEntry("row 1", headerChoice)));
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
        commonGrid.gridHeaderSelectorSelect(headerChoice, headerInfo);
        try {
            wait.until(ExpectedConditions.stalenessOf(commonGrid.gridEntry("row 1", headerChoice)));
        } catch (TimeoutException ignored) {
        }
        BaseUtil.pageLoaded();
    }

    @And("Verify the following headers are present")
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
        if(selections.containsKey("Time")) {
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
        dateMap.put("Month", new DateFormatSymbols().getMonths()[Integer.parseInt(date[0].replaceAll("^0", ""))-1]);
        dateMap.put("Day", date[1].replaceAll("^0", ""));
        dateMap.put("Year", date[2].replaceAll(" .+", ""));

        if (driver.getCurrentUrl().contains("policychecking")) {
            String[] time = date[2].split(" ");
            dateMap.put("From / To", "From");
            dateMap.put("Time", time[1]);
            dateMap.put("AM / PM", time[2]);
            commonGrid.gridHeaderDateSelect(headerChoice, dateMap);
            dateMap.put("From / To", "To");
            commonGrid.gridHeaderDateSelect(headerChoice, dateMap);

        } else {

            commonGrid.gridHeaderDateSelect(headerChoice, "From", dateMap.get("Day"), dateMap.get("Month"), dateMap.get("Year"));
            commonGrid.gridHeaderDateSelect(headerChoice, "To", dateMap.get("Day"), dateMap.get("Month"), dateMap.get("Year"));
        }


        BaseUtil.pageLoaded();
    }

    @And("set the tab to {string}")
    public void setTheTabTo(String tabName) {
        System.out.println("Setting the tab to "+tabName);
        currentTab = tabName;
    }

    @And("I sort the {string} grid column by {string}")
    public void iSortTheGridColumnBy(String headerName, String sort) {
        System.out.println("Sorting the "+headerName+" column by "+sort);
        commonGrid.gridHeaderSort(headerName, sort);
    }

    @Then("The {string} column is sorted by {string}")
    public void theColumIsSortedBy(String headerName, String sort) {
        System.out.println("Checking that the "+headerName+" is sorted by "+sort);
        commonGrid.gridHeaderSorted(headerName, sort);
    }

    @When("I click on the top record link")
    public void iClickOnTheTopRecordLink() {
        System.out.println("Opening the top Record");
        commonGrid.clickTopRecord(currentTab);
        BaseUtil.pageLoaded();
    }

    @Then("The {string} grid header is {string}")
    public void theGridHeaderIs(String header, String expectation) {
        System.out.println("Comparing the " + header + " header contents ("+commonGrid.gridHeaderFieldRead(header)+") to the expectation ("+expectation+").");
        BaseUtil.pageLoaded();
        Assert.assertEquals(commonGrid.gridHeaderFieldRead(header), expectation);
    }

    @Then("I test the following columns")
    public void iTestTheFollowingColumns(List<String> columns) {
        // This is a compound method combining sorting, searching, and asserting

        for(String column : columns){
            System.out.println("Starting test for "+column+" column");
            // Check the sorting
            String currentRow1 = commonGrid.gridEntry("row 1", "Record ID").getText();
            System.out.println("Checking that the '"+column+"' can be sorted ascending.");
            commonGrid.gridHeaderSort(column, "ascending");
            commonGrid.waitForFilter(currentRow1);
            String rowOne = commonGrid.gridEntry("row 1", column).getText();
            String rowTwo = commonGrid.gridEntry("row 2", column).getText();
            try{ // If the column is only numerical values (eg. "# of Holder Certs"), we need to convert to ints for a proper check.
                int row1 = Integer.parseInt(rowOne);
                int row2 = Integer.parseInt(rowTwo);
                Assert.assertTrue(row1<=row2);
            }catch(NumberFormatException e){
                Assert.assertTrue(rowOne.compareToIgnoreCase(rowTwo)<=0);
            }
            System.out.println(column + "ascending check passed.");

            currentRow1 = commonGrid.gridEntry("row 1", "Record ID").getText();
            System.out.println("Checking that the '"+column+"' can be sorted descending.");
            commonGrid.gridHeaderSort(column, "descending");
            commonGrid.waitForFilter(currentRow1);
            rowOne = commonGrid.gridEntry("row 1", column).getText();
            rowTwo = commonGrid.gridEntry("row 2", column).getText();
            try{ // If the column is only numerical values (eg. "# of Holder Certs"), we need to convert to ints for a proper check.
                int row1 = Integer.parseInt(rowOne);
                int row2 = Integer.parseInt(rowTwo);
                Assert.assertTrue(row1>=row2);
            }catch(NumberFormatException e){
                Assert.assertTrue(rowOne.compareToIgnoreCase(rowTwo)>=0);
            }
            System.out.println(column + " descending check passed.");

            // Check the filtering
            System.out.println("Filtering by the '"+column+"' contents of the third row to check filtering.");
            String searchText = commonGrid.gridEntry("row 3", column).getText();

            currentRow1 = commonGrid.gridEntry("row 1", "Record ID").getText();
            // differentiate between text fields and dropdown selectors

            // check the header text
            System.out.println("Checking that the '" + column + "' search contents are correct.");
            // Check if the header is a selector
            if(commonGrid.gridHeaderSelector(column)!=null){
                commonGrid.gridHeaderSelectorSelect(column,searchText);
                commonGrid.waitForFilter(currentRow1);
                Assert.assertEquals(commonGrid.gridHeaderSelectorRead(column).get(0),searchText);
            }
            // If the header is not a selector, then it's a field
            else{
                commonGrid.gridHeaderField(column, searchText);
                commonGrid.waitForFilter(currentRow1);
                Assert.assertEquals(commonGrid.gridHeaderField(column,null),searchText);
            }

            // check the top row contents
            System.out.println("Checking the top row '"+column+"' contents.");
            if (commonGrid.gridRecordNumber(currentTab) == 0) {
                System.out.println("No entries in grid match " + searchText + " in " + column + " column");
            } else {
                String result = commonGrid.gridEntry("row 1", column).getText();
                System.out.println("result is: " + result);
                Assert.assertTrue(commonGrid.gridEntry("row 1", column).getText().toLowerCase().contains(searchText.toLowerCase()),
                        "Grid data does not match. Expected '" + searchText + "', found '" + commonGrid.gridEntry("row 1", column).getText() + "'");
                System.out.println("Entry for '" + column + "' header in first row of grid matches " + searchText);
            }

            // clean up for the next iteration.
            commonGrid.clickPrintShopResetButton(currentTab);
        }
    }

    @And("The top row background colour is {string}")
    public void iVerifiedTheWorkOrderBackGroundColor(String colourName) {
        // colourName should be one of the words in the Switch statement.
        // Or you can send in a custom colour using the format "RRR,GGG,BBB,A"

        System.out.println("Checking the background colour of the first row.");
        String expectatedColour;
        String actualColour = commonGrid.verifyBackGroundColor(currentTab);
        switch (colourName) {
            case "White" -> expectatedColour = "rgba(249, 249, 249, 1)";
            case "Certified Mail - Green" -> expectatedColour = "rgba(121, 173, 121, 1)";
            case "Rush - Purple" -> expectatedColour = "rgba(142, 68, 173, 1)";
            case "Overnight - Red" -> expectatedColour = "rgba(238, 67, 67, 1)";
            default -> {
                System.out.println("Colour: " + colourName + " was not in the switch statement. Using the manually entered value.");
                expectatedColour = "rgba(" + colourName + ")";
            }
        }
        Assert.assertEquals(actualColour,expectatedColour, "The colour of the first row ("+actualColour+") did not match the expected colour ("+expectatedColour+")");
    }

    @Then("The {string} entry has a date and time")
    public void theEntryHasADateAndTime(String columnEntry) {
        System.out.println("Checking that the "+columnEntry+" has a date and time component.");
        Assert.assertTrue(commonGrid.hasDateAndTime(columnEntry), "The date+time format was incorrect.");
    }

    @And("The {string} timestamp is {string}")
    public void theTimestampIs(String columnEntry, String timeValue) {
        System.out.println("Checking that the "+columnEntry+" has the time value "+timeValue);
        Assert.assertEquals(commonGrid.getTopEntryTime(columnEntry),timeValue,"Time was "+commonGrid.getTopEntryTime(columnEntry)+", not: "+timeValue);
    }

    @Then("There are no entries in the grid")
    public void thereAreNoEntriesInTheGrid() {
        System.out.println("Checking that the 'No data available in table' message is displayed.");
        Assert.assertTrue(commonGrid.tableIsEmpty());
    }

    @Then("There are/is {int} row(s) filtered")
    public void numberOfRowsFiltered(int numberOfRows) {
        System.out.println("Checking that there are "+numberOfRows+" rows.");
        Assert.assertEquals(commonGrid.gridRecordNumber(currentTab),numberOfRows);
        ;
    }

    @Then("There are/is more than {int} row(s) filtered")
    public void numberOfRowsFilteredMin(int numberOfRows) {
        System.out.println("Checking that there are more than"+numberOfRows+" rows.");
        Assert.assertTrue(commonGrid.gridRecordNumber(currentTab) > numberOfRows);
    }

    @Then("The {string} header does not exist")
    public void theHeaderDoesNotExist(String headerName) {
        System.out.println("Checking that the "+headerName+" header doesn't exist.");
        Assert.assertFalse(commonGrid.gridHeaderFind(currentTab,headerName), "The "+headerName+" does exist, but it should not.");
    }

    @Then("I verify the number of records in the {string} grid match the {string} KPI")
    public void iVerifyTheNumberOfRecordsInTheGridMatchTheKPI(String tabName, String kpi) {
        commonPage.clickKpi(kpi);
        commonPage.pageLoaded();
        String rowNum;
        rowNum = Integer.toString(commonGrid.gridRecordNumber(tabName));

        Assert.assertEquals(rowNum, valueStore.get(kpi), "Rows in grid do not match number of " + kpi + " KPI");
        System.out.println("Number of records in grid (" + rowNum + ") match the number shown in the " + kpi + " KPI (" + valueStore.get(kpi) + ")");
    }

    // Steps for grids found in Forms (eg. History tab)
    @Then("I test the following form columns")
    public void iTestTheFollowingFormColumns(List<String> columns) throws InterruptedException {
        // This is a compound method combining sorting, searching, and asserting

        for(String column : columns){
            System.out.println("Starting test for "+column+" column");
            // Check the sorting
            System.out.println("Checking that the '"+column+"' can be sorted ascending.");
            commonGrid.gridHeaderSort(column, "ascending");
            Thread.sleep(1000);// cannot wait for record ID because there is no record ID column
            String rowOne = commonGrid.gridEntry("row 1", column).getText();
            String rowTwo = commonGrid.gridEntry("row 2", column).getText();
            try{ // If the column is only numerical values (eg. "# of Holder Certs"), we need to convert to ints for a proper check.
                int row1 = Integer.parseInt(rowOne);
                int row2 = Integer.parseInt(rowTwo);
                Assert.assertTrue(row1<=row2);
            }catch(NumberFormatException e){
                Assert.assertTrue(rowOne.compareToIgnoreCase(rowTwo)<=0);
            }
            System.out.println(column + "ascending check passed.");

            System.out.println("Checking that the '"+column+"' can be sorted descending.");
            commonGrid.gridHeaderSort(column, "descending");
            Thread.sleep(1000);
            rowOne = commonGrid.gridEntry("row 1", column).getText();
            rowTwo = commonGrid.gridEntry("row 2", column).getText();
            try{ // If the column is only numerical values (eg. "# of Holder Certs"), we need to convert to ints for a proper check.
                int row1 = Integer.parseInt(rowOne);
                int row2 = Integer.parseInt(rowTwo);
                Assert.assertTrue(row1>=row2);
            }catch(NumberFormatException e){
                Assert.assertTrue(rowOne.compareToIgnoreCase(rowTwo)>=0);
            }
            System.out.println(column + " descending check passed.");

            // Check the filtering
            System.out.println("Filtering by the '"+column+"' contents of the third row to check filtering.");
            String searchText = commonGrid.gridEntry("row 3", column).getText();

            // check the header text
            System.out.println("Checking that the '" + column + "' search contents are correct.");
            // Check if the header is a selector
            if(commonGrid.gridHeaderSelector(column)!=null){
                commonGrid.gridHeaderSelectorSelect(column,searchText);
                Thread.sleep(1000);
                Assert.assertEquals(commonGrid.gridHeaderSelectorRead(column).get(0),searchText);
            }
            // If the header is not a selector, then it's a field
            else{
                commonGrid.gridHeaderField(column, searchText);
                Thread.sleep(1000);
                Assert.assertEquals(commonGrid.gridHeaderField(column,null),searchText);
            }

            // check the top row contents
            System.out.println("Checking the top row '"+column+"' contents.");
            if (commonGrid.gridRecordNumber(currentTab) == 0) {
                System.out.println("No entries in grid match " + searchText + " in " + column + " column");
            } else {
                String result = commonGrid.gridEntry("row 1", column).getText();
                System.out.println("result is: " + result);
                Assert.assertTrue(commonGrid.gridEntry("row 1", column).getText().toLowerCase().contains(searchText.toLowerCase()),
                        "Grid data does not match. Expected '" + searchText + "', found '" + commonGrid.gridEntry("row 1", column).getText() + "'");
                System.out.println("Entry for '" + column + "' header in first row of grid matches " + searchText);
            }

            // clean up for the next iteration.
            commonGrid.clickPrintShopResetButton(currentTab);
        }
    }

    @When("I get the number of records in the {string} form tab")
    public void iGetTheNumberOfRecordsInTheFormTab(String tabName) {
        BaseUtil.pageLoaded();
        //commonGrid.gridTab(tabName);
        gridRecords = commonGrid.gridRecordNumber(tabName);
        System.out.println("Number of records in " + tabName + " grid: " + gridRecords);
    }

    @Then("The number of records in the {string} tab has {string}")
    public void iCheckTheNumberOfRecordsChange(String tabName, String expectation) {
        // Expectation should be "increased," "decreased," or "stayed the same"
        System.out.println("Checking the change in number of records for the "+tabName+" tab.");
        if(expectation.equals("increased")){
            Assert.assertTrue(commonGrid.gridRecordNumber(tabName)>gridRecords);
        }
        else if(expectation.equals("decreased")){
            Assert.assertTrue(commonGrid.gridRecordNumber(tabName)<gridRecords);
        }
        else{
            Assert.assertEquals(commonGrid.gridRecordNumber(tabName),gridRecords);
        }
    }
}
