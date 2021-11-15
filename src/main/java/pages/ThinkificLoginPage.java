package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Wrappers;

public class ThinkificLoginPage {
    static WebDriver DRIVER;
    public ThinkificLoginPage(WebDriver driver){
        DRIVER=driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(css = "#email")
    public WebElement EmailTextBox;

    @FindBy(css = "#password")
    public WebElement PasswordTextBox;

    @FindBy(css = "button[type='submit']")
    public WebElement SignInButton;

    /**
     * Following method is used to enter email
     */
    public void enterEmail(String value){
        Wrappers.sendKeys(EmailTextBox,value);
    }

    /**
     * Following method is used to enter password
     */
    public void enterPassword(String value){
        Wrappers.sendKeys(PasswordTextBox,value);
    }

    /**
     * Following method is used to click SignIn button
     */
    public void clickSignInButton(){
        Wrappers.click(SignInButton);
    }

}
