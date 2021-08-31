package Steps;

import BaseUtil.BaseUtil;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.openqa.selenium.chrome.ChromeDriver;

public class Hook extends BaseUtil {
    private BaseUtil base;

    public Hook(BaseUtil base) {
        this.base = base;
    }

    @Before
    public void InitializeTest() {
        System.out.println("Opening the browser: Chrome");
        System.setProperty("webdriver.chrome.driver", "D:\\kiranchakrapani\\Downloads\\chromedriver_win32 (11)\\chromedriver.exe");
        base.driver = new ChromeDriver();
    }

    @After
    public void TearDownTest(Scenario scenario) {
        if (scenario.isFailed()) System.out.println(scenario.getName());
        System.out.println("Closing the browser: Chrome");
    }
}
