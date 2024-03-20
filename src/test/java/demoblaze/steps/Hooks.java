package demoblaze.steps;

import demoblaze.steps.browser.DriverInstance;
import io.cucumber.java.After;
import io.cucumber.java.Before;

import java.net.MalformedURLException;

public class Hooks {

    @Before(order = 0)
    public void startUp() throws MalformedURLException {
        DriverInstance.initializeDriver();
    }

    @After(order = 0)
    public void tearDown(){
        DriverInstance.Close();
    }
}
