package demoblaze.steps;

import demoblaze.pageobjects.HomePage;
import demoblaze.pageobjects.LogInPage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.testng.reporters.RuntimeBehavior.FILE_NAME;

public class LogInAndLogOutStepDefinitions {
    private WebDriver driver;
    private static final String baseUrl = "https://www.demoblaze.com/";

    public LogInAndLogOutStepDefinitions() {
    }

    @Before
    public void setUp() throws MalformedURLException {
        var loggerconfig = "src/test/resources/logging.properties"; //System.getenv("FILE_NAME");
        System.setProperty("java.util.logging.config.file", loggerconfig);
        var logger = Logger.getLogger(LogInAndLogOutStepDefinitions.class.getName());
        logger.log(Level.CONFIG, "Read data from " + FILE_NAME);

        var gridUrl = "http://192.168.1.6:4444/";
        var options = new ChromeOptions();
        driver = new RemoteWebDriver(new URL(gridUrl), options);
        driver.manage().window().maximize();
    }

    @Given("User select Log in option")
    public void logIn() {
        driver.get(baseUrl);
        new HomePage(driver).selectLogIn();
    }

    @And("Click Log in option")
    public void SelectLogIn() {
        new LogInPage(driver).clickLogInButton();
    }

    @When("User enters credential")
    public void entersLogInCredential(DataTable dataTable) {
        List<Map<String, String>> user = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> form : user) {
            var logInPage = new LogInPage(driver);
            var username = form.get("username");
            logInPage.setLogInData(username, form.get("password"));
        }
    }

    @Then("Verify welcome message {string}")
    public void checkWelcomeText(String welcomeText) {
        var homePage = new HomePage(driver);
        var message = homePage.getWelcomeMessage();
        Assert.assertEquals(message, welcomeText);
    }

    @And("Select Log Out")
    public void SelectLogOut() {
        var homePage = new HomePage(driver);
        homePage.selectLogOut();
    }

    @Then("Verify that {string} option is displayed")
    public void checkLogInText(String logInText) {
        var homePage = new HomePage(driver);
        var text = homePage.getLogInMessage();
        Assert.assertEquals(text, logInText);
    }
    @Then("Error message is displayed {string}")
    public void verifyErrorMassage(String errorMessage) {
        var textSuccessfulText = new LogInPage(driver).getErrorMessage();
        Assert.assertEquals(textSuccessfulText, errorMessage);
    }

    @After
    public void CloseBrowser() {
        driver.quit();
    }


}