package managers;

import org.junit.Assert;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class RestAPI {

    static String response;

    /**
     * Following method is used to return that record created in UI exists in the API
     * @param value = status code value to validate
     */
    public void verifyStatusCode(int value){
        //RestAssured.baseURI = "https://karanvir-s-school.thinkific.com/api/public/v1/instructors?page=1&limit=25";
        RestAssured.baseURI = FileReaderManager.getInstance().getConfigFileReader().getAPIData("endpoint");
        response = RestAssured.given().log().all()
                .queryParams("page",FileReaderManager.getInstance().getConfigFileReader().getAPIData("page"))
                .queryParams("limit",FileReaderManager.getInstance().getConfigFileReader().getAPIData("limit"))
                .header("Content-Type", FileReaderManager.getInstance().getConfigFileReader().getAPIData("Content-Type"))
                .header("X-Auth-API-Key",FileReaderManager.getInstance().getConfigFileReader().getAPIData("X-Auth-API-Key"))
                .header("X-Auth-Subdomain",FileReaderManager.getInstance().getConfigFileReader().getAPIData("X-Auth-Subdomain"))
                .when().get(FileReaderManager.getInstance().getConfigFileReader().getAPIData("resource"))
                .then().assertThat().statusCode(value).extract().response().asString();

        System.out.println("Successfully validated API response is : " + value);
    }

    /**
     * Following method is used to return that record created in UI exists in the API
     * @param email = record verification using email from UI
     * @return = returns boolean value for assertion in stepDef
     */
    public boolean verifyInstructor(String email){
        JsonPath js = new JsonPath(response);
        int count = 0;
        for(int i=0; i<js.getInt("items.size()"); i++){
            if (js.getString("items["+i+"].email").equals(email)){
                System.out.println("Instructor created in UI Automation is in the API response");
                count ++;
                break;
            }
        }
        return count == 1;
    }

    /**
     * Following method is used to return that all instructor fields are in API
     * @param email = argument passed to verify email from UI
     * @param firstname = argument passed to verify firstname from UI
     * @param lastname = argument passed to verify lastname from UI
     * @param title = argument passed to verify title from UI
     * @return = returns boolean value for assertion in stepDef
     */
    public boolean verifyAllInstructorFields(String email, String firstname, String lastname, String title){
        JsonPath js = new JsonPath(response);
        int count = 0;
        for (int i=0; i<js.getInt("items.size()"); i++){

            if (js.getString("items["+i+"].email").equals(email))
            {
                System.out.println("Email value from UI : "+email);
                System.out.println("Email value from API : "+js.getString("items["+i+"].email"));

                System.out.println("FirstName value from UI : "+firstname);
                System.out.println("FirstName value from API : "+js.getString("items["+i+"].first_name"));
                Assert.assertEquals("First Name of newly created instructor not received in GET request", js.getString("items[" + i + "].first_name"), firstname);

                System.out.println("LastName value from UI : "+lastname);
                System.out.println("LastName value from API : "+js.getString("items["+i+"].last_name"));
                Assert.assertEquals("Last Name of newly created instructor not received in GET request", js.getString("items[" + i + "].last_name"), lastname);

                System.out.println("Title value from UI : "+title);
                System.out.println("Title value from API : "+js.getString("items["+i+"].title"));
                Assert.assertEquals("Title of newly created instructor not received in GET request", js.getString("items[" + i + "].title"), title);
                count++;
            }
        }
        return count == 1;
    }
}
