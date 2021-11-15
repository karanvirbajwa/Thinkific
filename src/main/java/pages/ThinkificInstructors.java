package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import utilities.Wrappers;

import java.util.List;

public class ThinkificInstructors {
    static WebDriver DRIVER;
    public ThinkificInstructors(WebDriver driver){
        DRIVER=driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//a[contains(text(),'NEW INSTRUCTOR')]")
    public WebElement NewInstructorButton;

    @FindBy(css = "#instructor_first_name")
    public WebElement InstructorFirstName;

    @FindBy(css = "#instructor_last_name")
    public WebElement InstructorlastName;

    @FindBy(css = "#instructor_email")
    public WebElement InstructorEmail;

    @FindBy(css = "#instructor_title")
    public WebElement InstructorTitle;

    @FindBy(xpath = "//main //div // h1[contains(text(),'Instructors')]")
    public WebElement InstructorPageTitle;

    @FindBy(css = "[class='title-bar__actions'] button")
    public WebElement SaveButton;

    @FindBy(xpath = "//span[contains(text(),'Successfully created the instructor.')]//parent::div")
    public WebElement SuccessfullyCreatedMessage;

    @FindBy(xpath = "//h6//a[contains(text(),'Instructors')]")
    public WebElement InstructorsBackLink;

    @FindBy(xpath = "//label[text()='Search by']//following-sibling::input")
    public WebElement SearchTextbox;

    @FindBy(xpath = "//label[contains(text(),'order results')]//following-sibling::select")
    public WebElement orderResultsByDropdown;

    @FindBy(css = "[id='btn-search']")
    public WebElement SearchButton;

    @FindBy(xpath = "//td[contains(text(),'You do not currently have any instructors')]")
    public WebElement NoInstructorMessage;

    /**
     * Following method is used to click Instructors Back Link
     */
    public void clickInstructorsBackLink(){
        Wrappers.click(InstructorsBackLink);
    }

    /**
     * Following method is verify the Instructor Title
     */
    public boolean verifyInstructorTitle(){
        return Wrappers.isDisplayed(InstructorPageTitle);
    }

    /**
     * Following method is used to click on new Instructor button
     */
    public void clickNewInstructorButton(){
        Wrappers.click(NewInstructorButton);
    }

    /**
     * Following method is used to enter Instructor firstName
     */
    public void enterInstructorFirstName(String value){
        Wrappers.sendKeys(InstructorFirstName,value);
    }

    /**
     * Following method is used to enter Instructor lastName
     */
    public void enterInstructorLastName(String value){
        Wrappers.sendKeys(InstructorlastName,value);
    }

    /**
     * Following method is used to enter Instructor Email
     */
    public void enterInstructorEmail(String value){
        Wrappers.sendKeys(InstructorEmail,value);
    }

    /**
     * Following method is used to enter Instructor Title
     */
    public void enterInstructorTitle(String value){
        Wrappers.sendKeys(InstructorTitle,value);
    }

    /**
     * Following method is used to click Save Button
     */
    public void clickSaveButton(){
        Wrappers.click(SaveButton);
    }

    /**
     * Following method is verify the Successfully Created the instructor Message
     */
    public boolean verifySuccessfullyCreatedMessage(){
        return Wrappers.isDisplayed(SuccessfullyCreatedMessage);
    }

    /**
     * Following method is used to verify No Instructor message
     * @return
     */
    public boolean verifyNoInstructorMessage(){
        return Wrappers.isDisplayed(NoInstructorMessage);
    }

    /**
     * Following method is used to select Dropdown type and Search for element
     */
    public void filterRecordBySearchTextbox(String value,String Firstname){
        Wrappers.selectByVisibleText(orderResultsByDropdown,value);
        Wrappers.sendKeys(SearchTextbox,Firstname);
        Wrappers.click(SearchButton);
    }

    /**
     * Following method is used to verify new record is added in Instructors list
     */
    public boolean verifyRecordinTable(String email){
        int count = 0;
        List<WebElement> table = DRIVER.findElements(By.xpath("//tbody //tr"));

        for (WebElement rows : table){
            List<WebElement> record = rows.findElements(By.tagName("td"));
            for (WebElement element : record){
                if (element.getText().equals(email)){
                    System.out.println("Newly created Instructor is added to the list of Instructors");
                    count ++;
                    break;
                }
            }
        }
        return count ==1;
    }
}
