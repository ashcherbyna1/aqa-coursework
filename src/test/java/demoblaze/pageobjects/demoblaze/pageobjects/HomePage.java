package demoblaze.pageobjects.demoblaze.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;
import java.util.Objects;

public class HomePage extends BasePage {
    public HomePage(WebDriver driver) {
        super(driver);
    }

    private static final String SIGN_UP = "//*[@id='signin2']";
    private static final String LOG_IN = "//*[@id='login2']";
    private static final String LOG_OUT = "//*[@id='logout2']";
    private static final String WELCOME_TEXT = "//*[@id='nameofuser']";

    private WebElement sign_upElement;
    private WebElement log_inElement;
    private WebElement log_outElement;
    private WebElement welcometextElement;

    public void selectSignUp() {
        sign_upElement = driver.findElement(By.xpath(SIGN_UP));
        sign_upElement.click();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(100));
    }
    public void selectLogIn() {
        log_inElement = driver.findElement(By.xpath(LOG_IN));
        log_inElement.click();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(100));
    }
    public void selectLogOut() {
        log_outElement = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofMillis(100))
                .until(ExpectedConditions.elementToBeClickable(By.xpath(LOG_OUT)));
        log_outElement.click();
    }
    public String getWelcomeMessage() {
        new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofMillis(100))
                .until(d -> !Objects.equals(d.findElement(By.xpath(WELCOME_TEXT)).getText(), ""));
        welcometextElement = driver.findElement(By.xpath(WELCOME_TEXT));
        return welcometextElement.getText();
    }
    public String getLogInMessage() {
        new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofMillis(100))
                .until(d -> !Objects.equals(d.findElement(By.xpath(LOG_IN)).getText(), ""));
        log_inElement = driver.findElement(By.xpath(LOG_IN));
        return log_inElement.getText();
    }
}

