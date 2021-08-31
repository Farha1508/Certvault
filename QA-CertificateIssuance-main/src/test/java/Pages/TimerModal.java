package Pages;

import Base.BaseUtil;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TimerModal {
    private final WebDriver driver;
    private final JavascriptExecutor js;
    private final WebDriverWait wait;

    public TimerModal(WebDriver driver, JavascriptExecutor js) {
        this.driver = driver;
        this.js = js;
        this.wait = new WebDriverWait(this.driver, 10);
        PageFactory.initElements(driver, this);
    }

    public void clickTimeTrackingButton(String button) {
        if(button.equalsIgnoreCase("start")){
            timerModalStartBtn.click();
        }
    }

    public void setTimerWO(String workOrderNumber){
        timerModalWorkOrderField.sendKeys(workOrderNumber);
        BaseUtil.pageLoaded();
    }

    public String getTimerCompany(){
        return companyTimerText.getText();
    }

    // Selectors
    @FindBy(how = How.ID, using = "timer-Details-Submit")   //Timer modal start button
    public WebElement timerModalStartBtn;

    @FindBy(how = How.ID, using = "timerHeaderID")   //Timer company text
    public WebElement companyTimerText;

    @FindBy(how = How.ID, using = "timer_work_order_no")
    public WebElement timerModalWorkOrderField;
}
