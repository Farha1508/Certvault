package Steps;

import Base.BaseUtil;
import Pages.CommonForm;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.testng.Assert;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class FormSteps extends BaseUtil {

    private CommonForm commonForm;
    public FormSteps() {
        this.commonForm = new CommonForm(driver, js);
    }

    @When("I click the {string} button")
    public void iClickTheButton(String button) {
        BaseUtil.pageLoaded();
        Assert.assertTrue(commonForm.commonButton(button), button + " button could not be found on page");
        BaseUtil.pageLoaded();
        System.out.println("Clicking " + button + " button");
    }

    @And("I select {string} from the {string} drop down")
    public void iSelectFromTheDropDown(String selection, String dropDown) {
        BaseUtil.pageLoaded();
        System.out.println("Selecting " + selection + " from " + dropDown + " drop down.");
        Assert.assertTrue(commonForm.commonDropDownSelect(dropDown, selection),
                "Could not select " + selection + " from the " + dropDown + " dropdown");

        valueStore.put(dropDown, selection);
    }

    @And("{string} is not in the {string} drop down") // Used in C12750 and 12749 to make sure inactive users to not appear
    public void iCannotSelectFromTheDropDown(String selection, String dropDown) {
        BaseUtil.pageLoaded();
        System.out.println("Checking that " + selection + " is not in the " + dropDown + " drop down.");
        Assert.assertFalse(commonForm.commonDropDownSelect(dropDown, selection),
                "Found " + selection + " in the " + dropDown + " dropdown, but did not expect to.");
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
        System.out.println("Entering data into the form.");
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

    @Then("The following elements exist")
    public void fieldsExist(List<String> table) {
        pageLoaded();
        //Need atomic boolean to work with Java lambda expressions.
        AtomicBoolean elementExists = new AtomicBoolean(false);
        table.forEach(value -> {
            System.out.println("Checking the existance of: " + value);
            if(commonForm.commonField(value)!=null){
                elementExists.set(true);
            }
            else if(commonForm.commonDropDown(value)!=null){
                elementExists.set(true);
            }
            else if(commonForm.commonTextArea(value)!=null){
                elementExists.set(true);
            }
            else if(commonForm.commonButtonGet(value)!=null){
                elementExists.set(true);
            }
            else if(commonForm.commonCheckBoxGet(value)!=null){
                elementExists.set(true);
            }
            else{
                boolean linkExists;
                try{
                    linkExists = driver.findElement(By.linkText(value)) != null;
                } catch (NoSuchElementException e){
                    linkExists = false;
                }
                elementExists.set(linkExists);
            }

            Assert.assertTrue(elementExists.get(), "Could not find " + value + " field!");
        });
    }

    @Then("The {string} field contains {string}")
    public void theFieldContains(String fieldName, String contents) {
        if(contents.contains("<current date>")){
            SimpleDateFormat formDateFormat = new SimpleDateFormat("MM/dd/yyyy");
            contents = formDateFormat.format(new Date());
        }
        System.out.println("Checking that the "+fieldName+" field contains "+contents);
        Assert.assertTrue(commonForm.commonFieldRead(fieldName).contains(contents));
    }

    @Then("The {string} checkbox is {string}")
    public void theCheckboxIs(String checkBoxName, String expectation) {
        //expectation should be "selected" or "not selected" or "deselected"
        System.out.println("Checking whether the "+checkBoxName+" checkbox is selected or not.");
        if(expectation.toLowerCase().equals("selected") || expectation.toLowerCase().equals("checked")){
            Assert.assertTrue(commonForm.commonCheckBoxGet(checkBoxName).isSelected());
        }
        else{
            Assert.assertFalse(commonForm.commonCheckBoxGet(checkBoxName).isSelected());
        }
    }

    @Then("The following buttons are {string}")
    public void theFollowingButtonsAre(String expectation, List<String> buttons) {
        // expectation should be "enabled" or "disabled"
        for(String button:buttons){
            if(expectation.toLowerCase().equals("enabled")){
                System.out.println("Checking that the "+button+" button is enabled.");
                Assert.assertTrue(commonForm.commonButtonGet(button).isEnabled(), "The "+button+" button was disabled, but should be enabled.");
            }else{
                System.out.println("Checking that the "+button+" button is disabled.");
                Assert.assertFalse(commonForm.commonButtonGet(button).isEnabled(), "The "+button+" button was enabled, but should be disabled.");
            }
        }
    }
}
