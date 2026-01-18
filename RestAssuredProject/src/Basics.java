import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import files.Payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class Basics {

	public static void main(String[] args) {
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		String request = Payload.getData();
		System.out.println("Request: "+ request);
		String response = given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json").
		body(request).
		when().post("/maps/api/place/add/json").
//		then().log().all().header("Server","Apache/2.4.52 (Ubuntu)").body("scope",equalTo("APP")).assertThat().statusCode(200).extract().response().asString();
		then().extract().response().asString();
		System.out.println(response);
		
		JsonPath js = new JsonPath(response);
		String placeId = js.getString("place_id");
		System.out.println(placeId);

	}

}
