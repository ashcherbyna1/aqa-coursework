package demoblaze.pageobjects.demoblaze.steps;

import demoblaze.pageobjects.demoblaze.pageobjects.HomePage;
import demoblaze.pageobjects.demoblaze.pageobjects.LogInPage;
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

public class LogInAndLogOutStepDefinitions {
    private WebDriver driver;
    private static final String baseUrl = "https://www.demoblaze.com/";
    private LogInPage logInPage;

    public LogInAndLogOutStepDefinitions() {
    }

    @Before
    public void setUp() throws MalformedURLException {
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
        logInPage.clickLogInButton();
    }

    @When("User enters credential")
    public void entersLogInCredential(DataTable dataTable) {
        List<Map<String, String>> user = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> form : user) {
            logInPage = new LogInPage(driver);
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

    @After
    public void CloseBrowser() {
        driver.quit();
    }

}