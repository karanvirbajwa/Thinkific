package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Wrappers;

public class ThinkificDashboard {
    static WebDriver DRIVER;
    public ThinkificDashboard(WebDriver driver){
        DRIVER=driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//i[contains(@class,'manage')]//parent::a")
    public WebElement manageLearningContentLink;

    @FindBy(xpath = "//a[contains(text(),'Instructors')]")
    public WebElement instructorsOption;

    @FindBy(xpath = "//span[contains(text(),'Signed in successfully.')]//parent::div")
    public WebElement signedInMessage;

    @FindBy(css = "[id='account-accordion']")
    public WebElement userAccountLink;

    @FindBy(xpath = "//a[contains(text(),'Log out')]")
    public WebElement logout;

    @FindBy(xpath = "//p[contains(text(),'Signed out successfully.')]//parent::div")
    public WebElement signedOutSucessMessage;


    /**
     * Following method is used to logout from the application
     */
    public void logout(){
        Wrappers.click(userAccountLink);
        Wrappers.click(logout);
        Wrappers.isDisplayed(signedOutSucessMessage);
        DRIVER.close();
    }

    /**
     * Following method is used to click on Instructors link under Manage section
     */
    public void navigateToInstructors(){
        Wrappers.click(manageLearningContentLink);
        Wrappers.click(instructorsOption);
    }

    /**
     * Following method is verify the sign in message
     */
    public boolean verifySigninMessage(){
       return Wrappers.isDisplayed(signedInMessage);
    }
}
