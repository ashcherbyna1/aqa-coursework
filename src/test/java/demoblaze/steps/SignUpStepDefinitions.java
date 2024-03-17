package demoblaze.steps;

import com.fasterxml.jackson.databind.json.JsonMapper;
import demoblaze.pageobjects.HomePage;
import demoblaze.pageobjects.SignUpPage;
import demoblaze.dto.RegistrationForm;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import okhttp3.RequestBody;
import okhttp3.Request;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Instant;
import java.util.List;
import java.util.Map;

public class SignUpStepDefinitions {
    private WebDriver driver;
    private static final String baseUrl = "https://www.demoblaze.com/";
    String apiurl = "https://api.demoblaze.com/signup";
    OkHttpClient client = new OkHttpClient();
    JsonMapper mapper = new JsonMapper();
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
            var signUpPage = new SignUpPage(driver);
            var username = form.get("username") + timeStampMillis;
            signUpPage.setUsernameAndPassword(username, form.get("password"));
        }
    }

    @And("Select Registration option")
    public void selectRegistrationOption(){
        new SignUpPage(driver).selectingRegistrationOption();
    }

    @Then("Information message is displayed {string}")
    public void verifyErrorMassage(String successfulMassage) {
        var textSuccessfulText = new SignUpPage(driver).getSuccessfulMassage();
        Assert.assertEquals(textSuccessfulText, successfulMassage);
    }

    @And("Close modal window")
    public void closeModalWindow(){
        new SignUpPage(driver).closingModalWindow();
    }

    @When("User enters invalid credential")
    public void entersInvalidCredential(DataTable dataTable) throws IOException {
        Instant instant = Instant.now();
        long timeStampMillis = instant.toEpochMilli();
        List<Map<String, String>> user = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> form : user) {
            var signUpPage = new SignUpPage(driver);
            var username = form.get("username")+ timeStampMillis ;
            registerUserFromApi(username,form.get("password"));
            signUpPage.setUsernameAndPassword(username, form.get("password"));
        }
    }

    private void registerUserFromApi(String username, String password) throws IOException {
        var endpoint = apiurl;
        var user = new RegistrationForm();
        user.setUsername(username);
        user.setPassword(password);
        var dtoData = mapper.writeValueAsString(user);
        var requestbody = RequestBody.create(dtoData, MediaType.get("application/json"));
        Request request = new Request.Builder()
                .url(endpoint)
                .post(requestbody)
                .build();
       try(var response = client.newCall(request).execute()) {
       }
    }

    @After
    public void CloseBrowser(){
        driver.quit();
    }

}
