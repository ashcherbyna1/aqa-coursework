package demoblaze.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(tags = "", features = {"src/test/resources/demoblaze/tests/AddProductToCart.feature"}, glue = {"demoblaze.steps"},
        plugin = {"pretty", "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"})
public class AddProductToCard extends AbstractTestNGCucumberTests {
}
