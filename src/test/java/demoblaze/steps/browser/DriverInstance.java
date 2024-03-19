package demoblaze.steps.browser;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class DriverInstance {
    private final WebDriver driver;

    public static final String BASE_URL = "https://www.demoblaze.com/";

    private static DriverInstance instance = null;

    private DriverInstance() throws MalformedURLException {
        var gridUrl = BrowserSettings.getGridUrl();
        var options = new ChromeOptions();
        driver = new RemoteWebDriver(new URL(gridUrl), options);
        driver.get(BASE_URL);
        driver.manage().window().maximize();
    }

    public static WebDriver getDriver() throws MalformedURLException {
        if (instance == null){
            instance = new DriverInstance();
        }
        instance.driver.get(BASE_URL);
        return instance.driver;
    }

}
