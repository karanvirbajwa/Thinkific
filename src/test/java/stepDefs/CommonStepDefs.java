package stepDefs;

import managers.FileReaderManager;
import org.openqa.selenium.WebDriver;
import utilities.SingeltonWebDriver;
import utilities.SuiteLogger;

import java.util.concurrent.TimeUnit;

public class CommonStepDefs {

    protected WebDriver getWebDriver(){
        String browserName = System.getProperty("browser");
        if (browserName==null){
            SingeltonWebDriver.setBrowserName(FileReaderManager.getInstance().getConfigFileReader().getBrowserName().toString());
        }
        else {
            SingeltonWebDriver.setBrowserName(browserName);
        }
        return SingeltonWebDriver.getInstance();
    }

    public void navigateToUrl(String url){
        getWebDriver().manage().deleteAllCookies();
        getWebDriver().manage().window().maximize();
        getWebDriver().manage().timeouts().implicitlyWait(FileReaderManager.getInstance().getConfigFileReader().getImplicitlyWait(), TimeUnit.SECONDS);
        getWebDriver().get(url);
        SuiteLogger.getGlobal().info("url hit");
    }
}
