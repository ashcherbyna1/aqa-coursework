package demoblaze.steps;

import demoblaze.pageobjects.*;
import demoblaze.steps.browser.BrowserSettings;
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

public class AddProductToBasketStepDefinitions {
    private WebDriver driver;
    private static final String baseUrl = "https://www.demoblaze.com/";

    private PlaceOrder placeOrder;

    @Before
    public void setUp() throws MalformedURLException {
        var gridUrl = BrowserSettings.getGridUrl();
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

    @And("Select Delete option")
    public void deleteItem() {
        new BasketPage(driver).deleteItemOption();
    }

    @And("Select Place order option")
    public void selectingPlaceOrder() {
        new BasketPage(driver).selectPlaceOrderOption();
    }

    @And("Field valid data")
    public void entersPurchaseData(DataTable dataTable) {
        List<Map<String, String>> name = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> form : name) {
            placeOrder = new PlaceOrder(driver);
            var namebuyer = form.get("name");
            placeOrder.setPurchaseData(namebuyer, form.get("carddata"));
        }
    }

    @And("Select option to Purchase")
    public void selectingOptionPurchase() {
        new PlaceOrder(driver).selectToBuyOption();
    }

    @Then("Verify purchase message {string}")
    public void checkPurchaseText(String purchaseText) {
        var placeOrderPage = new PlaceOrder(driver);
        var message = placeOrderPage.getPurchaseMessage();
        Assert.assertEquals(message, purchaseText);
    }

    @Then("Verify that goods table empty")
    public void checkThatDataTableEmpty(){
        var basketPage = new BasketPage(driver);
        var result = basketPage.getDataTable();
        Assert.assertTrue(result);
    }

    @After
    public void CloseBrowser() {
        driver.quit();
    }
}
