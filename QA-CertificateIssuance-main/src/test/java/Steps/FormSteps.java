package Steps;

import Base.BaseUtil;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class FormSteps extends BaseUtil {

//    public FormSteps() {
//        commonForm = new CommonForm(driver, js);
//        commonPage = new CommonPage(driver, js);
//    }

    @When("I click the {string} button")
    public void iClickTheButton(String button) {
        System.out.println("Clicking " + button + " button");
        pageLoaded();
        Assert.assertTrue(commonForm.commonButton(button), button + " button could not be found on page");
        pageLoaded();
    }

    @And("I click on the {string} link")
    public void iClickOnTheLink(String linkText) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(By.linkText(linkText))).click();
        } catch (ElementClickInterceptedException e) {
            commonForm.clickErrorHandle(e.toString(), driver.findElement(By.linkText(linkText)));
        }
        pageLoaded();
    }

    @And("I select {string} from the {string} drop down")
    public void iSelectFromTheDropDown(String selection, String dropDown) {
        pageLoaded();
        System.out.println("Selecting " + selection + " from " + dropDown + " drop down.");
        Assert.assertTrue(commonForm.commonDropDownSelect(dropDown, selection),
                "Could not select " + selection + " from the " + dropDown + " dropdown");

        valueStore.put(dropDown, selection);
    }

    @And("I edit the following drop downs")
    public void iEditTheFollowingDropDowns(List<Map<String, String>> table) {
        Map<String, String> dropDowns = table.get(0);
        dropDowns.forEach((key, value) -> {
            System.out.println("Selecting " + value + " from " + key + " drop down");
            Assert.assertTrue(commonForm.commonDropDownSelect(key, value),
                    "Could not select " + value + " from " + key + " drop down");
            editedValues.put(key, value);
            valueStore.put(key, value);
        });

    }

    @And("I get the value for the {string} drop down")
    public void iGetTheValueForTheDropDown(String dropDown) {
        String value = commonForm.commonDropDownRead(dropDown);
        Assert.assertNotNull(value, "Could not read from " + dropDown + " drop down");

        System.out.println("Entry in " + dropDown + " drop down: " + value);
    }

    @Then("The value of the {string} dropdown is {string}")
    public void iCheckTheValueForTheDropDown(String dropDown, String expectation) {
        String value = commonForm.commonDropDownRead(dropDown);
        Assert.assertNotNull(value, "Could not read from " + dropDown + " drop down");
        Assert.assertEquals(value, expectation);
        System.out.println("Entry in " + dropDown + " drop down: " + value);
    }

    @And("I enter {string} in the {string} field")
    public void iEnterInTheField(String text, String fieldName) {
        String trackEntry;
        String dateString = dateFormat.format(new Date());
        String timeString = timeFormat.format(new Date());

        if (text.contains("<current date>")) {
            trackEntry = text.replaceAll("<current date>", dateString + " " + timeString);
        } else {
            trackEntry = text;
        }

        System.out.println("Entering " + trackEntry + " into " + fieldName + " field.");

        Assert.assertTrue(commonForm.commonFieldEnter(fieldName, trackEntry),
                "Field labeled " + fieldName + " is not present on page!");

        valueStore.put(fieldName, trackEntry);
    }

    @And("I edit the following fields")
    public void iEditTheFollowingFields(List<Map<String, String>> table) {
        String dateString = dateFormat.format(new Date());
        String timeString = timeFormat.format(new Date());
        Map<String, String> fields = table.get(0);
        fields.forEach((key, value) -> {
            if (value.contains("<current date>")) {
                value = value.replaceAll("<current date>", dateString + " " + timeString);
            }
            System.out.println("Entering " + value + " into " + key + " field");
            Assert.assertTrue(commonForm.commonFieldEnter(key, value), "Could not find " + key + " field!");
            editedValues.put(key, value);
            valueStore.put(key, value);
        });
    }

    @And("I enter {string} in the {string} text box")
    public void iEnterInTheTextBox(String text, String textArea) {
        String trackEntry;
        String dateString = dateFormat.format(new Date());
        String timeString = timeFormat.format(new Date());

        if (text.contains("<current date>")) {
            trackEntry = text.replaceAll("<current date>", dateString + " " + timeString);
        } else {
            trackEntry = text;
        }

        System.out.println("Entering " + trackEntry + " into " + textArea + " text box.");

        Assert.assertTrue(commonForm.commonTextAreaEnter(textArea, trackEntry),
                "Could not find " + textArea + " text area on page");

        valueStore.put(textArea, trackEntry);
    }

    @And("I edit the following text areas")
    public void iEditTheFollowingTextAreas(List<Map<String, String>> table) {
        String dateString = dateFormat.format(new Date());
        String timeString = timeFormat.format(new Date());
        Map<String, String> fields = table.get(0);
        fields.forEach((key, value) -> {
            if (value.contains("<current date>")) {
                value = value.replaceAll("<current date>", dateString + " " + timeString);
            }
            System.out.println("Entering " + value + " into " + key + " field");
            Assert.assertTrue(commonForm.commonTextAreaEnter(key, value), "Could not find " + key + " text area!");
            editedValues.put(key, value);
            valueStore.put(key, value);
        });
    }

    @And("I enter the following information into the form")
    public void iEnterTheFollowingInformationIntoTheForm(Map<String, String> table) {
        String dateString = dateFormat.format(new Date());
        String timeString = timeFormat.format(new Date());
        table.forEach((key, value) -> {
            boolean foundItem = false;
            if (value.contains("<current date>")) {
                value = value.replaceAll("<current date>", dateString + " " + timeString);
            }
            if (commonForm.commonFieldEnter(key, value)) {
                foundItem = true;
                System.out.println("Entering \"" + value + "\" into \"" + key + "\" field");
            } else if (commonForm.commonDropDownSelect(key, value)) {
                foundItem = true;
                System.out.println("Selecting \"" + value + "\" from \"" + key + "\" drop down");
            } else if (commonForm.commonTextAreaEnter(key, value)) {
                foundItem = true;
                System.out.println("Entering \"" + value + "\" into \"" + key + "\" text area");
            }
            Assert.assertTrue(foundItem, "Could not find " + key + " field, drop down, or text area!");
            valueStore.put(key, value);
        });
    }

    @And("I set the {string} date picker to today's date")
    public void iSetTheDatePickerToTodaySDate(String datePicker) {
        SimpleDateFormat day = new SimpleDateFormat("d");
        SimpleDateFormat month = new SimpleDateFormat("MMMM");
        SimpleDateFormat year = new SimpleDateFormat("yyyy");
        Map<String, String> dateTime = new HashMap<>();
        dateTime.put("Day", day.format(new Date()));
        dateTime.put("Month", month.format(new Date()));
        dateTime.put("Year", year.format(new Date()));
        dateTime.put("Time", "12:00");
        dateTime.put("AM / PM", "AM");
        if (!commonForm.commonDatePick(datePicker, dateTime.get("Day"), dateTime.get("Month"), dateTime.get("Year"))) {
            commonForm.commonDatePick(datePicker, dateTime);
        }
    }

    @And("I set the {string} date picker to {int} month(s) ago")
    public void iSetTheDatePickerToMonthsAgo(String datePicker, int monthsAgo) {
        SimpleDateFormat day = new SimpleDateFormat("d");
        SimpleDateFormat month = new SimpleDateFormat("MMMM");
        SimpleDateFormat year = new SimpleDateFormat("yyyy");
        Map<String, String> dateTime = new HashMap<>();

        long milliSecondsDate = System.currentTimeMillis();
        long oneMonthMilliseconds = 2592000000L;

        dateTime.put("Day", day.format(new Date(milliSecondsDate - monthsAgo * oneMonthMilliseconds)));
        dateTime.put("Month", month.format(new Date(milliSecondsDate - monthsAgo * oneMonthMilliseconds)));
        dateTime.put("Year", year.format(new Date(milliSecondsDate - monthsAgo * oneMonthMilliseconds)));
        dateTime.put("Time", "12:00");
        dateTime.put("AM / PM", "AM");
        if (!commonForm.commonDatePick(datePicker, dateTime.get("Day"), dateTime.get("Month"), dateTime.get("Year"))) {
            commonForm.commonDatePick(datePicker, dateTime);
        }
    }

    @And("I set the date in the {string} date picker to")
    public void iSetTheDateInTheDatePickerTo(String datePicker, List<Map<String, String>> table) {
        Map<String, String> selections = table.get(0);
        if (selections.containsKey("Time")) {
            Assert.assertTrue(commonForm.commonDatePick(datePicker, selections), "Could not enter date into " + datePicker + " date picker");
            System.out.println("Entering " + selections.get("Month") + " " + selections.get("Day") + ", " + selections.get("Year") + " " + selections.get("Time") + selections.get("AM / PM") + " into " + datePicker + " date picker.");
        } else {
            Assert.assertTrue(commonForm.commonDatePick(datePicker, selections.get("Day"), selections.get("Month"), selections.get("Year")), "Could not enter date into " + datePicker + " date picker");

            System.out.println("Entering " + selections.get("Month") + " " + selections.get("Day") + ", " + selections.get("Year") + " into " + datePicker + " date picker.");
        }
    }

    @Then("The {string} label is {string}")
    public void theLabelIs(String label, String expected) {
        System.out.println("Confirming that " + label + " is set to " + expected);
        Assert.assertEquals(commonForm.commonLabelRead(label), expected);
    }

    @And("I store the work order number")
    public void iStoreTheWorkOrderNumber() {
        valueStore.put("workOrder", commonForm.getWorkOrderNumber());
        System.out.println("Storing " + commonForm.getWorkOrderNumber() + " for later.");
    }

    @Then("The record is added")
    public void confirmAddRecord(List<String> fields) {
        // confirm record details match what were entered
        fields.forEach(key -> {
            boolean foundItem = false;
            if (commonForm.commonField(key) != null) {
                foundItem = true;
                String found = commonForm.commonFieldRead(key);
                if (key.contains("Date")) { // The way dates are stored and displayed are different. Need to do some parsing.
                    // Convert the expected format into the actual format, and then compare with the actual date.
                    // expected: "19-02-2021 103851", actual: "02/19/2021 10:38 AM"
                    SimpleDateFormat formatActual = new SimpleDateFormat("MM/dd/yyyy h:mm aa", Locale.ENGLISH); // need locale to catch AM/PM component
                    SimpleDateFormat formatExpected = new SimpleDateFormat("dd-MM-yyyy HHmmss");
                    try {
                        Date expectedDate = formatExpected.parse(valueStore.get(key));
                        String reformattedExpectedDate = formatActual.format(expectedDate);
                        // "Mail Date" in Print Shop is special. It is always set to 15:15 PT.
                        // Git issue: https://github.com/patracorp/print-shop/issues/34
                        // Test Case: https://patra.testrail.io/index.php?/cases/view/10466
                        if (key.equals("Mail Date")) {
                            reformattedExpectedDate = reformattedExpectedDate.substring(0, 11) + "3:15 PM";
                        }
                        System.out.println("Comparing actual (\"" + found + "\") to expected (\"" + reformattedExpectedDate + "\") in \"" + key + "\" field");
                        Assert.assertEquals(found, reformattedExpectedDate);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                } else {
                    System.out.println("Comparing actual (\"" + found + "\") to expected (\"" + valueStore.get(key) + "\") in \"" + key + "\" field");
                    Assert.assertEquals(found, valueStore.get(key));
                }
            } else if (commonForm.commonDropDown(key) != null) {
                foundItem = true;
                String found = commonForm.commonDropDownRead(key);
                System.out.println("Comparing actual (\"" + found + "\") to expected (\"" + valueStore.get(key) + "\") in \"" + key + "\" drop down");
                Assert.assertEquals(found, valueStore.get(key));
            } else if (commonForm.commonTextArea(key) != null) {
                foundItem = true;
                String found = commonForm.commonTextAreaRead(key);
                System.out.println("Comparing actual (\"" + found + "\") to expected (\"" + valueStore.get(key) + "\") in \"" + key + "\" test area");
                Assert.assertEquals(found, valueStore.get(key));
            }
            Assert.assertTrue(foundItem, "Could not find " + key + " field, drop down, or text area!");
        });
        System.out.println("Record has been successfully read");
    }

    @When("I clear the {string} field")
    public void iClearTheField(String fieldName) {
        System.out.println("Clearing the " + fieldName + " field.");
        commonForm.commonFieldClear(fieldName);
    }

    @Then("The following elements exist")
    public void fieldsExist(List<String> table) {
        pageLoaded();
        //Need atomic boolean to work with Java lambda expressions.
        AtomicBoolean elementExists = new AtomicBoolean(false);
        table.forEach(value -> {
            System.out.println("Checking the existance of: " + value);
            if (commonForm.commonField(value) != null) {
                elementExists.set(true);
            } else if (commonForm.commonDropDown(value) != null) {
                elementExists.set(true);
            } else if (commonForm.commonTextArea(value) != null) {
                elementExists.set(true);
            } else if (commonForm.commonButtonGet(value) != null) {
                elementExists.set(true);
            } else if (commonForm.commonCheckBox(value) != null) {
                elementExists.set(true);
            } else {
                boolean linkExists;
                try {
                    linkExists = driver.findElement(By.linkText(value)) != null;
                } catch (NoSuchElementException e) {
                    linkExists = false;
                }
                elementExists.set(linkExists);
            }

            Assert.assertTrue(elementExists.get(), "Could not find " + value + " field!");
        });
    }

    @Then("The following elements do not exist")
    public void fieldsDoNotExist(List<String> table) {
        pageLoaded();
        //Need atomic boolean to work with Java lambda expressions.
        AtomicBoolean elementExists = new AtomicBoolean(false);
        table.forEach(value -> {
            System.out.println("Checking the existance of: " + value);
            if (commonForm.commonField(value) != null) {
                elementExists.set(true);
            } else if (commonForm.commonDropDown(value) != null) {
                elementExists.set(true);
            } else if (commonForm.commonTextArea(value) != null) {
                elementExists.set(true);
            } else if (commonForm.commonButtonGet(value) != null) {
                elementExists.set(true);
            } else if (commonForm.commonCheckBox(value) != null) {
                elementExists.set(true);
            } else {
//                boolean linkExists;
//                try {
//                    linkExists = driver.findElement(By.linkText(value)) != null;
//                } catch (NoSuchElementException e) {
//                    linkExists = false;
//                }
//                elementExists.set(linkExists);
            }

            Assert.assertFalse(elementExists.get(), value + " was displayed, but should not have been.");
        });
    }

    @And("I set the {string} to {int} minutes after {string}")
    public void iSetTheToMinutesAfter(String dateField, long minutes, String referenceField) throws ParseException {
        // 07/22/2021 8:21 AM
        BaseUtil.pageLoaded();
        try {
            Thread.sleep(2000); // Todo: remove this. Currently the date picker is not being displayed if clicked too fast.
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String targeDate = commonForm.commonLabelRead(referenceField);
        LocalDateTime newDate = LocalDateTime.parse(targeDate, DateTimeFormatter.ofPattern("MM/dd/yyyy h:mm a", Locale.ENGLISH)).plusMinutes(minutes);

        Map<String, String> newDateMap = new HashMap<>();
        //"Year", "Month", "Day", "Time", "AM / PM"
        //newDate.put("Month", Month.of(Integer.parseInt(newDate2.getMonth())).getDisplayName(TextStyle.SHORT, Locale.ENGLISH));
        newDateMap.put("Month", newDate.format(DateTimeFormatter.ofPattern("MMMM")));
        newDateMap.put("Day", String.valueOf(newDate.getDayOfMonth()));
        newDateMap.put("Year", String.valueOf(newDate.getYear()));
        newDateMap.put("Time", newDate.format(DateTimeFormatter.ofPattern("h:mm", Locale.ENGLISH)));
        newDateMap.put("AM / PM", newDate.format(DateTimeFormatter.ofPattern("a", Locale.ENGLISH)));
        commonForm.commonDatePick(dateField, newDateMap);
    }

    @Then("the Original Email contents are {string}")
    public void theOriginalEmailContentsAre(String expectedContents) {
        String actualContents = driver.findElement(By.id("cert_description")).getText();
        Assert.assertEquals(actualContents, actualContents, "The Original email contents were not what was expected.");
    }

    @And("I select the {string} checkbox")
    public void iClickCheckbox(String checkBox) {
        System.out.println("Clicking " + checkBox + " check box");
        Assert.assertTrue(commonForm.commonCheckBoxClick(checkBox), checkBox + " checkbox could not be found!");
    }

    @And("I deselect the {string} checkbox")
    public void iDeselectCheckbox(String checkBox) {
        System.out.println("Deselecting " + checkBox + " check box");
        if (commonForm.commonCheckBoxSelected(checkBox)) {
            commonForm.commonCheckBoxClick(checkBox);
        }
        Assert.assertFalse(commonForm.commonCheckBoxSelected(checkBox), "The checkbox was still selected, but should not have been.");
    }

    @Then("The {string} checkbox {string} selected")
    public void iCheckCheckbox(String checkBox, String checked) {
        System.out.println("Checking " + checkBox + " check box");
        if (checked.equals("is not")) {
            Assert.assertFalse(commonForm.commonCheckBoxSelected(checkBox), checkBox + " checkbox could not be found!");
        } else {
            Assert.assertTrue(commonForm.commonCheckBoxSelected(checkBox), checkBox + " checkbox could not be found!");
        }
    }

    @And("I enter {int} characters into the {string} field")
    public void iEnterCharactersIntoTheField(long numberOfCharacters, String fieldName) {
        String template = "1234567890";
        int templateLength = template.length();
        StringBuilder toWrite = new StringBuilder();
        for (int i = 0; i < numberOfCharacters; i++) {
            toWrite.append(template.charAt(i % templateLength));
        }
        commonForm.commonFieldEnter(fieldName, toWrite.toString());
        try {
            Thread.sleep(numberOfCharacters); // Give the step some time to finish long strings.
        } catch (InterruptedException e) {
        }
        pageLoaded();
    }

    @Then("There will be {int} characters in the {string} field")
    public void thereWillBeCharactersInTheField(int numberOfCharacters, String fieldName) {
        int actualLength = commonForm.commonFieldRead(fieldName).length();
        Assert.assertEquals(actualLength, numberOfCharacters);
    }

    @Then("The following strings are displayed on the page")
    public void theFollowingStringsAreDisplayedOnThePage(List<String> strings) {
        // Used mostly to check the contents of PDF pages.
        System.out.println("Checking that certain elements are displayed on the page.");
        String pageContents = driver.getPageSource();
        for (String string : strings) {
            Assert.assertTrue(pageContents.contains(string));
        }
    }

    @And("I update my expectations")
    public void iUpdateMyExpectations(Map<String, String> table) {
        String dateString = dateFormat.format(new Date());
        String timeString = timeFormat.format(new Date());
        table.forEach((key, value) -> {
            if (value.contains("<current date>")) {
                value = value.replaceAll("<current date>", dateString + " " + timeString);
            }
            valueStore.put(key, value);
        });
    }

    @Then("The {string} label is marked required")
    public void theLabelIsMarkedRequired(String fieldName) {
        System.out.println("Checking that the 'required' asterisk is displayed.");
        Assert.assertTrue(driver.findElement(By.xpath(
                "//label[contains(text(),\"" + fieldName + "\")]/span[normalize-space()='*']")).isDisplayed());
    }

    @Then("The {string} label is not marked required")
    public void theLabelIsNotMarkedRequired(String fieldName) {
        System.out.println("Checking that the 'required' asterisk is not displayed.");
        try {
            Assert.assertFalse(driver.findElement(By.xpath(
                    "//label[contains(text(),\"" + fieldName + "\")]/span[normalize-space()='*']")).isDisplayed());
        } catch (NoSuchElementException e) {
            System.out.println("Requirement asterisk was not displayed, as expected.");
        }
    }

    @Then("The {string} field warning is displayed")
    public void theFieldWarningIsDisplayed(String fieldName) {
        // Generic message
        WebElement genericAlert = driver.findElement(By.id("cert_header_mandatory"));
        Assert.assertTrue(genericAlert.isDisplayed());
        Assert.assertEquals(genericAlert.getText(), "Please enter mandatory fields.");

        // Specific message
        WebElement specificAlert = driver.findElement(By.xpath(
                "//label[contains(text(),\"" + fieldName + "\")]/following-sibling::span"));
        Assert.assertTrue(specificAlert.isDisplayed());
        Assert.assertEquals(specificAlert.getText(), "Please enter " + fieldName);
    }

    @Then("The {string} dropdown warning is displayed")
    public void theDropDownWarningIsDisplayed(String dropdownName) {
        String expectedMessage = "fail";
        switch (dropdownName.toLowerCase()){
            case "folder":
                expectedMessage = "Please select Folder";
                break;
            case "discarded reason":
            case "sla miss reason":
                expectedMessage = "Please select reason";
                break;
        }

        // Specific message
        WebElement specificAlert = driver.findElement(By.xpath(
                "//label[contains(text(),\"" + dropdownName + "\")]/following-sibling::span"));
        Assert.assertTrue(specificAlert.isDisplayed());
        Assert.assertEquals(specificAlert.getText(), expectedMessage);
    }

    @Then("A modal is displayed stating {string}")
    public void aModalIsDisplayedStating(String expectedModalContents) {
        System.out.println("Checking the contents of modal.");
        pageLoaded();
        WebElement modal = commonForm.commonConfirmModal();
        if (modal == null) {
            Assert.fail("Modal is not displayed");
        }
        Assert.assertTrue(modal.getText().contains(expectedModalContents),
                "Expected \"" + expectedModalContents + "\", but found \"" + modal.getText() + "\"");
    }
}
