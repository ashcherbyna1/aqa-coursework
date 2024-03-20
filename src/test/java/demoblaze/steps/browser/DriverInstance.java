package demoblaze.steps.browser;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class DriverInstance {
    private WebDriver driver;

    private static final String BASE_URL = "https://www.demoblaze.com/";

    private static DriverInstance instance = null;

    private DriverInstance() throws MalformedURLException {
        var gridUrl = getGridUrl();
        var options = new ChromeOptions();
        driver = new RemoteWebDriver(new URL(gridUrl), options);
        driver.get(BASE_URL);
        driver.manage().window().maximize();
    }

    public static void initializeDriver() throws MalformedURLException {
        instance = new DriverInstance();
    }

    public static WebDriver getDriver() throws MalformedURLException {
        if(instance == null){
            initializeDriver();
        }
        return instance.driver;
    }

    public static void Close() {
        instance.driver.quit();
        instance.driver = null;
    }

    private static String getGridUrl(){
        var envGridUrl = System.getenv("GRID_URL");
        if(envGridUrl == null){
            return "http://192.168.1.6:4444/";
        }
        else {
            return envGridUrl;
        }
    }
}
