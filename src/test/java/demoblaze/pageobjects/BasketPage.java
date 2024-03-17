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

    private WebElement basketOptionElement;
    private WebElement titleTextElement;

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
}



