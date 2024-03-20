package demoblaze.steps;

import demoblaze.pageobjects.HomePage;
import demoblaze.pageobjects.LogInPage;
import demoblaze.steps.browser.DriverInstance;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.net.MalformedURLException;
import java.util.List;
import java.util.Map;

public class LogInAndLogOutStepDefinitions {
    private WebDriver driver;

    public LogInAndLogOutStepDefinitions() {
    }

    @Before
    public void setUp() throws MalformedURLException {
        driver = DriverInstance.getDriver();
    }

    @Given("User select Log in option")
    public void logIn() {
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
}