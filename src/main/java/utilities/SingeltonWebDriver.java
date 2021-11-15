package utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class SingeltonWebDriver {
    private static SingeltonWebDriver singeltonWebDriver = null;
    private static WebDriver WEB_DRIVER;
    private static String BROWSER_NAME;
    private static final String CHROME = "chrome";
    private static final String FIREFOX = "firefox";
    private static final String IE = "IE";
    private static final String EDGE = "edge";

    public static WebDriver getInstance(){
        if (singeltonWebDriver == null){
            singeltonWebDriver = new SingeltonWebDriver();
        }
        return WEB_DRIVER;
    }

    private SingeltonWebDriver(){
        if (WEB_DRIVER == null){
            if (BROWSER_NAME.equalsIgnoreCase(CHROME)){
                WebDriverManager.chromedriver().setup();
                WEB_DRIVER = new ChromeDriver();
            }else if (BROWSER_NAME.equalsIgnoreCase(FIREFOX)) {
                WebDriverManager.firefoxdriver().setup();
                WEB_DRIVER = new FirefoxDriver();
            }else if (BROWSER_NAME.equalsIgnoreCase(IE)) {
                WebDriverManager.iedriver().setup();
                WEB_DRIVER = new InternetExplorerDriver();
            }else if (BROWSER_NAME.equalsIgnoreCase(EDGE)) {
                WebDriverManager.edgedriver().setup();
                WEB_DRIVER = new EdgeDriver();
            }
        }else {
            SuiteLogger.getGlobal().warning("No Matching Browser found");
        }
    }

    public static void setBrowserName(String value){BROWSER_NAME=value;}
}
