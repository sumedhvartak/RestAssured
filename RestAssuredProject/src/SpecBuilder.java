import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.*;

public class SpecBuilder {
	
	@Test
	public void specTestCase_TraditionalApproach() {
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		
		String response = given().queryParam("key", "qaclick123").header("Content-Type", "application/json").
		body("{\r\n"
				+ "  \"location\": {\r\n"
				+ "    \"lat\": -38.383494,\r\n"
				+ "    \"lng\": 33.427362\r\n"
				+ "  },\r\n"
				+ "  \"accuracy\": 50,\r\n"
				+ "  \"name\": \"Frontline house\",\r\n"
				+ "  \"phone_number\": \"(+91) 983 893 3937\",\r\n"
				+ "  \"address\": \"29, side layout, cohen 09\",\r\n"
				+ "  \"types\": [\r\n"
				+ "    \"shoe park\",\r\n"
				+ "    \"shop\"\r\n"
				+ "  ],\r\n"
				+ "  \"website\": \"http://google.com\",\r\n"
				+ "  \"language\": \"French-IN\"\r\n"
				+ "}\r\n"
				+ "").
		when().post("maps/api/place/add/json").
		then().assertThat().statusCode(200).extract().response().asString();
		System.out.println("Response: " + response);
		
	}//End of specTestCases
	
	@Test
	public void specTestCaseActual() {
		
		RequestSpecification specBuild = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").
				addQueryParam("key", "qaclick123").
				setContentType(ContentType.JSON).build();
		
		ResponseSpecification resBuild = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		
		RequestSpecification reqBuild = given().spec(specBuild).
		body("{\r\n"
				+ "  \"location\": {\r\n"
				+ "    \"lat\": -38.383494,\r\n"
				+ "    \"lng\": 33.427362\r\n"
				+ "  },\r\n"
				+ "  \"accuracy\": 50,\r\n"
				+ "  \"name\": \"Frontline house\",\r\n"
				+ "  \"phone_number\": \"(+91) 983 893 3937\",\r\n"
				+ "  \"address\": \"29, side layout, cohen 09\",\r\n"
				+ "  \"types\": [\r\n"
				+ "    \"shoe park\",\r\n"
				+ "    \"shop\"\r\n"
				+ "  ],\r\n"
				+ "  \"website\": \"http://google.com\",\r\n"
				+ "  \"language\": \"French-IN\"\r\n"
				+ "}\r\n"
				+ "");
		Response resp = reqBuild.
		when().post("maps/api/place/add/json").
		then().spec(resBuild).extract().response();
		
		String payload = resp.asString();
		System.out.println("Response: " + payload);
		
	}//End of specTestCaseActual

}//End of Spec Builder
