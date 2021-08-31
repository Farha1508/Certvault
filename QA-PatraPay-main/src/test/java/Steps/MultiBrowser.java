package Steps;

import Base.BaseUtil;
import com.microsoft.edge.seleniumtools.EdgeDriver;
import io.cucumber.java.en.Given;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;

public class MultiBrowser extends BaseUtil {

    @Given("I am using the {string} browser")
    public void iAmUsingTheBrowser(String browser) {
        System.out.println("Quitting Chrome browser instance");
        driver.quit();
        driver = null;
        switch (browser) {
            case "chrome":
                System.out.println("Opening Chrome browser instance");
                driver = new ChromeDriver();
                js = (JavascriptExecutor)driver;
                break;
            case "firefox":
                System.out.println("Opening Firefox browser instance");
                driver = new FirefoxDriver();
                js = (JavascriptExecutor)driver;
                break;
            case "edge":
                System.out.println("Opening Edge browser instance");
                driver = new EdgeDriver();
                js = (JavascriptExecutor)driver;
                break;
            default:
                Assert.fail(browser.toUpperCase() + " not supported");
        }
    }
}
