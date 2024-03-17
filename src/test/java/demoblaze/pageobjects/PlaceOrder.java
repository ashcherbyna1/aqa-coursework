package demoblaze.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;

public class PlaceOrder extends BasePage {
    public PlaceOrder(WebDriver driver) {
        super(driver);
    }

    private static final String NAME = "//*[@id=\"name\"]";
    private static final String BANK_DETAILS = "//*[@id=\"card\"]";
    private static final String PURCHASE_OPTION = "//*[@id=\"orderModal\"]/div/div/div[3]/button[2]";
    private static final String PURCHASE_TEXT = "/html/body/div[10]/h2";

    private WebElement nameElement;
    private WebElement creditcardElement;
    private WebElement textElement;
    private WebElement purchaseoptionElement;

    public void setPurchaseData(String name, String carddata) {
        nameElement = driver.findElement(By.xpath(NAME));
        nameElement.sendKeys(name);
        creditcardElement = driver.findElement(By.xpath(BANK_DETAILS));
        creditcardElement.sendKeys(carddata);
    }

    public void selectToBuyOption() {
        purchaseoptionElement = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofMillis(100))
                .until(ExpectedConditions.elementToBeClickable(By.xpath(PURCHASE_OPTION)));
        purchaseoptionElement.click();
    }

    public String getPurchaseMessage() {
        new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofMillis(100))
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(PURCHASE_TEXT)));
        textElement = driver.findElement(By.xpath(PURCHASE_TEXT));
        return textElement.getText();
    }

}
