package demoblaze.pageobjects.demoblaze.steps;

import demoblaze.pageobjects.demoblaze.pageobjects.HomePage;
import demoblaze.pageobjects.demoblaze.pageobjects.SignUpPage;
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
import java.time.Instant;
import java.util.List;
import java.util.Map;

public class SignUpStepDefinitions {
    private WebDriver driver;
    private static final String baseUrl = "https://www.demoblaze.com/";
    private SignUpPage signUpPage;

    @Before
    public void setUp() throws MalformedURLException {
        var gridUrl = "http://192.168.1.6:4444/";
        var options = new ChromeOptions();
        driver = new RemoteWebDriver(new URL(gridUrl), options);
        driver.manage().window().maximize();
    }

    @Given("User select Sign up option")
    public void signUp(){
        driver.get(baseUrl);
        new HomePage(driver).selectSignUp();
    }

    @When("User enters valid credential")
    public void entersValidCredential(DataTable dataTable)
    {
        Instant instant = Instant.now();
        long timeStampMillis = instant.toEpochMilli();
        List<Map<String, String>> user = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> form : user) {
            signUpPage = new SignUpPage(driver);
            var username = form.get("username") + timeStampMillis;
            signUpPage.setUsernameAndPassword(username, form.get("password"));
        }
    }

    @And("Select Registration option")
    public void selectRegistrationOption(){
        signUpPage.selectingRegistrationOption();
    }

    @Then("Information massage is displayed {string}")
    public void verifyErrorMassage(String successfulMassage) {
        var textSuccessfulText = signUpPage.getSuccessfulMassage();
        Assert.assertEquals(textSuccessfulText, successfulMassage);
    }

    @And("Close modal window")
    public void closeModalWindow(){
        signUpPage.closingModalWindow();
    }

    @After
    public void CloseBrowser(){
        driver.quit();
    }
}
