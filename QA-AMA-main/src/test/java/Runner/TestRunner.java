package Runner;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features={"src/test/java/Feature"}, plugin={"json:target/cucumber.json","html:target/site/cucumber-pretty","junit:target/cucumber.xml"},glue="Steps",strict = true,monochrome = true, tags = {"@TestCases"})
public class TestRunner extends AbstractTestNGCucumberTests {
}
