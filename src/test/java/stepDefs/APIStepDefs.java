package stepDefs;

import managers.RestAPI;
import org.junit.Assert;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.Given;

public class APIStepDefs {
    RestAPI api;
    static String TITLE ;
    static String FIRSTNAME;
    static String LASTNAME;
    static String EMAIL;
    static String[] arr;

    public APIStepDefs(){
        api = new RestAPI();
    }

    @Given("^User successfully hit the API and response code is \"([^\"]*)\"$")
    public void compareapii(int value){
        api.verifyStatusCode(value);
    }

    @Then("^Verify Instructor created in UI task is in response$")
    public void verifyInstructor(){
        arr = HookStepDefs.getExcelData();
        EMAIL = arr[0]; FIRSTNAME = arr[1]; LASTNAME = arr[2]; TITLE = arr[3];
        Assert.assertTrue("Instructor you created in your UI Automation is not in the response",api.verifyInstructor(EMAIL));
    }

    @Then("^Verify response of API contains data of Instructor created in UI task$")
    public void verifyInstructorData(){
        //we can also pass array in verifyAllInstructorFields method
        Assert.assertTrue("First Name, Last Name, Email and Title of the Instructor not received via GET request.",api.verifyAllInstructorFields(EMAIL,FIRSTNAME,LASTNAME,TITLE));
    }
}
