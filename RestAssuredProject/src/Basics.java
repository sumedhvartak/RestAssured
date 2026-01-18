import static io.restassured.RestAssured.given;

import files.Payload;
import io.restassured.RestAssured;

public class Basics {

	public static void main(String[] args) {
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json").
		body(Payload.getData()).
		when().post("/maps/api/place/add/json").
		then().assertThat().statusCode(200);

	}

}
