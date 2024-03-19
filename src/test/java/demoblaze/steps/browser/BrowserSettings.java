package demoblaze.steps.browser;

public class BrowserSettings {
    public static String getGridUrl(){
       var envGridUrl = System.getenv("GRID_URL");
       return envGridUrl  == null
                ? "http://192.168.1.6:4444/"
                : envGridUrl;
    }
}
