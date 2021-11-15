package utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class Wrappers {
    static WebDriver DRIVER;
    public Wrappers(WebDriver driver){
        DRIVER = driver;
        PageFactory.initElements(driver,this);
    }

    public static void click(WebElement element){
        CommonUtils.waitTillElementDisplayed(element);
        highLightElement(DRIVER,element);
        element.click();
    }

    public static void sendKeys(WebElement element,String value){
        CommonUtils.waitTillElementDisplayed(element);
        highLightElement(DRIVER,element);
        element.clear();
        element.sendKeys(value);
    }

    public static void selectByVisibleText(WebElement element,String value){
        CommonUtils.waitTillElementDisplayed(element);
        highLightElement(DRIVER,element);
        Select dropdown = new Select(element);
        dropdown.selectByVisibleText(value);
    }

    public static boolean isDisplayed(WebElement element){
        CommonUtils.waitTillElementDisplayed(element);
        highLightElement(DRIVER,element);
        return element.isDisplayed();
    }

    public static void highLightElement(WebDriver driver, WebElement element){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].style.border='2px solid red'", element);
        try {
            Thread.sleep(500);
        }catch (InterruptedException e){
            System.out.println(e.getMessage());
        }
        js.executeScript("arguments[0].style.border='2px solid white'", element);
    }

    public static List<WebElement> findelements_xpath(String element){
        return DRIVER.findElements(By.xpath(element));
    }

    public static List<WebElement> findelements_css(String element){
        return DRIVER.findElements(By.cssSelector(element));
    }
}
