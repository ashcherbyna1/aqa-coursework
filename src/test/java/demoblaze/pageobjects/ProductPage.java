package demoblaze.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;

public class ProductPage extends BasePage{
    public ProductPage(WebDriver driver) {
        super(driver);
    }
    private static final String BUY_OPTION = "//*[@id=\'tbodyid\']/div[2]/div/a";

    private WebElement buyOptionElement;
    public void selectBuyOption() {
        buyOptionElement =new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofMillis(100))
                .until(ExpectedConditions.elementToBeClickable(By.xpath(BUY_OPTION)));
        buyOptionElement.click();
    }

    public String getSuccessMessage() {
        return GetAlertTextAndClose();
    }

}
