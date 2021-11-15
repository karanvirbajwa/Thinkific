package stepDefs;

import dataProviders.PasswordEncryption;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import managers.FileReaderManager;
import org.junit.Assert;
import pages.ThinkificDashboard;
import pages.ThinkificInstructors;
import pages.ThinkificLoginPage;
import utilities.CommonUtils;
import utilities.Wrappers;

public class ThinkificStepDefs extends CommonStepDefs {
    static String TITLE = "Senior Instructor";
    static String FIRSTNAME;
    static String LASTNAME;
    static String EMAIL;
    Wrappers wrappers;
    CommonUtils commonUtils;
    ThinkificLoginPage loginPage;
    ThinkificDashboard dashboard;
    ThinkificInstructors instructors;

    public ThinkificStepDefs(){
        wrappers = new Wrappers(getWebDriver());
        commonUtils = new CommonUtils(getWebDriver());
        loginPage = new ThinkificLoginPage(getWebDriver());
        dashboard = new ThinkificDashboard(getWebDriver());
        instructors = new ThinkificInstructors(getWebDriver());
    }

    @Given("^User logged into the application$")
    public void thinkificLogin(){
        navigateToUrl(FileReaderManager.getInstance().getConfigFileReader().getUIdata("url"));
        loginPage.enterEmail(FileReaderManager.getInstance().getConfigFileReader().getUIdata("username"));
        loginPage.enterPassword(PasswordEncryption.Decode(FileReaderManager.getInstance().getConfigFileReader().getUIdata("password")));
        loginPage.clickSignInButton();
        Assert.assertTrue("User not successfully Signed in",dashboard.verifySigninMessage());
    }


    @When("^Navigate to 'Instructors' section and create new instructor$")
    public void goToInstructors(){
        dashboard.navigateToInstructors();
        Assert.assertTrue("Instructor page not successfully loaded",instructors.verifyInstructorTitle());

        instructors.clickNewInstructorButton();

        FIRSTNAME = CommonUtils.RandomString().toLowerCase();
        instructors.enterInstructorFirstName(FIRSTNAME);

        LASTNAME = CommonUtils.RandomString().toLowerCase();
        instructors.enterInstructorLastName(LASTNAME);

        EMAIL = FIRSTNAME+"_"+LASTNAME+"@"+CommonUtils.RandomString().toLowerCase()+".com";
        instructors.enterInstructorEmail(EMAIL);

        instructors.enterInstructorTitle(TITLE);
        instructors.clickSaveButton();
    }

    @Then("^Verify Successfully Created Message on the left bottom side$")
    public void verifyMessage(){
        Assert.assertTrue("Instructor page not successfully loaded",instructors.verifySuccessfullyCreatedMessage());
        instructors.clickInstructorsBackLink();

        //verify record which is not in the Table
        instructors.filterRecordBySearchTextbox("First Name",CommonUtils.RandomString().toLowerCase());
        Assert.assertTrue("No Instructor message not displayed",instructors.verifyNoInstructorMessage());

        instructors.filterRecordBySearchTextbox("First Name",FIRSTNAME);
        Assert.assertTrue("Newly created Instructor not added to the list of Instructors",instructors.verifyRecordinTable(EMAIL));
        dashboard.logout();
    }

    public static String[] getData(){
        return new String[]{EMAIL,FIRSTNAME,LASTNAME,TITLE};
    }
}
