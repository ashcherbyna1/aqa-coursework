package demoblaze.pageobjects.demoblaze.pageobjects;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

public class SignUpPage extends BasePage {
    public SignUpPage(WebDriver driver) {
        super(driver);
    }

    private static final String USERNAME = "//*[@id=\'sign-username\']";
    private static final String PASSWORD = "//*[@id=\'sign-password\']";
    private static final String REGISTRATION = "//*[@id=\'signInModal\']/div/div/div[3]/button[2]";
    private static final String CLOSE_OPTION = "//*[@id='signInModal']/div/div/div[3]/button[1]";
    private WebElement usernameElement;
    private WebElement passwordElement;
    private WebElement registrationElement;
    private WebElement closeOptionElement;

    public void setUsernameAndPassword(String username, String password) {
        usernameElement = driver.findElement(By.xpath(USERNAME));
        usernameElement.sendKeys(username);
        passwordElement = driver.findElement(By.xpath(PASSWORD));
        passwordElement.sendKeys(password);
    }

    public void selectingRegistrationOption() {
        registrationElement = driver.findElement(By.xpath(REGISTRATION));
        registrationElement.click();
    }

    public String getSuccessfulMassage() {
        Alert alert = new FluentWait<>(driver).until(ExpectedConditions.alertIsPresent());
        String text = alert.getText();
        alert.accept();
        return text;
    }

    public void closingModalWindow() {
        closeOptionElement = driver.findElement(By.xpath(CLOSE_OPTION));
        closeOptionElement.click();
    }

}
