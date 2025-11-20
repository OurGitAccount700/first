package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

public class StepDef {

    RequestSpecification req;
    Response response;

    @Given("add addplace api payload")
    public void add_addplace_api_payload() {
        RestAssured.baseURI = "https://rahulshettyacademy.com"; 
        

        
        String payload = "{\n" +
                "  \"name\": \"Test Place\",\n" +
                "  \"location\": {\n" +
                "    \"lat\": -38.383494,\n" +
                "    \"lng\": 33.427362\n" +
                "  },\n" +
                "  \"accuracy\": 50,\n" +
                "  \"phone_number\": \"(+91) 983 893 3937\",\n" +
                "  \"address\": \"29, side layout, cohen 09\",\n" +
                "  \"types\": [\"shoe park\",\"shop\"],\n" +
                "  \"website\": \"http://google.com\",\n" +
                "  \"language\": \"French-IN\"\n" +
                "}";

        req = given().log().all()
                .header("Content-Type", "application/json")
                .body(payload);
    }

    @When("User calls {string} api with post call")
    public void user_calls_api_with_post_call(String resource) {

        // Build API path from resource name
        String endpoint = "";
        if (resource.equalsIgnoreCase("addplace")) {
            endpoint = "/maps/api/place/add/json";   // <-- change if needed
        }

        response = req.when().post(endpoint)
                .then().log().all().extract().response();
    }

    @Then("Success reposnse should retrieved with {int} statuc code")
    public void success_response_should_retrieved_with_status_code(int expectedStatusCode) {
        assertEquals(expectedStatusCode, response.getStatusCode());
    }

    @Then("Verify {string} field value is {string}")
    public void verify_field_value_is(String key, String expectedValue) {
        String actualValue = response.jsonPath().getString(key);
        assertEquals(expectedValue, actualValue);
    }
}
