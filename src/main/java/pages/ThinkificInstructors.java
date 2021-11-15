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
    public WebElement newInstructorButton;

    @FindBy(css = "#instructor_first_name")
    public WebElement instructorFirstName;

    @FindBy(css = "#instructor_last_name")
    public WebElement instructorlastName;

    @FindBy(css = "#instructor_email")
    public WebElement instructorEmail;

    @FindBy(css = "#instructor_title")
    public WebElement instructorTitle;

    @FindBy(xpath = "//main //div // h1[contains(text(),'Instructors')]")
    public WebElement instructorPageTitle;

    @FindBy(css = "[class='title-bar__actions'] button")
    public WebElement saveButton;

    @FindBy(xpath = "//span[contains(text(),'Successfully created the instructor.')]//parent::div")
    public WebElement successfullyCreatedMessage;

    @FindBy(xpath = "//h6//a[contains(text(),'Instructors')]")
    public WebElement instructorsBackLink;

    @FindBy(xpath = "//label[text()='Search by']//following-sibling::input")
    public WebElement searchTextbox;

    @FindBy(xpath = "//label[contains(text(),'order results')]//following-sibling::select")
    public WebElement orderResultsByDropdown;

    @FindBy(css = "[id='btn-search']")
    public WebElement searchButton;

    @FindBy(xpath = "//td[contains(text(),'You do not currently have any instructors')]")
    public WebElement noInstructorMessage;

    /**
     * Following method is used to click Instructors Back Link
     */
    public void clickInstructorsBackLink(){
        Wrappers.click(instructorsBackLink);
    }

    /**
     * Following method is verify the Instructor Title
     */
    public boolean verifyInstructorTitle(){
        return Wrappers.isDisplayed(instructorPageTitle);
    }

    /**
     * Following method is used to click on new Instructor button
     */
    public void clickNewInstructorButton(){
        Wrappers.click(newInstructorButton);
    }

    /**
     * Following method is used to enter Instructor firstName
     */
    public void enterInstructorFirstName(String value){
        Wrappers.sendKeys(instructorFirstName,value);
    }

    /**
     * Following method is used to enter Instructor lastName
     */
    public void enterInstructorLastName(String value){
        Wrappers.sendKeys(instructorlastName,value);
    }

    /**
     * Following method is used to enter Instructor Email
     */
    public void enterInstructorEmail(String value){
        Wrappers.sendKeys(instructorEmail,value);
    }

    /**
     * Following method is used to enter Instructor Title
     */
    public void enterInstructorTitle(String value){
        Wrappers.sendKeys(instructorTitle,value);
    }

    /**
     * Following method is used to click Save Button
     */
    public void clickSaveButton(){
        Wrappers.click(saveButton);
    }

    /**
     * Following method is verify the Successfully Created the instructor Message
     */
    public boolean verifySuccessfullyCreatedMessage(){
        return Wrappers.isDisplayed(successfullyCreatedMessage);
    }

    /**
     * Following method is used to verify No Instructor message
     * @return
     */
    public boolean verifyNoInstructorMessage(){
        return Wrappers.isDisplayed(noInstructorMessage);
    }

    /**
     * Following method is used to select Dropdown type and Search for element
     */
    public void filterRecordBySearchTextbox(String value,String Firstname){
        Wrappers.selectByVisibleText(orderResultsByDropdown,value);
        Wrappers.sendKeys(searchTextbox,Firstname);
        Wrappers.click(searchButton);
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
