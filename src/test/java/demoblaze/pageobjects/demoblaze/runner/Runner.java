package demoblaze.pageobjects.demoblaze.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(tags = "", features = {"src/test/resources/demoblaze/tests"}, glue = {"demoblaze.steps"},
        plugin = {"pretty"})
public class Runner extends AbstractTestNGCucumberTests {
}
