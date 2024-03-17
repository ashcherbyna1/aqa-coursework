package demoblaze.steps;

import demoblaze.pageobjects.BasketPage;
import demoblaze.pageobjects.HomePage;
import demoblaze.pageobjects.ProductPage;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;

import java.net.MalformedURLException;
import java.net.URL;

public class AddProductToBasketStepDefinitions {
    private WebDriver driver;
    private static final String baseUrl = "https://www.demoblaze.com/";

    @Before
    public void setUp() throws MalformedURLException {
        var gridUrl = "http://192.168.1.6:4444/";
        var options = new ChromeOptions();
        driver = new RemoteWebDriver(new URL(gridUrl), options);
        driver.get(baseUrl);
        driver.manage().window().maximize();
    }

    @Given("Select product from main page")
    public void selectProduct() {
        new HomePage(driver).selectProductOnPage();
    }

    @And("Select add option")
    public void selectBuyOption() {
        new ProductPage(driver).selectBuyOption();
    }

    @Then("Verify message {string}")
    public void verifySuccessfulMassage(String successfulMassage) {
        var productPage = new ProductPage(driver);
        var textSuccessfulText = productPage.getSuccessMessage();
        Assert.assertEquals(textSuccessfulText, successfulMassage);
    }

    @And("Select basket option")
    public void selectBasketOption() {
        new BasketPage(driver).selectBasketOption();
    }
    @Then("Verify title {string}")
    public void checkLogInText(String titleText) {
        var basketPage = new BasketPage(driver);
        var text = basketPage.getTitleText();
        Assert.assertEquals(text, titleText);
    }
    @After
    public void CloseBrowser(){
        driver.quit();
    }
}
