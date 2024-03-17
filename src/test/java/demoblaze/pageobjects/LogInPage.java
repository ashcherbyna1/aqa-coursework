package demoblaze.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LogInPage extends BasePage {
    public LogInPage(WebDriver driver) {
        super(driver);
    }

    private static final String LOGIN_USERNAME = "//*[@id=\'loginusername\']";
    private static final String LOGIN_PASSWORD = "//*[@id=\'loginpassword\']";
    private static final String LOG_IN_OPTION = "//*[@id=\'logInModal\']/div/div/div[3]/button[2]";

    private WebElement loginusernameElement;
    private WebElement loginpasswordElement;
    private WebElement loginElement;

    public void setLogInData(String username, String password) {
        loginusernameElement = driver.findElement(By.xpath(LOGIN_USERNAME));
        loginusernameElement.sendKeys(username);
        loginpasswordElement = driver.findElement(By.xpath(LOGIN_PASSWORD));
        loginpasswordElement.sendKeys(password);
    }

    public void clickLogInButton() {
        loginElement = driver.findElement(By.xpath(LOG_IN_OPTION));
        loginElement.click();
    }
}
