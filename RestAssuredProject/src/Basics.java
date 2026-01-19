import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.Assert;
import files.Payload;
import files.ReusableJsonMethod;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class Basics {

	public static void main(String[] args) throws IOException {
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		String request = Payload.getData();
		String postResponse = given().queryParam("key", "qaclick123").header("Content-Type", "application/json").
		//body(request).
		body(new String(Files.readAllBytes(Paths.get("D://NewKnowledge//RestAssured//RestAssuredWorkspace//RestAssuredProject//src//files//Data.json")))).
		when().post("/maps/api/place/add/json").
		then().log().all().header("Server","Apache/2.4.52 (Ubuntu)").body("scope",equalTo("APP")).assertThat().statusCode(200).extract().response().asString();
			
		JsonPath js = ReusableJsonMethod.rawToJson(postResponse);
		String placeId = js.getString("place_id");
		
		//Updated Address using Put HTTP Request
		String AddressToBeUpdated = "800, Sahara Desert";
		given().queryParam("key", "qaclick123").header("Content-Type","application/json").
		body("{\r\n"
				+ "\"place_id\":\"" + placeId + "\",\r\n"
				+ "\"address\":\"" + AddressToBeUpdated +"\",\r\n"
				+ "\"key\":\"qaclick123\"\r\n"
				+ "}\r\n"
				+ "").
		when().put("/maps/api/place/update/json").
		then().log().all().statusCode(200).body("msg", equalTo("Address successfully updated")).extract().response().asString();
		
		//Retrieving Updated Address using Get HTTP Request
		String getResponse = given().queryParam("key", "qaclick123").queryParam("place_id", placeId).
		when().get("/maps/api/place/get/json").
		then().log().all().statusCode(200).body("address", equalTo(AddressToBeUpdated)).extract().response().asString();
		JsonPath js1 = ReusableJsonMethod.rawToJson(getResponse);
		String updatedAddress = js1.getString("address");
		Assert.assertEquals(AddressToBeUpdated, updatedAddress);

	}//End of main Method

}//End of Basics Class
