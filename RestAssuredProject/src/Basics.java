import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import files.Payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class Basics {

	public static void main(String[] args) {
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		String request = Payload.getData();
		String response = given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json").
		body(request).
		when().post("/maps/api/place/add/json").
		then().log().all().header("Server","Apache/2.4.52 (Ubuntu)").body("scope",equalTo("APP")).assertThat().statusCode(200).extract().response().asString();
			
		JsonPath js = new JsonPath(response);
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
		then().log().all().assertThat().statusCode(200).body("msg", equalTo("Address successfully updated")).extract().response().asString();
		
		
		//Retrieving Updated Address using Get HTTP Request
		String getResponse = given().log().all().queryParam("key", "qaclick123").queryParam("place_id", placeId).
		when().get("/maps/api/place/get/json").
		then().log().all().statusCode(200).body("address", equalTo(AddressToBeUpdated)).extract().response().asString();
		JsonPath js1 = new JsonPath(getResponse);
		String updatedAddress = js1.getString("address");
		System.out.println("Updated Address for Get: " + updatedAddress);

		

	}

}
