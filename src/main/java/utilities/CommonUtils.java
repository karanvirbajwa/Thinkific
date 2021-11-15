package utilities;

import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;
import java.util.Random;

public class CommonUtils {
    static WebDriver DRIVER;
    public CommonUtils(WebDriver driver){
        DRIVER = driver;
        PageFactory.initElements(driver,this);
    }

    public static void waitTillElementDisplayed(final WebElement element){
        SuiteLogger.getGlobal().info("waiting for the element " + element);
        try {
            Thread.sleep(700);
        }catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        FluentWait<WebDriver> wait = new FluentWait<>(SingeltonWebDriver.getInstance());
        wait.withTimeout(Duration.ofSeconds(10)).pollingEvery(Duration.ofSeconds(10));
        wait.withTimeout(Duration.ofSeconds(20));
        wait.ignoring(StaleElementReferenceException.class);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public static String RandomString() {
        String ranString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder builder = new StringBuilder();
        Random rnd = new Random();
        while (builder.length() < 5) { // length of the random string.
            int index = (int) (rnd.nextFloat() * ranString.length());
            builder.append(ranString.charAt(index));
        }
        String value = builder.toString();
        return value;
    }
}
