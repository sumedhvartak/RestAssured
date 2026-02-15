package stepDefinations;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import resourcess.TestDataBuild;
import resourcess.Utils;


public class StepDefination extends Utils {
	RequestSpecification reqSpec;
	Response resp;
	TestDataBuild tbd = new resourcess.TestDataBuild();
	
	@Given("Add Place Payload")
	public void add_place_payload() {
		reqSpec = given().spec(requestSpecification()).body(tbd.addPlacePayload());	
	}
	
	@When("User call AddPlaceAPI with post http Request")
	public void user_call_add_place_api_with_post_http_request() {
		resp = reqSpec.when().post("maps/api/place/add/json").
				then().spec(responseSpecification()).assertThat().statusCode(200).extract().response();
	}
	
	@Then("API call get success with status code {int}")
	public void api_call_get_success_with_status_code(Integer int1) {
		assertEquals(resp.getStatusCode(),200);
	}
	
	@Then("status in response body is {string}")
	public void status_in_response_body_is(String ExpectedValue) {
		JsonPath js = new JsonPath(resp.asString());
		assertEquals(js.getString("status"), ExpectedValue);
	}

}//End of StepDefination class
