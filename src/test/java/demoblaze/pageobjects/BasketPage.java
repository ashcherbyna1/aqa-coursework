package demoblaze.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;


public class BasketPage extends BasePage {
    public BasketPage(WebDriver driver) {
        super(driver);
    }

    private static final String BASKET_OPTION = "//*[@id=\'navbarExample\']/ul/li[4]/a";
    private static final String TITLE_TEXT = "//*[@id=\"tbodyid\"]/tr/td[2]";
    private static final String DELETE_OPTION = "//*[@id=\"tbodyid\"]/tr[1]/td[4]/a";
    private static final String PlACE_ORDER_OPTION = "//*[@id=\"page-wrapper\"]/div/div[2]/button";
    private static final String TABLE = "//*[@id=\"tbodyid\"]";

    private WebElement basketOptionElement;
    private WebElement titleTextElement;
    private WebElement deleteElement;
    private WebElement placeorderElement;
    private WebElement tableElement;

    public void selectBasketOption() {
        basketOptionElement = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofMillis(100))
                .until(ExpectedConditions.elementToBeClickable(By.xpath(BASKET_OPTION)));
        basketOptionElement.click();
    }

    public String getTitleText() {
        new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(TITLE_TEXT)));
        titleTextElement = driver.findElement(By.xpath(TITLE_TEXT));
        return titleTextElement.getText();
    }

    public void deleteItemOption() {
        deleteElement = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofMillis(100))
                .until(ExpectedConditions.elementToBeClickable(By.xpath(DELETE_OPTION)));
        deleteElement.click();
    }

    public void selectPlaceOrderOption() {
        placeorderElement = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofMillis(100))
                .until(ExpectedConditions.elementToBeClickable(By.xpath(PlACE_ORDER_OPTION)));
        placeorderElement.click();
    }

    public boolean getDataTable() {
        return new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofMillis(100))
                .until(d -> driver.findElement(By.xpath(TABLE)).findElements(By.tagName("tr")).isEmpty());
    }
}



